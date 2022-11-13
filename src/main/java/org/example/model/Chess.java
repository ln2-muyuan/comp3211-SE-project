package org.example.model;

public abstract class Chess {
    public enum AnimalState {
        NORMAL, TRAPPED, SWIMMING
    }
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
}
