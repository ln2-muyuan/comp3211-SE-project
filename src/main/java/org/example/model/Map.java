package org.example.model;
import org.example.model.block.*;
import org.example.model.chess.*;

import java.util.HashMap;

public class Map {
    private final Elephant redElephant = new Elephant(Team.RED);
    private final Elephant blueElephant = new Elephant(Team.BLUE);
    private final Lion redLion = new Lion(Team.RED);
    private final Lion blueLion = new Lion(Team.BLUE);
    private final Tiger redTiger = new Tiger(Team.RED);
    private final Tiger blueTiger = new Tiger(Team.BLUE);
    private final Leopard redLeopard = new Leopard(Team.RED);
    private final Leopard blueLeopard = new Leopard(Team.BLUE);
    private final Wolf redWolf = new Wolf(Team.RED);
    private final Wolf blueWolf = new Wolf(Team.BLUE);
    private final Dog redDog = new Dog(Team.RED);
    private final Dog blueDog = new Dog(Team.BLUE);
    private final Cat redCat = new Cat(Team.RED);
    private final Cat blueCat = new Cat(Team.BLUE);
    private final Rat redRat = new Rat(Team.RED);
    private final Rat blueRat = new Rat(Team.BLUE);
    public final HashMap<String, Chess> redTeam = new HashMap<String, Chess>() {{
        put("redElephant", redElephant);
        put("redLion", redLion);
        put("redTiger", redTiger);
        put("redLeopard", redLeopard);
        put("redWolf", redWolf);
        put("redDog", redDog);
        put("redCat", redCat);
        put("redRat", redRat);
    }};
    public final HashMap<String, Chess> blueTeam = new HashMap<String, Chess>() {{
        put("blueElephant", blueElephant);
        put("blueLion", blueLion);
        put("blueTiger", blueTiger);
        put("blueLeopard", blueLeopard);
        put("blueWolf", blueWolf);
        put("blueDog", blueDog);
        put("blueCat", blueCat);
        put("blueRat", blueRat);
    }};
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
        Map[0][0].putChess(redLion);
        Map[0][6].putChess(redTiger);
        Map[1][1].putChess(redDog);
        Map[1][5].putChess(redCat);
        Map[2][0].putChess(redRat);
        Map[2][2].putChess(redLeopard);
        Map[2][4].putChess(redWolf);
        Map[2][6].putChess(redElephant);
        Map[6][0].putChess(blueElephant);
        Map[6][2].putChess(blueWolf);
        Map[6][4].putChess(blueLeopard);
        Map[6][6].putChess(blueRat);
        Map[7][1].putChess(blueCat);
        Map[7][5].putChess(blueDog);
        Map[8][0].putChess(blueTiger);
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
        if (Map[x][y] instanceof Trap){
            chess.setState(Chess.AnimalState.TRAPPED);
        }
        Map[x][y].putChess(chess);
    }
    public void removeChess(Integer x, Integer y) {
        Map[x][y].removeChess();
    }
    public String getMap() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int k = 0; k < 3; k++) {
                for (int j = 0; j < 7; j++) {
                    result.append(Map[i][j].getBlockLayer(k));
                }
                result.append("\n");
            }
        }
        return result.toString();
    }
}