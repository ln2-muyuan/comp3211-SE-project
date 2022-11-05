package org.example.model.chess;
import org.example.model.Chess;
import org.example.model.Team;

public class Elephant extends Chess {

    public Elephant(Team team) {
        super(team, 8);
    }

    @Override
    public void eat(Chess chess) {
        if (chess.getRank() < this.getRank()) {
            chess = null;
        } else {
            throw new RuntimeException("Elephant can't eat dog");
        }
    }
}
