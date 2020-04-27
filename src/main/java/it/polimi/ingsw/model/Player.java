package it.polimi.ingsw.model;

import it.polimi.ingsw.model.God.God;

import java.util.ArrayList;

public class Player {

    private String name;
    private int playerNumber;
    private ArrayList<Worker> workers;
    private God godCard;

    public Player(String name, int playerNumber, ArrayList<Worker> workers, God godCard) {
        this.name = name;
        this.playerNumber = playerNumber;
        this.workers = workers;
        this.godCard = godCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public ArrayList<Worker> getWorkers() {
        return workers;
    }

    public void addWorkers(Worker worker) {
        this.workers.add(worker);
    }

    public God getGodCard() {
        return godCard;
    }

    public void setGodCard(God godCard) {
        this.godCard = godCard;
    }

    public Worker getSingleWorker(int workerNumber) {

        // returns a worker given the playerNumber

        for (Worker w: this.workers) {
            if(w.getWorkerNumber() == workerNumber) {
                return w;
            }
        }
        return null;
    }
}




