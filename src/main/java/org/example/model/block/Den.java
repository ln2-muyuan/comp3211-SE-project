package org.example.model.block;

import org.example.model.Block;
import org.example.model.Team;
import org.example.util.Ansi;

public class Den extends Block {
    private final String den = """
                ┌─────────┐
                │   Den   │
                └─────────┘
                """;

    public Den(Team team) {
        super(team);
    }

    public String getBlockLayer(Integer index) {
        if (this.hasChess() && index == 1) {
            if (this.chess.getTeam() == Team.RED && this.team == Team.RED) {
                return Ansi.RED + "|" + Ansi.RED + fixedLengthString(this.chess.getClass().getSimpleName(),9) + Ansi.RED + "|";
            }
            else if (this.chess.getTeam() == Team.BLUE && this.team == Team.BLUE) {
                return Ansi.CYAN + "|" + Ansi.CYAN + fixedLengthString(this.chess.getClass().getSimpleName(),9) + Ansi.CYAN + "|";
            }
            else if(this.chess.getTeam() == Team.RED && this.team == Team.BLUE) {
                return Ansi.CYAN + "|" + Ansi.RED + fixedLengthString(this.chess.getClass().getSimpleName(),9) + Ansi.CYAN + "|";
            }
            else {
                return Ansi.RED + "|" + Ansi.CYAN + fixedLengthString(this.chess.getClass().getSimpleName(),9) + Ansi.RED + "|";
            }
        }
        String[] result = den.split("\n");
        if (this.team == Team.RED) {
            return Ansi.Red.format("%s", result[index]);
        }
        else {
            return Ansi.Cyan.format("%s", result[index]);
        }
    }
}
