package com.liuksoft.easyserver;

import com.liuksoft.easyserver.errors.SendingException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Luca
 */
public class EasyClient extends Thread {

    private List<EasyClientListener> listeners;

    private String ip;
    public Socket socket;
    private final Scanner toServer;
    private final PrintStream toClient;
    private final Date accesstime;

    public EasyClient(Socket socket) throws IOException {
        listeners = new ArrayList<>();
        accesstime = Calendar.getInstance().getTime();
        this.socket = socket;
        ip = socket.getInetAddress().getHostAddress();
        toServer = new Scanner(socket.getInputStream());
        toClient = new PrintStream(socket.getOutputStream());
        this.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                String message = toServer.nextLine();
                for (EasyClientListener cl : listeners) {
                    cl.messageReceived(this, message);
                }
            } catch (NoSuchElementException exc) {
                for (EasyClientListener cl : listeners) {
                    cl.userUnreachable(this, getIP());
                }
                break;
            }
        }
    }

    public boolean sendTo(String message) throws SendingException {
        try {
            toClient.println(message);
            return true;
        } catch (Exception exc) {
            throw new SendingException(getIP() + " -> " + message);
        }
    }

    public String getIP() {
        return ip;
    }

    /**
     * restituisce l'ora di connessione del client
     *
     * @return una stringa rappresentante l'roa di connessione del client
     */
    public String getAccessDate() {
        return accesstime.toString();
    }

    public synchronized void addEasyClientListener(EasyClientListener cl) {
        listeners.add(cl);
    }

    public synchronized void removeEasyClientListener(EasyClientListener cl) {
        listeners.remove(cl);
    }
}
