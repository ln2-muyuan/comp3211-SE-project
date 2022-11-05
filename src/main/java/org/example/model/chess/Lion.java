package org.example.model.chess;

import org.example.model.Chess;
import org.example.model.Team;

public class Lion extends Chess {
    public Lion(Team team) {
        super(team, 7);
    }

    @Override
    public void eat(Chess chess) {
        if (chess.getRank() < this.getRank()) {
            chess = null;
        } else {
            throw new RuntimeException("Cat can't eat dog");
        }
    }

}
