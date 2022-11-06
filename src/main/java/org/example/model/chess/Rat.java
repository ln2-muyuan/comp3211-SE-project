package org.example.model.chess;

import org.example.model.Chess;
import org.example.model.Team;

public class Rat extends Chess {
    public Rat(Team team) {
        super(team, 1);
    }

    @Override
    public void eat(Chess chess) throws Exception {
        if (!(chess.getClass().equals(Elephant.class) || chess.getClass().equals(Rat.class))) throw new Exception("Rat can't eat " + chess.getClass().getSimpleName());
    }

    public void swim() {
        this.state = AnimalState.SWIMMING;
    }
}

