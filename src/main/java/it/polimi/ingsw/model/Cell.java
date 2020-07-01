package it.polimi.ingsw.model;

import java.io.Serializable;

/**
 * Represent a cell, a 1/25 part of the board.
 */
public class Cell implements Serializable {

    private final int x;
    private final int y;
    private Level level;
    private Worker worker;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.level = Level.GROUND;
        this.worker = null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Level getLevel(){
        return this.level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Worker getWorker() {
        return worker;
    }

    /**
     * It places a worker to a cell.
     * @param worker
     * The worker that we want to add.
     */
    public void addWorker(Worker worker) {

        if (this.worker == null) {
            this.worker = worker;
        }
    }
    /**
     * It removes a worker from a cell.
     */
    public void removeWorker() {
        this.worker = null;
    }

}