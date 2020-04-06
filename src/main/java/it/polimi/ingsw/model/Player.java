package it.polimi.ingsw.model;

public class Player {

    private String name;
    private Worker worker1;
    private Worker worker2;
    private WinGod godCard;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlayerName() {
        return name;
    }

    public void setPlayerName(String name) {
        this.name = name;
    }

    public Worker getPlayerWorker1() {
        return worker1;
    }

    public Worker getWorker1() {
        return worker1;
    }

    public void setWorker1(Worker worker1) {
        this.worker1 = worker1;
    }

    public Worker getWorker2() {
        return worker2;
    }

    public void setWorker2(Worker worker2) {
        this.worker2 = worker2;
    }

    public WinGod getGodCard() {
        return godCard;
    }

    public void setGodCard(WinGod godCard) {
        this.godCard = godCard;
    }

}

