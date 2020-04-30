package it.polimi.ingsw.observer.messages;

import it.polimi.ingsw.model.Board;

public class PlayerLoseMessage {
    private Board boardCopy;
    private int playerID;

    public PlayerLoseMessage(Board boardCopy, int playerID){
        this.boardCopy = boardCopy;
        this.playerID = playerID;
    }

    public Board getBoardCopy() {
        return boardCopy;
    }

    public int getPlayerID() {
        return playerID;
    }
}
