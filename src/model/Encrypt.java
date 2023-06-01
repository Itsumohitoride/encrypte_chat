package model;

import Interface.EncryptInteraction;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Encrypt implements EncryptInteraction {
    private static final IvParameterSpec iv = new IvParameterSpec(hexToBytes("00000000000000000000000000000000"));

    /**
     * The function encrypts a message using AES encryption with a given secret key and returns the
     * encrypted message in Base64 format.
     *
     * @param secretKey The secret key used for encryption in byte array format.
     * @param message   The message parameter is the plaintext message that needs to be encrypted.
     * @return The method is returning a Base64 encoded string of the encrypted message.
     */
    @Override
    public String encrypt(byte[] secretKey, String message) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(secretKey, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);
        byte[] encryptedBytes = cipher.doFinal(message.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * This Java function decrypts an encrypted message using a secret key and returns the decrypted
     * message as a string.
     *
     * @param secretKey        The secret key is a byte array that is used to decrypt the encrypted message.
     *                         It should be the same key that was used to encrypt the message.
     * @param encryptedMessage The encrypted message that needs to be decrypted. It is a string
     *                         representation of the encrypted bytes.
     * @return The decrypted message as a String in UTF-8 encoding.
     */
    @Override
    public String decrypt(byte[] secretKey, String encryptedMessage) throws Exception {
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedMessage);
        SecretKeySpec keySpec = new SecretKeySpec(secretKey, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, "UTF-8");
    }


    /**
     * This function converts a hexadecimal string to a byte array.
     *
     * @param str A string representing a hexadecimal value that needs to be converted to a byte array.
     * @return The method is returning a byte array.
     */
    public static byte[] hexToBytes(String str) {
        if (str == null) {
            return null;
        } else if (str.length() < 2) {
            return null;
        } else {
            int len = str.length() / 2;
            byte[] buffer = new byte[len];
            for (int i = 0; i < len; i++) {
                buffer[i] = (byte) Integer.parseInt(
                        str.substring(i * 2, i * 2 + 2), 16);
            }
            return buffer;
        }
    }
}
