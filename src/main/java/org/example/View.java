package org.example;

import org.example.model.Block;

public class View {
    private String fixedLengthString(String string, int length) {
        return String.format("%1$"+length+ "s", string);
    }

    public void print(Block[][] board) {
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

    public void print(String string) {
        System.out.println(string);
    }
}
