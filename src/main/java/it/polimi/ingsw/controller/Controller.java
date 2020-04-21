package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Game;

import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {

    private Game game;

    public Controller(Game game) {
        this.game = game;
    }


    private void Game() {


    }

    public void handleMove(){}

    @Override
    public void update(Observable o, Object arg) {

    }
}
