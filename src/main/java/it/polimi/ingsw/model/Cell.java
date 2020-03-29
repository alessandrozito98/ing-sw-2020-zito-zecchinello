package it.polimi.ingsw.model;

public class Cell {

    private int x;
    private int y;
    private boolean occupied;
    private Block level;
    private Worker worker;

    public Cell(int x, int y) {
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

    public boolean getOccupied(){
        return this.occupied;
    }

    public Block getLevel(){
        return this.level;
    }


}