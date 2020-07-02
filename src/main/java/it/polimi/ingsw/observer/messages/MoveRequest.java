package it.polimi.ingsw.observer.messages;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.view.View;

/**
 * Request to move a worker in a new cell
 */
public class MoveRequest {
    private final View view;
    private final Player player;
    private final int workerNumber;
    private final int xPosition;
    private final int yPosition;

    public MoveRequest(View view, Player player, int workerNumber, int xPosition, int yPosition) {
        this.view = view;
        this.player = player;
        this.workerNumber = workerNumber;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public View getView(){
        return view;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWorkerNumber() {
        return workerNumber;
    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }
}
