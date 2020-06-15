package it.polimi.ingsw.model;

import java.io.Serializable;

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

    public void addWorker(Worker worker) {

        if (this.worker == null) {
            this.worker = worker;
        }
    }

    public void removeWorker() {
        this.worker = null;
    }

}