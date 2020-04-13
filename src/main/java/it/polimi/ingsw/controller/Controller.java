package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.view.View;

import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {

    private Game game;
    private View view;

    public Controller(Game game, View view) {
        this.game = game;
        this.view=view;
    }


    private void Game() {


    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
