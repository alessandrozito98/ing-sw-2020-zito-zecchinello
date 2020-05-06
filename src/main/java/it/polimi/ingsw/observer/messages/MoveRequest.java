package it.polimi.ingsw.observer.messages;

import it.polimi.ingsw.model.Level;
import it.polimi.ingsw.model.Player;

public class MoveRequest {
    private final Player player;
    private final int workerNumber;
    private final int xPosition;
    private final int yPosition;

    public MoveRequest(Player player, int workerNumber, int xPosition, int yPosition){
        this.player = player;
        this.workerNumber = workerNumber;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
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
}
