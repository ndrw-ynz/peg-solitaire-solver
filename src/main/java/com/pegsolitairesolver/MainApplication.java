package com.pegsolitairesolver;

import javafx.application.Application;
import javafx.event.EventTarget;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainApplication extends Application {

    private PegBoard pegBoard;
    private PegPiece currentPegPiece;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Peg Solitaire Solver");
        stage.setWidth(800);
        stage.setHeight(800);
        stage.setResizable(false);

        Group rootNode = new Group();
        Scene scene = new Scene(rootNode);

        // Text
        Text text = new Text(10, 10, "Peg Solitaire Solver");

        // PegBoard
        pegBoard = new PegBoard();
        Square[][] squares = pegBoard.getBoard();
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 7; col++) {
                pegBoard.getChildren().add(squares[row][col]);
            }
        }
        setupPegBoardEventHandler(pegBoard);

        rootNode.getChildren().addAll(text, pegBoard);

        stage.setScene(scene);
        stage.show();
    }

    private void setupPegBoardEventHandler(PegBoard pegBoard) {
        pegBoard.setOnMouseClicked(event -> {
            EventTarget target = event.getTarget();
            if (target instanceof Square square) {
                if (square.isOccupied()) {
                    PegPiece pegPiece = (PegPiece) square.getChildren().get(0);
                    if (currentPegPiece != null) {
                        deselectPegPiece();
                    }
                    currentPegPiece = pegPiece;
                    selectPegPiece();
                }
                else {
                    if (currentPegPiece == null) return;
                    if (currentPegPiece.getValidMoves().contains(square.getName())) movePegPiece(square);
                }
            } else {
                PegPiece pegPiece = (PegPiece) target;
                Square square = (Square) pegPiece.getParent();
                if (currentPegPiece == null){
                    currentPegPiece = pegPiece;
                    selectPegPiece();
                } else {
                    if(currentPegPiece.getValidMoves().contains(square.getName())){
                        movePegPiece(square);
                    }
                    else{
                        deselectPegPiece();
                        currentPegPiece = pegPiece;
                        selectPegPiece();
                    }
                }
            }
        });
    }

    private void selectPegPiece() {
        DropShadow borderGlow = new DropShadow();
        borderGlow.setColor(Color.BLACK);
        borderGlow.setOffsetX(0f);
        borderGlow.setOffsetY(0f);
        currentPegPiece.setEffect(borderGlow);
        pegBoard.getPegPieceValidMoves(currentPegPiece);
        pegBoard.showValidMoves(true, currentPegPiece);
    }

    private void deselectPegPiece() {
        currentPegPiece.setEffect(null);
        pegBoard.showValidMoves(false, currentPegPiece);
        currentPegPiece = null;
    }

    private void movePegPiece(Square destinationSquare){
        System.out.println("MOVE!");
        Square initialSquare = (Square) currentPegPiece.getParent();
        Square killSquare = pegBoard.getBoard()[(currentPegPiece.getRow() + destinationSquare.getRow())/2][(currentPegPiece.getCol() + destinationSquare.getCol())/2];

        killSquare.getChildren().remove(0);
        killSquare.setOccupied(false);

        destinationSquare.getChildren().add(new PegPiece(destinationSquare.getRow(), destinationSquare.getCol()));
        destinationSquare.setOccupied(true);

        initialSquare.getChildren().remove(0);
        initialSquare.setOccupied(false);
        deselectPegPiece();
    }

    public static void main(String[] args) {
        launch();
    }
}