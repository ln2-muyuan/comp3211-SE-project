package org.example.model.chess;

import org.example.model.Chess;
import org.example.model.Team;

public class Wolf extends Chess {
    public Wolf(Team team) {
        super(team, 4);
    }

    @Override
    public void eat(Chess chess) throws Exception {
        if (chess.getState() == AnimalState.TRAPPED) {
            return;
        }
        if (chess.getRank() > this.getRank()) {
            throw new Exception("Wolf can't eat " + chess.getClass().getSimpleName());
        }
    }
}
