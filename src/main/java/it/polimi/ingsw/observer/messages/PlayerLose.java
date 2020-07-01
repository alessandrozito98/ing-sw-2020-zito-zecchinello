package it.polimi.ingsw.observer.messages;

import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.Player;

/**
 * A message that notifies a player that he has lost the game.
 */
public class PlayerLose {

    private final Board boardCopy;
    private final Player losePlayer;
    private final Player nextPlayer;

    public PlayerLose(Board boardCopy, Player losePlayer, Player nextPlayer) {
        this.boardCopy = boardCopy;
        this.losePlayer = losePlayer;
        this.nextPlayer = nextPlayer;
    }

    public Board getBoardCopy() {
        return boardCopy;
    }

    public Player getLosePlayer() {
        return losePlayer;
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }
}
