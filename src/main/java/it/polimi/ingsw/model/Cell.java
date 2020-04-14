package it.polimi.ingsw.model;

public class Cell {

    private int x;
    private int y;
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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