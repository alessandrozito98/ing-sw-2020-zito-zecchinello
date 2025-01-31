package it.polimi.ingsw.model;

import java.io.Serializable;

/**
 * This class represent a Worker, the constructor of the buildings.
 */
public class Worker implements Serializable {

    private int workerNumber;
    private WorkerColor color;
    private Cell position;

    public Worker(int workerNumber, WorkerColor color) {
        this.workerNumber = workerNumber;
        this.color = color;
        this.position = null;
    }

    public int getWorkerNumber() {
        return workerNumber;
    }

    public void setWorkerNumber(int workerNumber) {
        this.workerNumber = workerNumber;
    }

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


