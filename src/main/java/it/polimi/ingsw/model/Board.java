package it.polimi.ingsw.model;

import java.io.Serializable;

public class Board implements Cloneable, Serializable {


    private final Cell[][] board = new Cell[5][5];

    public Board() {
        reset();
    }


    @Override
    public final Board clone() {
        final Board result = new Board();
        for(int i = 0; i < 5; i++) {
            result.board[i] = board[i].clone();
        }
        return result;
    }


    public Cell getCell(int row, int column) throws RuntimeException {
        if(row >= 0 && column >= 0 && row <= 5 && column <= 5) {
            return board[row][column];
        } else {
            throw new RuntimeException();
        }
    }


    public void reset(){
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                board[i][j] = new Cell(i, j);
            }
        }
    }

    public void printBoard() {
        System.out.println("  0 1 2 3 4");
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                System.out.print(board[i][j] + "|");
            }
            System.out.println();
        }
    }
}