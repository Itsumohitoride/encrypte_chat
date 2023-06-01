package ui;

import Interface.ConnectionInteraction;
import model.Chat;
import model.DiffieHellman;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server implements ConnectionInteraction {

    private ServerSocket serverSocket;

    private Socket socket;

    private static Chat chat;

    private static DiffieHellman diffieHellman;

    public static void main(String[] args) {

        diffieHellman = new DiffieHellman();

        Server server = new Server();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter port: ");
        String port = sc.nextLine();
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        server.connectServers("", Integer.parseInt(port), name);
        server.createConnection();
        server.exchangeKeys();
        server.writeData();
    }

    @Override
    public void createConnection() {
        Thread thread = new Thread(() -> {
            while (true){
                try {
                    flow();
                    receiveData();
                } finally {
                    endConnection();
                }
            }
        });
        thread.start();
    }

    public void exchangeKeys(){
        diffieHellman.generateKeys();
        chat.sendKey(diffieHellman.getPublicKey());
        chat.receiveKey();
        diffieHellman.receivedPublicKey(chat.getKey());
        diffieHellman.generateSecretKey();
    }

    @Override
    public void connectServers(String ip, int port, String name) {
        try {
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            chat = new Chat(socket, name);
            chat.printText("Connection established with: " + socket.getInetAddress().getHostAddress() + "\n");
            chat.printText("This is a end-to-end encrypted chat \n\n");

        } catch (IOException e) {
            System.out.println("Error connecting with client: " + e.getMessage() + "\n");
            System.exit(0);
        }
    }

    @Override
    public void flow() {
        chat.flow();
    }

    @Override
    public void endConnection() {
        chat.endConnection();
    }

    @Override
    public void receiveData(){
        chat.receiveData();
    }

    @Override
    public void writeData(){
        chat.writeData(diffieHellman.getSecretKey());
    }
}
