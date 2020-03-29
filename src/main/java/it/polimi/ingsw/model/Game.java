package it.polimi.ingsw.model;


import it.polimi.ingsw.model.God.AbstractGod;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Game extends Observable implements Cloneable {

    private Board board;
    private List<Player> players = new ArrayList<Player>();
    private ArrayList<AbstractGod> gods = new ArrayList<AbstractGod>();

    @Override
    public Game clone() {
        Game model = new Game();
        model.board = board;
        model.players.set(0, players.get(0));
        model.players.set(1, players.get(1));
        model.players.set(2, players.get(2));
        model.gods.set(0, gods.get(0));
        model.gods.set(1, gods.get(1));
        model.gods.set(2, gods.get(2));
        return model;
    }


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
