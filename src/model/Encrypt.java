package model;

import Interface.EncryptInteraction;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

public class Encrypt implements EncryptInteraction {
    @Override
    public String encrypt(byte[] secretKey, String message) {
        Cipher cf = null;
        byte[] theCph = new byte[0];

        try {

            SecretKeySpec keySpec = new SecretKeySpec(secretKey,"AES");
            cf = Cipher.getInstance("AES/ECB/NoPadding");
            cf.init(Cipher.ENCRYPT_MODE, keySpec);
            theCph = cf.doFinal(message.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Arrays.toString(theCph);
    }

    @Override
    public void decrypt() {

    }

    @Override
    public String publicKeyToString(PublicKey key) {
        byte[] publicKeyBytes = key.getEncoded();

        return Base64.getEncoder().encodeToString(publicKeyBytes);
    }

    @Override
    public PublicKey stringToPublicKey(String key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] publicKeyBytes = Base64.getDecoder().decode(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);

        KeyFactory keyFactory = KeyFactory.getInstance("DH");

        return keyFactory.generatePublic(keySpec);
    }
}
