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

    /**
     * set the attribute myTurn with the param t
     * if t is true, the method wake up the thread of this instance
     * @param t
     * a boolean value
     */
    public synchronized void setMyTurn(boolean t){
        myTurn = t;
        if(t) notify();
    }

    /**
     * set activeGame to false and wake up the thread of this instance
     */
    public synchronized void setEndGame(){
        activeGame = false;
        notify();
    }

    /**
     * put the thread of this instance in wait until it is the turn of this connection
     * @throws InterruptedException
     * throws a new InterruptedExecption when an Interrupted exception of some sort has occurred.
     */
    public synchronized void waitMyTurn() throws InterruptedException {
        while(!myTurn) wait();
    }

    public void setView(View view) {
        this.view = view;
    }

    public View getView() {
        return view;
    }

    public Server getServer(){ return server;}

    public synchronized void closeConnection() throws IOException {
        send("Connection closed!");
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("Error when closing socket!");
        }
    }

    /**
     * Send a message to the Object Output Stream
     * @param message
     * the message sent by the method
     * @throws IOException
     * throws a new IOExecption when an I/O exception of some sort has occurred.
     */
    public synchronized void send(Object message) throws IOException {
        out.reset();
        out.writeObject(message);
        out.flush();
        if(message.equals("YOU WIN\nPress ENTER to close")){
            server.removeAllConnection();
        }
    }

    /**
     * Read the nextLine from the Input Stream
     * @return
     * Return the string read
     * @throws NoSuchElementException
     * throws a new NoSuchElementExecption when there is no nextLine.
     */
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
            server.incrementClosingCount();
            socket.close();
            //System.out.println("connessioni cancellate");
        } catch (IOException | NoSuchElementException | InterruptedException e) {
            System.err.println("Error!" + e.getMessage());
        }
    }
}
