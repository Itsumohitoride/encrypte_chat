package model;

import Interface.EncryptInteraction;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class Encrypt implements EncryptInteraction {
    @Override
    public void encrypt() {

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
