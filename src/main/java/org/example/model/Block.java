package org.example.model;

public abstract class Block {
    private Chess chess;

    protected final Team team;
    public Block() {
        this.team = Team.NEUTRAL;
    }
    public Block(Team team) {
        this.team = team;
    }
    public void putChess(Chess chess) {
        this.chess = chess;
    }

    public void removeChess() {
        this.chess = null;
    }

    public boolean haveChess() {
        return this.chess != null;
    }

    public abstract String print(Integer index);

//    public abstract String toString(Chess chess);
}
