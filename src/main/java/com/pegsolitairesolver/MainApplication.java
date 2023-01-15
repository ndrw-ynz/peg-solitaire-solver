package com.pegsolitairesolver;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainApplication extends Application {

    private PegBoard pegBoard;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Peg Solitaire Solver");
        stage.setWidth(800);
        stage.setHeight(800);
        stage.setResizable(false);

        Group root = new Group();
        Scene scene = new Scene(root);

        Text text = new Text(10, 10, "Peg Solitaire Solver");
        root.getChildren().add(text);

        this.pegBoard = new PegBoard();
        Square[][] squares = pegBoard.getBoard();
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 7; col++) {
                root.getChildren().add(squares[row][col]);
            }
        }
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}