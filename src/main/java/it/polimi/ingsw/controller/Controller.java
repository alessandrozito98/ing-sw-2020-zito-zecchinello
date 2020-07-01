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

    /**
     *
     * @param newPlayerTurn
     * Given the new playerNumber, it changes the player turn.
     */
    public void setPlayerTurn(int newPlayerTurn) {
        int oldPlayerTurn = this.playerTurn;
        this.playerTurn = newPlayerTurn;
        for(SocketClientConnection connection : connections) {
            if(connection.getView().getPlayer().getPlayerNumber() == oldPlayerTurn) {
                connection.setMyTurn(false);
            }
        }
        for(SocketClientConnection connection : connections) {
            if(connection.getView().getPlayer().getPlayerNumber() == this.playerTurn) {
                connection.setMyTurn(true);
            }
        }
    }

    public int getChosenWorker() {
        return chosenWorker;
    }

    public void setChosenWorker(int chosenWorker) {
        this.chosenWorker = chosenWorker;
    }

    /**
     * This class manage a new move by a player.
     * @param message
     * it reads from message who is the player and all his attributes.
     * @throws IOException
     * throws a new IOExecption when an I/O exception of some sort has occurred.
     */
    public synchronized void handleMove(MoveRequest message) throws IOException {
        if(playerTurn==message.getPlayer().getPlayerNumber()) {
            if (loseControl(message.getPlayer()) == 0) {
                setChosenWorker(-1);
                if(game.getPlayers().indexOf(game.getSinglePlayer(playerTurn)) == game.getPlayers().size() - 1) {
                    setPlayerTurn(game.getPlayers().get(0).getPlayerNumber());
                }
                else {
                    setPlayerTurn(game.getPlayers().get(game.getPlayers().indexOf(message.getPlayer()) + 1).getPlayerNumber());
                }
                game.remove(message.getPlayer());
            } else if (getChosenWorker() == -1) {
                if(message.getPlayer().getGodCard().isFeasibleMove(game.getBoard().getCell(message.getXPosition(), message.getYPosition()), message.getPlayer().getSingleWorker(message.getWorkerNumber()))) {
                    setChosenWorker(message.getWorkerNumber());
                    game.performMove(message.getPlayer(), message.getWorkerNumber(), message.getXPosition(), message.getYPosition());
                } else {
                    message.getView().reportError("Error! This move is not feasible!");
                }
            } else if (message.getWorkerNumber() != getChosenWorker()) {
                message.getView().reportError("Error! You chose the incorrect worker!");
            } else {
                if(message.getPlayer().getGodCard().isFeasibleMove(game.getBoard().getCell(message.getXPosition(), message.getYPosition()), message.getPlayer().getSingleWorker(message.getWorkerNumber()))) {
                    game.performMove(message.getPlayer(), message.getWorkerNumber(), message.getXPosition(), message.getYPosition());
                } else {
                    message.getView().reportError("Error! This move is not feasible!");
                }
            }
            //System.out.println("arrivato a fine handleMove");
        }
        else {
            message.getView().reportError("Error! It's not your turn!");
        }
    }

    /**
     * This class manage a new build by a player
     * @param message
     * it reads from message who is the player and all his attributes.
     * @throws IOException
     * throws a new IOExecption when an I/O exception of some sort has occurred.
     */
    public synchronized void handleBuild(BuildRequest message) throws IOException {
        if(playerTurn==message.getPlayer().getPlayerNumber()) {
            if (loseControl(message.getPlayer()) == 0) {
                setChosenWorker(-1);
                game.remove(message.getPlayer());
                if(game.getPlayers().indexOf(game.getSinglePlayer(playerTurn)) == game.getPlayers().size() - 1) {
                    setPlayerTurn(game.getPlayers().get(0).getPlayerNumber());
                }
                else {
                    setPlayerTurn(game.getPlayers().get(game.getPlayers().indexOf(message.getPlayer()) + 1).getPlayerNumber());
                }
            } else if (getChosenWorker() == -1) {
                if(message.getPlayer().getGodCard().isFeasibleBuild(game.getBoard().getCell(message.getXPosition(), message.getYPosition()), message.getPlayer().getSingleWorker(message.getWorkerNumber()), message.getLevel())) {
                    setChosenWorker(message.getWorkerNumber());
                    game.performBuild(message.getPlayer(), message.getWorkerNumber(), message.getXPosition(), message.getYPosition(), message.getLevel());
                } else {
                    message.getView().reportError("Error! This build is not feasible!");
                }
            } else if (getChosenWorker() != message.getWorkerNumber()) {
                message.getView().reportError("Error! You chose the incorrect worker!");
            } else {
                if(message.getPlayer().getGodCard().isFeasibleBuild(game.getBoard().getCell(message.getXPosition(), message.getYPosition()), message.getPlayer().getSingleWorker(message.getWorkerNumber()), message.getLevel())) {
                    game.performBuild(message.getPlayer(), message.getWorkerNumber(), message.getXPosition(), message.getYPosition(), message.getLevel());
                } else {
                    message.getView().reportError("Error! This build is not feasible!");
                }
            }
        }
        else {
            message.getView().reportError("Error! It's not your turn!");
        }
    }

    /**
     * This class manage the end of a turn.
     * @param message
     * it contains the player that wants to end his own turn.
     */
    public synchronized void manageTurn(EndTurnRequest message) {
        setChosenWorker(-1);
        game.manageEndTurn(message.getPlayer());
        if(game.getPlayers().indexOf(game.getSinglePlayer(playerTurn)) == game.getPlayers().size() - 1) {
            setPlayerTurn(game.getPlayers().get(0).getPlayerNumber());
        }
        else {
            setPlayerTurn(game.getPlayers().get(game.getPlayers().indexOf(message.getPlayer()) + 1).getPlayerNumber());
        }
    }

    public void updateMoveRequest(MoveRequest message) throws IOException {
        System.out.println("New MoveRequest from " + message.getPlayer().getName());
        handleMove(message);
    }

    public void updateBuildRequest(BuildRequest message) throws IOException {
        System.out.println("New BuildRequest from " + message.getPlayer().getName());
        handleBuild(message);
    }

    public void updateEndTurnRequest(EndTurnRequest message) {
        System.out.println("New EndTurnRequest from " + message.getPlayer().getName());
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

    /**
     * It check if the player can't move or build with his workers at the beginning of his turn
     * @param player
     * the player that we want to check
     * @return
     * returns an int, that is the number of available moves and buildings
     */
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
