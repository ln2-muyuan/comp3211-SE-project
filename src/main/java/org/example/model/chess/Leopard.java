package org.example.model.chess;

import org.example.model.Chess;
import org.example.model.Team;

public class Leopard extends Chess {

    public Leopard(Team team) {
        super(team, 5);
    }

    @Override
    public void eat(Chess chess) {
        if (chess.getRank() < this.getRank()) {
            chess = null;
        } else {
            throw new RuntimeException("Leopard can't eat dog");
        }
    }
}

