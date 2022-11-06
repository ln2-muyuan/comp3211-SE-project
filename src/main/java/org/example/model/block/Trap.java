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
        String[] result = trap.split("\n");
        String msg = Ansi.Yellow.format("%s", result[index]);
        return msg;
    }
}
