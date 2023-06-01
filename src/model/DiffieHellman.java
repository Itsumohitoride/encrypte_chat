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

    /**
     * This function generates a public-private key pair using the Diffie-Hellman algorithm with a key
     * size of 1024 bits.
     */
    @Override
    public void generateKeys() {
        try {
            final KeyPairGenerator generator = KeyPairGenerator.getInstance("DH");
            generator.initialize(1024);
            final KeyPair keyPair = generator.generateKeyPair();

            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This Java function generates a secret key using the Diffie-Hellman key exchange algorithm.
     */
    @Override
    public void generateSecretKey() {
        try {
            final KeyAgreement keyAgreement = KeyAgreement.getInstance("DH");
            keyAgreement.init(privateKey);
            keyAgreement.doPhase(receivedPublicKey, true);

            byte[] secret = new byte[32];
            System.arraycopy(keyAgreement.generateSecret(), 0, secret, 0, secret.length);

            secretKey = secret;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This function sets the received public key to a variable.
     *
     * @param key The "key" parameter is a PublicKey object that is being received as input to the
     *            "receivedPublicKey" method. The method then assigns this input key to the "receivedPublicKey"
     *            variable.
     */
    @Override
    public void receivedPublicKey(PublicKey key) {
        receivedPublicKey = key;
    }

    /**
     * The function returns the public key.
     *
     * @return The method `getPublicKey()` is returning a `PublicKey` object.
     */
    public PublicKey getPublicKey() {
        return publicKey;
    }

    /**
     * The function returns the public key that was received.
     *
     * @return The method `getReceivedPublicKey()` is returning a `PublicKey` object.
     */
    public PublicKey getReceivedPublicKey() {
        return publicKey;
    }

    /**
     * The function returns a byte array representing a secret key.
     *
     * @return A byte array named "secretKey" is being returned.
     */
    public byte[] getSecretKey() {
        return secretKey;
    }
}
