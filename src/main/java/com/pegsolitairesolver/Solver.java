package com.pegsolitairesolver;

import java.util.ArrayList;

public class Solver {
    /**The board of the current peg solitaire game.*/
    private int[][] board;
    /**Stores boards that show immediate fail solve. For memoization.*/
    private ArrayList<int[][]> failBoard;
    /**The list of moves conducted in solving the current peg solitaire board.*/
    private ArrayList<Move> movesList;

    public class Move {
        int from;
        int jump;
        int to;
        public Move(int from, int jump, int to) {
            this.from = from;
            this.jump = jump;
            this.to = to;
        }

        @Override
        public String toString() {
            return ""+from+jump+to;
        }
    }

    public Solver(int[][] currentBoard) {
        this.board = currentBoard;
        this.movesList = new ArrayList<>();
    }
}
