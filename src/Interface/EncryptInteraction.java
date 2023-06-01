package Interface;

public interface EncryptInteraction {
    String encrypt(byte[] secretKey, String message) throws Exception;
    String decrypt(byte[] secretKey, String message) throws Exception;
}
