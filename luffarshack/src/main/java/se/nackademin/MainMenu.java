package se.nackademin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class MainMenu {

    private Stats stats;

    public MainMenu(Stats stats) {
        this.stats = stats;
    }

    /**
     * Method to check if all chars in a string are digits
     * 
     * Loops through all the digits and looks if they are larger than 0 and less than 9
     * @param str takes in string to compare
     * @param len takes in len of the string to determine number of times to
     *            itterate
     * @return true if all are digits and false if not
     */
    public boolean intCheck(String str, int len) {
        for (int i = 0; i < len; i++) {
            if (str.charAt(i) < '0'
                    || str.charAt(i) > '9') {
                System.out.println("You can only input digits.");
                return false;
            }
        }
        return true;
    }

    /**
     * Method to check if the entered size is a valid choice
     * 
     * @param choice takes in the requested size
     * @return true if = or between 4 and 101, false if not
     */
    public boolean validSizeCheck(int choice) {
        if (choice == 0) {
            return true;
        } else if (choice > 4 && choice < 101) {
            return true;
        } else {
            System.out.println("Invalid size. Has to be between 1 - 100 or 0 for infinite map.");
            return false;
        }
    }

    public Game loadGame(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Game game = (Game) ois.readObject();
            if (game == null) {
                System.out.println("Game object is null");
            }
            return game;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Stats loadStats(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Stats stats = (Stats) ois.readObject();
            if (stats == null) {
                System.out.println("Stats object is null");
            }
            return stats;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void saveStats(Stats stats, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(stats);
            System.out.println("Game saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String takeInput(String text){
        Scanner scanner = new Scanner(System.in);
        System.out.println(text);
        String input = scanner.next();
        return input;
    }

    public boolean startTwoPlayerGame(Stats stats, String player1Name, String player2Name, int boardSize) {
        Game game = new Game(stats, player1Name, player2Name, boardSize);
        game.setGame(game);
        game.twoPlayerGame();
        return true;
    }

    public void handleLoadGame() {
        File file = new File("test.bin");
        if (file.exists() && !file.isDirectory()) {
            Game game = loadGame("test.bin");
            game.setStats(stats);
            if (game.getIsComputerGame()) {
                game.computerGame();
            } else if (game != null) {
                game.twoPlayerGame();
            }
        }
    }

    public boolean handleChoice(String choice) {
        if (choice.equals("1")) {
            String player1Name = takeInput("Enter player 1 name: ");
            String player2Name = takeInput("Enter player 1 name: ");
            while (true) {
                String inputBoardSize = takeInput("Enter Board Size 5-100 or 0 for infinite map");
                int len = inputBoardSize.length();
                if (intCheck(inputBoardSize, len)) {
                    int intBoardSize = Integer.parseInt(inputBoardSize);
                    if (validSizeCheck(intBoardSize)) {
                        startTwoPlayerGame(stats, player1Name, player2Name, intBoardSize);
                        return true;

                    }
                }
            }
        } else if (choice.equals("2")) {
            Game game = new Game(stats, "player1", 0);
            game.setGame(game);
            game.computerGame();
            return true;
        } else if (choice.equals("3")) {
            stats.printStats();
            return true;
        } else if (choice.equals("4")) {
            File file = new File("test.bin");
            if (file.exists() && !file.isDirectory()) {
                Game game = loadGame("test.bin");
                stats = game.getStats();
                if (game.getIsComputerGame()) {
                    game.computerGame();
                } else if (game != null) {
                    game.twoPlayerGame();
                }
            }
            return true;
        } else if (choice.equals("q")) {
            saveStats(stats, "stats.bin");
            return false;
        } else {
            System.out.println("That is not a valid input.");
            return true;
        }
    }

    public void runMenu() {
        Scanner scanner = new Scanner(System.in);
        File file = new File("stats.bin");
        if (file.exists() && !file.isDirectory()) {
            this.stats = loadStats("stats.bin");
        } else {
            System.out.println("File does not exist.");
        }
        while (true) {
            System.out.println(Constants.MAIN_MENU_TEXT);
            String choice = scanner.next();
            if (!handleChoice(choice)) {
                scanner.close();
                break;
            }
        }
    }
}