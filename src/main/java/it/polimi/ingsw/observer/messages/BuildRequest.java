package it.polimi.ingsw.observer.messages;

import it.polimi.ingsw.model.Level;

public class BuildRequest {
    private int playerNumber;
    private int workerNumber;
    private int xPosition;
    private int yPosition;
    private Level level;

    public BuildRequest(int playerNumber, int workerNumber, int xPosition, int yPosition,Level level){
        this.playerNumber = playerNumber;
        this.workerNumber = workerNumber;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.level = level;
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

    public Level getLevel() {
        return level;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public void setWorkerNumber(int workerNumber) {
        this.workerNumber = workerNumber;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
