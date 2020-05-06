package it.polimi.ingsw.observer.messages;

import it.polimi.ingsw.model.Level;
import it.polimi.ingsw.model.Player;

public class BuildRequest {
    private final Player player;
    private final int workerNumber;
    private final int xPosition;
    private final int yPosition;
    private final Level level;

    public BuildRequest(Player player, int workerNumber, int xPosition, int yPosition, Level level){
        this.player = player;
        this.workerNumber = workerNumber;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.level = level;
    }

    public Player getPlayer() {
        return player;
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
