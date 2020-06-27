package it.polimi.ingsw.controller;

import it.polimi.ingsw.connection.SocketClientConnection;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.observer.Observer;
import it.polimi.ingsw.observer.messages.*;

import java.io.IOException;
import java.util.ArrayList;

public class Controller implements Observer {

    private final Game game;
    private int playerTurn;
    private int chosenWorker;
    private ArrayList<SocketClientConnection> connections;

    public Controller(Game game,ArrayList<SocketClientConnection> connections) {
        this.game = game;
        this.connections = connections;
        this.chosenWorker = -1;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(int newPlayerTurn) {
        int oldPlayerTurn = this.playerTurn;
        this.playerTurn = newPlayerTurn;
        for(SocketClientConnection connection : connections){
            if(connection.getView().getPlayer().getPlayerNumber() == oldPlayerTurn) connection.setMyTurn(false);
        }
        for(SocketClientConnection connection : connections){
            if(connection.getView().getPlayer().getPlayerNumber() == this.playerTurn) connection.setMyTurn(true);
        }
    }

    public int getChosenWorker() {
        return chosenWorker;
    }

    public void setChosenWorker(int chosenWorker) {
        this.chosenWorker = chosenWorker;
    }

    public synchronized void handleMove(MoveRequest message) throws IOException {
        if(playerTurn==message.getPlayer().getPlayerNumber()) {
            //System.out.println("arrivato a inizio handleMove");
            if (loseControl(message.getPlayer()) == 0) {
                setChosenWorker(-1);
                if(game.getPlayers().indexOf(game.getSinglePlayer(playerTurn)) == game.getPlayers().size()-1) {
                    setPlayerTurn(game.getPlayers().get(0).getPlayerNumber());
                }
                else {
                    setPlayerTurn(game.getPlayers().get(game.getPlayers().indexOf(message.getPlayer())+1).getPlayerNumber());
                }
                game.remove(message.getPlayer());
            } else if (getChosenWorker() == -1) {
                if(message.getPlayer().getGodCard().isFeasibleMove(game.getBoard().getCell(message.getxPosition(),message.getyPosition()),message.getPlayer().getSingleWorker(message.getWorkerNumber()))) {
                    setChosenWorker(message.getWorkerNumber());
                    game.performMove(message.getPlayer(), message.getWorkerNumber(), message.getxPosition(), message.getyPosition());
                } else {
                    message.getView().reportError("Error! this move is not feasible!");
                }
            } else if (message.getWorkerNumber() != getChosenWorker()) {
                message.getView().reportError("Error! You chose the incorrect worker!");
            } else {
                if(message.getPlayer().getGodCard().isFeasibleMove(game.getBoard().getCell(message.getxPosition(),message.getyPosition()),message.getPlayer().getSingleWorker(message.getWorkerNumber()))) {
                    game.performMove(message.getPlayer(), message.getWorkerNumber(), message.getxPosition(), message.getyPosition());
                } else {
                    message.getView().reportError("Error! this move is not feasible!");
                }
            }
            //System.out.println("arrivato a fine handleMove");
        }
        else {
            message.getView().reportError("Error! It's not your turn!");
        }
    }

    public synchronized void handleBuild(BuildRequest message) throws IOException {
        if(playerTurn==message.getPlayer().getPlayerNumber()) {
            if (loseControl(message.getPlayer()) == 0) {
                setChosenWorker(-1);
                game.remove(message.getPlayer());
                if(game.getPlayers().indexOf(game.getSinglePlayer(playerTurn)) == game.getPlayers().size()-1) {
                    setPlayerTurn(game.getPlayers().get(0).getPlayerNumber());
                }
                else {
                    setPlayerTurn(game.getPlayers().get(game.getPlayers().indexOf(message.getPlayer())+1).getPlayerNumber());
                }
            } else if (getChosenWorker() == -1) {
                if(message.getPlayer().getGodCard().isFeasibleBuild(game.getBoard().getCell(message.getxPosition(),message.getyPosition()),message.getPlayer().getSingleWorker(message.getWorkerNumber()),message.getLevel())) {
                    setChosenWorker(message.getWorkerNumber());
                    game.performBuild(message.getPlayer(), message.getWorkerNumber(), message.getxPosition(), message.getyPosition(), message.getLevel());
                } else {
                    message.getView().reportError("Error! this build is not feasible!");
                }
            } else if (getChosenWorker() != message.getWorkerNumber()) {
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
        setChosenWorker(-1);
        game.manageEndTurn(message.getPlayer());
        if(game.getPlayers().indexOf(game.getSinglePlayer(playerTurn)) == game.getPlayers().size()-1) {
            setPlayerTurn(game.getPlayers().get(0).getPlayerNumber());
        }
        else {
            setPlayerTurn(game.getPlayers().get(game.getPlayers().indexOf(message.getPlayer())+1).getPlayerNumber());
        }
    }

    public void updateMoveRequest(MoveRequest message) throws IOException {
        System.out.println("update move request");
        handleMove(message);
    }

    public void updateBuildRequest(BuildRequest message) throws IOException {
        handleBuild(message);
    }

    public void updateEndTurnRequest(EndTurnRequest message) {
        manageTurn(message);
    }

    @Override
    public void updateEndGame() {
        for(SocketClientConnection c : connections){
            try{
                c.send("Game canceled!! A client has disconnected\nPress ENTER to close");
                c.setEndGame();
            } catch (IOException e) {
                c.getServer().incrementClosingCount();
            }
        }
    }

    public int loseControl(Player player) {
        int actionCounter = 0;
        for (int i = 1; i < 3; i++) {
            if (player.getGodCard().getAvailableBuildNumber() != 0) {
                if (!game.checkBuilding(player.getPlayerNumber(), i, game.getBoardCopy()).isEmpty()) {
                    actionCounter++;
                }
            }
            if (!game.checkMovement(player.getPlayerNumber(), i, game.getBoardCopy()).isEmpty()) {
                actionCounter++;
            }
        }
        return actionCounter;
    }
}
