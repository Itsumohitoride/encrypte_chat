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
    private final String ip;
    private final Socket socket;

    public Chat(Socket socket, String ip){
        this.socket = socket;
        this.ip = ip;
        flow();
    }
    @Override
    public void receiveData() {
        String message = "";
        try {
            do {
                message = (String) input.readUTF();
                printText("[" + ip + "]: " + message);
            } while (!message.equalsIgnoreCase(TERMINATE_CONNECTION));
        } catch (IOException e) {
            printText("Error receiving message: " + e.getMessage());
            endConnection();
        }
    }

    @Override
    public void writeData() {
        String message = "";
        while (true){
            printText("[" + ip + "]: ");
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
            printText("Error sending message: " + e.getMessage());
        }
    }

    @Override
    public void printText(String message) {
        System.out.println(message);
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
            printText("Error creating chat");
        }
    }

    public void endConnection() {
        try {
            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            printText("Error closing chat: " + e.getMessage());
            System.exit(0);
        }
    }
}
