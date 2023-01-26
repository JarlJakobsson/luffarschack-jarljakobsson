package se.nackademin;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class PlacementMenu implements Serializable, Menu {
    private InfiniteBoard board;
    private boolean quit = false;
    private boolean winner = false;
    Helper helper = new Helper();

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

    public void setHelper(Helper helper) {
        this.helper = helper;
    }

    public void saveGame(Game game, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(game);
            System.out.println("Game saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to place mark, it also checks for win
     * 
     * @param stats takes in stats as parameter to update total moves after a move
     * @param player takes inplayer to place the players mark and call the players name if winner
     * @param board takes in the bard to place mark on
     * @param game takes in game to be able to save it
     * @return true unsuccessfull mark, false if successfull or winner
     */

    public boolean placeMark(Stats stats, Player player, InfiniteBoard board, Game game) {
        if (board.placeMark(player.getMark())) {
            game.appendMove(board.getCursorY(), board.getCursorX(), player.getMark());
            stats.setTotalMoves(stats.getTotalMoves() + 1);
            if (board.checkWin(player.getMark(), Constants.NUMBER_FOR_WIN)) {
                if (!board.getIsInfinite()){
                    saveGame(game, "replay.bin");
                }
                setWinner(true);
                stats.setFinnishedGames(stats.getFinnishedGames() + 1);
                Helper.CLEAR();
                board.printBoard();
                helper.takeInput(("\n*** " + player.getName() + " Won ***\n"));
                return false;
            }
            return false;
        }
        return true;
    }
     
    /**
     * Checks for valid input, changes cursor value and
     * sets new location on board as current square
     * Marks current square and does a win check if e
     * quits the game if q
     * 
     * @param choice
     * @param player 
     * @param stats
     * @param game
     * @return
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
            saveGame(game, "savedgame.bin");
            return true;
        } else if (choice.equals("e")) {
            if (!placeMark(stats, player, board, game)) {
                return false;
            }
            return true;
        }
        return true;
    }

    @Override
    public String takeInput(String text) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(text);
        String input = scanner.next();
        return input;
    }

    /**
     * Method to run menu
     * 
     * @param player Takes player as parameter to keep track of players mark
     */
    public boolean runMenu(Player player, Stats stats, Game game) {
            while (true) {
                Helper.CLEAR();
                board.printBoard();
                System.out.println();
                System.out.println("Player " + player.getMark() + " turn");
                String choice = takeInput(Constants.MOVE_MENU_TEXT);
                if (!handleChoice(choice, player, stats, game)) {
                    return true;
                }
            
        }
    }
}