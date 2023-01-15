package com.pegsolitairesolver;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * PegBoard is a class containing all the state and behavior of the
 * peg board of the GUI application.
 */
public class PegBoard {

    /**The peg board containing peg pieces.*/
    private final Square[][] board;

    public PegBoard() {
        this.board = new Square[7][7];
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 7; col ++) {
                boolean invalid = ((row <=1) && (col<=1)) || ((row >= 5) && (col <= 1)) ||((row <= 1) && (col >= 5)) || ((row >= 5) && (col >= 5));
                boolean occupied = !(row == 3 && col == 3) && !invalid;
                board[row][col] = new Square(row, col, occupied, invalid);
            }
        }
    }

    public Square[][] getBoard() {
        return board;
    }


    private boolean isValidMove(int sourceRow, int sourceCol, int targetRow, int targetCol) {
        return sourceRow >= 0 && sourceRow < board.length
                && sourceCol >= 0 && sourceCol < board[sourceRow].length
                && targetRow >= 0 && targetRow < board.length
                && targetCol >= 0 && targetCol < board[targetRow].length
                && !board[targetRow][targetCol].isOccupied()
                && board[(sourceRow + targetRow)/2][(sourceCol + targetCol)/2].isOccupied()
                && board[sourceRow][sourceCol].isOccupied();
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
            board[targetRow][targetCol].setOccupied(true);
            board[(sourceRow + targetRow)/2][(sourceCol + targetCol)/2].setOccupied(false);
            board[sourceRow][sourceCol].setOccupied(false);
        }
    }


    public void isGameOver() {

    }

    public void displayBoard() {

    }
}
