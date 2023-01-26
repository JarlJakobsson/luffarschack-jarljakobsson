package se.nackademin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Game implements Serializable {
    private Player player1;
    private Player player2;
    private InfiniteBoard board;
    private Stats stats;
    private boolean isComputerGame = false;
    private Computer computer;
    private boolean isPlayer1Turn = true;
    private PlacementMenu placementMenu;
    private List<Move> moves;
    private Helper helper = new Helper();

    /**
     * Constructor for Two Player Game
     * @param stats       takes in a stats class to update stats during the game
     * @param player1Name takes in player1name to create player 1
     * @param player2Name takes in player2name to create player 2
     * @param boardSize   takes in boardsize to create correct board
     */
    public Game(Stats stats, String player1Name, String player2Name, int boardSize) {
        this.stats = stats;
        board = new InfiniteBoard(boardSize);
        player1 = new Player(player1Name, "X");
        player2 = new Player(player2Name, "0");
        placementMenu = new PlacementMenu(board);
        moves = new ArrayList<Move>();
    }

    /**
     * Constructor for Game against Computer
     * @param stats
     * @param player1Name
     * @param boardSize
     */
    public Game(Stats stats, String player1Name, int boardSize) {
        this.stats = stats;
        isComputerGame = true;
        board = new InfiniteBoard(boardSize);
        player1 = new Player(player1Name, "X");
        placementMenu = new PlacementMenu(board);
        moves = new ArrayList<Move>();
        computer = new Computer(board);
    }

    public void setPlacementMenu(PlacementMenu placementMenu) {
        this.placementMenu = placementMenu;
    }

    public void appendMove(int row, int col, String playerMark) {
        moves.add(new Move(row, col, playerMark));
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

    /**
     * Method to handle the twoPlayerGameLoop
     * Keeps going until a winner or quitter
     * before player 1 moves checks if its player1s turn
     * incase of loading a saved game during player2s turn
     * 
     * Changes playerturn variable after each move unless quitter
     */
    public boolean twoPlayerGame() {
        while (!placementMenu.getQuit() && !placementMenu.getWinner()) {
            if(isPlayer1Turn) {
                placementMenu.runMenu(player1, stats, this);
                isPlayer1Turn = false;
            }
            if (!placementMenu.getQuit() && !placementMenu.getWinner()) {
                placementMenu.runMenu(player2, stats, this);
                if(!placementMenu.getQuit()) {
                    isPlayer1Turn = true;
                }
            } else {
                return true;
            }
        } return false;
    }

    /**
     * Method to handle computerGameLoop
     */
    public void computerGame() {
        computer.setMoves(moves);
        while (!placementMenu.getQuit() && !placementMenu.getWinner()) {
            placementMenu.runMenu(player1, stats, this);
            if (!placementMenu.getQuit() && !placementMenu.getWinner()) {
                stats.setTotalMoves(stats.getTotalMoves() + 1);
                computer.makeMove();
                if (board.checkWin(computer.getMark(), 4)) {
                    stats.setFinnishedGames(stats.getFinnishedGames() + 1);
                    placementMenu.setWinner(true);
                    board.printBoard();
                    helper.takeInput("Computer: EZ  WIN");
                }
            }
        }
    }

    public void replay() {
        board.clearBoard();
        for (Move move : moves) {
            board.setMark(move.row, move.col, move.playerMark);
            board.printBoard();
            System.out.println("Player " + move.playerMark + " places mark on row " + move.row + ", col " + move.col);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
