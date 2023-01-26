package se.nackademin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class MainMenuTest {

    @Mock
    Stats stats;
    @Mock
    Game game;
    @Mock
    InfiniteBoard board;
    @Mock
    ObjectInputStream ois;
    @Mock
    ObjectOutputStream oos;
    @Mock
    Scanner scanner;

    MainMenu mainMenu = new MainMenu(stats);

    @Test
    void IntCheckTest() {
        assertTrue(mainMenu.intCheck("123", 3));
        assertFalse(mainMenu.intCheck("123a", 4));
        assertTrue(mainMenu.intCheck("0", 1));
    }

    @Test
    void ValidSizeCheckTest() {
        assertTrue(mainMenu.validSizeCheck(0));
        assertTrue(mainMenu.validSizeCheck(5));
        assertTrue(mainMenu.validSizeCheck(100));
        assertFalse(mainMenu.validSizeCheck(-1));
        assertFalse(mainMenu.validSizeCheck(101));
    }

    // @Test
    // void testLoadGame() throws ClassNotFoundException, IOException {
    //     when(ois.readObject()).thenReturn(game);
    //     assertTrue(mainMenu.loadGame("filename") == game);
    // }

    // @Test
    // void testLoadStats() throws ClassNotFoundException, IOException {
    //     when(ois.readObject()).thenReturn(stats);
    //     assertTrue(mainMenu.loadStats("filename") == stats);
    // }

    // @Test
    // public void takeInputTest() {
    //     Scanner mockScanner = mock(Scanner.class);
    //     when(mockScanner.next()).thenReturn("test", "test2", "0");
    //     when(mainMenu.takeInput("Enter player 1 name: ", mockScanner)).thenReturn("test");
    //     when(mainMenu.takeInput("Enter player 2 name: ", mockScanner)).thenReturn("test2");
    //     when(mainMenu.takeInput("Enter Board Size 5-100 or 0 for infinite map", mockScanner)).thenReturn("0");
    //     when(mainMenu.startTwoPlayerGame(stats, "test", "test2", 0)).thenReturn(true);
    //     assertTrue(mainMenu.handleChoice("1"));

        
    // }

    // @Test
    // public void startTwoPlayerGameTest() {

    //     Game game = mock(Game.class);
    //     when(game.twoPlayerGame()).thenReturn(true);
    //     assertTrue(mainMenu.startTwoPlayerGame(stats, "test", "test2", 0));
    // }



}