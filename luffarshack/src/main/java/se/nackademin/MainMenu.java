package se.nackademin;

import java.util.Scanner;

public class MainMenu {

    public MainMenu() {

    }

    public boolean handleChoice(String choice) {
        if (choice.equals("1")) {
            Game game = new Game();
            game.twoPlayerGame(game);
            return true;
        } else if (choice.equals("2")) {
            // game.playerVsComputerGame
            return true;
        } else if (choice.equals("3")) {
            // Show stats
            return true;
        } else if (choice.equals("4")) {
            //load game
            return true;
        } else if (choice.equals("q")) {
            return false;
        } else { 
            System.out.println("That is not a valid input.");
            return true;
        }
    }

    public void runMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(Constants.MAIN_MENU_TEXT);
            String choice = scanner.next();
            if(!handleChoice(choice)) {
                scanner.close();
                break;
            }
        }
    }
}