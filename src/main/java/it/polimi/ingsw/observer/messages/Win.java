package it.polimi.ingsw.observer.messages;

import it.polimi.ingsw.model.Player;


/**
 * Message that notifies to a player that he has won the game.
 */
public class Win {

    private final Player player;

    public Win(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}