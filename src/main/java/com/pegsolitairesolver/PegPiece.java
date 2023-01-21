package com.pegsolitairesolver;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

/**
 * The class PegPiece extends from the Class circle
 * and contains the state of a given peg piece of a
 * peg board.
 */
public class PegPiece extends Circle {
    /**The row value of the peg piece.*/
    private final int row;
    /**The col value of the peg piece.*/
    private final int col;
    /**An ArrayList containing the valid moves of a peg piece.*/
    private final ArrayList<String> validMoves = new ArrayList<>();

    /**
     * Initializes a peg piece with its row and col value.
     * @param row The row value of the peg piece.
     * @param col The col value of the peg piece.
     */
    public PegPiece(int row, int col) {
        this.row = row;
        this.col = col;
        this.setFill(Color.BLACK);
        this.setRadius(15);
        this.setCenterX(155+35*row);
        this.setCenterY(70+35*col);
    }

    /**
     * Fetches the current row value of the peg piece.
     * @return Returns the int row value of the peg piece.
     */
    public int getRow() {
        return row;
    }

    /**
     * Fetches the current col value of the peg piece.
     * @return Returns the int col value of the peg piece.
     */
    public int getCol() {
        return col;
    }

    /**
     * Fetches the ArrayList of String containing the valid moves of the peg piece.
     * @return Returns an ArrayList<String> containing the valid moves of a peg piece.
     */
    public ArrayList<String> getValidMoves() {return validMoves;}

    /**
     * Adds a valid move on the current valid moves of the peg piece.
     * @param move The String containing the move of the peg piece.
     */
    public void addValidMove(String move) {
        this.validMoves.add(move);
    }

    /**
     * Clears the valid moves of the peg piece.
     */
    public void resetValidMoves() {
        this.validMoves.clear();
    }
}
