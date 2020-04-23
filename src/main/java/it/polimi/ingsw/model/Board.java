package it.polimi.ingsw.model;

import java.util.ArrayList;

public class Board implements Cloneable{


    private final Cell[][] board = new Cell[5][5];

    public Board() {
        reset();
    }


    @Override
    protected final Board clone() {
        final Board result = new Board();
        for(int i = 0; i < 5; i++){
            result.board[i] = board[i].clone();
        }
        return result;
    }


    public Cell getCell(int row, int column) throws RuntimeException {
        if(row >= 0 && column >= 0 && row <= 5 && column <= 5){
            return board[row][column];
        } else {
            throw new RuntimeException();
        }
    }


    public void reset(){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                board[i][j] = new Cell(i, j);
            }
        }
    }


}