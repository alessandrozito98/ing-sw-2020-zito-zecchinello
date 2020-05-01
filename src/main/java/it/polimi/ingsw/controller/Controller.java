package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.observer.Observer;
import it.polimi.ingsw.observer.messages.*;

import java.util.Observable;

public class Controller implements Observer {

    private final Game game;

    public Controller(Game game) {
        this.game = game;
    }


    private void Game() {


    }

    public void handleMove(){}


    @Override
    public void updateMoveRequest(MoveRequest message) {
        //QUA FA QUALCOSA
    }

    @Override
    public void updateBuildRequest(BuildRequest message) {
        //QUA FA QUALCOSA
    }

    @Override
    public void updateEndTurnRequest(EndTurnRequest message) {
        //QUA FA QUALCOSA
    }
    //I PROSSIMI UPDATE NON LI USERA' MAI MA BISOGNA "IMPLEMENTARLI" TUTTI PER FORZA
    @Override
    public void updateBoardChange(BoardChange message) {

    }

    @Override
    public void updateStartTurn(StartTurn message) {

    }

    @Override
    public void updatePlayerLose(PlayerLose message) {

    }
}
