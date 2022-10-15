package org.example;

import org.example.model.PlayBoard;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Controller {
    public void intro(){

    }
    public void playNewGame(){
        PlayBoard playBoard = new PlayBoard();
        playBoard.printBoard();

        boolean error = true;
        while (error) {
            int turn = 1;
            while (turn % 2 != 0) {
                try {
                    System.out.println("Choose an X Coordinate (A, B, C, D, E, F, G): ");
                    char xCoordinateC;
                    while (true) {
                        Scanner input = new Scanner(System.in);
                        String xCoordinateS = input.nextLine();
                        xCoordinateC = xCoordinateS.charAt(0);
                        int len = xCoordinateS.length();
                        if (len == 1){
                            if (xCoordinateC == 'A' || xCoordinateC == 'B' || xCoordinateC == 'C' || xCoordinateC == 'D' || xCoordinateC == 'E' || xCoordinateC == 'F' || xCoordinateC == 'G') {
                                break;
                            }
                        }
                        System.out.println("Invalid input. Please try again: ");
                    }

                    System.out.println("Choose a Y Coordinate (1, 2, 3, 4, 5, 6, 7, 8, 9): ");
                    char yCoordinateC;
                    while (true) {
                        Scanner input = new Scanner(System.in);
                        String yCoordinateS = input.nextLine();
                        yCoordinateC = yCoordinateS.charAt(0);
                        int len = yCoordinateS.length();
                        if (len == 1){
                            if (yCoordinateC == '1' || yCoordinateC == '2' || yCoordinateC == '3' || yCoordinateC == '4' || yCoordinateC == '5' || yCoordinateC == '6' || yCoordinateC == '7' || yCoordinateC == '8' || yCoordinateC == '9') {
                                break;
                            }
                        }
                        System.out.println("Invalid input. Please try again: ");
                    }

                    System.out.println("Choose a Direction (W, D, S, A): ");
                    char directionC;
                    while (true) {
                        Scanner input = new Scanner(System.in);
                        String directionS = input.nextLine();
                        directionC = directionS.charAt(0);
                        int len = directionS.length();
                        if (len == 1){
                            if (directionC == 'W' || directionC == 'D' || directionC == 'S' || directionC == 'A') {
                                break;
                            }
                        }
                        System.out.println("Invalid input. Please try again: ");
                    }

                    playBoard.move(xCoordinateC, yCoordinateC, directionC);
                    error = false;

                } catch (Exception e) {
                    System.out.println(e);
                }
                playBoard.printBoard();
                turn++;
            }

            while (turn % 2 == 0) {
                try {
                    System.out.println("Choose an X Coordinate (A, B, C, D, E, F, G): ");
                    char xCoordinateC;
                    while (true) {
                        Scanner input = new Scanner(System.in);
                        String xCoordinateS = input.nextLine();
                        xCoordinateC = xCoordinateS.charAt(0);
                        int len = xCoordinateS.length();
                        if (len == 1){
                            if (xCoordinateC == 'A' || xCoordinateC == 'B' || xCoordinateC == 'C' || xCoordinateC == 'D' || xCoordinateC == 'E' || xCoordinateC == 'F' || xCoordinateC == 'G')
                                break;
                        }
                        System.out.println("Invalid input. Please try again: ");
                    }

                    System.out.println("Choose a Y Coordinate (1, 2, 3, 4, 5, 6, 7, 8, 9): ");
                    char yCoordinateC;
                    while (true) {
                        Scanner input = new Scanner(System.in);
                        String yCoordinateS = input.nextLine();
                        yCoordinateC = yCoordinateS.charAt(0);
                        int len = yCoordinateS.length();
                        if (len == 1){
                            if (yCoordinateC == '1' || yCoordinateC == '2' || yCoordinateC == '3' || yCoordinateC == '4' || yCoordinateC == '5' || yCoordinateC == '6' || yCoordinateC == '7' || yCoordinateC == '8' || yCoordinateC == '9')
                                break;
                        }
                        System.out.println("Invalid input. Please try again: ");
                    }

                    System.out.println("Choose a Direction (W, D, S, A): ");
                    char directionC;
                    while (true) {
                        Scanner input = new Scanner(System.in);
                        String directionS = input.nextLine();
                        directionC = directionS.charAt(0);
                        int len = directionS.length();
                        if (len == 1){
                            if (directionC == 'W' || directionC == 'D' || directionC == 'S' || directionC == 'A') break;
                        }
                        System.out.println("Invalid input. Please try again: ");
                    }

                    playBoard.move(xCoordinateC, yCoordinateC, directionC);
                    error = false;

                } catch (Exception e) {
                    System.out.println(e);
                }
                playBoard.printBoard();
                turn++;
            }
        }

    }

    public void pauseGame(PlayBoard board){
        System.out.println("0");
    }

    public void resumeGame(PlayBoard board){

    }

    public void restartGame(PlayBoard board){

    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.playNewGame();
    }
}
