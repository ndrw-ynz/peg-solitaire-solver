package com.pegsolitairesolver;

import javafx.application.Application;
import javafx.event.EventTarget;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The class MainApplication contains the scenes and nodes, and the logic
 * used in the main GUI application.
 */
public class MainApplication extends Application {
    /**The root node of the main scene of the application.*/
    private Group rootNode;
    /**The current peg board of the application.*/
    private PegBoard pegBoard;
    /**The current active peg piece of the application.*/
    private PegPiece currentPegPiece;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Peg Solitaire Solver");
        stage.setWidth(800);
        stage.setHeight(800);
        stage.setResizable(false);

        rootNode = new Group();
        Scene scene = new Scene(rootNode);

        // Text
        Text text = new Text(10, 10, "Peg Solitaire Solver");
        rootNode.getChildren().add(text);

        // PegBoard
        setupPegBoard();

        // Button Reset
        Button restartButton = new Button("Restart");
        restartButton.setLayoutX(800/2.0-25);
        restartButton.setLayoutY(600);
        restartButton.setOnAction(event -> {
            rootNode.getChildren().remove(pegBoard);
            setupPegBoard();
        });
        rootNode.getChildren().add(restartButton);

        // Button Solve
        Button solveButton = new Button("Solve");
        solveButton.setLayoutX(800/2.0-20);
        solveButton.setLayoutY(640);
        solveButton.setOnAction(event -> {
            Solver solver = new Solver(this.pegBoard);
            boolean solved = solver.solve();
            System.out.println("Done!");
            if (solved) {
                System.out.println("Solved!");
            } else {
                System.out.println("Unsolvable!");
                solver.printBoard();
                solver.printOutput();
            }
        });
        rootNode.getChildren().add(solveButton);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sets up the peg board of the GUI application.
     */
    private void setupPegBoard() {
        pegBoard = new PegBoard();
        currentPegPiece = null;
        Square[][] squares = pegBoard.getBoard();
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 7; col++) {
                pegBoard.getChildren().add(squares[row][col]);
            }
        }
        setupPegBoardEventHandler(pegBoard);
        rootNode.getChildren().add(pegBoard);
    }

    /**
     * Sets up the event handlers of the application.
     * @param pegBoard The current peg board of the application.
     */
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

    /**
     * Applies effects on the Nodes displayed on the current Scene of the GUI application
     * as a peg piece is selected.
     */
    private void selectPegPiece() {
        DropShadow borderGlow = new DropShadow();
        borderGlow.setColor(Color.BLACK);
        borderGlow.setOffsetX(0f);
        borderGlow.setOffsetY(0f);
        currentPegPiece.setEffect(borderGlow);
        pegBoard.getPegPieceValidMoves(currentPegPiece);
        pegBoard.showValidMoves(true, currentPegPiece);
    }

    /**
     * Removes effects on the Node displayed on the current Scene of the GUI application
     * as a peg piece is deselected.
     */
    private void deselectPegPiece() {
        currentPegPiece.setEffect(null);
        pegBoard.showValidMoves(false, currentPegPiece);
        currentPegPiece = null;
    }

    /**
     * Moves a given peg piece on a given destination.
     * @param destinationSquare The Square destination of the peg piece.
     */
    private void movePegPiece(Square destinationSquare){
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