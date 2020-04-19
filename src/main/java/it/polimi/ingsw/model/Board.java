package it.polimi.ingsw.model;

import java.util.ArrayList;

public class Board {


    private final Cell[][] board = new Cell[5][5];

    public Cell[][] getBoard() {
        return board;
    }


    public Cell getCell(int row, int column) {
        if(row >= 0 && column >= 0 && row <= 5 && column <= 5){
            return board[row][column];
        } else {
            throw new RuntimeException();
        }
    }






}