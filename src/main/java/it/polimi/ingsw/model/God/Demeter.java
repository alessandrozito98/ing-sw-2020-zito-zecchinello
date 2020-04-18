package it.polimi.ingsw.model.God;

import it.polimi.ingsw.model.Cell;
import it.polimi.ingsw.model.Level;
import it.polimi.ingsw.model.Worker;

public class Demeter extends God {
    private Cell firstBuildPosition;

    public Demeter(){
        super();
        this.firstBuildPosition = null;
    }

    @Override
    public void Build(Cell cell, Worker worker, Level level) {
        if(firstBuildPosition==null) {
            firstBuildPosition = cell;
        }
        else {
            setAvailableBuildNumber(this.getAvailableBuildNumber() - 1);
        }
        cell.setLevel(level);
    }

    @Override
    public boolean isFeasibleBuild(Cell cell, Worker worker, Level level) {
        if(this.firstBuildPosition==cell){
            return false;
        }
        return super.isFeasibleBuild(cell, worker, level);
    }

    @Override
    public void resetTurn() {
        super.resetTurn();
        this.firstBuildPosition = null;
    }
}
