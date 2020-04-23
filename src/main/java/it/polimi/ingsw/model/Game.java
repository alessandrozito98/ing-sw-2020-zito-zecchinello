package it.polimi.ingsw.model;


import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.observer.Observable;

import java.util.ArrayList;

public class Game extends Observable<Controller> {

    private final Board board = new Board();
    private final ArrayList<Player> players = new ArrayList<Player>();


    public Board getBoardCopy() {
        return board.clone();
    }

    public ArrayList<Player> getPlayers() {
        return (ArrayList<Player>) players;
    }

    public Player getSinglePlayer(int playerNumber) {

        // returns a player given the playerNumber

        for (Player p: players) {
            if(p.getPlayerNumber() == playerNumber) {
                return p;
            }
        }
        return null;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayers(Player player) {
        players.remove(player);
    }
}
