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
        if (!invalid) this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }

    public PegPiece getPegPiece() {
        return (PegPiece) this.getChildren().get(0);
    }

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
