package it.polimi.ingsw.model;

public class Cell {

    private int x;
    private int y;
    private boolean occupied;
    private Block level1,level2,level3,dome;
    private Worker worker;
    private boolean north = false;
    private boolean south = false;
    private boolean east = false;
    private boolean west = false;




    public Cell(int x, int y, boolean occupied, Block level1, Block level2, Block level3, Block dome, Worker worker) {
        this.x = x;
        this.y = y;
        this.occupied = occupied;
        this.level1 = level1;
        this.level2 = level2;
        this.level3 = level3;
        this.dome = dome;
        this.worker = worker;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isNorth() {
        return north;
    }

    public void setNorth(boolean north) {
        this.north = north;
    }

    public boolean isSouth() {
        return south;
    }

    public void setSouth(boolean south) {
        this.south = south;
    }

    public boolean isEast() {
        return east;
    }

    public void setEast(boolean east) {
        this.east = east;
    }

    public boolean isWest() { return west; }

    public void setWest(boolean west) { this.west = west; }

    public void addWorker(Worker worker) {

        if(!occupied) {
            this.worker = worker;
            occupied = true;
        }

    }
    public void removeWorker() {
        this.worker = null;
    }


    public void build () {
        if (!occupied) {

            if (level1 == null) {
                level1 = Block.LEVEL1;
            }
            else {
                if (level2 == null) {
                    level2 = Block.LEVEL2;
                }
                else {
                    if (level3 == null) {
                        level3 = Block.LEVEL3;
                    }
                    else {
                        if (dome == null) {
                            dome = Block.DOME;
                        }
                    }
                }
            }
        }
    }
}
