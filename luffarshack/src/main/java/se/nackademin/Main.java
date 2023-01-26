package se.nackademin;

/**
 * Main class to start program.
 * Creates a Stats object and a Mainmenu object and runs mainmenu
 */
public class Main {
    public static void main(String[] args) {
        Stats stats = new Stats();
        MainMenu mainMenu = new MainMenu(stats);
        mainMenu.runMenu();
    }
}
