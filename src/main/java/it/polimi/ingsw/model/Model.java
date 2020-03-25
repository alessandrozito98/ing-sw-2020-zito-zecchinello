package it.polimi.ingsw.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Model extends Observable implements Cloneable {

    private Board board;
    List<Player> players = new ArrayList<Player>();
    ArrayList<God> gods = new ArrayList<God>();

    @Override
    public Model clone() {
        Model model = new Model();
        model.board = board;
        model.players.set(0, players.get(0));
        model.players.set(1, players.get(1));
        model.players.set(2, players.get(2));
        model.gods.set(0, gods.get(0));
        model.gods.set(1, gods.get(1));
        model.gods.set(2, gods.get(2));
        return model;
    }


    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }


}
