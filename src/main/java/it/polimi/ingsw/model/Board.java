package it.polimi.ingsw.model;

import java.io.Serializable;

import static it.polimi.ingsw.utils.Colors.*;

/**
 * Represent the board, where the player can play.
 */
public class Board implements Cloneable, Serializable {

    private final Cell[][] board = new Cell[5][5];

    public Board() {
        reset();
    }


    /**
     * Getting a boardCopy of the original board.
     * @return
     * A copy of the board
     */
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

    /**
     * Removes all the workers and buildings from the board.
     */
    public void reset() {
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                board[i][j] = new Cell(i, j);
            }
        }
    }

    /**
     * Prints the board (used for CLI).
     */
    public void printBoard() {
        System.out.println("      0        1        2        3        4");
        for(int i = 0; i < 5; i++) {
            System.out.println("  "+ ANSI_BG_WHITE + "                                              " + ANSI_RESET);
            for(int j = 0; j < 3; j++) {
                if(j == 1) {
                    System.out.print(i + " "+ ANSI_BG_WHITE +" "+ANSI_RESET);
                }
                else {
                    System.out.print("  "+ ANSI_BG_WHITE +" "+ANSI_RESET);
                }
                for(int k = 0; k < 5; k++) {
                    if(j == 1) {
                        System.out.print(this.board[k][i].getLevel().label + "    ");
                        if (this.board[k][i].getWorker() == null) {
                            System.out.print("    " + ANSI_RESET + ANSI_BG_WHITE + " " + ANSI_RESET);
                        } else {
                            System.out.print(ANSI_RESET + this.board[k][i].getWorker().getColor().label + ANSI_WHITE + this.board[k][i].getWorker().getWorkerNumber() + this.board[k][i].getLevel().label + "   " + ANSI_RESET + ANSI_BG_WHITE + " " + ANSI_RESET);
                        }
                    } else {
                        System.out.print(this.board[k][i].getLevel().label + "        " + ANSI_RESET + ANSI_BG_WHITE + " " + ANSI_RESET);
                    }
                }
                System.out.println();
            }
        }
        System.out.println("  " + ANSI_BG_WHITE + "                                              " + ANSI_RESET);
    }
}