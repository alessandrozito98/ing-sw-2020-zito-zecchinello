package it.polimi.ingsw.model;

public class Board implements Cloneable {

    private Cell[][] board = new Cell[5][5];


    @Override
    protected final Board clone() {
        final Board result = new Board();
        for (int i = 0; i < 3; i++) {
            result.board[i] = board[i].clone();
        }
        return result;
    }


    public Cell getCell(int row, int column) {
        if(row >= 0 && column >= 0 && row <= 5 && column <= 5){
            return board[row][column];
        } else {
            throw new RuntimeException();
        }
    }



}