package se.nackademin;

/**
 * Interface for boards
 * 
 * (Plan was for Static board and infiniteboard to be two diffrent classes
 * After refactoring, infinite board could handle static boards job
 * making static board unnessesary)
 */
public interface Board {

    /**
     * Method to print out the board
     */
    public void printBoard();

    /**
     * Method to move the cursor up
     * @return should return true if successful and false if not
     */
    public boolean moveUp();

    /**
     * Method to move the cursor down
     * @return should return true if successful and false if not
     */
    public boolean moveDown();

    /**
     * Method to move the cursor left
     * @return should return true if successful and false if not
     */
    public boolean moveLeft();

    /**
     * Method to move the cursor right
     * @return should return true if successful and false if not
     */
    public boolean moveRight();

    /**
     * Method to check for win
     * @param playerMark takes in a players mark to check compare for win
     * @return true if win, false if not 
     */
    public boolean checkWin(String playerMark, int numberForWin);

    /**
     * Method place the player mark on a square
     * Method should handle if square is already marked
     * @param playerMark to add the mark to the square
     * @param numberForWin added as parameter to improve computer AI
     * @return true if player could mark the square, false if it was already marked
     */
    public boolean placeMark(String playerMark);
}
