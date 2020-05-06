package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.observer.Observer;
import it.polimi.ingsw.observer.messages.*;

import java.util.ArrayList;
import java.util.Observable;

public class Controller implements Observer {

    private final Game game;
    private int playerTurn;
    private int workerNumber;

    public Controller(Game game) {
        this.game = game;
        this.workerNumber = 0;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    public int getWorkerNumber() {
        return workerNumber;
    }

    public void setWorkerNumber(int workerNumber) {
        this.workerNumber = workerNumber;
    }

    public synchronized void handleMove(MoveRequest message) {
        game.performMove(message.getPlayerNumber(), message.getWorkerNumber(), message.getxPosition(), message.getyPosition());
    }

    public synchronized void handleBuild(BuildRequest message) {
        game.performBuild(message.getPlayerNumber(),message.getWorkerNumber(),message.getxPosition(), message.getyPosition(), message.getLevel());
    }

    public synchronized void manageTurn(EndTurnRequest message) {
        game.manageEndTurn(message.getPlayerNumber());
    }

    public void updateMoveRequest(MoveRequest message) {
        handleMove(message);
    }

    public void updateBuildRequest(BuildRequest message) {
        handleBuild(message);
    }

    public void updateEndTurnRequest(EndTurnRequest message) {
        manageTurn(message);
    }
}
