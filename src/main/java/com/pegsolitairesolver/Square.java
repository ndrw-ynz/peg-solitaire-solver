package com.pegsolitairesolver;

import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Square extends StackPane {

    private final int row;
    private final int col;
    private boolean occupied;
    private final boolean invalid;
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

    private void setupValidSquare() {
        this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }

    public String getName() {
        return ""+row+col;
    }

    public int getRow() {return row;}

    public int getCol() {return col;}

    public boolean isOccupied() {
        return occupied;
    }

    public boolean isInvalid() {
        return invalid;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
