package org.example.model.chess;
import org.example.model.Chess;
import org.example.model.Team;

public class Elephant extends Chess {

    public Elephant(Team team) {
        super(team, 8);
    }

    @Override
    public void eat(Chess chess) throws Exception {
        if (chess.getClass().equals(Rat.class)) {
           throw new Exception("Elephant can't eat rat");
        }
    }
}
