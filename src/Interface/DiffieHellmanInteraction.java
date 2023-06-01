package Interface;

import java.security.PublicKey;

public interface DiffieHellmanInteraction {

    void generateKeys();

    void generateSecretKey();

    void receivedPublicKey(PublicKey key);

}
