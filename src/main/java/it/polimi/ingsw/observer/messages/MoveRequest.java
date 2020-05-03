package it.polimi.ingsw.observer.messages;

import it.polimi.ingsw.model.Level;

public class MoveRequest {
    private final int playerNumber;
    private final int workerNumber;
    private final int xPosition;
    private final int yPosition;

    public MoveRequest(int playerNumber, int workerNumber, int xPosition, int yPosition){
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
