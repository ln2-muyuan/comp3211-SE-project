package org.example.model.block;

import org.example.model.Block;
import org.example.model.Chess;
import org.example.model.Team;
import org.example.util.Ansi;

public class Grass extends Block {
    private final String grass = """
                ┌─ ─ ─ ─ ─┐
                |  Grass  |
                └─ ─ ─ ─ ─┘
                """;
    public Grass() {
        super();
    }

    @Override
    public String print(Integer index) {
        String msg = Ansi.Green.format("%s", grass);
        String[] result = msg.split("\n");
        return result[index];
    }

}
