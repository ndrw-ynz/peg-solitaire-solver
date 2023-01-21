package com.pegsolitairesolver;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The class Solver solves a given peg solitaire board using
 * the back-tracking algorithm.
 */
public class Solver {
    /**The board of the current peg solitaire game.*/
    private final int[][] board;
    /**Stores boards that doesn't solve the game. For memoization.*/
    private final ArrayList<int[][]> failBoard;
    /**The list of moves conducted in solving the current peg solitaire board.*/
    private final ArrayList<Move> movesList;

    /**
     * The class Move stores the relevant Coordinates of a peg piece.
     */
    public static class Move implements Comparable<Move> {
        /**The starting coordinate of the peg piece.*/
        Coordinate from;
        /**The coordinate of the jumped peg piece.*/
        Coordinate jump;
        /**The coordinate for the destination of the peg piece.*/
        Coordinate to;

        /**
         * The class Coordinate stores the row and col value of a peg piece.
         */
        public static class Coordinate {
            /**The row value of the coordinate.*/
            int row;
            /**The col value of the coordinate.*/
            int col;

            /**
             * Initializes a Coordinate storing the row and col of a peg piece.
             * @param row The row value of the peg piece.
             * @param col The col value of the peg piece.
             */
            public Coordinate(int row, int col) {
                this.row = row;
                this.col = col;
            }

            /**
             * Fetches the value of a coordinate.
             * @return The int value of the coordinate.
             */
            public int getValue() {
                return row / 7 + col % 7;
            }
        }

        /**
         * Move initializes a given move of a peg piece.
         * @param rowFrom The row value of the starting coordinate of a peg piece.
         * @param colFrom The col value of the starting coordinate of a peg piece.
         * @param rowJump The row value of the jumped peg piece.
         * @param colJump The col value of the jumped peg piece.
         * @param rowTo   The row value of the destination coordinate of a peg piece.
         * @param colTo   The col value of the destination coordinate of a peg piece.
         */
        public Move(int rowFrom, int colFrom, int rowJump, int colJump, int rowTo, int colTo) {
            this.from = new Coordinate(rowFrom, colFrom);
            this.jump = new Coordinate(rowJump, colJump);
            this.to = new Coordinate(rowTo, colTo);
        }

        @Override
        public String toString() {
            return ""+from.row+ from.col+ " " + jump.row+jump.col+ " " +to.row+to.col;
        }

        @Override
        public int compareTo(Move move) {
            return Integer.compare(this.from.getValue(), move.from.getValue());
        }
    }

    /**
     * Initializes a Solver that solves a given PegBoard.
     * @param currentBoard The current PegBoard.
     */
    public Solver(PegBoard currentBoard) {
        this.board = new int[7][7];
        Square[][] pegBoard = currentBoard.getBoard();
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 7; col++) {
                int value = 0;
                if (pegBoard[row][col].isOccupied()) value = 1;
                if (pegBoard[row][col].isInvalid()) value = -1;
                board[row][col] = value;
            }
        }
        this.failBoard = new ArrayList<>();
        this.movesList = new ArrayList<>();
    }

    /**
     * Prints the list of moves conducted in solving the current peg board.
     */
    public void printOutput() {
        for (Move move: movesList) {
            System.out.println(move.toString());
        }
    }

    /**
     * Prints a graphical representation of the current peg board.
     */
    public void printBoard() {
        for (int[] row : board) {
            for (int element : row) {
                if (element == -1) System.out.print("-");
                else System.out.print(""+element);
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Initiates a given move and applies it on the current peg board.
     * @param move The move of a peg piece.
     */
    private void initiateMove(Move move) {
        board[move.from.row][move.from.col] = 0;
        board[move.jump.row][move.jump.col] = 0;
        board[move.to.row][move.to.col] = 1;
        movesList.add(move);
    }

    /**
     * Undoes a given move on the current peg board.
     * @param move The move of a peg piece.
     */
    private void undoMove(Move move) {
        board[move.from.row][move.from.col] = 1;
        board[move.jump.row][move.jump.col] = 1;
        board[move.to.row][move.to.col] = 0;
        movesList.remove(movesList.size()-1);
    }

    /**
     * Fetches the list of all valid moves that a given peg board contains.
     * @return Returns an ArrayList containing Move instances.
     */
    private ArrayList<Move> computeAllValidMoves() {
        ArrayList<Move> validMoves = new ArrayList<>();
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 7; col++) {
                if (board[row][col] == 0) {
                    if (row+2 <= 6) {
                        if ((board[row+2][col] == 1) && (board[row+1][col] == 1)) validMoves.add(new Move(row+2, col, row+1, col, row, col));
                    }
                    if (col+2 <= 6) {
                        if ((board[row][col+2] == 1) && (board[row][col+1]) == 1) validMoves.add(new Move(row, col+2, row, col+1, row, col));
                    }
                    if (row-2 >= 0) {
                        if ((board[row-2][col]) == 1 && (board[row-1][col]) == 1) validMoves.add(new Move(row-2, col, row-1, col, row, col));
                    }
                    if (col-2 >= 0) {
                        if ((board[row][col-2] == 1)  && (board[row][col-1] == 1)) validMoves.add(new Move(row, col-2, row, col-1, row, col));
                    }
                }
            }
        }
        return validMoves;
    }

    /**
     * Determines if a given board is a duplicate.
     * @param checkBoard The current board.
     * @return Returns a boolean value.
     */
    private boolean containsDuplicate(int[][] checkBoard) {
        for (int[][] currentBoard : failBoard) {
            boolean duplicate = true;
            for (int row = 0; row < 7; row++) {
                for (int col = 0; col < 7; col++) {
                    if (checkBoard[row][col] != currentBoard[row][col]) {
                        duplicate = false;
                        break;
                    }
                }
                if (!duplicate) break;
            }
            if (duplicate) return true;
        }
        return false;
    }

    /**
     * Returns a deep copy of a given board.
     * @param board The current board.
     * @return Returns an int[][]
     */
    private int[][] deepCopyBoard(int[][] board) {
        int[][] copyBoard = new int[7][7];
        for (int row = 0; row < 7; row++) {
            System.arraycopy(board[row], 0, copyBoard[row], 0, 7);
        }
        return copyBoard;
    }

    /**
     * Fetches the current number of peg pieces on the board.
     * @return Returns the number of peg pieces on the board.
     */
    private int getCount() {
        int count = 0;
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 7; col++) {
                if (board[row][col] == 1) count ++;
            }
        }
        return count;
    }

    /**
     * Solves the current peg board.
     * @return Returns a boolean value determining whether the board is solved or not.
     */
    public boolean solve() {
        if (containsDuplicate(board)) {
            return false;
        }
        if (getCount() == 1 && board[3][3] == 1) {
            printBoard();
            printOutput();
            return true;
        }
        ArrayList<Move> moves = computeAllValidMoves();
        Collections.sort(moves);
        for (Move move : moves) {
            initiateMove(move);
            if (solve()) {
                return true;
            } else {
                undoMove(move);
            }
        }
        if (!containsDuplicate(board)) {
            failBoard.add(deepCopyBoard(board));
        }
        return false;
    }
}