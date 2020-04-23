package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.observer.Observer;

import java.util.Observable;

public class Controller implements Observer<Game> {

    private final Game game;

    public Controller(Game game) {
        this.game = game;
    }


    private void Game() {


    }

    public void handleMove(){}


    @Override
    public void update(Game message) {

    }
}
