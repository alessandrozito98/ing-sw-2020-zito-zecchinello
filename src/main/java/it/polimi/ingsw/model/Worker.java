package it.polimi.ingsw.model;

public class Worker {

    enum workerColors {
        WHITE, BROWN, BLUE;
    }

    private Cell position;
    private boolean goesNorth;
    private boolean goesSouth;
    private boolean goesWest;
    private boolean goesEast;
    private boolean goesNEast;
    private boolean goesSEast;
    private boolean goesNWest;
    private boolean goesSWest;


    public Cell getPosition() {
        return position;
    }

    public void setPosition(Cell position) {
        this.position = position;
    }


}



