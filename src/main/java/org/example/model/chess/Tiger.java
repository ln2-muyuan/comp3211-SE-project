package org.example.model.chess;
import org.example.model.Chess;
import org.example.model.Team;

public class Tiger extends Chess implements Jump {

    public Tiger(Team team) {
        super(team, 6);
    }

    @Override
    public void eat(Chess chess) {
        if (chess.getRank() < this.getRank()) {
            chess = null;
        } else {
            throw new RuntimeException("Cat can't eat dog");
        }
    }

    @Override
    public void jump() {
        System.out.println("Jump");
    }
}


