package se.nackademin;

import java.io.Serializable;

// Move class to handle the row, column, and playerMark of each move
public class Move implements Serializable {
    int col;
    int row;
    String playerMark;

    Move(int row, int col, String playerMark) {
        this.row = row;
        this.col = col;
        this.playerMark = playerMark;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public String getPlayerMark() {
        return playerMark;
    }
}
