package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.observer.Observer;
import it.polimi.ingsw.observer.messages.*;

import java.util.ArrayList;
import java.util.Observable;

public class Controller implements Observer {

    private final Game game;
    private int playerTurn;
    private int choosenWorker;

    public Controller(Game game) {
        this.game = game;
        this.choosenWorker = -1;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    public int getChoosenWorker() {
        return choosenWorker;
    }

    public void setChoosenWorker(int choosenWorker) {
        this.choosenWorker = choosenWorker;
    }

    public synchronized void handleMove(MoveRequest message) {
        if (getChoosenWorker() == -1) {
            setChoosenWorker(message.getWorkerNumber());
            game.performMove(message.getPlayer(), message.getWorkerNumber(), message.getxPosition(), message.getyPosition());
        }
        else {
            if (message.getWorkerNumber() != getChoosenWorker()) {
                message.getView().reportError("Error! You chose the incorrect worker!");
            }
            else {
                game.performMove(message.getPlayer(), message.getWorkerNumber(), message.getxPosition(), message.getyPosition());
            }

        }
    }

    public synchronized void handleBuild(BuildRequest message) {
        if (getChoosenWorker() == -1) {
            setChoosenWorker(message.getWorkerNumber());
            game.performBuild(message.getPlayer(), message.getWorkerNumber(), message.getxPosition(), message.getyPosition(), message.getLevel());
        }
        else {
            if (getChoosenWorker() != message.getWorkerNumber()) {
                message.getView().reportError("Error! You chose the incorrect worker!");
            }
            else {
                game.performBuild(message.getPlayer(), message.getWorkerNumber(), message.getxPosition(), message.getyPosition(), message.getLevel());
            }
        }
    }

    public synchronized void manageTurn(EndTurnRequest message) {
        game.manageEndTurn(message.getPlayer());
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
