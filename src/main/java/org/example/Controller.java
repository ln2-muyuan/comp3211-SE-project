package org.example;

import org.example.model.Block;
import org.example.model.PlayBoard;
import org.example.util.Ansi;
import java.util.Scanner;

public class Controller {
    private final View view = new View();
    public void playNewGame(){
        PlayBoard playBoard = new PlayBoard();
        Block[][] temp;
        temp = playBoard.getBoard();
        view.print(temp);
        int turn = 1;
        while (true) {
            if (turn % 2 == 1) {
                System.out.println("Red's turn");
            } else {
                System.out.println("Blue's turn");
            }

            boolean coordinateValid;
            char xCoordinateC = ' ';
            char yCoordinateC = ' ';
            do{
                coordinateValid = true;
                try {
                    while (true) {
                        System.out.print("Choose an X Coordinate (A, B, C, D, E, F, G): ");
                        Scanner input = new Scanner(System.in);
                        String xCoordinateS = input.nextLine();
                        xCoordinateC = xCoordinateS.charAt(0);
                        int len = xCoordinateS.length();
                        if (len == 1){
                            if (xCoordinateC == 'A' || xCoordinateC == 'B' || xCoordinateC == 'C' || xCoordinateC == 'D' || xCoordinateC == 'E' || xCoordinateC == 'F' || xCoordinateC == 'G') {
                                break;
                            }
                        }
                        System.out.println("Invalid input. Please try again.");
                    }
                    while (true) {
                        System.out.print("Choose a Y Coordinate (1, 2, 3, 4, 5, 6, 7, 8, 9): ");
                        Scanner input = new Scanner(System.in);
                        String yCoordinateS = input.nextLine();
                        yCoordinateC = yCoordinateS.charAt(0);
                        int len = yCoordinateS.length();
                        if (len == 1){
                            if (yCoordinateC == '1' || yCoordinateC == '2' || yCoordinateC == '3' || yCoordinateC == '4' || yCoordinateC == '5' || yCoordinateC == '6' || yCoordinateC == '7' || yCoordinateC == '8' || yCoordinateC == '9') {
                                break;
                            }
                        }
                        System.out.println("Invalid input. Please try again.");
                    }
                    playBoard.checkLegalInput(xCoordinateC, yCoordinateC);
                }
                catch (Exception e){
                    System.out.println(e);
                    coordinateValid = false;
                }
            } while (!coordinateValid);
            boolean moveValid;
            do{
                moveValid = true;
                try {
                    System.out.print("Choose a Direction (W, D, S, A): ");
                    char directionC;
                    while (true) {
                        Scanner input = new Scanner(System.in);
                        String directionS = input.nextLine();
                        directionC = directionS.charAt(0);
                        int len = directionS.length();
                        if (len == 1) {
                            if (directionC == 'W' || directionC == 'D' || directionC == 'S' || directionC == 'A') {
                                break;
                            }
                        }
                        System.out.println("Invalid input. Please try again.");
                    }
                    playBoard.move(xCoordinateC, yCoordinateC, directionC);
                } catch (Exception e) {
                    System.out.println(e);
                    moveValid = false;
                }
            } while (!moveValid);

            System.out.println("Move successful.");
            temp = playBoard.getBoard();
            view.print(temp);
            turn++;
        }
    }


    public void intro(){
        System.out.println("Welcome to the chess game!");
        playNewGame();
    }


    public static void main(String[] args) {
        String name = "Chess Game";
        String msg = Ansi.Red.and(Ansi.BgYellow).format("Hello %s", name);
        System.out.println(msg);
        Controller controller = new Controller();
        controller.intro();
    }
}
