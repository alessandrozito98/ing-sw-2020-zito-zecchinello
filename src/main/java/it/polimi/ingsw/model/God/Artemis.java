package it.polimi.ingsw.model.God;

import it.polimi.ingsw.model.Cell;
import it.polimi.ingsw.model.Level;
import it.polimi.ingsw.model.Worker;

public class Artemis extends God {
    private Cell firstMovePosition;

    public Artemis(){
        super();
        this.availableMoveNumber = 2;
        this.firstMovePosition = null;
    }

    @Override
    public void Move(Cell cell, Worker worker) {
        Level oldLevel = worker.getPosition().getLevel();
        worker.getPosition().removeWorker();
        if(this.firstMovePosition==null){
            this.firstMovePosition = worker.getPosition();
            setAvailableBuildNumber(this.getAvailableBuildNumber()+1);
        }
        cell.addWorker(worker);
        worker.setPosition(cell);
        setAvailableMoveNumber(this.getAvailableBuildNumber()-1);
        if(winControl(oldLevel,cell.getLevel())){} //TO DO!!!!!!!!!
    }

    @Override
    public boolean isFeasibleMove(Cell cell, Worker worker) {
        if(this.firstMovePosition==cell){
            return false;
        }
        return super.isFeasibleMove(cell, worker);
    }

    @Override
    public void resetTurn() {
        super.resetTurn();
        this.availableMoveNumber = 2;
        this.firstMovePosition = null;
    }
}
