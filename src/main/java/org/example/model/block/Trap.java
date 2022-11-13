package org.example.model.block;

import org.example.model.Block;
import org.example.model.Team;
import org.example.util.Ansi;

public class Trap extends Block {
    private final String trap = """
                //-------\\\\
                ||       ||
                \\\\-------//
                """;

    public Trap(Team team) {
        super(team);
    }

    @Override
    public String getBlockLayer(Integer index) {
        if (this.hasChess() && index == 1) {
            if (this.chess.getTeam() == Team.RED) {
                return Ansi.YELLOW + "||" + Ansi.RED + fixedLengthString(this.chess.getClass().getSimpleName(),7) + Ansi.YELLOW + "||";
            }
            else {
                return Ansi.YELLOW + "||" + Ansi.CYAN + fixedLengthString(this.chess.getClass().getSimpleName(),7) + Ansi.YELLOW + "||";
            }
        }
        else {
            String[] result = trap.split("\n");
            return Ansi.Yellow.format("%s", result[index]);
        }
    }
}
