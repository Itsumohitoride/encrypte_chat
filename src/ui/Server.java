package ui;

import Interface.ConnectionInteraction;
import model.Chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server implements ConnectionInteraction {
    private ServerSocket serverSocket;
    private Socket socket;
    private Chat chat;

    public static void main(String[] args) {

        Server server = new Server();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter ip: ");
        String ip = sc.nextLine();
        System.out.println("Enter port: ");
        String port = sc.nextLine();

        server.connectServers(ip, Integer.parseInt(port));
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
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            chat = new Chat(socket, ip);
            chat.printText("Connection established with: " + socket.getInetAddress().getHostAddress());
        } catch (IOException e) {
            System.out.println("Error connecting with client: " + e.getMessage());
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
