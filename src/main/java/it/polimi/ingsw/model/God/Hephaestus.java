package it.polimi.ingsw.model.God;

import it.polimi.ingsw.model.Cell;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Level;
import it.polimi.ingsw.model.Worker;

public class Hephaestus extends God {
    private Cell firstBuildPosition;
    public Hephaestus(Game game){
        super(game);
        this.firstBuildPosition = null;
    }

    public Cell getFirstBuildPosition(){ return this.firstBuildPosition; }
    public void setFirstBuildPosition(Cell cell){ this.firstBuildPosition = cell; }

    /**
     * {@inheritDoc}
     */
    @Override
    public void build(Cell cell, Worker worker, Level level) {
        if(this.firstBuildPosition == null) {
            this.firstBuildPosition = cell;
        }
        else {
            setAvailableBuildNumber(this.getAvailableBuildNumber() - 1);
        }
        setHasBuilt(true);
        cell.setLevel(level);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFeasibleBuild(Cell cell, Worker worker, Level level) {
        if(this.firstBuildPosition == null) {
            return super.isFeasibleBuild(cell, worker, level);
        }
        else {
            if((this.firstBuildPosition == cell) && (level.ordinal() == (cell.getLevel().ordinal() + 1)) && (level != Level.DOME)) {
                return true;
            }
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetTurn() {
        super.resetTurn();
        this.firstBuildPosition = null;
    }
}
