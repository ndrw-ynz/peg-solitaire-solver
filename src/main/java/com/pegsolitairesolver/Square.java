package com.pegsolitairesolver;

import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * The class Square extends the class Stack Pane, and
 * contains the information of a given square on the
 * current peg board of the GUI application.
 */
public class Square extends StackPane {
    /**The row value of the square.*/
    private final int row;
    /**The col value of the square.*/
    private final int col;
    /**A boolean value determining if the square is occupied by a peg piece.*/
    private boolean occupied;
    /**A boolean value determining if the square is an invalid part of the peg board.*/
    private final boolean invalid;

    /**
     * Initializes a Square of the peg board.
     * @param row       The row of the square.
     * @param col       The col of the square.
     * @param occupied  The boolean value determining if the square is occupied by a peg piece.
     * @param invalid   The boolean value determining if the square is an invalid part of the peg board.
     */
    public Square(int row, int col, boolean occupied, boolean invalid) {
        this.row = row;
        this.col = col;
        this.occupied = occupied;
        this.invalid = invalid;

        if (occupied) {
            this.getChildren().add(new PegPiece(row, col));
            this.setAlignment(Pos.CENTER);
        }

        this.setLayoutX(155+70*row);
        this.setLayoutY(70+70*col);
        this.setPrefSize(70, 70);

        if (!invalid) setupValidSquare();
    }

    /**
     * Sets up the borders of the valid squares.
     */
    private void setupValidSquare() {
        this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }

    /**
     * Fetches the name of the square.
     * @return Returns a String containing the name of the square.
     */
    public String getName() {
        return ""+row+col;
    }

    /**
     * Fetches the row value of the square.
     * @return Returns the int row value of the square.
     */
    public int getRow() {return row;}

    /**
     * Fetches the col value of the square.
     * @return Returns the int col value of the square.
     */
    public int getCol() {return col;}

    /**
     * Determines whether the square is occupied by a peg piece.
     * @return Returns a boolean value determining if the square is occupied by a peg piece.
     */
    public boolean isOccupied() {
        return occupied;
    }

    /**
     * Determines whether the square is an invalid part of the peg board.
     * @return Returns a boolean value determining if the square is an invalid part of the peg board.
     */
    public boolean isInvalid() {
        return invalid;
    }

    /**
     * Sets the occupied state of the square.
     * @param occupied The boolean value setting the occupied state of the square.
     */
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
