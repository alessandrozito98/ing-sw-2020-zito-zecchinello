package it.polimi.ingsw.view;

import it.polimi.ingsw.connection.SocketClientConnection;
import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.observer.Observable;
import it.polimi.ingsw.observer.Observer;

import java.io.IOException;

public abstract class View extends Observable implements Observer {

    private Player player;
    private SocketClientConnection connection;
    private Board boardCopy;
    private int availableMoveNumber;
    private int availableBuildNumber;
    private boolean hasMoved;
    private boolean hasBuilt;

    public abstract Player getPlayer();

    /**
     * Allows the player to choose the next action
     */
    public abstract void chooseAction();

    /**
     * The player can choose which worker to move and where to move it
     * @throws IOException
     * throws a new IOExecption when an I/O exception of some sort has occurred.
     */
    public abstract void moveHandler() throws IOException;

    /**
     * The player can choose which worker is to build the block, which block he wants to build and where he want to build
     * @throws IOException
     * throws a new IOExecption when an I/O exception of some sort has occurred.
     */
    public abstract void buildHandler() throws IOException;

    /**
     * when the player decides to end the turn this method creates the endTurnRequest message
     */
    public abstract void endTurnHandler();

    /**
     * handles the error reported by the controller
     * @param message
     * message sent by the controller
     * @throws IOException
     * throws a new IOExecption when an I/O exception of some sort has occurred.
     */
    public abstract void reportError(Object message) throws IOException;
}
