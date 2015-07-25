package easyserver;

/**
 *
 * @author Luca Aguzzoli
 */
public interface EasyServerListener {

    public void connectedUser(EasyClient client);

    public void disconnectedUser(EasyClient client, String message);
    
    public void messageReceived(EasyClient client, String message);
}
