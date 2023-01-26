package se.nackademin;

import java.io.Serializable;

/**
 * Stats class to hadle stats
 */
public class Stats implements Serializable{
    private int totalMoves = 0;
    private int finnishedGames = 0;

    public Stats() {

    }

    public int getFinnishedGames() {
        return finnishedGames;
    }

    public void setFinnishedGames(int finnishedGames) {
        this.finnishedGames = finnishedGames;
    }

    public int getTotalMoves() {
        return totalMoves;
    }

    public void setTotalMoves(int totalMoves) {
        this.totalMoves = totalMoves;
    }

    public void printStats() {
        System.out.println("Total games: " + getFinnishedGames());
        System.out.println("Total moves: " + getTotalMoves());
    }

}
