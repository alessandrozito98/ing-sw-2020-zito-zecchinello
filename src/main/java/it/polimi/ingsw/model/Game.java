package it.polimi.ingsw.model;


import it.polimi.ingsw.model.God.God;
import it.polimi.ingsw.observer.Observable;
import it.polimi.ingsw.observer.messages.BoardChange;
import it.polimi.ingsw.observer.messages.ResetTurn;

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
    public void performMove(Player player, int workerNumber, int xPosition, int yPosition) {
        // creo questo attributo per avere un codice più leggibile nelle 2 righe successive
        God godCard = player.getGodCard();
        Level oldLevel = player.getSingleWorker(workerNumber).getPosition().getLevel();
        godCard.move(this.board.getCell(xPosition,yPosition),player.getSingleWorker(workerNumber));
        notifyBoardChange(new BoardChange(getBoardCopy(),godCard.getAvailableMoveNumber(),godCard.getAvailableBuildNumber(),godCard.getHasMoved(),godCard.getHasBuilt(), player));
        if(godCard.winControl(oldLevel,player.getSingleWorker(workerNumber).getPosition().getLevel())) {
            //notifyWin();  TO DO
        }
    }

    //esegue la costruzione di un worker e notifica le view del cambiamento
    public void performBuild(Player player, int workerNumber, int xPosition, int yPosition, Level level) {
        // creo questo attributo per avere un codice più leggibile nelle 2 righe successive
        God godCard = player.getGodCard();
        godCard.build(this.board.getCell(xPosition, yPosition),player.getSingleWorker(workerNumber), level);
        notifyBoardChange(new BoardChange(getBoardCopy(),godCard.getAvailableMoveNumber(),godCard.getAvailableBuildNumber(),godCard.getHasMoved(),godCard.getHasBuilt(), player));
    }

    public void manageEndTurn(Player player) {
        // creo questo attributo per avere un codice più leggibile nelle 2 righe successive
        God godCard = player.getGodCard();
        godCard.resetTurn();
        Player loseControlPlayer;
        Player nextPlayer;
        //quello che fa il codice da questa riga fino alla notifyEndTurn serve per controllare la possibile
        //sconfitta del prossimo giocatore
        if(players.indexOf(player)==players.size()-1){
            loseControlPlayer=players.get(0);
        }
        else{
            loseControlPlayer=players.get(players.indexOf(player)+1);
        }
        int actionCounter=0;
        for(int i=1; i<3; i++){
            if(loseControlPlayer.getGodCard().getAvailableBuildNumber()!=0){
                if(!checkBuilding(loseControlPlayer.getPlayerNumber(),i,this.board).isEmpty()){ actionCounter++;}
            }
            if(!checkMovement(loseControlPlayer.getPlayerNumber(),i,this.board).isEmpty()){ actionCounter++;}
        }
        if(actionCounter==0){
            remove(loseControlPlayer);
            if(players.indexOf(player)==players.size()-1){
                nextPlayer=players.get(0);
            }
            else{
                nextPlayer=players.get(players.indexOf(player)+1);
            }
        }else{
            nextPlayer=loseControlPlayer;
            loseControlPlayer=null;
        }
        notifyResetTurn(new ResetTurn(godCard.getAvailableMoveNumber(),godCard.getAvailableBuildNumber(),godCard.getHasMoved(),godCard.getHasBuilt(),player,nextPlayer,loseControlPlayer,getBoardCopy()));
    }

    public void managePlayerLose(Player player) {

    }

    public void remove(Player player){

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
