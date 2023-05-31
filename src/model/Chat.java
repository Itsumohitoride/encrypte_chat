package model;

import Interface.ChatInteraction;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Chat implements ChatInteraction {
    private DataInputStream input;
    private DataOutputStream output;
    private final String name;
    private final Socket socket;

    public Chat(Socket socket, String name){
        this.socket = socket;
        this.name = name;
        flow();
    }
    @Override
    public void receiveData() {
        String message = "";
        try {
            while (!message.equalsIgnoreCase(TERMINATE_CONNECTION)){
                message = (String) input.readUTF();
                printText("\n[" + socket.getInetAddress().getHostAddress() + "]: " + message
                        + "\n[" + name + "]:" );
            }
            endConnection();
        } catch (IOException e) {
            printText("Error receiving message: " + e.getMessage() + "\n");
        }
    }

    @Override
    public void writeData() {
        String message = "";
        while (true){
            printText("[" + name + "]: ");
            message = getMessage();
            if (message.length() > 0){
                send(message);
            }
        }
    }

    @Override
    public void send(String message) {
        try {
            output.writeUTF(message);
            output.flush();
        } catch (IOException e) {
            printText("Error sending message: " + e.getMessage() + "\n");
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

    public void flow() {
        try {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            output.flush();
        } catch (IOException e) {
            printText("Error creating chat\n");
        }
    }

    public void endConnection() {
        try {
            input.close();
            output.close();
            socket.close();
            printText("Chat closed....");
            System.exit(0);

        } catch (IOException e) {
            printText("Error closing chat: " + e.getMessage() + "\n");
            System.exit(0);
        }
    }
}
