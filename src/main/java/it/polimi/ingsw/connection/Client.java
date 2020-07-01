package it.polimi.ingsw.connection;

import it.polimi.ingsw.model.Board;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client {
    private String ip;
    private int port;

    public Client(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

    private boolean active = true;

    public synchronized boolean isActive(){
        return active;
    }

    public synchronized void setActive(boolean active){
        this.active = active;
    }

    /**
     * create a thread that read objects from socket and print them on the Client screen
     * @param socketIn
     * Object Input Stream stream which objects are read
     * @return
     * returns the created thread
     */
    public Thread asyncReadFromSocket(final ObjectInputStream socketIn){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                    while (isActive()) {
                        try {
                            Object inputObject = socketIn.readObject();
                            if (inputObject instanceof String) {
                                System.out.println((String) inputObject);
                                if(inputObject.equals("Game canceled!! A client has disconnected\nPress ENTER to close")||inputObject.equals("YOU WIN\nPress ENTER to close")||inputObject.equals("YOU LOSE\nPress ENTER to close")){
                                    setActive(false);
                                }
                            } else if (inputObject instanceof Board) {
                                ((Board) inputObject).printBoard();
                            } else {
                                throw new IllegalArgumentException();
                            }
                        } catch (Exception e) {
                            setActive(false);
                        }
                    }

            }
        });
        t.start();
        return t;
    }

    /**
     * create a thread that read objects from the client keyboard and send them to the socket
     * @param stdin
     * scanner that reads the strings written by the keyboard
     * @param socketOut
     * Output Stream where strings are sent
     * @return
     * returns the created thread
     */
    public Thread asyncWriteToSocket(final Scanner stdin, final PrintWriter socketOut){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                    while (isActive()) {
                        try {
                            String inputLine = stdin.nextLine();
                            socketOut.println(inputLine);
                            socketOut.flush();
                        } catch (Exception e) {
                            setActive(false);
                        }
                    }

            }
        });
        t.start();
        return t;
    }

    public void run() throws IOException {
        Socket socket = new Socket(ip, port);
        System.out.println("Connection established");
        ObjectInputStream socketIn = new ObjectInputStream(socket.getInputStream());
        PrintWriter socketOut = new PrintWriter(socket.getOutputStream());
        Scanner stdin = new Scanner(System.in);

        try{
            Thread t0 = asyncReadFromSocket(socketIn);
            Thread t1 = asyncWriteToSocket(stdin, socketOut);
            t1.join();
            t0.join();
        } catch(InterruptedException | NoSuchElementException e){
            System.out.println("Connection closed from the client side");
        } finally {
            stdin.close();
            socketIn.close();
            socketOut.close();
            socket.close();
        }
    }
}
