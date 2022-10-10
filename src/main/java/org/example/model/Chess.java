package org.example.model;

public class Chess {
    private String name;
    private String team;
    Chess(String name, String team) {
        this.name = name;
        this.team = team;
    }
    String getName(){
        return this.name;
    }
    String getTeam(){
        return this.team;
    }
}
