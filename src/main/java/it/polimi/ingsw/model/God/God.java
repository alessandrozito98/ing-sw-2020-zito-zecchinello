package it.polimi.ingsw.model.God;

import it.polimi.ingsw.model.Level;
import it.polimi.ingsw.model.Cell;
import it.polimi.ingsw.model.Worker;

public abstract class God {
    private int availableMoveNumber;
    private int availableBuildNumber;
    private boolean canMoveUp;

    public void Move(Cell cell, Worker worker){
        cell.removeWorker();
        cell.addWorker(worker);
        worker.setPosition(cell);
        setAvailableMoveNumber(this.availableMoveNumber-1);
        setAvailableBuildNumber(this.availableBuildNumber+1);
    }

    public void Build(Cell cell, Worker worker, Level level){
        cell.setLevel(level);
        setAvailableBuildNumber(this.availableBuildNumber-1);
    }

    public boolean isFeasibleMove(Cell cell,Worker worker){

    }

    public boolean isFeasibleBuild(Cell cell,Worker worker, Level level){

    }

    public int getAvailableMoveNumber() {
        return availableMoveNumber;
    }

    public int getAvailableBuildNumber() {
        return availableBuildNumber;
    }

    public void setAvailableMoveNumber(int availableMoveNumber) {
        this.availableMoveNumber = availableMoveNumber;
    }

    public void setAvailableBuildNumber(int availableBuildNumber) {
        this.availableBuildNumber = availableBuildNumber;
    }

    public void setCanMoveUp(boolean canMoveUp) {
        this.canMoveUp = canMoveUp;
    }

    public boolean getCanMoveUp() {
        return canMoveUp;
    }

    public void resetTurn(){
        this.availableMoveNumber = 1;
        this.availableBuildNumber = 0;
        this.canMoveUp = true;
    }


}