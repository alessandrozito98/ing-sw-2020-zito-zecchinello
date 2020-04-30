package it.polimi.ingsw.observer.messages;

public class EndTurnRequestMessage {
    private int playerNumber;

    public EndTurnRequestMessage(int playerNumber){
        this.playerNumber = playerNumber;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }
}
