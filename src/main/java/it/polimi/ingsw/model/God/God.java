package it.polimi.ingsw.model.God;

import it.polimi.ingsw.model.Level;
import it.polimi.ingsw.model.Cell;
import it.polimi.ingsw.model.Worker;

import java.util.Observable;


public abstract class God extends Observable {
    protected int availableMoveNumber;
    protected int availableBuildNumber;
    protected boolean canMoveUp;

    public God(){
        this.availableMoveNumber = 1;
        this.availableBuildNumber = 0;
        this.canMoveUp = true;
    }

    public void Move(Cell cell, Worker worker){
        Level oldLevel = worker.getPosition().getLevel();
        worker.getPosition().removeWorker();
        cell.addWorker(worker);
        worker.setPosition(cell);
        setAvailableMoveNumber(this.getAvailableBuildNumber()-1);
        setAvailableBuildNumber(this.getAvailableBuildNumber()+1);
        setChanged();
        notifyObservers();
        if(winControl(oldLevel,cell.getLevel())){} //TO DO!!!!!!!!!
    }

    public void Build(Cell cell, Worker worker, Level level){
        cell.setLevel(level);
        setAvailableBuildNumber(this.getAvailableBuildNumber()-1);
    }

    public boolean isFeasibleMove(Cell cell,Worker worker){

        if(cell.getWorker()!=null) {
            return false;
        }

        if(cell.getLevel()==Level.DOME){
            return false;
        }

        if(cell.getLevel().ordinal()>(worker.getPosition().getLevel().ordinal()+1)){
            return false;
        }

        if(!this.getCanMoveUp()&&(cell.getLevel().ordinal()==(worker.getPosition().getLevel().ordinal()+1))){
            return false;
        }

        if(cell.getX() == worker.getPosition().getX() - 1 || cell.getX() == worker.getPosition().getX() + 1 || cell.getX() == worker.getPosition().getX()) {
            if (cell.getY() == worker.getPosition().getY() - 1 || cell.getY() == worker.getPosition().getY() + 1 || cell.getY() == worker.getPosition().getY()) {
                return true;
            }
        }
        return false;
    }

    public boolean isFeasibleBuild(Cell cell, Worker worker, Level level){

        if(cell.getWorker()!=null) {
            return false;
        }

        if(cell.getLevel()==Level.DOME){
            return false;
        }

        if(!(cell.getLevel().ordinal()==(level.ordinal()-1))){
            return false;
        }

        if(cell.getX() == worker.getPosition().getX() - 1 || cell.getX() == worker.getPosition().getX() + 1 || cell.getX() == worker.getPosition().getX()) {
            if (cell.getY() == worker.getPosition().getY() - 1 || cell.getY() == worker.getPosition().getY() + 1 || cell.getY() == worker.getPosition().getY()) {
                return true;
            }
        }
        return false;
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

    public boolean winControl(Level oldLevel, Level newLevel){
        if(oldLevel!=newLevel&&newLevel==Level.LEVEL3){
            return true;
        }
        return false;
    }

    public void resetTurn(){
        this.availableMoveNumber = 1;
        this.availableBuildNumber = 0;
        this.canMoveUp = true;
    }


}