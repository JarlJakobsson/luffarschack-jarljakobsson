package se.nackademin;

/**
 * The interface for a menu.
 * It has two methods, checkValidInput and runMenu
 * 
 * Keeping some design thoughts, even though they couldnt be used after some refactoring
 */
public interface Menu {
    // /**
    //  * Checks if the input is valid.
    //  * @param choice The input from the user.
    //  * @return true if the input is valid, false otherwise.
    //  */
    // public boolean handleChoice(String choice);

    /**
     * Method to take input from user
     * @param text takes in text print to the user what the expected input should be
     * @return returns the users string
     */
    public String takeInput(String text);

    // /**
    //  * Runs the menu.
    //  * @param board  The current board.
    //  * @param player The current player.
    //  * @param game   The game object.
    //  */
    // public void runMenu();
}