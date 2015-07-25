package example;

import com.liuksoft.easyserver.EasyClient;
import com.liuksoft.easyserver.EasyServer;
import com.liuksoft.easyserver.EasyServerListener;

/**
 *
 * @author Luca Aguzzoli
 */
public class EasyServerExample implements EasyServerListener {

    public EasyServerExample() {
        EasyServer es = new EasyServer(1234); //create the EasyServer and bind the port
        es.addServerListener(this); //add a listener for the server actions
        es.start(); //start the server to listening for new clients
    }

    public static void main(String[] args) {
        new EasyServerExample();
    }

    @Override
    public void connectedUser(EasyClient client) { // when a new user connects
        System.out.println("User " + client.getIP() + " connected at " + client.getAccessDate());
    }

    @Override
    public void disconnectedUser(EasyClient client, String message) { //when an user disconnects
        System.out.println("User " + client.getIP() + " disconnected!");
    }

    @Override
    public void messageReceived(EasyClient client, String message) { // when a user sent a message to the server
        System.out.println("User " + client.getIP() + " send this message\"" + message + "\"");
    }
}
