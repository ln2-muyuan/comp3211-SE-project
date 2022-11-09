package org.example.model;

import org.example.model.block.Grass;
import org.example.model.block.River;
import org.example.model.chess.Lion;
import org.example.model.chess.Rat;
import org.example.model.chess.Tiger;

public class Game {
    private final Map map = new Map();
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
    enum MovementType {
        LANDtoLAND,
        LANDtoWATER,
        WATERtoWATER,
        WATERtoLAND,
    }
    public enum GameState {
        REDTURN,
        BLUETURN,
        REDWIN,
        BLUEWIN,
    }
    private Integer turnCount = 0;
    private Integer redChessCount = 8;
    private Integer blueChessCount = 8;
    public void move(Integer x, Integer y, Direction direction) throws Exception {
        MovementType movement = getMovementType(x, y, direction);
        Integer[] nextPosition = getNextPosition(x, y, direction);
        Chess chess = map.getChess(x, y);
        boolean meetAnimal = checkMeetAnimal(x, y, direction);
        if (meetAnimal) {
            chess.eat(map.getChess(nextPosition[0], nextPosition[1]));
            if (chess.getTeam() == Team.RED) {
                blueChessCount--;
            }
            else {
                redChessCount--;
            }
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
                    map.removeChess(x, y);      // remove chess from old position here
                    turnCount++;
                    return;
                }
                else {
                    throw new Exception("The animal can neither swim or jump!");
                }
       }
        map.removeChess(x, y);
        map.putChess(nextPosition[0], nextPosition[1], chess);
        turnCount++;
    }
    public void move(Integer x, Integer y, Integer nextX, Integer nextY, Direction direction) throws Exception {
        MovementType movement = getMovementType(nextX, nextY, direction);
        Integer[] nextPosition = getNextPosition(nextX, nextY, direction);
        Chess chess = map.getChess(x, y);
        boolean meetAnimal = checkMeetAnimal(nextX, nextY, direction);
        if (meetAnimal) {
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
    public boolean checkMeetAnimal(Integer x, Integer y, Direction direction) {
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
    public void checkLegalInput(Integer xCoordinate, Integer yCoordinate) throws Exception {
        if (!map.getBlock(xCoordinate, yCoordinate).hasChess()) {
            throw new Exception("There is no chess on this coordinate!");
        }
        if (turnCount % 2 == 0){
            if (map.getBlock(xCoordinate, yCoordinate).getChess().getTeam() != Team.RED) {
                throw new Exception("It's not your turn!");
            }
        } else {
            if (map.getBlock(xCoordinate, yCoordinate).getChess().getTeam() != Team.BLUE) {
                throw new Exception("It's not your turn!");
            }
        }
    }
    public void printMap() {
        System.out.println(map.getMap());
    }
    public GameState getGameState() {
        if (redChessCount == 0) {
            return GameState.BLUEWIN;
        }
        else if (blueChessCount == 0) {
            return GameState.REDWIN;
        }
        else if (turnCount % 2 == 0) {
            return GameState.REDTURN;
        }
        else {
            return GameState.BLUETURN;
        }
    }

    /**
     * A quick start here without any input validation
     * You can ignore the priority order
     */
    public static void main(String[] args) {
        Game game = new Game();
        try {
            game.move(0, 0, Game.Direction.DOWN);
            game.move(2, 0, Game.Direction.DOWN);
            game.move(3, 0, Game.Direction.DOWN);
            game.move(1, 0, Game.Direction.DOWN);
            game.move(2, 0, Game.Direction.DOWN);
            game.move(3, 0, Game.Direction.RIGHT);
            game.move(3, 3, Game.Direction.RIGHT);
            game.printMap();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}