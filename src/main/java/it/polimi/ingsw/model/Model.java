package it.polimi.ingsw.model;


import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable {

    private Board board;
    private ArrayList<Player> players = new ArrayList<Player>();


    public ArrayList<Player> getPlayers() {
        return (ArrayList<Player>) players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void createBoard() {
        Board board = new Board();

    }

}
