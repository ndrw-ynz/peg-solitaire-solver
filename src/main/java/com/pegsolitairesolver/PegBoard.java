package com.pegsolitairesolver;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * PegBoard is a class containing all the state and behavior of the
 * peg board of the GUI application.
 */
public class PegBoard{
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

    private final int[][] clearBoard = new int[][] {
            {0, 0, 2, 2, 2, 0, 0},
            {0, 0, 2, 2, 2, 0, 0},
            {2, 2, 2, 2, 2, 2, 2},
            {2, 2, 2, 1, 2, 2, 2},
            {2, 2, 2, 2, 2, 2, 2},
            {0, 0, 2, 2, 2, 0, 0},
            {0, 0, 2, 2, 2, 0, 0}
    };


    public PegBoard() {

    }

    private boolean isValidMove(int sourceRow, int sourceCol, int targetRow, int targetCol) {
        return sourceRow >= 0 && sourceRow < board.length
                && sourceCol >= 0 && sourceCol < board[sourceRow].length
                && targetRow >= 0 && targetRow < board.length
                && targetCol >= 0 && targetCol < board[targetRow].length
                && board[targetRow][targetCol] == EMPTY
                && board[(sourceRow + targetRow)/2][(sourceCol + targetCol)/2] == OCCUPIED
                && board[sourceRow][sourceCol] == OCCUPIED;
    }

    public ArrayList<ArrayList<Integer>> getValidMoves(int row, int col) {
        ArrayList<ArrayList<Integer>> validMoves = new ArrayList<>();
        if (isValidMove(row, col, row-2, col)) validMoves.add(new ArrayList<>(Arrays.asList(row-2, col)));
        if (isValidMove(row, col, row+2, col)) validMoves.add(new ArrayList<>(Arrays.asList(row+2, col)));
        if (isValidMove(row, col, row, col-2)) validMoves.add(new ArrayList<>(Arrays.asList(row, col-2)));
        if (isValidMove(row, col, row, col+2)) validMoves.add(new ArrayList<>(Arrays.asList(row, col+2)));
        return validMoves;
    }

    public void movePeg(int sourceRow, int sourceCol, int targetRow, int targetCol) {
        if (isValidMove(sourceRow, sourceCol, targetRow, targetCol)) {
            board[targetRow][targetCol] = OCCUPIED;
            board[(sourceRow + targetRow)/2][(sourceCol + targetCol)/2] = EMPTY;
            board[sourceRow][sourceCol] = EMPTY;
        }
    }

    public boolean isBoardCleared() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == clearBoard[row][col]) return false;
            }
        }
        return true;
    }

    public void isGameOver() {

    }

    public void displayBoard() {

    }
}
