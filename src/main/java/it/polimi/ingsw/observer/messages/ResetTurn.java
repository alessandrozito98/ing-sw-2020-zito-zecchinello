package it.polimi.ingsw.observer.messages;

import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.Player;

public class ResetTurn {
    private final int availableMoveNumber;
    private final int availableBuildNumber;
    private final boolean hasMoved;
    private final boolean hasBuilt;
    private final Player previousPlayer;
    private final Player nextPlayer;
    private final Player losePlayer;
    private final Board boardCopy;

    public ResetTurn(int availableMoveNumber, int availableBuildNumber, boolean hasMoved, boolean hasBuilt, Player previousPlayer, Player nextPlayer, Player losePlayer, Board boardCopy){
        this.availableMoveNumber = availableMoveNumber;
        this.availableBuildNumber = availableBuildNumber;
        this.hasMoved = hasMoved;
        this.hasBuilt = hasBuilt;
        this.previousPlayer = previousPlayer;
        this.nextPlayer = nextPlayer;
        this.losePlayer = losePlayer;
        this.boardCopy = boardCopy;
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

    public Player getPreviousPlayer() {
        return previousPlayer;
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }

    public Player getLosePlayer() {
        return losePlayer;
    }

    public Board getBoardCopy() {
        return boardCopy;
    }
}
