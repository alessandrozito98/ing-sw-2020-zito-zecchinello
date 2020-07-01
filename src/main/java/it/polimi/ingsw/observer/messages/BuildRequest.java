package it.polimi.ingsw.observer.messages;

import it.polimi.ingsw.model.Level;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.view.View;

/**
 * Message request of new build
 */
public class BuildRequest {
    private final View view;
    private final Player player;
    private final int workerNumber;
    private final int xPosition;
    private final int yPosition;
    private final Level level;

    public BuildRequest(View view, Player player, int workerNumber, int xPosition, int yPosition, Level level) {
        this.view = view;
        this.player = player;
        this.workerNumber = workerNumber;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.level = level;
    }

    public View getView() {
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

    public Level getLevel() {
        return level;
    }
}
