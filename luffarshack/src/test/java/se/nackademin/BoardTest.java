package se.nackademin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// import java.io.ByteArrayOutputStream;
// import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for the Board class
 * Create board
 * Place brick (X or 0)
 * Check Win
 * Check available square
 */
public class BoardTest {
    private Board board;
    private Player player;

    @Before
    public void setUp() {
        board = new Board();
        player = new Player();
    }

    /**
     * Test if choice of board size matches the lenght of the 2D array created
     */
    @Test
    public void BoardSizeTest() {
        int size = 10;
        assertEquals(board.getBoard().size(), size);
    }

    /** Test to make sure player with Mark X can mark a square */
    @Test
    public void PlaceMarkerTest() {
        board.getCurrentSquare().setMark(player.getMark());
        assertEquals(board.getCurrentSquare().getMark(), "(X)");
    }

    /**
     * Test to make sure, checkVailidPlacement returns true on available squares
     */
    @Test
    public void SquareAvailable() {
        assertTrue("Test Failed", board.checkValidPlacement());
    }

    /**
     * Test to make sure, checkVailidPlacement returns false on unavailable squares
     */
    @Test
    public void SquareAlreadyMarked() {
        board.getCurrentSquare().setMark("0");
        assertFalse("Test Failed", board.checkValidPlacement());

        board.getCurrentSquare().setMark("X");
        assertFalse("Test Failed", board.checkValidPlacement());
    }

    @Test
    public void CheckForWinRowWinTest() {
        board.getBoard().get(0).get(0).setMark("X");
        board.getBoard().get(0).get(1).setMark("X");
        board.getBoard().get(0).get(2).setMark("X");
        board.getBoard().get(0).get(3).setMark("X");

        assertTrue(board.checkForWin("X"));
    }

    @Test
    public void CheckForWinColumnWinTest() {
        board.getBoard().get(0).get(0).setMark("X");
        board.getBoard().get(1).get(0).setMark("X");
        board.getBoard().get(2).get(0).setMark("X");
        board.getBoard().get(3).get(0).setMark("X");

        assertTrue(board.checkForWin("X"));
    }

    @Test
    public void CheckForWinRightDiagonalWinTest() {
        board.getBoard().get(0).get(0).setMark("X");
        board.getBoard().get(1).get(1).setMark("X");
        board.getBoard().get(2).get(2).setMark("X");
        board.getBoard().get(3).get(3).setMark("X");

        assertTrue(board.checkForWin("X"));
    }

    @Test
    public void CheckForWinLeftDiagonalWinTest() {
        board.getBoard().get(0).get(3).setMark("X");
        board.getBoard().get(1).get(2).setMark("X");
        board.getBoard().get(2).get(1).setMark("X");
        board.getBoard().get(3).get(0).setMark("X");

        assertTrue(board.checkForWin("X"));
    }

    @Test
    public void testCheckForWin_noWin() {
        board.getBoard().get(0).get(0).setMark("X");
        board.getBoard().get(1).get(1).setMark("X");
        board.getBoard().get(2).get(2).setMark("X");
        board.getBoard().get(3).get(3).setMark("O");

        assertFalse(board.checkForWin("X"));
    }
    
    // @Test
    // public void testPrintBoard() {
    //     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    //     System.setOut(new PrintStream(outputStream));
    //     board.printBoard(" ");
    //     //String expectedOutput = "\n{ } ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) \n ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) \n ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) \n ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) \n ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) \n ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) \n ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) \n ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) \n ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) \n ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ";
    //     String expectedOutput = "{ } ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( )\n( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( )\n( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( )\n( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( )\n( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( )\n( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( )\n( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( )\n( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( )\n( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( )\n( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( )\n";

    //     assertEquals(expectedOutput, outputStream.toString());
    //     System.setOut(System.out);
    //     }


}
