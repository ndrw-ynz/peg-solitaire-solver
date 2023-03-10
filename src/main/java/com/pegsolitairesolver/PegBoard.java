package com.pegsolitairesolver;

import javafx.scene.Group;
import javafx.scene.effect.Glow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * PegBoard is a class containing all the state and behavior of the
 * peg board of the GUI application.
 */
public class PegBoard extends Group {

    /**The peg board containing peg pieces.*/
    private final Square[][] board;

    /**
     * Initializes a new peg board on a starting state
     */
    public PegBoard() {
        this.board = new Square[7][7];
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 7; col++) {
                boolean invalid = ((row <=1) && (col<=1)) || ((row >= 5) && (col <= 1)) ||((row <= 1) && (col >= 5)) || ((row >= 5) && (col >= 5));
                boolean occupied = !(row == 3 && col == 3) && !invalid;
                board[row][col] = new Square(row, col, occupied, invalid);
            }
        }
    }

    /**
     * Fetchess the current state of the peg board.
     * @return Returns a 2D square array.
     */
    public Square[][] getBoard() {
        return board;
    }

    /**
     * Determines if a given move is valid based on its source and target row and col.
     * @param sourceRow The source row of the move.
     * @param sourceCol The source col of the move.
     * @param targetRow The target row of the move.
     * @param targetCol The target col of the move.
     * @return Returns a boolean value determining if the move is valid.
     */
    private boolean isValidMove(int sourceRow, int sourceCol, int targetRow, int targetCol) {
        return sourceRow >= 0 && sourceRow < board.length
                && sourceCol >= 0 && sourceCol < board[sourceRow].length
                && targetRow >= 0 && targetRow < board.length
                && targetCol >= 0 && targetCol < board[targetRow].length
                && !board[targetRow][targetCol].isInvalid()
                && !board[targetRow][targetCol].isOccupied()
                && board[(sourceRow + targetRow)/2][(sourceCol + targetCol)/2].isOccupied()
                && board[sourceRow][sourceCol].isOccupied();
    }

    /**
     * Determines the current valid moves of a given peg piece.
     * @param pegPiece The current peg piece.
     */
    public void getPegPieceValidMoves(PegPiece pegPiece) {
        int row = pegPiece.getRow();
        int col = pegPiece.getCol();
        pegPiece.resetValidMoves();
        if (isValidMove(row, col, row-2, col)) pegPiece.addValidMove(""+(row-2)+col);
        if (isValidMove(row, col, row+2, col)) pegPiece.addValidMove(""+(row+2)+col);
        if (isValidMove(row, col, row, col-2)) pegPiece.addValidMove(""+row+(col-2));
        if (isValidMove(row, col, row, col+2)) pegPiece.addValidMove(""+row+(col+2));
    }

    /**
     * Applies effects on Squares based from the valid moves of the current peg piece.
     * @param show A boolean value determining if special effects are applied.
     * @param currentPegPiece The current peg piece.
     */
    public void showValidMoves(boolean show, PegPiece currentPegPiece) {
        if (show) {
            Glow glow = new Glow();
            glow.setLevel(0.7);
            for(String move : currentPegPiece.getValidMoves()){
                int row = Character.getNumericValue(move.charAt(0));
                int col = Character.getNumericValue(move.charAt(1));
                Square square = board[row][col];
                square.setEffect(glow);
                square.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1.2))));
            }
        } else {
            for(String move : currentPegPiece.getValidMoves()){
                int row = Character.getNumericValue(move.charAt(0));
                int col = Character.getNumericValue(move.charAt(1));
                Square square = board[row][col];
                square.setEffect(null);
                square.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
        }
    }
}
