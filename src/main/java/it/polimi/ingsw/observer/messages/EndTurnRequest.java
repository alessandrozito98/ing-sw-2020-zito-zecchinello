package it.polimi.ingsw.observer.messages;

public class EndTurnRequest {
    private final int playerNumber;

    public EndTurnRequest(int playerNumber){
        this.playerNumber = playerNumber;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }
}
