package Interface;

public interface ConnectionInteraction {
    void createConnection();
    void connectServers(String ip, int port, String name);
    void flow();
    void endConnection();
    void receiveData();
    void writeData();
}
