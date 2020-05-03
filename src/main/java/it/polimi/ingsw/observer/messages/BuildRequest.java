package it.polimi.ingsw.observer.messages;

import it.polimi.ingsw.model.Level;

public class BuildRequest {
    private final int playerNumber;
    private final int workerNumber;
    private final int xPosition;
    private final int yPosition;
    private final Level level;

    public BuildRequest(int playerNumber, int workerNumber, int xPosition, int yPosition, Level level){
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
}
