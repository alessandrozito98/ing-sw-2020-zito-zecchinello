package it.polimi.ingsw.observer.messages;

import it.polimi.ingsw.model.Level;

public class MoveRequestMessage {
    private int playerNumber;
    private int workerNumber;
    private int xPosition;
    private int yPosition;

    public MoveRequestMessage(int playerNumber, int workerNumber, int xPosition, int yPosition){
        this.playerNumber = playerNumber;
        this.workerNumber = workerNumber;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public int getWorkerNumber() {
        return workerNumber;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }
}
