package Interface;

public interface ConnectionInteraction {
    void createConnection(String ip, int port);
    void connectServers(String ip, int port);
    void flow();
    void endConnection();
}
