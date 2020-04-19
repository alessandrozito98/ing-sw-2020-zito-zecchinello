package it.polimi.ingsw.model;

import it.polimi.ingsw.model.God.God;

import java.util.ArrayList;

public class Player {

    private String name;
    private int playerNumber;
    private ArrayList<Worker> workers;
    private God godCard;


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

    public void setWorkers(ArrayList<Worker> workers) {
        this.workers = workers;
    }

    public God getGodCard() {
        return godCard;
    }

    public void setGodCard(God godCard) {
        this.godCard = godCard;
    }

}



