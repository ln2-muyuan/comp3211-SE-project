package org.example.model.chess;

import org.example.model.Chess;
import org.example.model.Team;

public class Rat extends Chess {
    public Rat(Team team) {
        super(team, 1);
    }

    @Override
    public void eat(Chess chess) {
        if (chess.getRank() < this.getRank()) {
            chess = null;
        } else {
            throw new RuntimeException("Rat can't eat dog");
        }
    }
}

