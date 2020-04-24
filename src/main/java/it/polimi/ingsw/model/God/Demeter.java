package it.polimi.ingsw.model.God;

import it.polimi.ingsw.model.Cell;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Level;
import it.polimi.ingsw.model.Worker;

public class Demeter extends God {
    private Cell firstBuildPosition;

    public Demeter(Game game){
        super(game);
        this.firstBuildPosition = null;
    }

    public Cell getFirstBuildPosition(){ return this.firstBuildPosition; }
    public void setFirstBuildPosition(Cell cell){ this.firstBuildPosition = cell; }

    @Override
    public void build(Cell cell, Worker worker, Level level) {
        if(this.firstBuildPosition==null) {
            this.firstBuildPosition = cell;
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
