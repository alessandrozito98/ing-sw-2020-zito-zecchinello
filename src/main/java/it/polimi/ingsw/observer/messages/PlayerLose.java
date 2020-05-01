package it.polimi.ingsw.observer.messages;

import it.polimi.ingsw.model.Board;

public class PlayerLose {
    private Board boardCopy;

    public PlayerLose(Board boardCopy, int playerID){
        this.boardCopy = boardCopy;
    }

    public Board getBoardCopy() {
        return boardCopy;
    }
}
