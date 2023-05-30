package ui;

import Interface.ConnectionInteraction;
import model.Chat;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client implements ConnectionInteraction {
    private Socket socket;
    private Chat chat;

    public static void main(String[] args) {

        Client client = new Client();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter ip: ");
        String ip = sc.nextLine();
        System.out.print("Enter port: ");
        String port = sc.nextLine();
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        client.connectServers(ip, Integer.parseInt(port), name);
        client.createConnection();
        client.writeData();
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
            chat.printText("Connecting with " + socket.getInetAddress().getHostAddress() + "\n");
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

    public void receiveData(){
        chat.receiveData();
    }

    public void writeData(){
        chat.writeData();
    }
}
