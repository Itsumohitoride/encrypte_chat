package Interface;

public interface ChatInteraction {
    final String TERMINATE_CONNECTION = "exit()";
    void receiveData();
    void writeData();
    void send(String message);
    void printText(String message);
    String getMessage();
}
