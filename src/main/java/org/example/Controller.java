package org.example;

import org.example.model.Game;
import org.example.util.Ansi;

import java.util.HashMap;
import java.util.Scanner;

public class Controller {
    private final View view = new View();
    private final HashMap<String, Game> cache = new HashMap<>();
    private Integer[] converter(char X, char Y){
        Integer[] coordinates = new Integer[2];
        switch (X) {
            case 'A' -> coordinates[1] = 0;
            case 'B' -> coordinates[1] = 1;
            case 'C' -> coordinates[1] = 2;
            case 'D' -> coordinates[1] = 3;
            case 'E' -> coordinates[1] = 4;
            case 'F' -> coordinates[1] = 5;
            case 'G' -> coordinates[1] = 6;
        }
        switch (Y) {
            case '1' -> coordinates[0] = 8;
            case '2' -> coordinates[0] = 7;
            case '3' -> coordinates[0] = 6;
            case '4' -> coordinates[0] = 5;
            case '5' -> coordinates[0] = 4;
            case '6' -> coordinates[0] = 3;
            case '7' -> coordinates[0] = 2;
            case '8' -> coordinates[0] = 1;
            case '9' -> coordinates[0] = 0;
        }
        return coordinates;
    }
    private void newGame() {
        View.print("Please enter your new game's name: ");
        while (true) {
            Scanner input = new Scanner(System.in);
            String gameName = input.nextLine();
            if (cache.containsKey(gameName)) {
                View.println("This name has been used. Please try again.");
            }
            else {
                cache.put(gameName, new Game());
                View.println("New game created.");
                while (true) {
                    View.print("Do you want to start the game? (Y/N) ");
                    input = new Scanner(System.in);
                    String ifStart = input.nextLine();
                    if (ifStart.equals("Y")) {
                        continueGame(cache.get(gameName));
                        break;
                    }
                    else if (ifStart.equals("N")) {
                        break;
                    }
                    else {
                        View.println("Invalid input. Please try again.");
                    }
                }
                break;
            }
        }

    }
    private void continueGame(Game game) {
        game.printMap();
        while (true) {
            if (game.getGameState() == Game.GameState.REDTURN) {
                String red = new Ansi(Ansi.RED, Ansi.ITALIC).format("Red's turn");
                View.println(red);
            } else {
                String blue = new Ansi(Ansi.BLUE, Ansi.ITALIC).format("Blue's turn");
                View.println(blue);
            }

            while (true) {
                //check whether users' input coordinate is correct
                char xCoordinateC;
                char yCoordinateC;
                while (true){
                    while (true) {
                        View.print("Choose an X Coordinate (A, B, C, D, E, F, G): ");
                        Scanner input = new Scanner(System.in);
                        String xCoordinateS = input.nextLine();
                        xCoordinateC = xCoordinateS.charAt(0);
                        int len = xCoordinateS.length();
                        if (len == 1){
                            if (xCoordinateC == 'A' || xCoordinateC == 'B' || xCoordinateC == 'C' || xCoordinateC == 'D' || xCoordinateC == 'E' || xCoordinateC == 'F' || xCoordinateC == 'G') {
                                break;
                            }
                        }
                        View.println("Invalid input. Please try again.");
                    }
                    while (true) {
                        View.print("Choose a Y Coordinate (1, 2, 3, 4, 5, 6, 7, 8, 9): ");
                        Scanner input = new Scanner(System.in);
                        String yCoordinateS = input.nextLine();
                        yCoordinateC = yCoordinateS.charAt(0);
                        int len = yCoordinateS.length();
                        if (len == 1){
                            if (yCoordinateC == '1' || yCoordinateC == '2' || yCoordinateC == '3' || yCoordinateC == '4' || yCoordinateC == '5' || yCoordinateC == '6' || yCoordinateC == '7' || yCoordinateC == '8' || yCoordinateC == '9') {
                                break;
                            }
                        }
                        View.println("Invalid input. Please try again.");
                    }
                    Integer xCoordinate = converter(xCoordinateC, yCoordinateC)[0];
                    Integer yCoordinate = converter(xCoordinateC, yCoordinateC)[1];
                    try {
                        game.checkLegalInput(xCoordinate, yCoordinate);
                        break;
                    } catch (Exception e) {
                        View.print(e.getMessage());
                        View.println(" Please try again.");
                    }
                }

                Integer xCoordinate = converter(xCoordinateC, yCoordinateC)[0];
                Integer yCoordinate = converter(xCoordinateC, yCoordinateC)[1];
                Game.Direction direction;


                View.print("Choose a Direction (W, D, S, A): ");
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
                    View.print("Invalid input. Please input the direction again: ");
                }
                switch (directionC) {
                    case 'W' -> direction = Game.Direction.UP;
                    case 'D' -> direction = Game.Direction.RIGHT;
                    case 'S' -> direction = Game.Direction.DOWN;
                    case 'A' -> direction = Game.Direction.LEFT;
                    default -> throw new IllegalStateException("Unexpected value: " + directionC);
                }
                try {
                    //check whether users' input direction is correct(whether the index will be out of bound)
                    game.move(xCoordinate, yCoordinate, direction);
                    break;
                }
                catch (Exception e) {
                    View.print(e.getMessage());
                    View.println(" Please try again.");
                }
            }


            while(true) {
                View.print("Do you want to continue the game? (Y/N) ");
                Scanner input = new Scanner(System.in);
                String ifContinue = input.nextLine();
                if (ifContinue.equals("N")) {
                    View.print("Do you want to pause or quit? (P/Q) ");
                    input = new Scanner(System.in);
                    String pauseOrQuit = input.nextLine();
                    //pause situation
                    if (pauseOrQuit.equals("P")) {
                        View.println("The game is paused. You can continue the game in 'Load game' menu. ");
                        return;
                    }
                    //quit situation
                    else if (pauseOrQuit.equals("Q")) {
                        if (game.getGameState() == Game.GameState.BLUETURN) {
                            game.setGameState(Game.GameState.REDQUIT);
                        } else {game.setGameState(Game.GameState.BLUEQUIT);}
                        break;
                    }
                    else {
                        View.println("Invalid input. Please try again.");
                    }
                }
                else if (ifContinue.equals("Y"))
                    break;
                else{
                    View.println("Invalid input. Please try again.");
                }
            }

            //check whether the game is over
            if (game.getGameState() == Game.GameState.REDWIN || game.getGameState() == Game.GameState.BLUEWIN || game.getGameState() == Game.GameState.REDQUIT || game.getGameState() == Game.GameState.BLUEQUIT) {
                String red = new Ansi(Ansi.RED, Ansi.ITALIC).format("Red wins!");
                String blue = new Ansi(Ansi.BLUE, Ansi.ITALIC).format("Blue wins!");
                switch (game.getGameState()) {
                    case REDWIN -> View.println(red);
                    case BLUEWIN -> View.println(blue);
                    case REDQUIT -> View.println("Red quits. " + blue);
                    case BLUEQUIT -> View.println("Blue quits. " + red);
                }
                break;
            }
            game.printMap();


        }


    }
    private void loadGame() {
        if (cache.isEmpty()){
            View.println("There is no saved game. Please start a new game.");
        }
        else {
            View.println("The saved games are: ");
            //if game state is end , inform the user which side wins
            for (String key : cache.keySet()) {
                View.println(key + " - " + cache.get(key).getGameState());
            }
            View.print("Please enter the game's name you want to load: (Q to quit) ");
            do{
                Scanner input = new Scanner(System.in);
                String gameName = input.nextLine();
                if (cache.containsKey(gameName)){
                    if ((cache.get(gameName).getGameState() == Game.GameState.REDTURN || cache.get(gameName).getGameState() == Game.GameState.BLUETURN)){
                        View.println("Welcome back!");
                        continueGame(cache.get(gameName));
                        return;
                    }
                    else {
                        View.print("The game is over. Please choose another game: (Q to quit) ");
                    }
                }
                else if (gameName.equals("Q")){
                    return;
                }
                else {
                    View.print("There is no game with this name. Please try again: ");
                }
            } while (true);
        }
    }
    private void mainMenu() {
        System.out.print("1. New Game\n2. Load a game\n3. Exit\n");
        System.out.print("Please enter your choice: ");
        Scanner input = new Scanner(System.in);
        String choice = input.nextLine();
        switch (choice) {
            case "1" -> newGame();
            case "2" -> loadGame();
            case "3" -> {
                View.print("Thank you for playing!");
                System.exit(0);
            }
            default -> {
                View.println("Invalid input. Please try again.");
                mainMenu();
            }
        }
    }
    public void intro() {
        String title = "Welcome to the Jungle Game";
        String msg = Ansi.Italic.and(Ansi.HighIntensity).and(Ansi.Cyan).format("%s", title);
        View.println(msg);
        while (true) {
            mainMenu();
        }
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.intro();
    }
}