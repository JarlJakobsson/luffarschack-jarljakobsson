package se.nackademin;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class ComputerTest {

    InfiniteBoard board = new InfiniteBoard(0);
    Computer computer = new Computer(board);
    List<Move> moves = new ArrayList<Move>();
    
    /**
     * Test to see if computer will place a winning move
     * when 3 "0" in a row
     */
    @Test
    public void winCheckTest() {

        computer.setMoves(moves);
        board.setMark(0, 0, "0");
        board.setMark(0, 1, "0");
        board.setMark(0, 2, "0");

        computer.checkWin(4);
        assertTrue(board.checkWin("0", 4));
        assertEquals(board.getMark(0, 3), "0");
    }

    @Test
    public void winCheckFailTest() {

        board.setMark(0, 0, "0");
        board.setMark(0, 1, "0");
        board.setMark(0, 2, "X");

        computer.checkWin(4);
        assertFalse(board.checkWin("X", 4));
        assertEquals(board.getMark(0, 3), " ");
    }

    /**
     * Test to see if computer will block the player at
     * square 0,3, and check if computer marks it
     */
    @Test
    public void checkBlockTest() {

        computer.setMoves(moves);
        board.setMark(0, 0, "X");
        board.setMark(0, 1, "X");
        board.setMark(0, 2, "X");

        computer.checkBlock(4);
        assertEquals(board.getMark(0, 3), "0");
    }

    @Test
    public void checkBlockFailTest() {

        board.setMark(0, 0, "X");
        board.setMark(0, 1, "X");
        board.setMark(0, 2, "0");

        
        assertFalse(computer.checkBlock(4));
        assertEquals(board.getMark(0, 3), " ");
    }

    @Test
    public void randomMoveTest() {
        computer.setMoves(moves);
        assertTrue(computer.randomMove());
    }

    @Test
    public void makeMoveTest() {
        computer.setMoves(moves);
        assertTrue(computer.makeMove());
    }
}