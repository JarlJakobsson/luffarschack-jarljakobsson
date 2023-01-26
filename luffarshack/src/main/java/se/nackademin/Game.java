package se.nackademin;

import java.io.Serializable;

public class Game implements Serializable {
    private Player player1;
    private Player player2;
    private InfiniteBoard board;
    private Stats stats;
    private Game game;
    private boolean isComputerGame = false;
    private String player1Name;
    private Computer computer;
    private boolean isPlayer1Turn = true;
    private PlacementMenu placementMenu;

    public Game(Stats stats, String player1Name, String player2Name, int boardSize) {
        this.stats = stats;
        board = new InfiniteBoard(boardSize);
        player1 = new Player(player1Name, "X");
        player2 = new Player(player2Name, "0");
        placementMenu = new PlacementMenu(board);
    }
    
    public Game(Stats stats, String player1Name, int boardSize) {
        this.stats = stats;
        isComputerGame = true;
        board = new InfiniteBoard(boardSize);
        computer = new Computer(board);
        player1 = new Player(player1Name, "X");
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public void setBoard(InfiniteBoard board) {
        this.board = board;
    }

    public InfiniteBoard getBoard() {
        return board;
    }

    public boolean getIsComputerGame(){
        return isComputerGame;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player1;
    }

    public void setPlayer(Player player) {
        this.player1 = player;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void initializeGame() {
        // set the cursor position to the last move made by the computer
        board.setCursorX(0);
        board.setCursorY(0);
        // update any other necessary state variables here
    }

    /**
     * Method to start a two player game.
     * Creates a new board, players and a placement menu.
     * Method handles the game loop by alternating between player 1 and player 2
     * until a winner or quitter
     * 
     * TODO make none hardcoded markers.
     * 
     * @param stats       takes in a stats class to update stats during the game
     * @param player1Name takes in player1name to create player 1
     * @param player2Name takes in player2name to create player 2
     * @param boardSize   takes in boardsize to create correct board
     * 
     */
    public boolean twoPlayerGame() {
        while (!placementMenu.getQuit() && !board.checkWin(player2.getMark())) {
            if(isPlayer1Turn){
                placementMenu.runMenu(player1, stats, game);
                isPlayer1Turn = false;
            }
            if (!placementMenu.getQuit() && !placementMenu.getWinner()) {
                placementMenu.runMenu(player2, stats, game);
                if(!placementMenu.getQuit()) {
                    isPlayer1Turn = true;
                }
            } else {
                break;
            }
        } return true;
    }

    public void computerGame() {
        PlacementMenu placementMenu = new PlacementMenu(board);
        Computer computer = new Computer(board);
        while (!placementMenu.getQuit() && !placementMenu.getWinner()) {
            placementMenu.runMenu(player1, stats, game);
            if (!placementMenu.getQuit() && !placementMenu.getWinner()) {
                stats.setTotalMoves(stats.getTotalMoves() + 1);
                computer.makeMove();
                if (board.checkWin(computer.getMark())) {
                    stats.setFinnishedGames(stats.getFinnishedGames() + 1);
                    placementMenu.setWinner(true);
                    System.out.println("Computer: EZ  WIN");
                }
            }
        }
    }
}
