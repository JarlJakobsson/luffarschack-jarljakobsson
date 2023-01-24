package se.nackademin;

import java.util.Scanner;

public class MoveMenu {
    private Game game;
    private InfiniteBoard board;

    public MoveMenu(Game game, InfiniteBoard board) {
        this.game = game;
        this.board = board;
    }


    /**
     * Checks for valid input, changes cursor value and
     * sets new location on board as current square
     * Marks current square and does a win check if e
     * quits the game if q
     */
    public boolean handleChoice(String choice, Player player) {
        if (choice.equals("w")) {
            board.moveUp();
            return true;
        } else if (choice.equals("a")) {
            //board.moveCursor(board.getCursorX() -1, board.getCursorY());
            board.moveLeft();
            // board.setCursorX(board.getCursorX() - 1);
            // board.setCurrentSquare();
            return true;
        } else if (choice.equals("s")) {
            //board.moveCursor(board.getCursorX(), board.getCursorY() + 1);
            board.moveDown();
            // board.setCursorY(board.getCursorY() + 1);
            // board.setCurrentSquare();
            return true;
        } else if (choice.equals("d")) {
            board.moveRight();
            //board.moveCursor(board.getCursorX() + 1, board.getCursorY());
            // board.setCursorX(board.getCursorX() + 1);
            // board.setCurrentSquare();
            return true;
        } else if (choice.equals("q")) {
            game.setQuit(true);
            return false;
        } else if (choice.equals("e")) {
            if (board.placeMark(player.getMark())) {
                return false;
            } else {
                return true;
            }
        //     if (board.checkValidPlacement()) {
        //         board.placeMark(board, player);
        //         if (board.checkForWin(player.getMark()) || board.checkAllSquaresMarked()) {
        //             game.setWinner(true);
        //             return false;
        //         }
        //         return false;
        //     }
        // } else {
        //     System.out.println("That is not a valid input.");
        //     return true;
        }
        return true;
    }

    /**
     * Method to run menu
     * @param player Takes player as parameter to keep track of players mark
     */
    public void runMenu(Player player) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            board.printBoard();
            System.out.println();
            System.out.println(Constants.MOVE_MENU_TEXT);
            String choice = scanner.next();
            if (!handleChoice(choice, player)) {
                break;
            }
        }
    }
}