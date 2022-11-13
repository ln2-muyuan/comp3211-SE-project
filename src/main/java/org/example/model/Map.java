package org.example.model;
import org.example.model.block.*;
import org.example.model.chess.*;

import java.util.HashMap;

public class Map {
    private final Block[][] Map = new Block[9][7];
    public Map() {
        Map[0][3] = new Den(Team.RED);
        Map[0][2] = new Trap(Team.RED);
        Map[0][4] = new Trap(Team.RED);
        Map[1][3] = new Trap(Team.RED);
        Map[8][3] = new Den(Team.BLUE);
        Map[8][2] = new Trap(Team.BLUE);
        Map[8][4] = new Trap(Team.BLUE);
        Map[7][3] = new Trap(Team.BLUE);
        Map[3][1] = new River();
        Map[3][2] = new River();
        Map[3][4] = new River();
        Map[3][5] = new River();
        Map[4][1] = new River();
        Map[4][2] = new River();
        Map[4][4] = new River();
        Map[4][5] = new River();
        Map[5][1] = new River();
        Map[5][2] = new River();
        Map[5][4] = new River();
        Map[5][5] = new River();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 7; j++) {
                if (Map[i][j] == null) {
                    Map[i][j] = new Grass();
                }
            }
        }
        Lion redLion = new Lion(Team.RED);
        Map[0][0].putChess(redLion);
        Tiger redTiger = new Tiger(Team.RED);
        Map[0][6].putChess(redTiger);
        Dog redDog = new Dog(Team.RED);
        Map[1][1].putChess(redDog);
        Cat redCat = new Cat(Team.RED);
        Map[1][5].putChess(redCat);
        Rat redRat = new Rat(Team.RED);
        Map[2][0].putChess(redRat);
        Leopard redLeopard = new Leopard(Team.RED);
        Map[2][2].putChess(redLeopard);
        Wolf redWolf = new Wolf(Team.RED);
        Map[2][4].putChess(redWolf);
        Elephant redElephant = new Elephant(Team.RED);
        Map[2][6].putChess(redElephant);
        Elephant blueElephant = new Elephant(Team.BLUE);
        Map[6][0].putChess(blueElephant);
        Wolf blueWolf = new Wolf(Team.BLUE);
        Map[6][2].putChess(blueWolf);
        Leopard blueLeopard = new Leopard(Team.BLUE);
        Map[6][4].putChess(blueLeopard);
        Rat blueRat = new Rat(Team.BLUE);
        Map[6][6].putChess(blueRat);
        Cat blueCat = new Cat(Team.BLUE);
        Map[7][1].putChess(blueCat);
        Dog blueDog = new Dog(Team.BLUE);
        Map[7][5].putChess(blueDog);
        Tiger blueTiger = new Tiger(Team.BLUE);
        Map[8][0].putChess(blueTiger);
        Lion blueLion = new Lion(Team.BLUE);
        Map[8][6].putChess(blueLion);
    }
    public Block getBlock(Integer x, Integer y) {
        return Map[x][y];
    }
    public Chess getChess(Integer x, Integer y) {
        return Map[x][y].getChess();
    }
    public void putChess(Integer x, Integer y, Chess chess) {
        if (Map[x][y] instanceof River){
            chess.setState(Chess.AnimalState.SWIMMING);
        }
        else if (Map[x][y] instanceof Trap){
            chess.setState(Chess.AnimalState.TRAPPED);
        }
        else if (Map[x][y] instanceof Grass){
            chess.setState(Chess.AnimalState.NORMAL);
        }
        Map[x][y].putChess(chess);
    }
    public void removeChess(Integer x, Integer y) {
        Map[x][y].removeChess();
    }
    public String getMap() {
        StringBuilder result = new StringBuilder();
        result.append("  ");
        for (int i = 0; i < 9; i++) {
            for (int k = 0; k < 3; k++) {
                for (int j = 0; j < 7; j++) {
                    result.append(Map[i][j].getBlockLayer(k));
                }
                result.append("\n");
                if (k == 0) {
                    result.append(9-i).append(" ");
                }
                else {
                    result.append("  ");
                }
            }
        }
        result.append("      A         B          C          D          E          F          G\n");
        return result.toString();
    }
}