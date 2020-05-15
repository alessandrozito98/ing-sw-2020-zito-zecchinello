package it.polimi.ingsw.view;

import it.polimi.ingsw.connection.SocketClientConnection;
import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.Player;

public class RemoteView extends View {
    private Player player;
    private SocketClientConnection connection;
    private Board boardCopy;
    private int availableMoveNumber;
    private int availableBuildNumber;
    private boolean hasMoved;
    private boolean hasBuilt;

    public RemoteView(Player player, SocketClientConnection connection){

    }

}
