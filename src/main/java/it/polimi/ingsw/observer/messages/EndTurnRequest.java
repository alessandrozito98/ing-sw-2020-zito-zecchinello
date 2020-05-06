package it.polimi.ingsw.observer.messages;

import it.polimi.ingsw.model.Player;

public class EndTurnRequest {

    private final Player player;

    public EndTurnRequest(Player player){
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
