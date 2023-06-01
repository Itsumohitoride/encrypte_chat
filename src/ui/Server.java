package ui;

import Interface.ChatInteraction;
import model.DiffieHellman;
import model.Encrypt;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.PublicKey;
import java.util.Scanner;

public class Server implements ChatInteraction {
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private ServerSocket serverSocket;
    private Socket socket;
    private static DiffieHellman diffieHellman;
    private static Encrypt encrypt;
    private static String name;
    private PublicKey key;

    public static void main(String[] args) {

        diffieHellman = new DiffieHellman();
        encrypt = new Encrypt();

        Server server = new Server();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter port: ");
        String port = sc.nextLine();
        System.out.print("Enter your name: ");
        name = sc.nextLine();

        server.createConnection("", Integer.parseInt(port));
        server.writeData();
    }

    @Override
    public void createConnection(String ip, Integer port) {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    connectServers(ip, port);
                    flow();
                    exchangeKeys();
                    diffieHellman.generateSecretKey();
                    receiveData();
                } finally {
                    endConnection();
                }
            }
        });
        thread.start();
    }

    @Override
    public void connectServers(String ip, int port) {
        try {
            serverSocket = new ServerSocket(port);
            printText("Waiting for connection\n");
            socket = serverSocket.accept();
            printText("\nConnection established with: " + socket.getInetAddress().getHostAddress() + "\n");
            printText("This is a end-to-end encrypted chat \n\n");
        } catch (IOException e) {
            System.out.println("Error connecting with client: " + e.getMessage() + "\n");
            System.exit(0);
        }
    }

    public void exchangeKeys() {
        diffieHellman.generateKeys();
        sendKey(diffieHellman.getPublicKey());
        receiveKey();
    }

    @Override
    public void sendKey(PublicKey key) {
        try {
            outputStream.writeObject(diffieHellman.getPublicKey());
            outputStream.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void receiveKey() {
        try {
            diffieHellman.receivedPublicKey((PublicKey) inputStream.readObject());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void flow() {
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.flush();
        } catch (IOException e) {
            printText("Error creating chat\n");
        }
    }

    @Override
    public void receiveData() {
        String message = "";
        try {
            do {
                String  encryptedMessage = (String) inputStream.readObject();
                message = encrypt.decrypt(diffieHellman.getSecretKey(), encryptedMessage);
                printText("\n[" + socket.getInetAddress().getHostAddress() + "]: " + message
                        + "\n[" + name + "]:");
            } while (!message.equalsIgnoreCase(TERMINATE_CONNECTION));
            endConnection();
        } catch (Exception e) {
            printText("Error receiving message: " + e.getMessage() + "\n");
        }
    }

    @Override
    public void writeData() {
        String message = "";
        while(true){
            printText("[" + name + "]: ");
            message = getMessage();
            if (message.length() > 0) {
                String encryptedMessage = null;
                try {
                    encryptedMessage = encrypt.encrypt(diffieHellman.getSecretKey(), message);
                } catch (Exception e) {
                    printText("Error writing message: " + e.getMessage() + "\n");
                }
                send(encryptedMessage);
            }
        }
    }

    @Override
    public void send(String message) {
        try {
            outputStream.writeObject(message);
            outputStream.flush();
        } catch (IOException e) {
            printText("Error sending message: " + e.getMessage() + "\n");
        }
    }

    @Override
    public void endConnection() {
        try {
            inputStream.close();
            outputStream.close();
            socket.close();
            printText("Chat closed....");
            System.exit(0);

        } catch (IOException e) {
            printText("Error closing chat: " + e.getMessage() + "\n");
            System.exit(0);
        }
    }

    @Override
    public void printText(String message) {
        System.out.print(message);
    }

    @Override
    public String getMessage() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
