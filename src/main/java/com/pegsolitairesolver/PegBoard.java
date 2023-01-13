package com.pegsolitairesolver;

import javafx.scene.canvas.Canvas;

/**
 * PegBoard is a class containing all the state and behavior of the
 * peg board of the GUI application.
 */
public class PegBoard extends Canvas {
    /**The integer value of an invalid piece.*/
    private static final int INVALID = 0;
    /**The integer value of an occupied piece.*/
    private static final int OCCUPIED = 1;
    /**The integer value of an empty piece.*/
    private static final int EMPTY = 2;
    /**The peg board containing peg pieces.*/
    private final int[][] board = new int[][] {
            {0, 0, 1, 1, 1, 0, 0},
            {0, 0, 1, 1, 1, 0, 0},
            {1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 2, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1},
            {0, 0, 1, 1, 1, 0, 0},
            {0, 0, 1, 1, 1, 0, 0}
    };

    public void putPeg(int row, int col) {
        this.board[row][col] =  OCCUPIED;
    }

    public void setClear(int row, int col) {
        this.board[row][col] = EMPTY;
    }

    public boolean isValidMove(int sourceRow, int sourceCol, int targetRow, int targetCol) {
        return false;
    }


}
