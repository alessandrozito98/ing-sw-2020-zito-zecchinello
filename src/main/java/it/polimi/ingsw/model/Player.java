package it.polimi.ingsw.model;

import it.polimi.ingsw.model.God.AbstractGod;

public class Player {

    private String name;
    private int age;
    private Worker worker1;
    private Worker worker2;
    private AbstractGod godCard;


    public String getPlayerName() {
        return name;
    }

    public void setPlayerName(String name) {
        this.name = name;
    }

    public int getPlayerAge() {
        return age;
    }

    public void setPlayerAge(int age) {
        this.age = age;
    }


}

