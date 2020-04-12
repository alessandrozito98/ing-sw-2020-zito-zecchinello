package it.polimi.ingsw.model;

import java.util.ArrayList;

public class Board {

    private Cell[][] board = new Cell[5][5];


    public Cell getCell(int row, int column) {
        if(row >= 0 && column >= 0 && row <= 5 && column <= 5){
            return board[row][column];
        } else {
            throw new RuntimeException();
        }
    }


    // returns all the squares a player can move to, given his coordinates
    public ArrayList<Cell> checkMovement(Worker worker, ArrayList<Cell> cells){

        Cell workerCell = worker.getPosition();




        return cells;
    }




}