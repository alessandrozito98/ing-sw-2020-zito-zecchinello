package it.polimi.ingsw.model;

public class Cell {

    private int x;
    private int y;
    private boolean occupied;
    private Block level;
    private Worker worker;

    public Cell(int x, int y, boolean occupied, Block level, Worker worker) {
        this.x = x;
        this.y = y;
        this.occupied = false;
        this.level = null;
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

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public Block getLevel(){
        return this.level;
    }

    public void setLevel(Block level) {
        this.level = level;
    }

    public Worker getWorker() {
        return worker;
    }


    public void addWorker(Worker worker) {

        if (!occupied) {
            this.worker = worker;
            occupied = true;
        }
    }

    public void removeWorker() {
        this.worker = null;
        occupied = false;
    }


    public void build() {
        if (level == null) {
            level = Block.LEVEL1;
        } else {
            if (level == Block.LEVEL1) {
                level = Block.LEVEL2;
            } else {
                if (level == Block.LEVEL2) {
                    level = Block.LEVEL3;
                } else {
                    if (level == Block.LEVEL3) {
                        level = Block.DOME;
                    }
                }
            }
        }

    }


}