package org.example;

import org.example.model.Game;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class AppTest {

    @Before
    public void setUp() throws Exception {
    }

    /**
     * Transform the old input to new input
     */
    public Integer[] transform(char input1, char input2) {
        Integer[] output = new Integer[2];
        switch (input1) {
            case 'A' -> output[1] = 0;
            case 'B' -> output[1] = 1;
            case 'C' -> output[1] = 2;
            case 'D' -> output[1] = 3;
            case 'E' -> output[1] = 4;
            case 'F' -> output[1] = 5;
            case 'G' -> output[1] = 6;
        }
        switch (input2) {
            case '1' -> output[0] = 8;
            case '2' -> output[0] = 7;
            case '3' -> output[0] = 6;
            case '4' -> output[0] = 5;
            case '5' -> output[0] = 4;
            case '6' -> output[0] = 3;
            case '7' -> output[0] = 2;
            case '8' -> output[0] = 1;
            case '9' -> output[0] = 0;
        }
        return output;
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
            game.printMap();
        } catch (Exception e) {
            assertEquals("The animal can neither swim or jump!", e.getMessage());
        }
    }

    /**
     * The lion can jump over the river
     * Move the chess Lion at (A, 7) to (G, 6), and check if the Lion can right jump across two rivers.
     */
    @Test
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
            game.printMap();
        } catch (Exception e) {
        }
    }

    /**
     * The LION and TIGER cannot cross the river if there is a rat in the river
     * Move the chess TIGER at (A, 1) to (B, 3), move the chess RAT at (A, 7) to (B, 6), and check if the TIGER can right cross the river
     */
    @Test
    public void testIsLegalRiverCase3()
    {
        Game game = new Game();
        try{
            game.move(2, 0, Game.Direction.RIGHT);
            game.move(8, 0, Game.Direction.RIGHT);
            game.move(2, 1, Game.Direction.DOWN);
            game.move(7, 1, Game.Direction.RIGHT);
            game.move(2, 4, Game.Direction.LEFT);
            game.move(8, 1, Game.Direction.UP);
            game.move(0, 6, Game.Direction.LEFT);
            game.move(7, 1, Game.Direction.UP);
            game.move(1, 5, Game.Direction.RIGHT);
            game.move(6, 1, Game.Direction.UP);
            game.printMap();
        }
        catch (Exception e){
            assertEquals("The animal cannot jump over a swimming rat.", e.getMessage());
        }
    }

    /**
     * Test the function of eating opponent pieces (Eat according to the normal rank)
     * Move the chess Lion (A,9) to (A,6), move the chess Elephant (A,3) to (A,6), to check whether a Lion can eat an Elephant or not.
     */
    @Test
    public void testEatCase1()
    {
        Game game = new Game();
        try{
            game.move(transform('A', '3')[0], transform('A', '3')[1], Game.Direction.UP);
            game.move(transform('A', '7')[0], transform('A', '7')[1], Game.Direction.RIGHT);
            game.move(transform('A', '9')[0], transform('A', '9')[1], Game.Direction.DOWN);
            game.move(transform('A', '4')[0], transform('A', '4')[1], Game.Direction.UP);
            game.move(transform('A', '8')[0], transform('A', '8')[1], Game.Direction.DOWN);
            game.move(transform('A', '5')[0], transform('A', '5')[1], Game.Direction.UP);
            game.move(transform('A', '7')[0], transform('A', '7')[1], Game.Direction.DOWN);
            game.printMap();
        }
        catch (Exception e){
            assertEquals("Lion can't eat Elephant", e.getMessage());
        }
    }

    /**
     * Test the function of eating opponent pieces (tiger jump over the river to catch a rat)
     * Move the chess Rat (A,7) to (B,7), move the chess Tiger (A,1) to (B,3) to (B,7), to check the special case that a tiger can jump over the river to catch a rat or not
     */
    @Test
    public void testEatCase2()
    {
        Game game = new Game();
        try{
            game.move(transform('A', '3')[0], transform('A', '3')[1], Game.Direction.UP);
            game.move(transform('A', '7')[0], transform('A', '7')[1], Game.Direction.RIGHT);
            game.move(transform('A', '1')[0], transform('A', '1')[1], Game.Direction.UP);
            game.move(transform('E', '7')[0], transform('E', '7')[1], Game.Direction.LEFT);
            game.move(transform('A', '2')[0], transform('A', '2')[1], Game.Direction.UP);
            game.move(transform('D', '7')[0], transform('D', '7')[1], Game.Direction.DOWN);
            game.move(transform('A', '3')[0], transform('A', '3')[1], Game.Direction.RIGHT);
            game.move(transform('D', '6')[0], transform('D', '6')[1], Game.Direction.DOWN);
            game.move(transform('B', '3')[0], transform('B', '3')[1], Game.Direction.UP);
            game.printMap();
        }
        catch (Exception e){
            System.out.println("Caught exception: " + e.getMessage());
        }
    }

    /**
     * Test the function of eating opponent pieces (a rat in river cannot directly eat the elephant on land)
     * Move the chess Elephant (A,3) to (A,6), move the chess Cat (A,7) to (B,6) to (A,6), to check the special case that a rat in river can directly eat the elephant on land or not
     */
    @Test
    public void testEatCase3()
    {
        Game game = new Game();
        try{
            game.move(transform('A', '3')[0], transform('A', '3')[1], Game.Direction.UP);
            game.move(transform('A', '7')[0], transform('A', '7')[1], Game.Direction.RIGHT);
            game.move(transform('A', '4')[0], transform('A', '4')[1], Game.Direction.UP);
            game.move(transform('B', '7')[0], transform('B', '7')[1], Game.Direction.DOWN);
            game.move(transform('A', '5')[0], transform('A', '5')[1], Game.Direction.UP);
            game.move(transform('B', '6')[0], transform('B', '6')[1], Game.Direction.LEFT);
        }
        catch (Exception e){
            assertEquals("Rat can't eat Elephant when swimming.", e.getMessage());
        }
    }

    /**
     * Test the warning part that players should not step onto their own chess
     * Move the chess Tiger onto its own Elephant from (A,1) to (A,3) to see the warning
     */
    @Test
    public void testWrongStep_OnOwnChess()
    {
        Game game = new Game();
        try {
            game.move(transform('A', '1')[0], transform('A', '1')[1], Game.Direction.UP);
            game.move(transform('A', '7')[0], transform('A', '7')[1], Game.Direction.DOWN);
            game.move(transform('A', '2')[0], transform('A', '2')[1], Game.Direction.UP);
        } catch (Exception e) {
            assertEquals("You cannot eat your team's chess.", e.getMessage());
        }
    }

    /**
     * The trapped case
     */
    @Test
    public void testTrap() {
        Game game = new Game();
        try {
            game.move(0, 0, Game.Direction.DOWN);
            game.move(2, 0, Game.Direction.DOWN);
            game.move(3, 0, Game.Direction.DOWN);
            game.move(1, 0, Game.Direction.DOWN);
            game.move(2, 0, Game.Direction.RIGHT);
            game.move(7, 1, Game.Direction.UP);
            game.move(2, 1, Game.Direction.DOWN);
            game.move(6, 1, Game.Direction.RIGHT);
            game.move(6, 2, Game.Direction.RIGHT);
            game.move(6, 3, Game.Direction.DOWN);
            game.move(7, 5, Game.Direction.LEFT);
            game.move(7, 4, Game.Direction.LEFT);
            game.printMap();
        } catch (Exception e) {
        }
    }

    /**
     * Test a winning ways to capturing the den
     */
    @Test
    public void testWin_OnDen()
    {
        Game game = new Game();
        try {
            game.move(transform('C', '3')[0], transform('C', '3')[1], Game.Direction.RIGHT);
            game.move(transform('B', '8')[0], transform('B', '8')[1], Game.Direction.UP);
            game.move(transform('D', '3')[0], transform('D', '3')[1], Game.Direction.UP);
            game.move(transform('E', '7')[0], transform('E', '7')[1], Game.Direction.RIGHT);
            game.move(transform('D', '4')[0], transform('D', '4')[1], Game.Direction.UP);
            game.move(transform('G', '7')[0], transform('G', '7')[1], Game.Direction.DOWN);
            game.move(transform('D', '5')[0], transform('D', '5')[1], Game.Direction.UP);
            game.move(transform('G', '6')[0], transform('G', '6')[1], Game.Direction.DOWN);
            game.move(transform('D', '6')[0], transform('D', '6')[1], Game.Direction.UP);
            game.move(transform('G', '5')[0], transform('G', '5')[1], Game.Direction.DOWN);
            game.move(transform('D', '7')[0], transform('D', '7')[1], Game.Direction.UP);
            game.move(transform('A', '7')[0], transform('A', '7')[1], Game.Direction.DOWN);
            game.move(transform('D', '8')[0], transform('D', '8')[1], Game.Direction.UP);
            game.printMap();
        } catch  (Exception e) {
        }
        assertEquals(game.getGameState(), Game.GameState.BLUEWIN);
    }
}