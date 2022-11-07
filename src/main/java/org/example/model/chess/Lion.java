package org.example.model.chess;

import org.example.model.Chess;
import org.example.model.Team;

public class Lion extends Chess {
    public Lion(Team team) {
        super(team, 7);
    }

    @Override
    public void eat(Chess chess) throws Exception {
        if (chess.getTeam() == this.getTeam()) {
            throw new Exception("You cannot eat your team's chess");
        }
        if (chess.getState() == AnimalState.TRAPPED) {
            return;
        }
        if (chess.getState() == AnimalState.SWIMMING) {
            throw new Exception("The animal cannot jump over a swimming rat");
        }
        if (chess.getRank() > this.getRank()) {
            throw new Exception("Lion can't eat " + chess.getClass().getSimpleName());
        }
    }
}
