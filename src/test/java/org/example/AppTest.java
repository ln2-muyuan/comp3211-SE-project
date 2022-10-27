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
            assertEquals("This animal cannot jump over river or go to river!", e.getMessage());
        }
    }

    /**
     * The LION and TIGER can cross the river
     * Move the chess LION at (A, 9) to (A, 6), and check if the LION can right cross the river
     */
    public void testIsLegalRiverCase2(){

    }


    /**
     * The LION and TIGER cannot cross the river if there is a cat in the river
     * Move the chess TIGER at (A, 1) to (B, 3), move the chess RAT at (A, 7) to (B, 6), and check if the TIGER can right cross the river
     */
    public void testIsLegalRiverCase3(){

    }

}
