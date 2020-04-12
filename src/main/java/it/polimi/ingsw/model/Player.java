package it.polimi.ingsw.model;

import it.polimi.ingsw.model.God.God;

import java.util.ArrayList;

public class Player {

    private String name;
    private ArrayList<Worker> workers;
    private God godCard;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(ArrayList<Worker> workers) {
        this.workers = workers;
    }


}



