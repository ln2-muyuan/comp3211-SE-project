package org.example.model.block;

import org.example.model.Block;
import org.example.model.Chess;
import org.example.model.Team;
import org.example.util.Ansi;

public class Grass extends Block {
    private final String grass = """
                ┌─ ─ ─ ─ ─┐
                |         |
                └─ ─ ─ ─ ─┘
                """;
    public Grass() {
        super();
    }

    @Override
    public String getBlockLayer(Integer index) {
        if (this.hasChess() && index == 1) {
            if (this.chess.getTeam() == Team.RED) {
                return Ansi.Red.format("|%s|", fixedLengthString(this.chess.getClass().getSimpleName(),9));
            }
            if (this.chess.getTeam() == Team.BLUE) {
                return Ansi.Cyan.format("|%s|", fixedLengthString(this.chess.getClass().getSimpleName(),9));
            }
        }
        String[] result = grass.split("\n");
        String msg = Ansi.Green.format("%s", result[index]);
        return msg;
    }

}
