package it.polimi.ingsw.model.God;

import it.polimi.ingsw.model.Cell;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Level;
import it.polimi.ingsw.model.Worker;

public class Prometheus extends God {

    public Prometheus(Game game) {
        super(game);
        this.availableMoveNumber = 1;
        this.availableBuildNumber = 1;
        this.canMoveUp = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void build(Cell cell, Worker worker, Level level) {
        cell.setLevel(level);
        setAvailableBuildNumber(this.getAvailableBuildNumber() - 1);
        setHasBuilt(true);
        if(this.availableMoveNumber == 1) {
            this.setCanMoveUp(false);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void move(Cell cell, Worker worker) {
        worker.getPosition().removeWorker();
        cell.addWorker(worker);
        worker.setPosition(cell);
        setAvailableMoveNumber(this.getAvailableMoveNumber() - 1);
        setAvailableBuildNumber(this.getAvailableBuildNumber() + 1);
        setHasMoved(true);
        setAvailableBuildNumber(1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetTurn() {
        this.availableMoveNumber = 1;
        this.availableBuildNumber = 1;
        this.canMoveUp = true;
        this.hasMoved = false;
        this.hasBuilt = false;
    }
}
