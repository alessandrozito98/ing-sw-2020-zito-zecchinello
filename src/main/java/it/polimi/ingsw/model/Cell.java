package it.polimi.ingsw.model;

public class Cell {

    private int x;
    private int y;
    private boolean occupied;
    private BlockType level1,level2,level3,dome;
    private Worker worker;




    public Cell(int x, int y, boolean occupied, BlockType level) {
        this.x = x;
        this.y = y;
        this.occupied = occupied;
    }

    //Returns x coordinate
    public int getX() {
        return x;
    }

    //Returns y coordinate
    public int getY() {
        return y;
    }

    public void addWorker(Worker worker) {

        if(!occupied) {
            this.worker = worker;
            occupied = true;
        }

    }

    public void removeWorker() {
        this.worker = null;
    }

    public void buildLevel1 () {
        level1 = BlockType.LEVEL1;
    }

    public void buildLevel2 () {
        if(level1 != null && level2 == null) {
            level2 = BlockType.LEVEL2;
        }
    }

    public void buildLevel3 () {
        if(level2 != null && level3 == null) {
            level3 = BlockType.LEVEL3;
        }
    }

    public void buildDome () {
        if (level3 != null && dome == null) {
            dome = BlockType.DOME;
        }
    }

}
