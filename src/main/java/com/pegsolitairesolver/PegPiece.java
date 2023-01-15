package com.pegsolitairesolver;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PegPiece extends Circle {

    private int row;
    private int col;
    public PegPiece(int row, int col) {
        this.row = row;
        this.col = col;
        this.setFill(Color.BLACK);
        this.setRadius(15);
        this.setCenterX(155+35*row);
        this.setCenterY(70+35*col);
    }
}
