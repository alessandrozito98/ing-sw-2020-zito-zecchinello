package it.polimi.ingsw.observer.messages;

import it.polimi.ingsw.model.Board;

public class BoardChangeMessage {
    private Board boardCopy;
    private int playerID;
    private int availableMoveNumber;
    private int availableBuildNumber;
    private boolean hasMoved;
    private boolean hasBuilt;

    public BoardChangeMessage(Board boardCopy, int playerID, int availableMoveNumber, int availableBuildNumber, boolean hasMoved, boolean hasBuilt){
        this.boardCopy = boardCopy;
        this.playerID = playerID;
        this.availableMoveNumber = availableMoveNumber;
        this.availableBuildNumber = availableBuildNumber;
        this.hasMoved = hasMoved;
        this.hasBuilt = hasBuilt;
    }

    public Board getBoardCopy() {
        return boardCopy;
    }

    public int getPlayerID() {
        return playerID;
    }

    public int getAvailableMoveNumber() {
        return availableMoveNumber;
    }

    public int getAvailableBuildNumber() {
        return availableBuildNumber;
    }

    public boolean getHasMoved() {
        return hasMoved;
    }

    public boolean getHasBuilt() {
        return hasBuilt;
    }
}
