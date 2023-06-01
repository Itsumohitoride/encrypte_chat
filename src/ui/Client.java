package ui;

import Interface.ConnectionInteraction;
import model.Chat;
import model.DiffieHellman;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client implements ConnectionInteraction {

    private Socket socket;

    private static Chat chat;

    private static DiffieHellman diffieHellman;

    public static void main(String[] args) {

        diffieHellman = new DiffieHellman();

        Client client = new Client();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter server IP: ");
        String ip = sc.nextLine();
        System.out.print("Enter port: ");
        String port = sc.nextLine();
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        client.connectServers(ip, Integer.parseInt(port), name);
        client.createConnection();
        client.exchangeKeys();
        client.writeData();
    }

    public void exchangeKeys(){
        diffieHellman.generateKeys();
        chat.sendKey(diffieHellman.getPublicKey());
        chat.receiveKey();
        diffieHellman.receivedPublicKey(chat.getKey());
        diffieHellman.generateSecretKey();
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

    @Override
    public void connectServers(String ip, int port, String name) {
        try {
            socket = new Socket(ip, port);
            chat = new Chat(socket, name);
            chat.printText("Connecting with " + socket.getInetAddress().getHostName() + "\n");
            chat.printText("This is a end-to-end encrypted chat \n\n");

        } catch (IOException e) {
            System.out.println("Error connecting with server: " + e.getMessage() + "\n");
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
