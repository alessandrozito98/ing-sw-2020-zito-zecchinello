package it.polimi.ingsw.model;


import java.util.ArrayList;
import java.util.Observable;

public class Game extends Observable {

    private Board board = new Board();
    private ArrayList<Player> players = new ArrayList<Player>();


    public ArrayList<Player> getPlayers() {
        return (ArrayList<Player>) players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

}
