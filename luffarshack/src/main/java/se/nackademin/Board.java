package se.nackademin;

import java.util.ArrayList;

/**
 * Board class for keeping track of board
 * 
 * @param size for board size
 */

public class Board {
    private int size = 5;
    private ArrayList<ArrayList<Square>> board = new ArrayList<ArrayList<Square>>();
    private int cursorX = 0;
    private int cursorY = 0;
    private Square currentSquare;

    public Board() {
        for (int i = 0; i < size; i++) {
            ArrayList<Square> row = new ArrayList<Square>();
            for (int j = 0; j < size; j++) {
                row.add(new Square());
            }
            board.add(row);
        }
        currentSquare = board.get(cursorX).get(cursorY);
    }

    public int getCursorX() {
        return cursorX;
    }

    public void setCursorX(int cursorX) {
        this.cursorX = cursorX;
    }

    public int getCursorY() {
        return cursorY;
    }

    public void setCursorY(int cursorY) {
        this.cursorY = cursorY;
    }

    public Square getCurrentSquare() {
        return currentSquare;
    }

    public void setCurrentSquare() {
        currentSquare = board.get(getCursorY()).get(getCursorX());
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<ArrayList<Square>> getBoard() {
        return board;
    }

    public Square getSquare(int cursorY, int cursorX) {
        return getBoard().get(cursorY).get(cursorX);
    }

    public boolean checkSquareExist(int newIndex) {
        if (newIndex >= size || newIndex < 0) {
            return false;
        }
        return true;
    }

    public void placeMark(Board board, Player player) {
        board.getCurrentSquare().setMark(player.getMark());
    }

    public boolean checkValidPlacement() {
        if (getCurrentSquare().getMark().equals("(X)") || getCurrentSquare().getMark().equals("(0)")) {
            System.out.println("\nThat square is already marked.");
            return false;
        }
        return true;
    }

    public boolean checkAllSquaresMarked() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board.get(i).get(j).getMark().equals("( )")) {
                    return false;
                }
            }
        }
        System.out.println("Game is a Draw, All squares have been marked."); 
        return true;
    }

    public void printBoard(String playerMark) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (currentSquare.equals(board.get(i).get(j))) {
                    currentSquare.setCursorMark(playerMark);
                    System.out.print(board.get(i).get(j).getCursorMark() + " ");
                } else {
                    System.out.print(board.get(i).get(j).getMark() + " ");
                }
            }
            System.out.println();
        }
    }

    public boolean checkForWin(String playerMark) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Square currentSquare = board.get(i).get(j);
                if (currentSquare.getMark().equals("(" + playerMark + ")")) {
                    if (checkRow(i, j, playerMark) || checkColumn(i, j, playerMark)
                            || checkDiagonals(i, j, playerMark)) {
                        System.out.println("you won"); // TODO write better victory
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkRow(int row, int col, String playerMark) {
        for (int i = 0; i < 4; i++) {
            if (col + i >= size || !board.get(row).get(col + i).getMark().equals("(" + playerMark + ")")) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumn(int row, int col, String playerMark) {
        for (int i = 0; i < 4; i++) {
            if (row + i >= size || !board.get(row + i).get(col).getMark().equals("(" + playerMark + ")")) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagonals(int row, int col, String playerMark) {
        return checkRightDiagonal(row, col, playerMark) || checkLeftDiagonal(row, col, playerMark);
    }

    private boolean checkRightDiagonal(int row, int col, String playerMark) {
        for (int i = 0; i < 4; i++) {
            if (row + i >= size || col + i >= size || !board.get(row + i).get(col + i).getMark().equals(
                    "(" + playerMark + ")")) {
                return false;
            }
        }
        return true;
    }

    private boolean checkLeftDiagonal(int row, int col, String playerMark) {
        for (int i = 0; i < 4; i++) {
            if (row + i >= size || col - i < 0 || !board.get(row + i).get(col - i).getMark().equals(
                    "(" + playerMark + ")")) {
                return false;
            }
        }
        return true;
    }

}
