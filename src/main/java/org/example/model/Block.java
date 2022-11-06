package org.example.model;

public abstract class Block {
    protected Chess chess;
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
    public boolean hasChess() {
        return this.chess != null;
    }
    protected String fixedLengthString(String string, int length) {
        return String.format("%1$" + length+ "s", string);
    }
    public abstract String getBlockLayer(Integer index);
}
