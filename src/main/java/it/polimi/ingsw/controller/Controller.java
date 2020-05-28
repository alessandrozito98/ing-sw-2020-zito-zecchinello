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
        if(playerTurn==message.getPlayer().getPlayerNumber()) {
            System.out.println("arrivato a inizio handleMove");
            if (loseControl(message.getPlayer()) == 0) {
                setChoosenWorker(-1);
                if(playerTurn == game.getPlayers().size()-1) {
                    setPlayerTurn(0);
                }
                else {
                    setPlayerTurn(game.getPlayers().get(game.getPlayers().indexOf(message.getPlayer())).getPlayerNumber());
                }
                game.remove(message.getPlayer());
            } else if (getChoosenWorker() == -1) {
                if(message.getPlayer().getGodCard().isFeasibleMove(game.getBoard().getCell(message.getxPosition(),message.getyPosition()),message.getPlayer().getSingleWorker(message.getWorkerNumber()))) {
                    setChoosenWorker(message.getWorkerNumber());
                    game.performMove(message.getPlayer(), message.getWorkerNumber(), message.getxPosition(), message.getyPosition());
                } else {
                    message.getView().reportError("Error! this move is not feasible!");
                }
            } else if (message.getWorkerNumber() != getChoosenWorker()) {
                message.getView().reportError("Error! You chose the incorrect worker!");
            } else {
                if(message.getPlayer().getGodCard().isFeasibleMove(game.getBoard().getCell(message.getxPosition(),message.getyPosition()),message.getPlayer().getSingleWorker(message.getWorkerNumber()))) {
                    game.performMove(message.getPlayer(), message.getWorkerNumber(), message.getxPosition(), message.getyPosition());
                } else {
                    message.getView().reportError("Error! this move is not feasible!");
                }
            }
            System.out.println("arrivato a fine handleMove");
        }
        else {
            message.getView().reportError("Error! It's not your turn!");
        }
    }

    public synchronized void handleBuild(BuildRequest message) {
        if(playerTurn==message.getPlayer().getPlayerNumber()) {
            if (loseControl(message.getPlayer()) == 0) {
                setChoosenWorker(-1);
                if(playerTurn == game.getPlayers().size()-1) {
                    setPlayerTurn(0);
                }
                else {
                    setPlayerTurn(game.getPlayers().get(game.getPlayers().indexOf(message.getPlayer())).getPlayerNumber());
                }
                game.remove(message.getPlayer());
            } else if (getChoosenWorker() == -1) {
                if(message.getPlayer().getGodCard().isFeasibleBuild(game.getBoard().getCell(message.getxPosition(),message.getyPosition()),message.getPlayer().getSingleWorker(message.getWorkerNumber()),message.getLevel())) {
                    setChoosenWorker(message.getWorkerNumber());
                    game.performBuild(message.getPlayer(), message.getWorkerNumber(), message.getxPosition(), message.getyPosition(), message.getLevel());
                } else {
                    message.getView().reportError("Error! this build is not feasible!");
                }
            } else if (getChoosenWorker() != message.getWorkerNumber()) {
                message.getView().reportError("Error! You chose the incorrect worker!");
            } else {
                if(message.getPlayer().getGodCard().isFeasibleBuild(game.getBoard().getCell(message.getxPosition(),message.getyPosition()),message.getPlayer().getSingleWorker(message.getWorkerNumber()),message.getLevel())) {
                    game.performBuild(message.getPlayer(), message.getWorkerNumber(), message.getxPosition(), message.getyPosition(), message.getLevel());
                } else {
                    message.getView().reportError("Error! this build is not feasible!");
                }
            }
        }
    }

    public synchronized void manageTurn(EndTurnRequest message) {
        setChoosenWorker(-1);
        if(playerTurn == game.getPlayers().size()-1) {
            setPlayerTurn(0);
        }
        else {
            setPlayerTurn(game.getPlayers().get(game.getPlayers().indexOf(message.getPlayer())).getPlayerNumber());
        }
        game.manageEndTurn(message.getPlayer());
    }

    public void updateMoveRequest(MoveRequest message) {
        System.out.println("update move request");
        handleMove(message);
    }

    public void updateBuildRequest(BuildRequest message) {
        handleBuild(message);
    }

    public void updateEndTurnRequest(EndTurnRequest message) {
        manageTurn(message);
    }

    public int loseControl(Player player) {
        Player loseControlPlayer;
        if (game.getPlayers().indexOf(player) == game.getPlayers().size() - 1) {
            loseControlPlayer = game.getPlayers().get(0);
        } else {
            loseControlPlayer = game.getPlayers().get(game.getPlayers().indexOf(player) + 1);
        }
        int actionCounter = 0;
        for (int i = 1; i < 3; i++) {
            if (loseControlPlayer.getGodCard().getAvailableBuildNumber() != 0) {
                if (!game.checkBuilding(loseControlPlayer.getPlayerNumber(), i, game.getBoardCopy()).isEmpty()) {
                    actionCounter++;
                }
            }
            if (!game.checkMovement(loseControlPlayer.getPlayerNumber(), i, game.getBoardCopy()).isEmpty()) {
                actionCounter++;
            }
        }
        return actionCounter;
    }
}
