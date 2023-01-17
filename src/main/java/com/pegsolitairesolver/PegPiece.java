package com.pegsolitairesolver;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class PegPiece extends Circle {
    private final int row;
    private final int col;
    private final ArrayList<String> validMoves = new ArrayList<>();
    public PegPiece(int row, int col) {
        this.row = row;
        this.col = col;
        this.setFill(Color.BLACK);
        this.setRadius(15);
        this.setCenterX(155+35*row);
        this.setCenterY(70+35*col);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public ArrayList<String> getValidMoves() {return validMoves;}

    public void addValidMove(String move) {
        this.validMoves.add(move);
    }

    public void resetValidMoves() {
        this.validMoves.clear();
    }
}
