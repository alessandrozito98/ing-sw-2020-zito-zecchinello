package it.polimi.ingsw.model;

public class Player {

    private String name;
    private int age;
    private Worker worker1;
    private Worker worker2;
    private God god;

    public void setPlayer(String name, int age) {
        this.name=name;
        this.age=age;

    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

}

