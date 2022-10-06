package org.example;
import java.util.HashMap;
import java.util.Scanner;


public class PlayBoard {
    private final Block[][] board = new Block[9][7];
    private Integer turnCount = 0;
    private String gameState;
    private HashMap<String, Integer> power;
    private HashMap<String,Integer> setPower(){
        HashMap<String,Integer> answer=new HashMap<>();
        answer.put("Rat", 0);
        answer.put("Cat", 1);
        answer.put("Dog", 2);
        answer.put("Wolf", 3);
        answer.put("Leopard", 4);
        answer.put("Tiger", 5);
        answer.put("Lion", 6);
        answer.put("Elephant", 7);
        return answer;
    }
    private void initialize(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = new Block("Empty");
            }
        }
        board[0][0].putChess(new Chess("Lion", "red"));
        board[0][2].changeState("Trap");
        board[0][3].changeState("Den");
        board[0][4].changeState("Trap");
        board[0][6].putChess(new Chess("Tiger", "red"));
        board[1][1].putChess(new Chess("Dog", "red"));
        board[1][3].changeState("Trap");
        board[1][5].putChess(new Chess("Cat", "red"));
        board[2][0].putChess(new Chess("Rat", "red"));
        board[2][2].putChess(new Chess("Leopard", "red"));
        board[2][4].putChess(new Chess("Wolf", "red"));
        board[2][6].putChess(new Chess("Elephant", "red"));
        board[6][0].putChess(new Chess("Elephant", "blue"));
        board[6][2].putChess(new Chess("Wolf", "blue"));
        board[6][4].putChess(new Chess("Leopard", "blue"));
        board[6][6].putChess(new Chess("Rat", "blue"));
        board[7][1].putChess(new Chess("Cat", "blue"));
        board[7][3].changeState("Trap");
        board[7][5].putChess(new Chess("Dog", "blue"));
        board[8][0].putChess(new Chess("Tiger", "blue"));
        board[8][2].changeState("Trap");
        board[8][3].changeState("Den");
        board[8][4].changeState("Trap");
        board[8][6].putChess(new Chess("Lion", "blue"));
        for (int i = 3; i < 6; i++){
            board[i][1].changeState("River");
            board[i][2].changeState("River");
            board[i][4].changeState("River");
            board[i][5].changeState("River");
        }
        power = setPower();
    }
    private String fixedLengthString(String string, int length) {
        return String.format("%1$"+length+ "s", string);
    }
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
    private Integer[] getDestination(Integer[] convertedInput, char direction){
        Integer[] destination = new Integer[2];
        switch (direction) {
            case 'W' -> {
                destination[0] = convertedInput[0] - 1;
                destination[1] = convertedInput[1];
            }
            case 'D' -> {
                destination[0] = convertedInput[0];
                destination[1] = convertedInput[1] + 1;
            }
            case 'S' -> {
                destination[0] = convertedInput[0] + 1;
                destination[1] = convertedInput[1];
            }
            case 'A' -> {
                destination[0] = convertedInput[0];
                destination[1] = convertedInput[1] - 1;
            }
        }
        if (board[destination[0]][destination[1]].getState().equals("River")){
            if (board[convertedInput[0]][convertedInput[1]].getChess().getName().equals("Lion") || board[convertedInput[0]][convertedInput[1]].getChess().getName().equals("Tiger")){
                switch (direction) {
                    case 'W' -> {
                        destination[0] = convertedInput[0] - 4;
                        destination[1] = convertedInput[1];
                    }
                    case 'D' -> {
                        destination[0] = convertedInput[0];
                        destination[1] = convertedInput[1] + 3;
                    }
                    case 'S' -> {
                        destination[0] = convertedInput[0] + 4;
                        destination[1] = convertedInput[1];
                    }
                    case 'A' -> {
                        destination[0] = convertedInput[0];
                        destination[1] = convertedInput[1] - 3;
                    }
                }
            }
        }
        return destination;
    }
    private boolean checkLegalInput(char xCoordinate, char yCoordinate) throws Exception {
        if (xCoordinate < 'A' || xCoordinate > 'G' || yCoordinate <= '0' || yCoordinate > '9'){
            throw new Exception("Input coordinate out of play area!");
        }
        if (!board[converter(xCoordinate, yCoordinate)[0]][converter(xCoordinate, yCoordinate)[1]].checkHasChess()){
            throw new Exception("There is no chess on this coordinate!");
        }
        if (turnCount % 2 == 0){
            if (board[converter(xCoordinate, yCoordinate)[0]][converter(xCoordinate, yCoordinate)[1]].getChess().getTeam().equals("blue")){
                throw new Exception("It's not your turn!");
            }
        } else {
            if (board[converter(xCoordinate, yCoordinate)[0]][converter(xCoordinate, yCoordinate)[1]].getChess().getTeam().equals("red")){
                throw new Exception("It's not your turn!");
            }
        }
        return true;
    }
    private boolean checkLegalMove(Integer[] convertedInput, char direction) throws Exception {
        Integer[] destination = getDestination(convertedInput, direction);
        if (destination[0] < 0 || destination[0] > 8 || destination[1] < 0 || destination[1] > 6){
            throw new Exception("Destination out of play area!");
        }
        return isLegalRiver(convertedInput, direction) && isLegalEat(convertedInput, direction) && isSameTeam(convertedInput, direction);
    }



    //isLegalRiver is not completed, rat can be in river and so on
    private boolean isLegalRiver(Integer[] convertedInput, char direction) throws Exception {
        Integer[] destination = getDestination(convertedInput, direction);
        if (!board[destination[0]][destination[1]].getState().equals("River")){
            return true;
        }
        if (board[convertedInput[0]][convertedInput[1]].getChess().getName().equals("Lion") || board[convertedInput[0]][convertedInput[1]].getChess().getName().equals("Tiger")){
            if (board[destination[0]][destination[1]].getState().equals("River")){
                return true;
            }
        }
        throw new Exception("This animal cannot go to river!");
    }
    //isLegalEat is not completed, rat can eat elephant and so on
    private boolean isLegalEat(Integer[] convertedInput, char direction) throws Exception {
        Integer[] destination = getDestination(convertedInput, direction);
        if (board[destination[0]][destination[1]].checkHasChess()){
            if (!board[convertedInput[0]][convertedInput[1]].getChess().getTeam().equals(board[destination[0]][destination[1]].getChess().getTeam())){
                if (power.get(board[convertedInput[0]][convertedInput[1]].getChess().getName()) > power.get(board[destination[0]][destination[1]].getChess().getName())){
                    return true;
                }
            }
        }
        throw new Exception("You cannot eat this animal!");
    }
    private boolean isSameTeam(Integer[] convertedInput, char direction) throws Exception {
        Integer[] destination = getDestination(convertedInput, direction);
        if (board[destination[0]][destination[1]].checkHasChess()){
            if (board[convertedInput[0]][convertedInput[1]].getChess().getTeam().equals(board[destination[0]][destination[1]].getChess().getTeam())){
                throw new Exception("You cannot go to your own chess!");
            }
        }
        return true;
    }
    private boolean checkWin(){
        if (board[0][3].checkHasChess() && board[0][3].getChess().getTeam().equals("blue")){
            System.out.println("Blue win!");
            return true;
        }
        if (board[8][3].checkHasChess() && board[8][3].getChess().getTeam().equals("red")){
            System.out.println("Red win!");
            return true;
        }
        return false;
    }










    public PlayBoard() {
        initialize();
    }
    public void printBoard() {
        for (int i = 0; i < 9; i++) {
            System.out.print(9 - i +" ");
            for (int j = 0; j < 7; j++) {
                String temp;
                if (!board[i][j].checkHasChess()){
                    temp = board[i][j].getState();
                }
                else {
                    temp = board[i][j].getChess().getName()+"-"+Character.toUpperCase(board[i][j].getChess().getTeam().charAt(0));
                }
                temp = fixedLengthString(temp, 12);
                System.out.print(temp);
            }
            System.out.println("");
        }
        System.out.println("           A           B           C           D           E           F           G");
    }
    public void move(char xCoordinate, char yCoordinate, char direction) throws Exception {
        Integer[] originalSite = new Integer[2];
        if (checkLegalInput(xCoordinate, yCoordinate)){
            originalSite = converter(xCoordinate, yCoordinate);
        }
        Integer[] desiredSite = new Integer[2];
        desiredSite = getDestination(originalSite, direction);
        if (checkLegalMove(originalSite, direction)){
            board[desiredSite[0]][desiredSite[1]].putChess(board[originalSite[0]][originalSite[1]].getChess());
            board[originalSite[0]][originalSite[1]].removeChess();
        }
        turnCount++;
        if (checkWin()){
            gameState = "end";
        }
    }




}
