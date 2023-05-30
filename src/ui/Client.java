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

        System.out.println("Enter ip: ");
        String ip = sc.nextLine();
        System.out.println("Enter port: ");
        String port = sc.nextLine();

        client.connectServers(ip, Integer.parseInt(port));
        client.chat.writeData();
    }

    @Override
    public void createConnection(String ip, int port) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    connectServers(ip, port);
                    flow();
                    chat.receiveData();
                    endConnection();
                }
            }
        });
    }

    @Override
    public void connectServers(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            chat = new Chat(socket, ip);
            chat.printText("Connecting with " + socket.getInetAddress().getHostAddress());
        } catch (IOException e) {
            System.out.println("Error connecting with server: " + e.getMessage());
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
}
