package org.example.model;

public abstract class Chess {
    private final Team team;
    private final Integer rank;

    private final Integer canJump = 0;
    protected Chess(Team team, Integer rank) {
        this.team = team;
        this.rank = rank;
    }

    public Team getTeam(){
        return this.team;
    }
    public Integer getRank(){
        return this.rank;
    }
    public abstract void eat(Chess chess);


}
