package it.polimi.ingsw.model;

import it.polimi.ingsw.model.God.God;

import java.util.ArrayList;

/**
 * This class represent a player.
 */
public class Player {

    private final String name;
    private final int playerNumber;
    private final ArrayList<Worker> workers;
    private final God godCard;

    public Player(String name, int playerNumber, ArrayList<Worker> workers, God godCard) {
        this.name = name;
        this.playerNumber = playerNumber;
        this.workers = workers;
        this.godCard = godCard;
    }

    public String getName() {
        return name;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public God getGodCard() {
        return godCard;
    }

    /**
     * It returns a Worker given its workerNumber.
     * @param workerNumber
     * The number of the selected worker (between 0 or 1)
     * @return
     * The chosen worker.
     */
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




