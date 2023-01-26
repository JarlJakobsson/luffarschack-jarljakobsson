package se.nackademin;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class MainMenuTest {

    @Mock
    Stats stats;

    MainMenu mainMenu = new MainMenu(stats);

    @Test
    void IntCheckTest() {
        assertTrue(mainMenu.intCheck("123"));
        assertFalse(mainMenu.intCheck("123a"));
        assertTrue(mainMenu.intCheck("0"));
    }

    @Test
    void ValidSizeCheckTest() {
        assertTrue(mainMenu.validSizeCheck(0));
        assertTrue(mainMenu.validSizeCheck(5));
        assertTrue(mainMenu.validSizeCheck(100));
        assertFalse(mainMenu.validSizeCheck(-1));
        assertFalse(mainMenu.validSizeCheck(101));
    }
}