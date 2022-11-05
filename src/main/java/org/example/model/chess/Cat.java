package org.example.model.chess;
import org.example.model.Chess;
import org.example.model.Team;

public class Cat extends Chess {

    public Cat(Team team) {
        super(team, 2);
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
