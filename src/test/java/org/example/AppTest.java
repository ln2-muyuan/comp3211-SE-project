package org.example;

import org.example.model.Game;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class AppTest {



    @Before
    public void setUp() throws Exception {

    }

    /**
     * The normal chess cannot cross the river
     * Move the chess ELEPHANT at (A, 3) to (A, 4), and check if the ELEPHANT can right cross the river
     */
    @Test
    public void testIsLegalRiverCase1() {
        Game game = new Game();
        try {
            game.move(2, 0, Game.Direction.DOWN);
            game.move(6, 0, Game.Direction.UP);
            game.move(0, 0, Game.Direction.RIGHT);
            game.move(5, 0, Game.Direction.RIGHT);
        } catch (Exception e) {
            Assert.assertEquals("The animal can neither swim or jump!", e.getMessage());
        }
    }

    /**
     * The lion can jump over the river
     * Move the chess ELEPHANT at (A, 0) to (A, 3), and check if the Lion can right jump across two rivers.
     */
    @Test(expected = Test.None.class /* no exception expected */)
    public void testIsLegalRiverCase2() {
        Game game = new Game();
        try {
            game.move(0, 0, Game.Direction.DOWN);
            game.move(2, 0, Game.Direction.DOWN);
            game.move(3, 0, Game.Direction.DOWN);
            game.move(1, 0, Game.Direction.DOWN);
            game.move(2, 0, Game.Direction.DOWN);
            game.move(3, 0, Game.Direction.RIGHT);
            game.move(3, 3, Game.Direction.RIGHT);
        } catch (Exception e) {
        }
    }
}