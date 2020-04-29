package it.polimi.ingsw.model.God;

import it.polimi.ingsw.model.Cell;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Level;
import it.polimi.ingsw.model.Worker;

public class Prometheus extends God {

    public Prometheus(Game game){
        super(game);
        this.availableMoveNumber = 1;
        this.availableBuildNumber = 1;
        this.canMoveUp = true;
    }

    @Override
    public void build(Cell cell, Worker worker, Level level) {
        cell.setLevel(level);
        setAvailableBuildNumber(this.getAvailableBuildNumber()-1);
        setHasBuilt(true);
        if(this.availableMoveNumber==1){
            this.setCanMoveUp(false);
        }
    }

    @Override
    public void move(Cell cell, Worker worker) {
        super.move(cell, worker);
        setAvailableBuildNumber(1);
    }

    @Override
    public void resetTurn() {
        this.availableMoveNumber = 1;
        this.availableBuildNumber = 1;
        this.canMoveUp = true;
        this.hasMoved = false;
        this.hasBuilt = false;
    }
}
