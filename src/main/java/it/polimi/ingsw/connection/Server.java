package it.polimi.ingsw.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final int PORT = 2020;
    private ServerSocket serverSocket;
    private ExecutorService executor = Executors.newFixedThreadPool(128);
    private ArrayList<SocketClientConnection> waitingConnection = new ArrayList<SocketClientConnection>();

    private int numberOfPlayers = 0;
    private ArrayList<EnumGodCard> availableGodCards = new ArrayList<EnumGodCard>();
    private Map<SocketClientConnection, EnumGodCard> chosenGodCards = new HashMap<>();

    public Server() throws IOException {
        this.serverSocket = new ServerSocket(PORT);
    }

    public synchronized void lobby(SocketClientConnection c, String name) {
        waitingConnection.add(c);
        //scelta numero giocatori e dei da parte del primo che si collega
        if(waitingConnection.size()==1){
            SocketClientConnection c1 = waitingConnection.get(0);
            c1.asyncSend((String)"Chose the number of player: 2 or 3?");
            // SCELTA NUMERO DEI GIOCATORI
            while(!(numberOfPlayers==2||numberOfPlayers==3)){
                try {
                    String s = c1.read();
                    int number = Integer.parseInt(s);
                    if (number == 2 || number == 3) {
                        numberOfPlayers = number;
                    } else {
                        c1.asyncSend((String) "Error! Chose 2 or 3:");
                    }
                } catch (NumberFormatException e) {
                    c1.asyncSend((String) "Error! Chose 2 or 3:");
                }
            }
            // SCELTA DEI
            ArrayList<EnumGodCard> godCards = new ArrayList<EnumGodCard>(Arrays.asList(EnumGodCard.values()));
            c1.send((String) "Chose "+numberOfPlayers+" godCards from this list:");
            while(availableGodCards.size()!=numberOfPlayers){
                try {
                    for (EnumGodCard g : godCards) {
                        c1.send((String) g.toString());
                    }
                    String s = c1.read();
                    EnumGodCard god = Enum.valueOf(EnumGodCard.class ,s.toUpperCase());
                    if (godCards.contains(god)) {
                        availableGodCards.add(god);
                        godCards.remove(god);
                    } else {
                        c1.send((String) "Error! Chose from this list:");
                    }
                } catch (IllegalArgumentException e) {
                    c1.send((String) "Error! Chose from this list:");
                }
            }
            System.out.println("numero giocatori:" + numberOfPlayers); //da togliere, usata solo per test stupido
            for(EnumGodCard g:  availableGodCards){System.out.println(g.toString());} //da togliere, usata solo per test stupido
        }
        //SE SONO COLLEGATI IL NUMERO GIUSTO DI CLIENT FA SCEGLIERE AI GIOCATORI GLI DEI
        //POI FA POSIZIONARE I WORKERS, POI INIZIALIZZA LA PARTITA
        if(waitingConnection.size()==numberOfPlayers&&waitingConnection.size()!=0){
            //SCELTA DEI
            for(int i=1; i<numberOfPlayers;i++){
                SocketClientConnection connection = waitingConnection.get(i);
                connection.send((String) "Chose one god from this list: ");
                while(chosenGodCards.size()<i) {
                    try {
                        for (EnumGodCard g : availableGodCards) {
                            connection.send((String) g.toString());
                        }
                        String s = connection.read();
                        EnumGodCard god = Enum.valueOf(EnumGodCard.class, s.toUpperCase());
                        if (availableGodCards.contains(god)) {
                            chosenGodCards.put(connection, god);
                            availableGodCards.remove(god);
                        } else {
                            connection.send((String) "Error! Chose from this list:");
                        }
                    } catch (IllegalArgumentException e) {
                        connection.send((String) "Error! Chose from this list:");
                    }
                }
            }
            chosenGodCards.put(waitingConnection.get(0),availableGodCards.get(0));
            availableGodCards.remove(availableGodCards.get(0));
            waitingConnection.get(0).send((String)"your god is: "+ chosenGodCards.get(waitingConnection.get(0)).toString());
            //da togliere, usata solo per test stupido
            for(int i=0; i<numberOfPlayers; i++){
                System.out.println(chosenGodCards.get(waitingConnection.get(i)).toString());
            }
        }
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
