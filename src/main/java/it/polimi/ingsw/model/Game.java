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
        return (ArrayList<Player>) players;
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

    public void removePlayers(Player player) {
        players.remove(player);
    }

    //esegue il movimento di un worker e notifica le view del cambiamento
    public void performMove(Player player, int workerNumber, int xPosition, int yPosition) throws IOException {
        System.out.println("arrivato a inizio performMove");
        // creo questo attributo per avere un codice più leggibile nelle 2 righe successive
        God godCard = player.getGodCard();
        Level oldLevel = player.getSingleWorker(workerNumber).getPosition().getLevel();
        godCard.move(this.board.getCell(xPosition,yPosition),player.getSingleWorker(workerNumber));
        notifyBoardChange(new BoardChange(getBoardCopy(),godCard.getAvailableMoveNumber(),godCard.getAvailableBuildNumber(),godCard.getHasMoved(),godCard.getHasBuilt(), player));
        System.out.println("arrivato a fine performMove");
        if(godCard.winControl(oldLevel,player.getSingleWorker(workerNumber).getPosition().getLevel())) {
            notifyWin(new Win(player));
        }
    }

    //esegue la costruzione di un worker e notifica le view del cambiamento
    public void performBuild(Player player, int workerNumber, int xPosition, int yPosition, Level level) throws IOException {
        // creo questo attributo per avere un codice più leggibile nelle 2 righe successive
        God godCard = player.getGodCard();
        godCard.build(this.board.getCell(xPosition, yPosition),player.getSingleWorker(workerNumber), level);
        notifyBoardChange(new BoardChange(getBoardCopy(),godCard.getAvailableMoveNumber(),godCard.getAvailableBuildNumber(),godCard.getHasMoved(),godCard.getHasBuilt(), player));
    }

    public void manageEndTurn(Player player) {
        // creo questo attributo per avere un codice più leggibile nelle 2 righe successive
        God godCard = player.getGodCard();
        godCard.resetTurn();
        Player nextPlayer;
        if(players.indexOf(player)==players.size()-1) {
            nextPlayer=players.get(0);
        }
        else {
            nextPlayer=players.get(players.indexOf(player)+1);
        }
        notifyResetTurn(new ResetTurn(godCard.getAvailableMoveNumber(),godCard.getAvailableBuildNumber(),godCard.getHasMoved(),godCard.getHasBuilt(),player, nextPlayer));
    }

    public void remove(Player player) throws IOException {
        //Removing the Worker
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if(board.getCell(i,j).getWorker() == player.getSingleWorker(1) || getBoard().getCell(i,j).getWorker() == player.getSingleWorker(2)) {
                    board.getCell(i,j).removeWorker();
                }
            }
        }
        //removing the player
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

    // crea un ArrayList che identifica le celle su cui un worker può fare una move
    public ArrayList<Cell> checkMovement(int playerNumber, int workerNumber, Board board) {
        ArrayList<Cell> availableMoveCells = new ArrayList<Cell>();
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(getSinglePlayer(playerNumber).getGodCard().isFeasibleMove(board.getCell(i,j),getSinglePlayer(playerNumber).getSingleWorker(workerNumber))){
                    availableMoveCells.add(new Cell(i,j));
                }
            }
        }
        return availableMoveCells;
    }

    // crea un ArrayList che identifica le celle su cui un worker può fare una build
    public ArrayList<Cell> checkBuilding(int playerNumber, int workerNumber, Board board) {
        ArrayList<Cell> availableBuildCells = new ArrayList<Cell>();
        Level level;
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                for(Level l: Level.values()) {
                    if (getSinglePlayer(playerNumber).getGodCard().isFeasibleBuild(board.getCell(i, j), getSinglePlayer(playerNumber).getSingleWorker(workerNumber),l)) {
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
