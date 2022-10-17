package org.example.model;

public class Block {
    private Chess chess;
    private String state;
    private String team;
    Block(String state, String team){
        this.state = state;
        this.team = team;
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

    public String getTeam(){
        return this.team;
    }

    public void setTeam(String team){
        this.team = team;
    }
}
