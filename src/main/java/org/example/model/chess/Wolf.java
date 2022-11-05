package org.example.model.chess;

import org.example.model.Chess;
import org.example.model.Team;

public class Wolf extends Chess {

    public Wolf(Team team) {
        super(team, 4);
    }

    @Override
    public void eat(Chess chess) {
        if (chess.getRank() < this.getRank()) {
            chess = null;
        } else {
            throw new RuntimeException("Wolf can't eat dog");
        }
    }
}
