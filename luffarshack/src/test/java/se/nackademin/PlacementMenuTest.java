package se.nackademin;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

/**
 * Unit test for the Board class
 * Create board
 * Place brick (X or 0)
 * Check Win
 * Check available square
 */
public class PlacementMenuTest {
    private Player player;
    private InfiniteBoard board;
    private PlacementMenu placementMenu;
    private Stats stats;
    private Game game;

    @Mock
    Helper helper = mock(Helper.class);

    @BeforeEach
    public void setUp() {
        player = new Player("tester", "X");
        board = new InfiniteBoard(0);
        stats = new Stats();
        placementMenu = new PlacementMenu(board);
        game = new Game(stats, "test", "test2", 0);
    }

    public InfiniteBoard getBoard() {
        return board;
    }

    /**
     * Test if w returns true if trying to move up as first move with infinite and
     * set boardsize
     */
    @Test
    public void handleChoiceWTest() {
        assertTrue(placementMenu.handleChoice("w", player, stats, game));
        board = new InfiniteBoard(5);
        assertTrue(placementMenu.handleChoice("w", player, stats, game));
    }

    @Test
    public void handleChoiceSTest() {
        assertTrue(placementMenu.handleChoice("s", player, stats, game));
        board = new InfiniteBoard(5);
        assertTrue(placementMenu.handleChoice("s", player, stats, game));
    }

    @Test
    public void handleChoiceATest() {
        assertTrue(placementMenu.handleChoice("a", player, stats, game));
        board = new InfiniteBoard(5);
        assertTrue(placementMenu.handleChoice("a", player, stats, game));
    }

    @Test
    public void handleChoiceDTest() {
        assertTrue(placementMenu.handleChoice("d", player, stats, game));
        board = new InfiniteBoard(5);
        assertTrue(placementMenu.handleChoice("d", player, stats, game));
    }

    /** 
     * Test if invalid input returns true
    */
    @Test
    public void handleChoiceInvalidChoiceTest() {
        assertTrue(placementMenu.handleChoice("INVALID CHOICE", player, stats, game));
    }

    /**
     * Test to check if e returns false after succesfull mark placement
     * or True if unsucsessful
     */
    @Test
    public void handleChoiceETest() {
        assertFalse(placementMenu.handleChoice("e", player, stats, game));
        board.setMark(0, 0, "X");
        assertTrue(placementMenu.handleChoice("e", player, stats, game));
    }

    @Test
    public void handleChoiceEWinTest() {
        placementMenu.setHelper(helper);
        when(helper.takeInput("\n*** tester Won ***\n")).thenReturn("0");

        board.setMark(0, 1, "X");
        board.setMark(0, 2, "X");
        board.setMark(0, 3, "X");

        assertFalse(placementMenu.handleChoice("e", player, stats, game));
    }

    @Test
    public void handleChoiceQTest() {
        assertFalse(placementMenu.handleChoice("q", player, stats, game));
    }


}
