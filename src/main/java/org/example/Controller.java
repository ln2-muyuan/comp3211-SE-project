package org.example;

import org.example.model.Game;

public class Controller {
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



    public static void main(String[] args) {
        Game game = new Game();
        try {
            game.checkLegalInput(1,2);
            game.move(0, 0, Game.Direction.RIGHT);
            game.move(1, 1, Game.Direction.UP);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        game.printMap();
    }


}
