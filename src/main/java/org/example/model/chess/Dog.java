package org.example.model.chess;
import org.example.model.Chess;
import org.example.model.Team;

public class Dog extends Chess {
    public Dog(Team team) {
        super(team, 3);
    }

    @Override
    public void eat(Chess chess) throws Exception {
        if (chess.getRank() > this.getRank()) {
            throw new Exception("Dog can't eat " + chess.getClass().getSimpleName());
        }
    }
}
