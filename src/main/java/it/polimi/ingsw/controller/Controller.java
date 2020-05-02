package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.Cell;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Level;
import it.polimi.ingsw.observer.Observer;
import it.polimi.ingsw.observer.messages.*;

import java.util.ArrayList;
import java.util.Observable;

public class Controller implements Observer {

    private final Game game;

    public Controller(Game game) {
        this.game = game;
    }

    // crea un ArrayList che identifica le celle su cui un worker può fare una move
    public ArrayList<Cell> availableMoveCells(int playerNumber, int workerNumber, Board board){
        ArrayList<Cell> availableMoveCells = new ArrayList<Cell>();
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(game.getSinglePlayer(playerNumber).getGodCard().isFeasibleMove(board.getCell(i,j),game.getSinglePlayer(playerNumber).getSingleWorker(workerNumber))){
                    availableMoveCells.add(new Cell(i,j));
                }
            }
        }
        return availableMoveCells;
    }

    // crea un ArrayList che identifica le celle su cui un worker può fare una build
    public ArrayList<Cell> availableBuildCells(int playerNumber, int workerNumber, Board board){
        ArrayList<Cell> availableBuildCells = new ArrayList<Cell>();
        Level level;
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                for(Level l: Level.values()) {
                    if (game.getSinglePlayer(playerNumber).getGodCard().isFeasibleBuild(board.getCell(i, j), game.getSinglePlayer(playerNumber).getSingleWorker(workerNumber),l)) {
                        availableBuildCells.add(new Cell(i, j));
                    }
                }
            }
        }
        return availableBuildCells;
    }

    public void updateMoveRequest(MoveRequest message) {
        //QUA FA QUALCOSA
    }

    public void updateBuildRequest(BuildRequest message) {
        //QUA FA QUALCOSA
    }

    public void updateEndTurnRequest(EndTurnRequest message) {
        //QUA FA QUALCOSA
    }
}
