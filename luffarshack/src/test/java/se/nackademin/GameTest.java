package se.nackademin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class GameTest {

    @Mock
    PlacementMenu placementMenu = mock(PlacementMenu.class);
    InfiniteBoard board = mock(InfiniteBoard.class);
    Helper helper = mock(Helper.class);

    Stats stats = new Stats();
    Game game = new Game(stats, "test", "test2", 0);
    Player player2 = new Player("test", "0");
    /**
     * Unnessecary test for the program, but mostly here for me to make sure the
     * mock works propely
     */
    @Test
    public void takeInputMockTest() {
        when(placementMenu.takeInput(Constants.MOVE_MENU_TEXT)).thenReturn("0");
        assertEquals(placementMenu.takeInput(Constants.MOVE_MENU_TEXT), "0");
    }

    @Test
    public void takeInputvxcMockTest() {
        when(helper.takeInput(Constants.MOVE_MENU_TEXT)).thenReturn("0");
        assertEquals(helper.takeInput(Constants.MOVE_MENU_TEXT), "0");
    }

    /**
     * Since game class doesnt take placement menu in constructor
     * I set games placement menu to the mocked placement menu
     * I think I get a red ok sign because I dont test the constructor
     */
    @Test
    public void twoPlayerGameTest() {
        game.setPlacementMenu(placementMenu);
        when(placementMenu.getQuit()).thenReturn(true);
        game.setPlacementMenu(placementMenu);
        assertFalse(game.twoPlayerGame());
    }

    /**
     * Test to make sure loop ends and method returns true
     * if player 2 wins the game
     */
    @Test
    public void twoPlayerGamePlayer2WonTest() {
        game.setPlacementMenu(placementMenu);
        when(placementMenu.runMenu(game.getPlayer(), stats, game)).thenReturn(true);
        when(placementMenu.getWinner()).thenReturn(true);
        assertFalse(game.twoPlayerGame());
    }
}