package Interface;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

public interface EncryptInteraction {
    String encrypt(byte[] secretKey, String message) throws Exception;
    String decrypt(byte[] secretKey, String message) throws Exception;
}
