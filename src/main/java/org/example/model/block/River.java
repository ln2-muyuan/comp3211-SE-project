package org.example.model.block;

import org.example.model.Block;
import org.example.model.Team;
import org.example.util.Ansi;

public class River extends Block {
    private final String river = """
                |||  -  |||
                |||     |||
                |||  -  |||
                """;


    public River() {
        super();
    }

    @Override
    public String getBlockLayer(Integer index) {
        if (this.hasChess() && index == 1) {
            if (this.chess.getTeam() == Team.RED) {
                return Ansi.BLUE + "|||" + Ansi.RED + " Rat " + Ansi.BLUE + "|||";
            }
            else {
                return Ansi.BLUE + "|||" + Ansi.CYAN + " Rat " + Ansi.BLUE + "|||";
            }
        }
        else {
            String[] result = river.split("\n");
            return Ansi.Blue.format("%s", result[index]);
        }
    }
}

