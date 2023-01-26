package se.nackademin;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class PlacementMenu implements Serializable {
    private InfiniteBoard board;
    private boolean quit = false;
    private boolean winner = false;

    public PlacementMenu(InfiniteBoard board) {
        this.board = board;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public boolean getWinner() {
        return winner;
    }

    public void setQuit(boolean quit) {
        this.quit = quit;
    }

    public boolean getQuit() {
        return quit;
    }

    private void saveGame(Game game, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(game);
            System.out.println("Game saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks for valid input, changes cursor value and
     * sets new location on board as current square
     * Marks current square and does a win check if e
     * quits the game if q
     */
    public boolean handleChoice(String choice, Player player, Stats stats, Game game) {
        if (choice.equals("w")) {
            board.moveUp();
            return true;
        } else if (choice.equals("a")) {
            board.moveLeft();
            return true;
        } else if (choice.equals("s")) {
            board.moveDown();
            return true;
        } else if (choice.equals("d")) {
            board.moveRight();
            return true;
        } else if (choice.equals("q")) {
            setQuit(true);
            return false;
        } else if (choice.equals("r")) {
            saveGame(game, "test.bin");
            // saveBoard(board, "test.bin");
            return true;
        } else if (choice.equals("e")) {
            if (board.placeMark(player.getMark())) {
                stats.setTotalMoves(stats.getTotalMoves() + 1);
                if (board.checkWin(player.getMark())) {
                    setWinner(true);
                    stats.setFinnishedGames(stats.getFinnishedGames() + 1);
                    System.out.println("\n*** " + player.getName() + " Won ***\n");
                    return false;
                }
                return false;
            }
        }
        return true;
    }

    /**
     * Method to run menu
     * 
     * @param player Takes player as parameter to keep track of players mark
     */
    public void runMenu(Player player, Stats stats, Game game) {
        Scanner scanner = new Scanner(System.in);
            while (true) {
                board.printBoard();
                System.out.println();
                System.out.println("Player " + player.getMark() + " turn");
                System.out.println(Constants.MOVE_MENU_TEXT);
                String choice = scanner.next();
                if (!handleChoice(choice, player, stats, game)) {
                    break;
                }
            
        }
    }
}