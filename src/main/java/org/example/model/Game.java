package org.example.model;

import org.example.model.block.Grass;
import org.example.model.block.River;
import org.example.model.chess.Lion;
import org.example.model.chess.Rat;
import org.example.model.chess.Tiger;

public class Game {
    private final Map map = new Map();
    enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
    enum MovementType {
        LANDtoLAND,
        LANDtoWATER,
        WATERtoWATER,
        WATERtoLAND,
    }
    public void move(Integer x, Integer y, Direction direction) throws Exception {
        MovementType movement = getMovementType(x, y, direction);
        Integer[] nextPosition = getNextPosition(x, y, direction);
        Chess chess = map.getChess(x, y);
        boolean EatFlag = checkEat(x, y, direction);
        if (EatFlag) {
            chess.eat(map.getChess(nextPosition[0], nextPosition[1]));
        }
        switch (movement) {
            case LANDtoLAND:
            case WATERtoWATER:
                chess.move();
                break;
            case LANDtoWATER:
                if (chess instanceof Rat) {
                    chess.move();
                    break;
                }
                else if (chess instanceof Tiger || chess instanceof Lion) {
                    move(x, y, nextPosition[0], nextPosition[1], direction);
                    map.removeChess(x, y);
                    return;
                }
                else {
                    throw new Exception("The animal can neither swim or jump!");
                }
        }
        map.removeChess(x, y);
        map.putChess(nextPosition[0], nextPosition[1], chess);
    }
    public void move(Integer x, Integer y, Integer nextX, Integer nextY, Direction direction) throws Exception {
        MovementType movement = getMovementType(nextX, nextY, direction);
        Integer[] nextPosition = getNextPosition(nextX, nextY, direction);
        Chess chess = map.getChess(x, y);
        boolean EatFlag = checkEat(nextX, nextY, direction);
        if (EatFlag) {
            chess.eat(map.getChess(nextPosition[0], nextPosition[1]));
        }
        switch (movement) {
            case WATERtoWATER -> move(x, y, nextPosition[0], nextPosition[1], direction);
            case WATERtoLAND -> map.putChess(nextPosition[0], nextPosition[1], chess);
        }
    }




    public MovementType getMovementType(Integer x, Integer y, Direction direction) {
        Block block = this.map.getBlock(x, y);
        Integer[] nextPosition = getNextPosition(x, y, direction);
        Block nextBlock = this.map.getBlock(nextPosition[0], nextPosition[1]);
        if (block instanceof Grass && nextBlock instanceof River) {
            return MovementType.LANDtoWATER;
        } else if (block instanceof River && nextBlock instanceof Grass) {
            return MovementType.WATERtoLAND;
        } else if (block instanceof River && nextBlock instanceof River) {
            return MovementType.WATERtoWATER;
        } else {
            return MovementType.LANDtoLAND;
        }
    }
    public boolean checkEat(Integer x, Integer y, Direction direction) {
        Integer[] nextPosition = getNextPosition(x, y, direction);
        Block nextBlock = this.map.getBlock(nextPosition[0], nextPosition[1]);
        return nextBlock.hasChess();
    }
    public Integer[] getNextPosition(Integer x, Integer y, Direction direction) {
        return switch (direction) {
            case UP -> new Integer[]{x - 1, y};
            case DOWN -> new Integer[]{x + 1, y};
            case LEFT -> new Integer[]{x, y - 1};
            case RIGHT -> new Integer[]{x, y + 1};
        };
    }

    public void printMap() {
        System.out.println(map.getMap());

    }

    public static void main(String[] args) {
        Game game = new Game();
        try {
            game.move(0, 0, Direction.RIGHT);

            game.move(1, 1, Direction.UP);

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }



        game.printMap();
    }
}
