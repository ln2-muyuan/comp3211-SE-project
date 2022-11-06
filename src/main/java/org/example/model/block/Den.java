package org.example.model.block;

import org.example.model.Block;
import org.example.model.Chess;
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

    public String print(Integer index) {
        String msg = "";
        if (this.team == Team.RED) {
            msg = Ansi.Red.format("%s", den);
        }
        if (this.team == Team.BLUE) {
            msg = Ansi.Cyan.format("%s", den);
        }
        String[] result = msg.split("\n");
        return result[index];
    }




    public static void main(String[] args) {
        River river = new River();
        System.out.print(river.print(0));
        System.out.print(river.print(1));
//        Grass grass = new Grass();
//        System.out.print(grass);
//        Den den = new Den(Team.RED);
//        System.out.print(den);
//        System.out.print(grass);
//        Trap trap = new Trap(Team.BLUE);
//        System.out.println(trap);
    }
}
