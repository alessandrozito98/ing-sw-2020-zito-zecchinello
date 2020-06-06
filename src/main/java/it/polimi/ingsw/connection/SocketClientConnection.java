package it.polimi.ingsw.connection;

import it.polimi.ingsw.observer.Observable;
import it.polimi.ingsw.view.View;

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
    private View view;

    private boolean myTurn = false;
    private boolean activeGame = true;

    public SocketClientConnection(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    public synchronized void setMyTurn(boolean t){
        myTurn = t;
        if(t) notify();
    }

    public synchronized void setEndGame(){
        activeGame = false;
        notify();
    }

    public synchronized void waitMyTurn() throws InterruptedException {
        while(!myTurn) wait();
    }

    public void setView(View view) {
        this.view = view;
    }

    public View getView() {
        return view;
    }

    public synchronized void closeConnection() throws IOException {
        send("Connection closed!");
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("Error when closing socket!");
        }
    }

    public synchronized void send(Object message) throws IOException {
        out.reset();
        out.writeObject(message);
        out.flush();
    }

    public String read() throws NoSuchElementException{
        String read;
        read = in.nextLine();
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
            while(activeGame){
                waitMyTurn();
                if(activeGame) view.chooseAction();
            }
            socket.close();
        } catch (IOException | NoSuchElementException | InterruptedException e) {
            System.err.println("Error!" + e.getMessage());
        }
    }
}
