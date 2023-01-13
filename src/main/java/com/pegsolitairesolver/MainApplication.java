package com.pegsolitairesolver;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Peg Solitaire Solver");
        stage.setWidth(800);
        stage.setHeight(800);
        stage.setResizable(false);

        Group root = new Group();
        Scene scene = new Scene(root);


        root.getChildren().addAll();

        PegBoard pegBoard = new PegBoard();

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}