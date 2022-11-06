package org.example.model.chess;

import org.example.model.Chess;
import org.example.model.Team;

public class Leopard extends Chess {

    public Leopard(Team team) {
        super(team, 5);
    }

    @Override
    public void eat(Chess chess) throws Exception {
        if (chess.getRank() > this.getRank()) {
            throw new Exception("Leopard can't eat " + chess.getClass().getSimpleName());
        }
    }
}

