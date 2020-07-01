package it.polimi.ingsw.model;


import it.polimi.ingsw.model.God.God;
import it.polimi.ingsw.observer.Observable;
import it.polimi.ingsw.observer.messages.BoardChange;
import it.polimi.ingsw.observer.messages.PlayerLose;
import it.polimi.ingsw.observer.messages.ResetTurn;
import it.polimi.ingsw.observer.messages.Win;

import java.io.IOException;
import java.util.ArrayList;

public class Game extends Observable {

    private final Board board;
    private final ArrayList<Player> players;

    public Game(Board board){
        this.board = board;
        this.players= new ArrayList<Player>();
    }


    public Board getBoardCopy() {
        return board.clone();
    }

    public Board getBoard() { return this.board; }

    public ArrayList<Player> getPlayers() {
        return players;
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

    /**
     * Performs, given the player, the worker and the new cell, the move of that worker to the new cell, and notifies the view of the change.
     * @param player
     * Player that wants to move his worker
     * @param workerNumber
     * Number of the Worker that the player wants to move
     * @param xPosition
     * Coordinates 'X' of the cell
     * @param yPosition
     * Coordinates 'Y' of the cell
     * @throws IOException
     * throws a new IOExecption when an I/O exception of some sort has occurred.
     */

    public void performMove(Player player, int workerNumber, int xPosition, int yPosition) throws IOException {
        //System.out.println("arrivato a inizio performMove");
        God godCard = player.getGodCard();
        Level oldLevel = player.getSingleWorker(workerNumber).getPosition().getLevel();
        godCard.move(this.board.getCell(xPosition, yPosition), player.getSingleWorker(workerNumber));
        notifyBoardChange(new BoardChange(getBoardCopy(), godCard.getAvailableMoveNumber(), godCard.getAvailableBuildNumber(), godCard.getHasMoved(), godCard.getHasBuilt(), player));
        //System.out.println("arrivato a fine performMove");
        if(godCard.winControl(oldLevel, player.getSingleWorker(workerNumber).getPosition().getLevel())) {
            notifyWin(new Win(player));
        }
    }

    /**
     * Performs, given the player, the worker, the new cell and the new level, the build action of that worker, and notifies the view of the change.
     * @param player
     * Player that wants to move his worker.
     * @param workerNumber
     * Number of the Worker that the player wants to move.
     * @param xPosition
     * Coordinates 'X' of the cell.
     * @param yPosition
     * Coordinates 'Y' of the cell.
     * @param level
     * Level that the player wants to build.
     * @throws IOException
     * throws a new IOExecption when an I/O exception of some sort has occurred.
     */
    public void performBuild(Player player, int workerNumber, int xPosition, int yPosition, Level level) throws IOException {
        God godCard = player.getGodCard();
        godCard.build(this.board.getCell(xPosition, yPosition),player.getSingleWorker(workerNumber), level);
        notifyBoardChange(new BoardChange(getBoardCopy(), godCard.getAvailableMoveNumber(), godCard.getAvailableBuildNumber(), godCard.getHasMoved(), godCard.getHasBuilt(), player));
    }

    /**
     * Given the player, it manages the end of the turn, and notifies the view with resetting all the attributes.
     * @param player
     * Player that ends the turn.
     */
    public void manageEndTurn(Player player) {
        God godCard = player.getGodCard();
        godCard.resetTurn();
        Player nextPlayer;
        if(players.indexOf(player) == players.size() - 1) {
            nextPlayer=players.get(0);
        }
        else {
            nextPlayer=players.get(players.indexOf(player) + 1);
        }
        notifyResetTurn(new ResetTurn(godCard.getAvailableMoveNumber(), godCard.getAvailableBuildNumber(), godCard.getHasMoved(), godCard.getHasBuilt(), player, nextPlayer));
    }

    /**
     * Remove the player and his objects from the game.
     * @param player
     * The player that we want to remove-
     * @throws IOException
     * throws a new IOExecption when an I/O exception of some sort has occurred.
     */
    public void remove(Player player) throws IOException {
        //Removing the Worker
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if(board.getCell(i,j).getWorker() == player.getSingleWorker(1) || getBoard().getCell(i,j).getWorker() == player.getSingleWorker(2)) {
                    board.getCell(i,j).removeWorker();
                }
            }
        }
        //Removing the player
        Player nextPlayer;
        if(players.indexOf(player)==players.size()-1) {
            nextPlayer=players.get(0);
        }
        else {
            nextPlayer=players.get(players.indexOf(player)+1);
        }
        notifyPlayerLose(new PlayerLose(getBoardCopy(),player,nextPlayer));
        players.remove(player);
    }

    /**
     * Identifies the cells on which a worker can make a move.
     * @param playerNumber
     * The player that has to be checked.
     * @param workerNumber
     * the worker that has to be checked.
     * @param board
     * The board where they play.
     * @return
     * An arraylist with all the possible moves.
     */
    // crea un ArrayList che identifica le celle su cui un worker può fare una move
    public ArrayList<Cell> checkMovement(int playerNumber, int workerNumber, Board board) {
        ArrayList<Cell> availableMoveCells = new ArrayList<Cell>();
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(getSinglePlayer(playerNumber).getGodCard().isFeasibleMove(board.getCell(i, j), getSinglePlayer(playerNumber).getSingleWorker(workerNumber))) {
                    availableMoveCells.add(new Cell(i,j));
                }
            }
        }
        return availableMoveCells;
    }

    /**
     * Identifies the cells on which a worker can make a build.
     * @param playerNumber
     * The player that has to be checked.
     * @param workerNumber
     * the worker that has to be checked.
     * @param board
     * The board where they play.
     * @return
     * An arraylist with all the possible build.
     */
    // crea un ArrayList che identifica le celle su cui un worker può fare una build
    public ArrayList<Cell> checkBuilding(int playerNumber, int workerNumber, Board board) {
        ArrayList<Cell> availableBuildCells = new ArrayList<Cell>();
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                for(Level l: Level.values()) {
                    if (getSinglePlayer(playerNumber).getGodCard().isFeasibleBuild(board.getCell(i, j), getSinglePlayer(playerNumber).getSingleWorker(workerNumber), l)) {
                        Cell cell = new Cell(i, j);
                        cell.setLevel(l);
                        availableBuildCells.add(cell);
                    }
                }
            }
        }
        return availableBuildCells;
    }
}
