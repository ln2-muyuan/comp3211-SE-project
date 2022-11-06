package org.example.model.chess;
import org.example.model.Chess;
import org.example.model.Team;

public class Tiger extends Chess {

    public Tiger(Team team) {
        super(team, 6);
    }

    @Override
    public void eat(Chess chess) throws Exception {
        if (chess.getRank() > this.getRank()) {
            throw new Exception("Lion can't eat " + chess.getClass().getSimpleName());
        }
    }

    @Override
    public void jump() {
    }
}


