package it.polimi.ingsw.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final int PORT = 2020;
    private ServerSocket serverSocket;
    private ExecutorService executor = Executors.newFixedThreadPool(128);
    private Map<String, SocketClientConnection> waitingConnection = new HashMap<>();

    private int numberOfPlayers = 0;

    public Server() throws IOException {
        this.serverSocket = new ServerSocket(PORT);
    }

    public synchronized void lobby(SocketClientConnection c, String name) {
        waitingConnection.put(name, c);
        if(waitingConnection.size()==1){ //scelta numero giocatori da parte del primo che si collega
            List<String> keys = new ArrayList<>(waitingConnection.keySet());
            SocketClientConnection c1 = waitingConnection.get(keys.get(0));
            c1.asyncSend((String)"chose the number of player: 2 or 3?");
            while(!(numberOfPlayers==2||numberOfPlayers==3)){
                try {
                    String s = c1.read();
                    int number = Integer.parseInt(s);
                    if (number == 2 || number == 3) {
                        numberOfPlayers = number;
                    } else {
                        c1.asyncSend((String) "Error! chose 2 or 3:");
                    }
                } catch (NumberFormatException e) {
                    c1.asyncSend((String) "Error! chose 2 or 3:");
                }
            }
        }
        System.out.println("numero giocatori:" + numberOfPlayers); //da togliere, usata solo per test stupido
    }

    public void run(){
        while(true){
            try {
                Socket newSocket = serverSocket.accept();
                SocketClientConnection socketClientConnection = new SocketClientConnection(newSocket, this);
                executor.submit(socketClientConnection);
            } catch (IOException e) {
                System.out.println("Connection Error!");
            }
        }
    }
}
