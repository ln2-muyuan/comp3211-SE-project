package org.example.model.block;

import org.example.model.Block;
import org.example.model.Chess;
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
    public String print(Integer index) {
        String msg = Ansi.Yellow.format("%s", trap);
        String[] result = msg.split("\n");
        return result[index];
    }



}
