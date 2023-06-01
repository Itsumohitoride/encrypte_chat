package Interface;

import java.security.PublicKey;

public interface ChatInteraction {
    final String TERMINATE_CONNECTION = "exit()";
    void receiveData();
    void receiveKey();
    void writeData();
    void sendKey(PublicKey key);
    void send(String message);
    void printText(String message);
    String getMessage();
    void createConnection(String  ip, Integer port);
    void connectServers(String ip, int port);
    void flow();
    void endConnection();
}
