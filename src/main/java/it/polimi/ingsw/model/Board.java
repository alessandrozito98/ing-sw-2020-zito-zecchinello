package it.polimi.ingsw.model;

public class Board {

    private Cell[][] board = new Cell[5][5];

    public Cell[][] getBoard() {
        return board;
    }

    public Cell getCell(int x, int y) {
        return board[x][y];
    }
}