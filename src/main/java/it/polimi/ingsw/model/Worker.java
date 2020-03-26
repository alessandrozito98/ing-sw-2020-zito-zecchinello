package it.polimi.ingsw.model;

public class Worker {

    private WorkerColor color;
    private Cell position;

    public Cell getPosition() {
        return position;
    }

    public void setPosition(Cell position) {
        this.position = position;
    }

    public WorkerColor getColor() {
        return color;
    }

    public void setColor(WorkerColor color) {
        this.color = color;
    }
}


