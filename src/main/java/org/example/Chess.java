package org.example;

public class Chess {
    public String name;
    public String team;
    public Chess(String name, String team) {
        this.name = name;
        this.team = team;
    }
    protected String getName(){
        return this.name;
    }
    protected String getTeam(){
        return this.team;
    }
}
