package it.polimi.ingsw.observer.messages;

import it.polimi.ingsw.model.Board;

public class PlayerLoseMessage {
    private Board boardCopy;

    public PlayerLoseMessage(Board boardCopy, int playerID){
        this.boardCopy = boardCopy;
    }

    public Board getBoardCopy() {
        return boardCopy;
    }
}
