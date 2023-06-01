package model;

import Interface.DiffieHellmanInteraction;

import javax.crypto.KeyAgreement;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class DiffieHellman implements DiffieHellmanInteraction {

    private PrivateKey privateKey;

    private PublicKey publicKey;

    private PublicKey receivedPublicKey;

    private byte[] secretKey;

    @Override
    public void generateKeys() {
        try {
            final KeyPairGenerator generator = KeyPairGenerator.getInstance("DH");
            generator.initialize(1024);
            final KeyPair keyPair = generator.generateKeyPair();

            privateKey = keyPair.getPrivate();
            publicKey  = keyPair.getPublic();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void generateSecretKey() {
        try {
            final KeyAgreement keyAgreement = KeyAgreement.getInstance("DH");
            keyAgreement.init(privateKey);
            keyAgreement.doPhase(receivedPublicKey, true);

            byte[] secret = new byte[32];
            System.arraycopy(keyAgreement.generateSecret(), 0, secret, 0,secret.length);

            secretKey = secret;
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

    public byte[] getSecretKey(){
        return secretKey;
    }
}
