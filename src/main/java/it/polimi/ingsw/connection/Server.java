package it.polimi.ingsw.connection;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.view.RemoteView;
import it.polimi.ingsw.view.View;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private ServerSocket serverSocket;
    private ExecutorService executor = Executors.newFixedThreadPool(128);
    private ArrayList<SocketClientConnection> waitingConnection = new ArrayList<SocketClientConnection>();

    private int numberOfPlayers = 0;
    int firstPlayer = 0;
    private int closingCount = 0;
    private Map<SocketClientConnection, String> names = new HashMap<>();
    private ArrayList<EnumGodCard> availableGodCards = new ArrayList<EnumGodCard>();
    private Map<SocketClientConnection, EnumGodCard> chosenGodCards = new HashMap<>();
    private Map<SocketClientConnection, ArrayList<Worker>> workerMap = new HashMap<>();

    public Server(int PORT) throws IOException {
        this.serverSocket = new ServerSocket(PORT);
    }

    /**
     * remove all connections to start a new game
     */
    public void removeAllConnection() {
        waitingConnection.clear();
        workerMap.clear();
        chosenGodCards.clear();
        availableGodCards.clear();
        names.clear();
        numberOfPlayers = 0;
        firstPlayer = 0;
        closingCount = 0;
        System.out.println("All connection removed");
    }

    /**
     *
     called by a connection when it is closing
     */
    public void incrementClosingCount() {
        closingCount++;
        if(closingCount == numberOfPlayers) {
            removeAllConnection();
        }
    }

    /**
     * Central method called by the SocketClientConnetions to initialize the game
     * @param c
     * the connection who called this method
     * @param loginName
     * the userName of the connection
     * @throws IOException
     * throws a new IOExecption when an I/O exception of some sort has occurred.
     */
    public synchronized void lobby(SocketClientConnection c, String loginName) throws IOException {
        waitingConnection.add(c);
        String name = loginName;
        List<SocketClientConnection> keys = new ArrayList<>(names.keySet());
        boolean alreadyUsedName = false;
        do {
            if(alreadyUsedName) {
                c.send("Error! this name is already used, choose another one: ");
                name = c.read();
                alreadyUsedName = false;
            }
            boolean b = false;
            for(SocketClientConnection k : keys) {
                if(names.get(k).equalsIgnoreCase(name)) {
                    b = true;
                }
            }
            if(b) {
                alreadyUsedName = true;
            }
        } while(alreadyUsedName);
        names.put(c,name);
        // First player will choose number of player
        if(waitingConnection.size() == 1){
            SocketClientConnection c1 = waitingConnection.get(0);
            c1.send((String)"Choose the number of player: 2 or 3?");
            while(!(numberOfPlayers == 2 || numberOfPlayers == 3)) {
                try {
                    String s = c1.read();
                    int number = Integer.parseInt(s);
                    if (number == 2 || number == 3) {
                        numberOfPlayers = number;
                    } else {
                        c1.send((String) "Error! Choose 2 or 3:");
                    }
                } catch (NumberFormatException e) {
                    c1.send((String) "Error! Choose 2 or 3:");
                }
            }
            // Choiche of the gods
            ArrayList<EnumGodCard> godCards = new ArrayList<>(Arrays.asList(EnumGodCard.values()));
            c1.send((String) "Choose " + numberOfPlayers + " godCards from this list:");
            while(availableGodCards.size() != numberOfPlayers){
                try {
                    for (EnumGodCard g : godCards) {
                        c1.send((String) g.toString() + ": " + g.description);
                    }
                    String s = c1.read();
                    EnumGodCard god = Enum.valueOf(EnumGodCard.class ,s.toUpperCase());
                    if (godCards.contains(god)) {
                        availableGodCards.add(god);
                        godCards.remove(god);
                    } else {
                        c1.send((String) "Error! Choose from this list:");
                    }
                } catch (IllegalArgumentException e) {
                    c1.send((String) "Error! Choose from this list:");
                }
            }
            // Test
            System.out.println("Number of players: " + numberOfPlayers);
            // Test
            System.out.println("Chosen gods: ");
            for(EnumGodCard g:  availableGodCards) {
                System.out.println(g.toString());
            }
        }
        // The other players will choose his own God
        if(waitingConnection.size() == numberOfPlayers&&waitingConnection.size() != 0) {
            for(int i = 1; i < numberOfPlayers; i++) {
                SocketClientConnection connection = waitingConnection.get(i);
                connection.send((String) "Choose one god from this list: ");
                while(chosenGodCards.size() < i) {
                    try {
                        for (EnumGodCard g : availableGodCards) {
                            connection.send((String) g.toString() + ": " + g.description);
                        }
                        String s = connection.read();
                        EnumGodCard god = Enum.valueOf(EnumGodCard.class, s.toUpperCase());
                        if (availableGodCards.contains(god)) {
                            chosenGodCards.put(connection, god);
                            availableGodCards.remove(god);
                        } else {
                            connection.send((String) "Error! Choose from this list:");
                        }
                    } catch (IllegalArgumentException e) {
                        connection.send((String) "Error! Choose from this list:");
                    }
                }
            }
            chosenGodCards.put(waitingConnection.get(0),availableGodCards.get(0));
            availableGodCards.remove(availableGodCards.get(0));
            waitingConnection.get(0).send((String)"Your god is: " + chosenGodCards.get(waitingConnection.get(0)).toString());
            // Test
            for(int i = 0; i < numberOfPlayers; i++) {
                System.out.println(chosenGodCards.get(waitingConnection.get(i)).toString());
            }
            // Choice of who will start the match
            waitingConnection.get(0).send((String) "Choose the first player with a number from 1 to " + numberOfPlayers + ": ");
            while(firstPlayer<1 || firstPlayer > numberOfPlayers) {
                try {
                    String s = waitingConnection.get(0).read();
                    int number = Integer.parseInt(s);
                    if (number == 1 || number == 2 || number == 3) {
                        firstPlayer = number;
                    } else {
                        waitingConnection.get(0).send((String) "Error! Choose the first player with a number from 1 to "+ numberOfPlayers +": ");
                    }
                } catch (NumberFormatException e) {
                    waitingConnection.get(0).send((String) "Error! Choose the first player with a number from 1 to " + numberOfPlayers + ": ");
                }
            }
            // Changing the first player
            if(firstPlayer != 1){
                SocketClientConnection first = waitingConnection.get(firstPlayer - 1);
                while(waitingConnection.get(0) != first) {
                    SocketClientConnection temp = waitingConnection.remove(0);
                    waitingConnection.add(temp);
                }
            }
            // Test
            System.out.println("First player is player number " + firstPlayer);

                Board board = new Board();

            // Placing workers
            for(int i = 0; i < numberOfPlayers; i++) {
                SocketClientConnection connection = waitingConnection.get(i);
                workerMap.put(connection,new ArrayList<Worker>());
                ArrayList<Worker> workers = workerMap.get(connection);
                for(int j = 1; j < 3; j++){
                    boolean done = false;
                    while (!done) {
                        connection.send((Board)board);
                        connection.send((String)"Choose the coordinates 'x,y' of the worker N^"+j+" from 0 to 4:");
                        try {
                            String s = connection.read();
                            String[] coordinates = s.split(",");
                            int x = Integer.parseInt(coordinates[0]);
                            int y = Integer.parseInt(coordinates[1]);
                            if(x < 0 || x > 4 || y < 0 || y > 4) {
                                connection.send((String)"Error! cell does not exist");
                            } else if(board.getCell(x,y).getWorker()!=null) {
                                connection.send((String)"Error! occupied cell");
                            }else {
                                Worker w = new Worker(j,WorkerColor.values()[i]);
                                w.setPosition(board.getCell(x,y));
                                workers.add(w);
                                board.getCell(x,y).addWorker(w);
                                done = true;
                            }
                        }catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                            connection.send((String)"Error! Write the coordinates with this format 'x,y'");
                        }
                    }
                }
            }
            // Initializing the match
            ArrayList<View> views = new ArrayList<>();
            Game game = new Game(board);
            Controller controller = new Controller(game,waitingConnection);
            for(int i = 0; i < numberOfPlayers; i++) {
                game.addPlayer(new Player(names.get(waitingConnection.get(i)), i, workerMap.get(waitingConnection.get(i)), chosenGodCards.get(waitingConnection.get(i)).createGod(game)));
                views.add(new RemoteView(game.getSinglePlayer(i), waitingConnection.get(i), game.getBoardCopy()));
                waitingConnection.get(i).setView(views.get(i));
                game.addObserver(views.get(i));
                views.get(i).addObserver(controller);
            }
            controller.setPlayerTurn(0);
        }
    }

    public void run() {
        while(true) {
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
