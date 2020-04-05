package it.polimi.ingsw.model;


import it.polimi.ingsw.model.God.AbstractGod;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Model extends Observable {

    private Board board;
    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<AbstractGod> gods = new ArrayList<AbstractGod>();


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
