package org.example;

public class Block {
    private Chess chess;
    private String state;
    public Block(String state){
        this.state = state;
    }
    public void changeState(String state){
        this.state = state;
    }
    public String getState(){
        return this.state;
    }
    public void putChess(Chess chess){
        this.chess = chess;
    }
    public void removeChess(){
        this.chess = null;
    }
    public Chess getChess(){
        return this.chess;
    }
    public boolean checkHasChess() {
        return this.chess != null;
    }
}
