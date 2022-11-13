package org.example.model.chess;

import org.example.model.Chess;
import org.example.model.Team;

public class Cat extends Chess {
    public Cat(Team team) {
        super(team, 2);
    }

    @Override
    public void eat(Chess chess) throws Exception {
        if (chess.getTeam() == this.getTeam()) {
            throw new Exception("You cannot eat your team's chess.");
        }
        if (chess.getState() == AnimalState.TRAPPED) {
            return;
        }
        if (chess.getRank() > this.getRank()) {
            throw new Exception("Cat can't eat " + chess.getClass().getSimpleName());
        }
    }
}
