package se.nackademin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class MainMenu implements Menu {

    private Stats stats;

    public MainMenu(Stats stats) {
        this.stats = stats;
    }

    /**
     * Method to check if all chars in a string are digits
     * 
     * Loops through all the digits and looks if they are larger than 0 and less
     * than 9
     * 
     * @param str takes in string to compare
     * @return true if all are digits and false if not
     */
    public boolean intCheck(String str) {
        int len = str.length();
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
            System.out.println("Invalid size.");
            return false;
        }
    }

    /**
     * Method to load game from file
     * 
     * @param fileName takes file name as argument
     * @return  returns the loaded game if not null
     */
    public Game loadGame(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Game game = (Game) ois.readObject();
            if (game == null) {
                takeInput("Game is null.");
            }
            return game;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method to load stats from file
     * @param fileName takes filename as argument
     * @return returns loaded stats if not null
     */
    public Stats loadStats(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Stats stats = (Stats) ois.readObject();
            if (stats == null) {
                takeInput("Stats is null");
            }
            return stats;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveStats(Stats stats, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(stats);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String takeInput(String text) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(text);
        String input = scanner.next();
        return input;
    }

    public boolean startTwoPlayerGame(Stats stats, String player1Name, String player2Name, int boardSize) {
        Game game = new Game(stats, player1Name, player2Name, boardSize);
        game.twoPlayerGame();
        return true;
    }

    public void StartLoadedGame() {
        File file = new File("test.bin");
        if (file.exists() && !file.isDirectory()) {
            Game game = loadGame("test.bin");
            game.setStats(stats);
            if (game.getIsComputerGame()) {
                game.computerGame();
            } else if (game != null) {
                game.twoPlayerGame();
            }
        } else {
            takeInput("File does not exist.");
        }
    }

    /**
     * Method to load stats when you start the program
     * Checks if path exists and is not a directory
     * if true sets this.stats to loaded stats
     */
    public void loadStatsStart() {
        File file = new File("stats.bin");
        if (file.exists() && !file.isDirectory()) {
            this.stats = loadStats("stats.bin");
        } else {
        }
    }

    private void loadReplay(String fileName) {
        File file = new File(fileName);
        if (file.exists() && !file.isDirectory()) {
            Game game = loadGame(fileName);
            game.replay();
        } else {
            takeInput("File does not exist.");
        }
    }

    public void startComputerGame() {
        String playerName = takeInput(Constants.CHOOSE_NAME_TEXT);
        while (true) {
            String inputBoardSize = takeInput(Constants.CHOOSE_BOARDSIZE_TEXT);
            if (intCheck(inputBoardSize)) {
                int intBoardSize = Integer.parseInt(inputBoardSize);
                if (validSizeCheck(intBoardSize)) {
                    Game game = new Game(stats, playerName, intBoardSize);
                    game.computerGame();
                    break;
                }
            }
        }
    }

    public void startTwoPlayerGame() {
        String player1Name = takeInput(Constants.CHOOSE_PLAYER1_NAME);
        String player2Name = takeInput(Constants.CHOOSE_PLAYER2_NAME);
        while (true) {
            String inputBoardSize = takeInput(Constants.CHOOSE_BOARDSIZE_TEXT);
            if (intCheck(inputBoardSize)) {
                int intBoardSize = Integer.parseInt(inputBoardSize);
                if (validSizeCheck(intBoardSize)) {
                    startTwoPlayerGame(stats, player1Name, player2Name, intBoardSize);
                    break;
                }
            }
        }
    }

    /**
     * Method to handle user mainmenu navigation
     * If 1 takes in pl
     * 
     * @param choice
     * @return
     */
    public boolean handleChoice(String choice) {

        // If 1 start computer game
        if (choice.equals("1")) {
            startTwoPlayerGame();
            return true;

            // If 2 Starts a game vs the Computer
        } else if (choice.equals("2")) {
            startComputerGame();
            return true;

            // If 3 prints stats
        } else if (choice.equals("3")) {
            stats.printStats();
            return true;

            // If 4 loads a game
        } else if (choice.equals("4")) {
            StartLoadedGame();
            return true;

            // If 5 Loads a replay
        } else if (choice.equals("5")) {
            loadReplay(choice);
            return true;

            // If q, saves stats in stats.bin and returns false to quit the program
        } else if (choice.equals("q")) {
            saveStats(stats, "stats.bin");
            return false;
        } else {
            System.out.println("That is not a valid input.");
            return true;
        }
    }

    public void runMenu() {
        loadStatsStart();
        while (true) {
            Helper.CLEAR();
            String choice = takeInput(Constants.MAIN_MENU_TEXT);
            if (!handleChoice(choice)) {
                break;
            }
        }
    }
}