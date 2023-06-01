package Interface;

import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

public interface EncryptInteraction {
    void encrypt();
    void decrypt();
    String publicKeyToString(PublicKey key);
    PublicKey stringToPublicKey(String key) throws NoSuchAlgorithmException, InvalidKeySpecException;
}
