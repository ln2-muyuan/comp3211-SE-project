package org.example.model.chess;

import org.example.model.Chess;
import org.example.model.Team;

public class Lion extends Chess {
    public Lion(Team team) {
        super(team, 7);
    }

    @Override
    public void eat(Chess chess) throws Exception {
        if (chess.getRank() > this.getRank()) {
            throw new Exception("Lion can't eat " + chess.getClass().getSimpleName());
        }
    }

    public void jump() {
    }

}
