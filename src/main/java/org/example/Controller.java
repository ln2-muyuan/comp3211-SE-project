package org.example;

import org.example.model.Block;
import org.example.model.PlayBoard;
import org.example.util.Ansi;

import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class Controller {
    private final View view = new View();
    private final HashMap<String, PlayBoard> cache = new HashMap<>();
    /**
     * This method is used to continue a game, regardless of whether it is a new game or a saved game.
     */
    private void continueGame(PlayBoard playBoard) {
        Block[][] temp;
        temp = playBoard.getBoard();
        view.print(temp);
        while (true) {
            if (playBoard.getTurnCount() % 2 == 0) {
                String red = new Ansi(Ansi.RED, Ansi.ITALIC).format("Red's turn");
                view.println(red);
            } else {
                String blue = new Ansi(Ansi.BLUE, Ansi.ITALIC).format("Blue's turn");
                view.println(blue);
            }


            //check whether users' input coordinate is correct
            boolean coordinateValid;
            char xCoordinateC = ' ';
            char yCoordinateC = ' ';
            do{
                coordinateValid = true;
                try {
                    while (true) {
                        view.print("Choose an X Coordinate (A, B, C, D, E, F, G): ");
                        Scanner input = new Scanner(System.in);
                        String xCoordinateS = input.nextLine();
                        xCoordinateC = xCoordinateS.charAt(0);
                        int len = xCoordinateS.length();
                        if (len == 1){
                            if (xCoordinateC == 'A' || xCoordinateC == 'B' || xCoordinateC == 'C' || xCoordinateC == 'D' || xCoordinateC == 'E' || xCoordinateC == 'F' || xCoordinateC == 'G') {
                                break;
                            }
                        }
                        view.println("Invalid input. Please try again.");
                    }
                    while (true) {
                        view.print("Choose a Y Coordinate (1, 2, 3, 4, 5, 6, 7, 8, 9): ");
                        Scanner input = new Scanner(System.in);
                        String yCoordinateS = input.nextLine();
                        yCoordinateC = yCoordinateS.charAt(0);
                        int len = yCoordinateS.length();
                        if (len == 1){
                            if (yCoordinateC == '1' || yCoordinateC == '2' || yCoordinateC == '3' || yCoordinateC == '4' || yCoordinateC == '5' || yCoordinateC == '6' || yCoordinateC == '7' || yCoordinateC == '8' || yCoordinateC == '9') {
                                break;
                            }
                        }
                        view.println("Invalid input. Please try again.");
                    }
                    playBoard.checkLegalInput(xCoordinateC, yCoordinateC);
                }
                catch (Exception e){
                    view.print(e.getMessage());
                    view.println(" Please try again.");
                    coordinateValid = false;
                }
            } while (!coordinateValid);


            //check whether chess's movement is correct
            boolean moveValid;
            do{
                moveValid = true;
                try {
                    view.print("Choose a Direction (W, D, S, A): ");
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
                        view.print("Invalid input. Please input the direction again:");
                    }
                    playBoard.move(xCoordinateC, yCoordinateC, directionC);
                } catch (Exception e) {
                    view.print(e.getMessage());
                    view.println(" Please try again.");
                    moveValid = false;
                }
            } while (!moveValid);
            view.println("Move successful.");


            boolean inputValid;
            inputValid = true;
            while(inputValid) {
                view.print("Do you want to continue the game? (Y/N) ");
                Scanner input = new Scanner(System.in);
                String ifContinue = input.nextLine();
                if (Objects.equals(ifContinue, "N")) {
                    view.print("Do you want to pause or quit? (P/Q) ");
                    input = new Scanner(System.in);
                    String pauseOrQuit = input.nextLine();
                    //pause situation
                    if (Objects.equals(pauseOrQuit, "P")) {
                        view.println("The game is paused. You can continue the game in 'Load game' menu. ");
                        return;
                    }
                    //quit situation
                    else if (Objects.equals(pauseOrQuit, "Q")) {
                        inputValid = false;
                        if (playBoard.getTurnCount() % 2 == 1) {
                            playBoard.checkWhoQuit("red");
                        } else { playBoard.checkWhoQuit("blue");}
                    }
                    else {
                        view.println("Invalid input. Please try again.");
                    }
                }


                else if (Objects.equals(ifContinue, "Y"))
                    inputValid = false;
                else{
                    view.println("Invalid input. Please try again.");
                }
            }
            //check whether the game is over
            if (!playBoard.getGameState().equals("progressing")){
                String red = new Ansi(Ansi.RED, Ansi.ITALIC).format("Red wins!");
                String blue = new Ansi(Ansi.BLUE, Ansi.ITALIC).format("Blue wins!");
                switch (playBoard.getGameState()) {
                    case "redWin" -> view.println(red);
                    case "blueWin" -> view.println(blue);
                    case "redQuit" -> view.println("Red quits. " + blue);
                    case "blueQuit" -> view.println("Blue quits. " + red);
                }
                break;
            }
            temp = playBoard.getBoard();
            view.print(temp);
        }
    }
    private void newGame(){
        view.print("Please enter your new game's name: ");
        while (true) {
            Scanner input = new Scanner(System.in);
            String gameName = input.nextLine();
            if (cache.containsKey(gameName)) {
                view.println("This name has been used. Please try again.");
            }
            else {
                cache.put(gameName, new PlayBoard());
                view.println("New game created.");
                while (true) {
                    view.print("Do you want to start the game? (Y/N) ");
                    input = new Scanner(System.in);
                    String ifStart = input.nextLine();
                    if (Objects.equals(ifStart, "Y")) {
                        continueGame(cache.get(gameName));
                        break;
                    }
                    else if (Objects.equals(ifStart, "N")) {
                        break;
                    }
                    else {
                        view.println("Invalid input. Please try again.");
                    }
                }
                break;
            }
        }
    }
    private void loadGame(){
        if (cache.isEmpty()){
            view.println("There is no saved game. Please start a new game.");
        }
        else {
            view.println("The saved games are: ");
            //if game state is end , inform the user which side wins
            for (String key : cache.keySet()) {
                view.println(key + " - " + cache.get(key).getGameState());
            }
            view.print("Please enter the game's name you want to load: (Q to quit) ");
            do{
                Scanner input = new Scanner(System.in);
                String gameName = input.nextLine();
                if (cache.containsKey(gameName)){
                    if (cache.get(gameName).getGameState().equals("progressing")){
                        view.println("Welcome back!");
                        continueGame(cache.get(gameName));
                        return;
                    }
                    else {
                        view.print("The game is over. Please choose another game: (Q to quit)");
                    }
                }
                else if (gameName.equals("Q")){
                    return;
                }
                else {
                    view.print("There is no game with this name. Please try again: ");
                }
            } while (true);
        }
    }
    private void mainMenu() {
        view.print("1. New Game\n2. Load Game\n3. Exit\n");
        view.print("Please enter your choice: ");
        Scanner input = new Scanner(System.in);
        String choice = input.nextLine();
        switch (choice) {
            case "1" -> newGame();
            case "2" -> loadGame();
            case "3" -> {
                view.print("Thank you for playing!");
                System.exit(0);
            }
            default -> {
                view.println("Invalid input. Please try again.");
                mainMenu();
            }
        }
    }



    public void intro() {
        String title = "Welcome to the Jungle Game";
        String msg = Ansi.Italic.and(Ansi.HighIntensity).and(Ansi.Cyan).format("%s", title);
        view.println(msg);
        while (true) {
            mainMenu();
        }
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.intro();
    }
}