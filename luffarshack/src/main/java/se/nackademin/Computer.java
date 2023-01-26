package se.nackademin;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class Computer implements Serializable {
    private InfiniteBoard board;
    private String mark = "0";
    private String opponentMark = "X";
    private List<Move> moves;

    public Computer(InfiniteBoard board) {
        this.board = board;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    /**
     * Method to see if computer can win
     * Tries to place its more on each square one by one
     * and calls the board.checkWin method to see if it would win
     * If true places its mark, if false sets the square back to " "
     * 
     * @return true if successfull winning move, false if not
     */
    public boolean checkWin(int numberForWin) {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getMark(i, j).equals(" ")) {
                    board.setMark(i, j, mark);
                    if (board.checkWin(mark, numberForWin)) {
                        moves.add(new Move(i, j, mark));
                        return true;
                    }
                    board.setMark(i, j, " ");
                }
            }
        }
        return false;
    }

    /**
     * Method for stopping opponent victory
     * Tries to place opponents mark on each available square one by one
     * Calls the checkWin method to see if the opponent would win with that move
     * If true places its mark, if false changes the square back to " "
     * 
     * @return true if successfully blocked the oppoenent, false if not
     */
    public boolean checkBlock(int numberForWin) {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getMark(i, j).equals(" ")) {
                    board.setMark(i, j, opponentMark);
                    if (board.checkWin(opponentMark, numberForWin)) {
                        board.setMark(i, j, mark);
                        moves.add(new Move(i, j, mark));
                        return true;
                    }
                    board.setMark(i, j, " ");
                }
            }
        }
        return false;
    }

    /**
     * Method for random move
     * Randomize a x and y cordinate within the board
     * If the square is markable, marks
     * if not continues the loop
     * 
     * @return true if successfull for testing purposes
     */
    public boolean randomMove() {
        while (true) {
            Random random = new Random();
            int x = random.nextInt(board.getSize());
            int y = random.nextInt(board.getSize());
            if (board.getMark(y, x).equals(" ")) {
                board.setMark(y, x, mark);
                moves.add(new Move(x, y, mark));
                return true;
            }
        }
    }

    /**
     * Method for when computes makes a move
     * Looks for InArow amount of squares in a row and tries to win or block the opponent
     * If in a row = 1, makes a random move
     * 
     * @return true after made a move for testing purposes
     */
    public boolean makeMove() {
        int inARow = 4;
        while (true) {
            if (!checkWin(inARow)) {
                if (!checkBlock(inARow)) {
                    inARow--;
                    if (inARow == 1) {
                        randomMove();
                        return true;
                    }
                } else {
                    return true;
                }
            } else {
                return true;
            }
        }
    }
}
 
