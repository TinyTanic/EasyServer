package com.liuksoft.easyserver;

/**
 *
 * @author Luca Aguzzoli
 */
public interface EasyClientListener {

    public void messageReceived(EasyClient client, String message);
    
    public void userUnreachable(EasyClient client, String message);
}
