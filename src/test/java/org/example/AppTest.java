package org.example;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.example.model.PlayBoard;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * The normal chess cannot cross the river
     * Move the chess ELEPHANT at (A, 3) to (A, 4), and check if the ELEPHANT can right cross the river
     */
    public void testIsLegalRiverCase1()
    {
        PlayBoard playBoard = new PlayBoard();
        try{
            playBoard.move('A', '7', 'S');
            playBoard.move('A', '3', 'W');
            playBoard.move('A', '9', 'D');
            playBoard.move('A', '4', 'D');
        }
        catch (Exception e){
            System.out.println("Caught exception: " + e.getMessage());
            assertEquals("This animal cannot jump over river or go to river!", e.getMessage());
        }
    }

    /**
     * The LION and TIGER can cross the river
     * Move the chess LION at (A, 9) to (A, 6), and check if the LION can right cross the river
     */
    public void testIsLegalRiverCase2()
    {
        PlayBoard playBoard = new PlayBoard();
        try{
            playBoard.move('A', '7', 'D');
            playBoard.move('G', '3', 'A');
            playBoard.move('A', '9', 'S');
            playBoard.move('F', '2', 'A');
            playBoard.move('A', '8', 'S');
            playBoard.move('C', '3', 'D');
            playBoard.move('A', '7', 'S');
            playBoard.move('D', '3', 'W');
            playBoard.move('A', '6', 'D');
        }
        catch (Exception e){
            assertEquals("This animal cannot jump over river or go to river!", e.getMessage());
        }
    }


    /**
     * The LION and TIGER cannot cross the river if there is a rat in the river
     * Move the chess TIGER at (A, 1) to (B, 3), move the chess RAT at (A, 7) to (B, 6), and check if the TIGER can right cross the river
     */
    public void testIsLegalRiverCase3()
    {
        PlayBoard playBoard = new PlayBoard();
        try{
            playBoard.move('A', '7', 'D');
            playBoard.move('A', '1', 'D');
            playBoard.move('B', '7', 'S');
            playBoard.move('B', '2', 'D');
            playBoard.move('E', '7', 'A');
            playBoard.move('B', '1', 'W');
            playBoard.move('G', '9', 'A');
            playBoard.move('B', '2', 'W');
            playBoard.move('F', '8', 'D');
            playBoard.move('B', '3', 'W');
        }
        catch (Exception e){
            assertEquals("There is a rat in the way! You can't cross the river!", e.getMessage());
        }
    }

    /**
     * Test the function of eating opponent pieces (Eat according to the normal rank)
     * Move the chess Lion (A,9) to (A,6), move the chess Elephant (A,3) to (A,6), to check whether a Lion can eat an Elephant or not.
     */
    public void testEatCase1()
    {
        PlayBoard playBoard = new PlayBoard();
        try{
            playBoard.move('A', '3', 'W');
            playBoard.move('A', '7', 'D');
            playBoard.move('A', '9', 'S');
            playBoard.move('A', '4', 'W');
            playBoard.move('A', '8', 'S');
            playBoard.move('A', '5', 'W');
            playBoard.move('A', '7', 'S');
        }
        catch (Exception e){
            assertEquals("You cannot eat this animal!", e.getMessage());
        }
    }

    /**
     * Test the function of eating opponent pieces (Rat eat elephant)
     * Move the chess Rat (A,7) to (A,5), move the chess Elephant (A,3) to (A,5), to check the special case that a rat can eat an elephant or not
     */
    public void testEatCase2()
    {
        PlayBoard playBoard = new PlayBoard();
        try{
            playBoard.move('A', '3', 'W');
            playBoard.move('A', '7', 'S');
            playBoard.move('A', '4', 'W');
            playBoard.move('A', '6', 'S');
        }
        catch (Exception e){
            assertEquals("You cannot eat this animal!", e.getMessage());
        }
    }


    /**
     * Test the function of eating opponent pieces (tiger jump over the river to catch a rat)
     * Move the chess Rat (A,7) to (B,7), move the chess Tiger (A,1) to (B,3) to (B,7), to check the special case that a tiger can jump over the river to catch a rat or not
     */
    public void testEatCase3()
    {
        PlayBoard playBoard = new PlayBoard();
        try{
            playBoard.move('A', '3', 'W');
            playBoard.move('A', '7', 'D');
            playBoard.move('A', '1', 'W');
            playBoard.move('E', '7', 'A');
            playBoard.move('A', '2', 'W');
            playBoard.move('D', '7', 'S');
            playBoard.move('A', '3', 'D');
            playBoard.move('D', '6', 'S');
            playBoard.move('B', '3', 'W');
        }
        catch (Exception e){
            System.out.println("Caught exception: " + e.getMessage());
            assertEquals("This animal cannot jump over river or go to river!", e.getMessage());
        }
        finally {
            System.out.println("END");
        }
    }



    /**
     * Test the function of eating opponent pieces (eat out of order in the trap)
     * Move the chess Wolf (C,3) to (D,8), move the chess Cat (F,8) to (D,8), to check the special case that a cat can eat a wolf in its own trap or not
     */
    public void testEatCase4()
    {
        PlayBoard playBoard = new PlayBoard();
        try{
            playBoard.move('C', '3', 'D');
            playBoard.move('F', '8', 'A');
            playBoard.move('D', '3', 'W');
            playBoard.move('G', '7', 'S');
            playBoard.move('D', '4', 'W');
            playBoard.move('G', '6', 'S');
            playBoard.move('D', '5', 'W');
            playBoard.move('G', '5', 'S');
            playBoard.move('D', '6', 'W');
            playBoard.move('G', '9', 'A');
            playBoard.move('D', '7', 'W');
            playBoard.move('E', '8', 'A');
        }
        catch (Exception e){
            assertEquals("You cannot eat this animal!", e.getMessage());
        }
    }


    /**
     * Test the function of eating opponent pieces (a rat in river cannot directly eat the elephant on land)
     * Move the chess Elephant (A,3) to (A,6), move the chess Cat (A,7) to (B,6) to (A,6), to check the special case that a rat in river can directly eat the elephant on land or not
     */
    public void testEatCase5()
    {
        PlayBoard playBoard = new PlayBoard();
        try{
            playBoard.move('A', '3', 'W');
            playBoard.move('A', '7', 'D');
            playBoard.move('A', '4', 'W');
            playBoard.move('B', '7', 'S');
            playBoard.move('A', '5', 'W');
            playBoard.move('B', '6', 'A');
        }
        catch (Exception e){
            assertEquals("You cannot eat this animal!", e.getMessage());
        }
    }

    /**
     * Test the warning part that players should not to enter their own den
     * Move the chess Cat (B,2) to (D,1) to see the warning
     */
    public void testGoOnOwnDen()
    {
        PlayBoard playBoard = new PlayBoard();
        try {
            playBoard.move('B', '2', 'S');
            playBoard.move('B', '8', 'W');
            playBoard.move('B', '1', 'D');
            playBoard.move('E', '7', 'A');
            playBoard.move('C', '1', 'D');
        } catch (Exception e) {
            assertEquals("You cannot go to your own den!", e.getMessage());
        }
    }



    public void testWin()
    {
        PlayBoard playBoard = new PlayBoard();
        try {
            playBoard.move('C', '3', 'D');
            playBoard.move('B', '8', 'W');
            playBoard.move('D', '3', 'W');
            playBoard.move('E', '7', 'D');
            playBoard.move('D', '4', 'W');
            playBoard.move('G', '7', 'S');
            playBoard.move('D', '5', 'W');
            playBoard.move('G', '6', 'S');
            playBoard.move('D', '6', 'W');
            playBoard.move('G', '5', 'S');
            playBoard.move('D', '7', 'W');
            playBoard.move('A', '7', 'S');
            playBoard.move('D', '8', 'W');
        } catch  (Exception e) {
            assertEquals("redWin", e.getMessage());
        }
    }
}