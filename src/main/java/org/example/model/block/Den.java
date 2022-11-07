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
        String[] result = den.split("\n");
        String msg = "";
        if (this.team == Team.RED) {
            msg = Ansi.Red.format("%s", result[index]);
        }
        if (this.team == Team.BLUE) {
            msg = Ansi.Cyan.format("%s", result[index]);
        }
        return msg;
    }
}
