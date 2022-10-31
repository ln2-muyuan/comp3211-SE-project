package org.example;

import org.example.model.Block;
import org.example.util.Ansi;

public class View {
    private String fixedLengthString(String string, int length) {
        return String.format("%1$"+length+ "s", string);
    }
    public void print(Block[][] board) {
        for (int i = 0; i < 9; i++) {
            System.out.print(9 - i +" ");
            for (int j = 0; j < 7; j++) {
                String temp;
                String adjustedTemp = "";
                if (!board[i][j].checkHasChess()){
                    temp = board[i][j].getState();
                    if (temp.equals("Empty")){
                        temp = fixedLengthString(temp, 12);
                        adjustedTemp = Ansi.LowIntensity.and(Ansi.Green).format("%s", temp);
                    }
                    if (temp.equals("River")){
                        temp = fixedLengthString(temp, 12);
                        adjustedTemp = Ansi.LowIntensity.and(Ansi.Blue).format("%s", temp);
                    }
                    if (temp.equals("Trap")){
                        temp = fixedLengthString(temp, 12);
                        adjustedTemp = Ansi.LowIntensity.and(Ansi.White).format("%s", temp);
                    }
                    if (temp.equals("Den")){
                        temp = fixedLengthString(temp, 12);
                        adjustedTemp = Ansi.LowIntensity.and(Ansi.Yellow).format("%s", temp);
                    }
                }
                else {
                    temp = board[i][j].getChess().getName().toUpperCase();
                    temp = fixedLengthString(temp, 12);
                    if (board[i][j].getChess().getTeam().equals("red")){
                        adjustedTemp = Ansi.HighIntensity.and(Ansi.Red).format("%s", temp);
                    }
                    if (board[i][j].getChess().getTeam().equals("blue")){
                        adjustedTemp = Ansi.HighIntensity.and(Ansi.Cyan).format("%s", temp);
                    }
                }
                System.out.print(adjustedTemp);
            }
            System.out.println("");
        }
        System.out.println("           A           B           C           D           E           F           G");
    }
    public void print(String string) {
        System.out.print(string);
    }
    public void println(String string) {
        System.out.println(string);
    }
}
