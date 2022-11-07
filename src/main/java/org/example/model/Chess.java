package org.example.model;

import org.example.model.chess.AnimalState;

public abstract class Chess {
    private final Team team;
    private final Integer rank;
    private AnimalState state;

    protected Chess(Team team, Integer rank) {
        this.team = team;
        this.rank = rank;
        this.state = AnimalState.NORMAL;
    }

    public Team getTeam(){
        return this.team;
    }
    public Integer getRank(){
        return this.rank;
    }
    public AnimalState getState() {
        return this.state;
    }
    public void setState(AnimalState state) {
        this.state = state;
    }
    public abstract void eat(Chess chess) throws Exception;

    public void walk() throws Exception {
        if (this.state == AnimalState.TRAPPED){
            throw new Exception("The animal is trapped");
        }
    }
    public void jump() throws Exception {
        throw new Exception("The animal can't jump");
    }
    public void swim() throws Exception {
        throw new Exception("The animal can't sleep");
    }
}
