package se.nackademin;

import java.io.Serializable;
import java.util.Random;

public class Computer implements Serializable {
    private InfiniteBoard board;
    private String mark = "0";
    private String opponentMark = "X";

    public Computer(InfiniteBoard board) {
        this.board = board;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    /**
     * Method to see if computer can win
     * Tries to place its more on each square one by one
     * and calls the board.checkWin method to see if it would win
     * If true places its mark, if false sets the square back to " "
     * 
     * @return true if successfull winning move, false if not
     */
    public boolean checkWin() {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getMark(i, j).equals(" ")) {
                    board.setMark(i, j, mark);
                    if (board.checkWin(mark)) {
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
    public boolean checkBlock() {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getMark(i, j).equals(" ")) {
                    board.setMark(i, j, opponentMark);
                    if (board.checkWin(opponentMark)) {
                        board.setMark(i, j, mark);
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
     */
    public boolean randomMove() {
        while (true) {
            Random random = new Random();
            int x = random.nextInt(board.getSize());
            int y = random.nextInt(board.getSize());
            if (board.getMark(y, x).equals(" ")) {
                board.setMark(y, x, mark);
                return true;
            }
        }
    }

    /**
     * Method for when computes makes a move
     * First checks if it can win
     * If not checks if player can win
     * If not makes a random move
     */
    public boolean makeMove() {
        if (!checkWin()) {
            if (!checkBlock()) {
                randomMove();
            }
        }
        return true;
    }
}
