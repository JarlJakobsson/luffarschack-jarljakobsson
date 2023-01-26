package se.nackademin;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test for the Board class
 * Create board
 * Place brick (X or 0)
 * Check Win
 * Check available square
 */
public class BoardTest {
    private Player player;
    private InfiniteBoard infiniteBoard;
    private InfiniteBoard setBoard;

    @BeforeEach
    public void setUp() {
        player = new Player("tester", "X");
        infiniteBoard = new InfiniteBoard(0);
        setBoard = new InfiniteBoard(10);
    }

    /**
     * Test if choice of board size matches the lenght of created board
     */
    @Test
    public void BoardSizeTest() {
        assertEquals(infiniteBoard.getSize(), 10);
        assertEquals(setBoard.getSize(), 10);
    }

    /**
     * Test to make sure player with Mark X can mark a square
     * Should return true when successfull and square mark should be changed
     */
    @Test
    public void PlaceMarkerTestSuccses() {
        assertTrue(infiniteBoard.placeMark(player.getMark()));
        assertEquals(infiniteBoard.getMark(0, 0), "X");
    }

    /**
     * Test to make sure placeMarker returns false if unsuccessfull
     * and square mark is not changed
     */
    @Test
    public void PlaceMarkerTestFail() {
        infiniteBoard.placeMark("0");

        assertFalse(infiniteBoard.placeMark(player.getMark()));
        assertNotEquals(infiniteBoard.getMark(0, 0), "X");
    }


    @Test
    public void CheckForWinRowWinTest() {
        infiniteBoard.setMark(0, 0, "X");
        infiniteBoard.setMark(0, 1, "X");
        infiniteBoard.setMark(0, 2, "X");
        infiniteBoard.setMark(0, 3, "X");

        assertTrue(infiniteBoard.checkWin(player.getMark(), 4));
    }

    @Test
    public void CheckForWinColumnWinTest() {
        infiniteBoard.setMark(0, 0, "X");
        infiniteBoard.setMark(1, 0, "X");
        infiniteBoard.setMark(2, 0, "X");
        infiniteBoard.setMark(3, 0, "X");

        assertTrue(infiniteBoard.checkWin(player.getMark(), 4));
    }

    @Test
    public void CheckForWinRightDiagonalWinTest() {
        infiniteBoard.setMark(0, 0, "X");
        infiniteBoard.setMark(1, 1, "X");
        infiniteBoard.setMark(2, 2, "X");
        infiniteBoard.setMark(3, 3, "X");

        assertTrue(infiniteBoard.checkWin(player.getMark(), 4));
    }

    @Test
    public void CheckForWinLeftDiagonalWinTest() {
        infiniteBoard.setMark(0, 3, "X");
        infiniteBoard.setMark(1, 2, "X");
        infiniteBoard.setMark(2, 1, "X");
        infiniteBoard.setMark(3, 0, "X");

        assertTrue(infiniteBoard.checkWin(player.getMark(), 4));
    }

    @Test
    public void CheckForWinNoWinTest() {
        infiniteBoard.setMark(0, 0, "X");
        infiniteBoard.setMark(1, 1, "X");
        infiniteBoard.setMark(2, 2, "X");
        infiniteBoard.setMark(3, 3, "0");

        assertFalse(infiniteBoard.checkWin(player.getMark(), 4));
    }

    /**
     * Test to make sure moveUp returns true if first move on an infinite board
     * and returns false if first move on a set board
     */
    @Test
    public void moveUpTest() {
        assertTrue(infiniteBoard.moveUp());
        assertFalse(setBoard.moveUp());
    }

    @Test
    public void moveLeftTest() {
        assertTrue(infiniteBoard.moveLeft());
        assertFalse(setBoard.moveLeft());
    }

    /**
     * Test to make sure moveDown returns true if used on bottom row on infinite board
     * and returns false if on set board
     */

    @Test
    public void moveDownTest() {
        infiniteBoard.setCursorY(9);
        assertTrue(infiniteBoard.moveDown());
        setBoard.setCursorY(9);
        assertFalse(setBoard.moveDown());
    }

    /**
     * Test to make sure moveRight returns true if used on right most column
     *  on infinite board
     * and returns false if on set board
     */
    @Test
    public void moveRightTest() {
        infiniteBoard.setCursorX(9);
        assertTrue(infiniteBoard.moveRight());
        setBoard.setCursorX(9);
        assertFalse(setBoard.moveRight());
    }
    

    /**
     * Test to make sure board test prints properly
     */
    @Test
    public void PrintBoardTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        infiniteBoard.printBoard();
        String expectedOutput = "\033[32m[ ]\033[0m ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) " + System.lineSeparator()
                + "( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) " + System.lineSeparator()
                + "( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) " + System.lineSeparator()
                + "( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) " + System.lineSeparator()
                + "( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) " + System.lineSeparator()
                + "( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) " + System.lineSeparator()
                + "( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) " + System.lineSeparator()
                + "( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) " + System.lineSeparator()
                + "( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) " + System.lineSeparator()
                + "( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) " + System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());

    }

}

