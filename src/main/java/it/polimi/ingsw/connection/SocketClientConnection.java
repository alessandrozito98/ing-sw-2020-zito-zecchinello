package it.polimi.ingsw.connection;

import it.polimi.ingsw.observer.Observable;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SocketClientConnection extends Observable implements Runnable {

    private Socket socket;
    private Scanner in;
    private ObjectOutputStream out;
    private Server server;

    private boolean active = true;

    public SocketClientConnection(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    private synchronized boolean isActive(){
        return active;
    }

    public synchronized void closeConnection() {
        send("Connection closed!");
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("Error when closing socket!");
        }
        active = false;
    }

    public synchronized void send(Object message) {
        try {
            out.reset();
            out.writeObject(message);
            out.flush();
        } catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

    public void asyncSend(final Object message){
        new Thread(new Runnable() {
            @Override
            public void run() {
                send(message);
            }
        }).start();
    }
    // SERVE AL SERVER PER LEGGERE DAL CLIENT PRIMA DI INIZIALIZZARE LA PARTITA
    public String read(){
        String read = in.nextLine();
        return read;
    }

    @Override
    public void run() {
        String name;
        try{
            in = new Scanner(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            send("Welcome!\nWhat is your name?");
            String read = in.nextLine();
            name = read;
            server.lobby(this, name);
            while(isActive()){Thread.sleep(10000);}
        } catch (IOException | NoSuchElementException | InterruptedException e) {
            System.err.println("Error!" + e.getMessage());
        }finally{
            closeConnection();
        }
    }
}
