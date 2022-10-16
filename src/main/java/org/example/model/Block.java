package org.example.model;

public class Block {
    private Chess chess;
    private String state;
    Block(String state){
        this.state = state;
    }
    void changeState(String state){
        this.state = state;
    }
    public String getState(){
        return this.state;
    }
    void putChess(Chess chess){
        this.chess = chess;
    }
    void removeChess(){
        this.chess = null;
    }
    public Chess getChess(){
        return this.chess;
    }
    public boolean checkHasChess() {
        return this.chess != null;
    }
}
