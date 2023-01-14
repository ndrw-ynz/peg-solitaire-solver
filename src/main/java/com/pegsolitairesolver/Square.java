package com.pegsolitairesolver;

import javafx.geometry.Point2D;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Square extends StackPane {

    private final Point2D coordinate;
    private boolean occupied;
    public Square(int x, int y) {
        this.coordinate = new Point2D(x, y);
        this.occupied = false;
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.setPrefSize(60, 60);
        this.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }

    public Point2D getCoordinate() {
        return coordinate;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
