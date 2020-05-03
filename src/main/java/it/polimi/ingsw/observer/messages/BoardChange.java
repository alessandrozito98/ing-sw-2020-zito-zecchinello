package it.polimi.ingsw.observer.messages;

import it.polimi.ingsw.model.Board;

public class BoardChange {
    private final Board boardCopy;
    private final int availableMoveNumber;
    private final int availableBuildNumber;
    private final boolean hasMoved;
    private final boolean hasBuilt;

    public BoardChange(Board boardCopy, int availableMoveNumber, int availableBuildNumber, boolean hasMoved, boolean hasBuilt){
        this.boardCopy = boardCopy;
        this.availableMoveNumber = availableMoveNumber;
        this.availableBuildNumber = availableBuildNumber;
        this.hasMoved = hasMoved;
        this.hasBuilt = hasBuilt;
    }

    public Board getBoardCopy() {
        return boardCopy;
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
