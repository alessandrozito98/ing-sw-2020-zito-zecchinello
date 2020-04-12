package it.polimi.ingsw.model.God;

import it.polimi.ingsw.model.Level;
import it.polimi.ingsw.model.Cell;

public abstract class God {


    public void move() {

    }

    public void build(Cell cell) {
        if (cell.getLevel() == Level.GROUND && cell.getWorker() == null) {
            cell.setLevel(Level.LEVEL1);
        } else {
            if (cell.getLevel() == Level.LEVEL1 && cell.getWorker() == null) {
                cell.setLevel(Level.LEVEL2);
            } else {
                if (cell.getLevel() == Level.LEVEL2 && cell.getWorker() == null) {
                    cell.setLevel(Level.LEVEL3);
                } else {
                    if (cell.getLevel() == Level.LEVEL3 && cell.getWorker() == null) {
                        cell.setLevel(Level.DOME);
                    }
                }
            }
        }
    }
}