package it.polimi.ingsw.observer.messages;

public class EndTurnRequest {
    private int playerNumber;

    public EndTurnRequest(int playerNumber){
        this.playerNumber = playerNumber;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }
}
