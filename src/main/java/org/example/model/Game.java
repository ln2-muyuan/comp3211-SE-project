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
    public void move(Integer x, Integer y, Direction direction) {
        MovementType movement = getMovementType(x, y, direction);
        Integer[] nextPosition = getNextPosition(x, y, direction);
        Chess chess = map.getChess(x, y);
        boolean EatFlag = checkEat(x, y, direction);
        if (EatFlag) {
            try {
                chess.eat(map.getChess(nextPosition[0], nextPosition[1]));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }
        }
        switch (movement) {
            case LANDtoLAND:
                try {
                    chess.walk();
                    map.putChess(nextPosition[0], nextPosition[1], chess);
                    map.removeChess(x, y);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return;
                }
                break;
            case LANDtoWATER:
                if (chess instanceof Rat) {
                    map.putChess(nextPosition[0], nextPosition[1], chess);
                    map.removeChess(x, y);
                    break;
                }
                else if (chess instanceof Tiger || chess instanceof Lion) {
                    map.putChess(nextPosition[0], nextPosition[1], chess);
                    map.removeChess(x, y);
                    move(nextPosition[0], nextPosition[1], direction);
                }
                else {
                    System.out.println("The animal can neither swim or jump!");
                    return;
                }
                break;
            case WATERtoWATER:
                map.putChess(nextPosition[0], nextPosition[1], chess);
                map.removeChess(x, y);
                move(nextPosition[0], nextPosition[1], direction);
                break;
            case WATERtoLAND:
                map.putChess(nextPosition[0], nextPosition[1], chess);
                map.removeChess(x, y);
                break;
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

        game.move(2, 0, Direction.RIGHT);
        game.move(2, 1, Direction.DOWN);
        game.move(0, 0, Direction.DOWN);
        game.move(1, 0, Direction.DOWN);
        game.move(2, 0, Direction.DOWN);
        game.move(3, 0, Direction.DOWN);
        game.move(4, 0, Direction.RIGHT);
        game.move(4, 3, Direction.RIGHT);
        game.printMap();
    }
}
