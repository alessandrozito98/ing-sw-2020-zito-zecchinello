package it.polimi.ingsw.observer.messages;

import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.Player;

public class PlayerLose {

    private final Board boardCopy;
    private final Player player;

    public PlayerLose(Board boardCopy, Player player){
        this.boardCopy = boardCopy;
        this.player = player;
    }

    public Board getBoardCopy() {
        return boardCopy;
    }

    public Player getPlayer() {
        return player;
    }
}
