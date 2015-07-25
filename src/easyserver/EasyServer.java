package com.liuksoft.easyserver;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luca
 */
public class EasyServer extends Thread {

    //Server del socket
    ServerSocket server;
    //ip del server
    private String ip;
    //porta in ascolto del server
    private final int port;

    private List<EasyClient> clients;

    private List<EasyServerListener> serverlisteners;

    /**
     * Constructor of the Server. Init the server socket and bind the local port
     * @param port An <code>int</code> that represent the local port to bind
     */
    public EasyServer(int port) {
        clients = new ArrayList<>();
        serverlisteners = new ArrayList<>();
        this.port = port;
        try {
            server = new ServerSocket(port);
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (IOException ex) {
            System.exit(1);
        }
    }

    /**
     * Add a listener from the server
     *
     * @param listener A <code>EasyServerListener</code> that listen the server
     */
    public synchronized void addServerListener(EasyServerListener listener) {
        serverlisteners.add(listener);
    }

    /**
     * Remove a listener from the server
     *
     * @param listener A <code>EasyServerListener</code> that listen the server
     */
    public synchronized void removeServerListener(EasyServerListener listener) {
        serverlisteners.remove(listener);
    }

    /**
     * Return the list of the client connected
     *
     * @return
     */
    public synchronized List<EasyClient> getClientList() {
        return clients;
    }

    /**
     * Return the client with the specified IP address
     * @param ip A <code>String</code> thet represent the ip of the client
     * @return An <code>EasyClient</code> with the specified IP address
     */
    public synchronized EasyClient getClient(String ip) {
        for (EasyClient client : clients) {
            if (client.getIP().equals(ip)) {
                return client;
            }
        }
        return null;
    }

    /**
     * Search 
     * @param ip
     * @return 
     */
    public boolean search(String ip) {
        for (EasyClient ec : clients) {
            if (ec.getIP().equals(ip)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void run() {
        while (true) {
            try {
                EasyClient c = new EasyClient(server.accept());
                c.addEasyClientListener(new AscoltoClient());
                clients.add(c);
                for (EasyServerListener s : serverlisteners) {
                    s.connectedUser(c);
                }
            } catch (IOException ex) {
            }
        }
    }

    class AscoltoClient implements EasyClientListener {

        @Override
        public void messageReceived(EasyClient client, String message) {
            for (EasyServerListener s : serverlisteners) {
                s.messageReceived(client, message);
            }
        }

        @Override
        public void userUnreachable(EasyClient client, String message) {
            for (EasyServerListener s : serverlisteners) {
                s.disconnectedUser(client, message);
            }
        }

    }
}
