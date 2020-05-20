package it.polimi.ingsw.view;

import it.polimi.ingsw.connection.SocketClientConnection;
import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.observer.Observable;
import it.polimi.ingsw.observer.Observer;

public abstract class View extends Observable implements Observer {

    private Player player;
    private SocketClientConnection connection;
    private Board boardCopy;
    private int availableMoveNumber;
    private int availableBuildNumber;
    private boolean hasMoved;
    private boolean hasBuilt;

    public abstract void chooseAction();
    public abstract void moveHandler();
    public abstract void buildHandler();
    public abstract void endTurnHandler();
    public abstract void reportError(Object message);
}
