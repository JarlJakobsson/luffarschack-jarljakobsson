package se.nackademin;

import java.io.Serializable;

public class InfiniteBoard implements Board, Serializable {
    private int size;
    private Square[][] board;
    private int cursorX = 0;
    private int cursorY = 0;
    private int offsetX = 0;
    private int offsetY = 0;
    private int printSize = 5;
    private boolean isInfinite = false;

    /**
     * Consutructor for InfiniteBoard
     * @param boardSize to decide what boardsize to use.
     * 0 for infinite
     */
    public InfiniteBoard(int boardSize) {
        if(boardSize == 0) {
            size = 5;
            isInfinite = true;
        } else {
            size = boardSize;
        }
        board = new Square[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Square();
            }
        }
    }

    public boolean getIsInfinite() {
        return isInfinite;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public Square[][] getBoard() {
        return board;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getMark(int cursorY, int cursorX) {
        return board[cursorY][cursorX].getMark();
    }

    public void setMark(int cursorY, int cursorX, String mark){
        board[cursorY][cursorX].setMark(mark);
    }

    public int getCursorX() {
        return cursorX;
    }

    public void setCursorX(int cursorX) {
        this.cursorX = cursorX;
    }

    public void setCursorY(int cursorY) {
        this.cursorY = cursorY;
    }

    public int getCursorY() {
        return cursorY;
    }

    /**
     * Method to expand the board when player moves outside the board
     * Creates a new board with size + 2 because i add 1 row and column in every
     * direction
     * Copies over the current board at i+1 and j+1 so current marks still have
     * correct positions
     * Then Creates new rows and columns and put new squares in them
     * 
     * Sets the new size to size +=2 and the board to nice board
     */
    public void expandBoard() {
        Square[][] newBoard = new Square[size + 2][size + 2];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                newBoard[i + 1][j + 1] = board[i][j];
            }
        }
        for (int i = 0; i < size + 2; i++) {
            newBoard[i][0] = new Square();
            newBoard[i][size + 1] = new Square();
        }
        for (int j = 0; j < size + 2; j++) {
            newBoard[0][j] = new Square();
            newBoard[size + 1][j] = new Square();
        }
        size += 2;
        board = newBoard;
    }

    /**
     * Method to print the board.
     * Added offset Y and X and printSize only print the correct 5x5 squares
     */
    @Override
    public void printBoard() {
        for (int i = offsetY; i < printSize + offsetY; i++) {
            for (int j = offsetX; j < printSize + offsetX; j++) {
                if (cursorX == j && cursorY == i) {
                    System.out.print("[" + board[i][j].getMark() + "]" + " ");
                } else {
                    System.out.print("(" + board[i][j].getMark() + ")" + " ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Method for moving the cursor up
     * If cursors Y value is within available squares move cursorY by -1
     * If cursors Y value is too far away from current offsetY change offsetY by -1
     * to print out the relevant board
     * 
     * If cursors Y value is outside the current board, call the expandBoard()
     * method to increase the boardsize
     * 
     * OffsetX changes with +1 and Cursors X value changes by +1 to keep cursor on
     * correct spot and print relevant board
     */
    @Override
    public boolean moveUp() {
        if (cursorY > 0) {
            cursorY--;
            if (printSize + offsetY - cursorY == 6) {
                offsetY--;
            }
        } else if (isInfinite) {
            expandBoard();
            offsetX++;
            cursorX++;
        } else {
            System.out.println("Invalid move.");
            return false;
        }
        return true;
    }

    @Override
    public boolean moveDown() {
        if (cursorY < size - 1) {
            cursorY++;
            if (cursorY - offsetY > 4) {
                offsetY++;
                if (printSize + offsetX - cursorX == 6) {
                    offsetX--;
                }
            }
        } else if (isInfinite){
            expandBoard();
            offsetY += 2;
            offsetX++;
            cursorX++;
            cursorY += 2;
        } else {
            System.out.println("Invalid move.");
            return false;
        }
        return true;
    }

    @Override
    public boolean moveLeft() {
        if (cursorX > 0) {
            cursorX--;
            if (printSize + offsetX - cursorX == 6) {
                offsetX--;
            }
        } else if(isInfinite) {
            expandBoard();
            offsetY++;
            cursorY++;
        } else {
            System.out.println("Invalid move.");
            return false;
        }
        return true;
    }

    @Override
    public boolean moveRight() {
        if (cursorX < size - 1) {
            cursorX++;
            if (printSize + cursorX - offsetX > 9) {
                offsetX++;
            }
        } else if (isInfinite) {
            expandBoard();
            offsetX += 2;
            offsetY++;
            cursorY++;
            cursorX += 2;
        } else {
            System.out.println("Invalid move.");
            return false;
        }
        return true;
    }

    /**
     * Method for placeing the players mark or the square where the cursor is pointing
     * @param playerMark takes in players mark so it places correct mark on the square
     * 
     * @return true if succsefull mark, false if already marked
     */
    @Override
    public boolean placeMark(String playerMark) {
        if (board[cursorY][cursorX].getMark().equals(" ")) {
            board[cursorY][cursorX].setMark(playerMark);
            return true;
        } else {
            System.out.println("Square already marked");
            return false;
        }
    }

    @Override
    public boolean checkWin(String playerMark) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Square currentSquare = board[i][j];
                if (currentSquare.getMark().equals(playerMark)) {
                    if (checkRow(i, j, 
                            playerMark) || checkColumn(i, j, 
                                    playerMark)
                            || checkDiagonals(i, j, playerMark)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkRow(int row, int col, String playerMark) {
        for (int i = 0; i < 4; i++) {
            if (col + i >= size || !board[row][col + i].getMark().equals(playerMark)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumn(int row, int col, String playerMark) {
        for (int i = 0; i < 4; i++) {
            if (row + i >= size || !board[row + i][col].getMark().equals(playerMark)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagonals(int row, int col, String playerMark) {
        return checkRightDiagonal(row, col, playerMark) || checkLeftDiagonal(row, col, playerMark);
    }

    private boolean checkRightDiagonal(int row, int col, String playerMark) {
        for (int i = 0; i < 4; i++) {
            if (row + i >= size || col + i >= size || !board[row + i][col + i].getMark().equals(playerMark)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkLeftDiagonal(int row, int col, String playerMark) {
        for (int i = 0; i < 4; i++) {
            if (row + i >= size || col - i < 0 || !board[row + i][col - i].getMark().equals(playerMark)) {
                return false;
            }
        }
        return true;
    }
}
