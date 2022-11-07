package org.example.model.chess;

import org.example.model.Chess;
import org.example.model.Team;

public class Rat extends Chess {
    public Rat(Team team) {
        super(team, 1);
    }

    @Override
    public void eat(Chess chess) throws Exception {
        if (chess.getState() == AnimalState.TRAPPED) {
            return;
        }
        if (!(chess.getClass().equals(Elephant.class) || chess.getClass().equals(Rat.class))) throw new Exception("Rat can't eat " + chess.getClass().getSimpleName());
        if (this.getState() == AnimalState.SWIMMING && chess.getState() != AnimalState.SWIMMING) throw new Exception("Rat can't eat " + chess.getClass().getSimpleName() + " when swimming");
        if (this.getState() == AnimalState.NORMAL && chess.getState() == AnimalState.SWIMMING) throw new Exception("Rat on land can't eat a swimming rat");
    }

    public void swim() {
        this.setState(AnimalState.SWIMMING);
    }
}

