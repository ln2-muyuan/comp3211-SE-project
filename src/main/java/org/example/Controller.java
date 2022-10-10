package org.example;

import org.example.model.PlayBoard;

public class Controller {
    public void intro(){

    }
    public void playNewGame(){
        PlayBoard playBoard = new PlayBoard();
        playBoard.printBoard();
        try{
            playBoard.move('F', '8', 'S');
        } catch (Exception e) {
            System.out.println(e);
        }
        playBoard.printBoard();
        try{
            playBoard.move('E', '7', 'S');
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void pauseGame(PlayBoard board){
        System.out.println("0");
    }

    public void resumeGame(PlayBoard board){

    }

    public void restartGame(PlayBoard board){

    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.playNewGame();
    }
}
