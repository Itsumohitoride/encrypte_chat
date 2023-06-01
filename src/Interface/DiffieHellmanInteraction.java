package Interface;

import java.security.PublicKey;

public interface DiffieHellmanInteraction {

    void generatePublicKey();

    void generatePrivateKey();

    void generateSecretKey();

    void receivedPublicKey(PublicKey key);

}
