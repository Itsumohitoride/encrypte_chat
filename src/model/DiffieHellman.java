package model;

import Interface.DiffieHellmanInteraction;

import javax.crypto.KeyAgreement;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class DiffieHellman implements DiffieHellmanInteraction {

    private PrivateKey privateKey;

    private PublicKey publicKey;

    private PublicKey receivedPublicKey;

    private byte[] secretKey;

    @Override
    public void generatePublicKey() {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("DH");
            generator.initialize(256);
            publicKey = generator.generateKeyPair().getPublic();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void generatePrivateKey() {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("DH");
            generator.initialize(256);
            privateKey = generator.generateKeyPair().getPrivate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void generateSecretKey() {
        try {
            KeyAgreement keyAgreement = KeyAgreement.getInstance("DH");
            keyAgreement.init(privateKey);
            keyAgreement.doPhase(privateKey, true);

            secretKey = new byte[32];
            System.arraycopy(keyAgreement.generateSecret(), 0, secretKey, 0,secretKey.length);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void receivedPublicKey(PublicKey key) {
        receivedPublicKey = key;
    }

    public PublicKey getPublicKey(){
        return publicKey;
    }

    public PublicKey getReceivedPublicKey(){
        return publicKey;
    }
}
