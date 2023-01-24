package se.nackademin;

public class Game {
    private boolean winner = false;
    private boolean quit = false;
    private Player player1;
    private Player2 player2; 

    public Game() {

    }

    public Player getPlayer() {
        return player1;
    }

    public void setPlayer(Player player) {
        this.player1 = player;
    }

    public Player2 getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player2 player2) {
        this.player2 = player2;
    }

    public void setQuit(boolean quit) {
        this.quit = quit;
    }

    public boolean getQuit() {
        return quit;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public boolean getWinner() {
        return winner;
    }

    /**
     * Method to start a two player game.
     * Creates a new board, players and movemenu
     * keeps looping until a winner or quitter
     * @param game TODO MIGHT NOT BE NEEDED WILL TEST LATER
     */
    public void twoPlayerGame(Game game) {
        InfiniteBoard board = new InfiniteBoard();
        Player player1 = new Player();
        Player2 player2 = new Player2();
        MoveMenu moveMenu = new MoveMenu(game, board);
        while (!getWinner() && !getQuit()) {
            moveMenu.runMenu(player1);
            if (!getWinner() && !getQuit() && !board.checkForWin(player1)) {
                moveMenu.runMenu(player2);
            } else {
                break;
            }
        }
        
    }
}
