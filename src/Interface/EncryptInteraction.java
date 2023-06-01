package Interface;

import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

public interface EncryptInteraction {
    String encrypt(byte[] secretKey, String message) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException;
    void decrypt();
    String publicKeyToString(PublicKey key);
    PublicKey stringToPublicKey(String key) throws NoSuchAlgorithmException, InvalidKeySpecException;
}
