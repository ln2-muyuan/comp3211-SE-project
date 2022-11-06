package org.example.model.block;

import org.example.model.Block;
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
            return Ansi.Blue.format("||| %s |||", this.chess.getClass().getSimpleName());
        }
        else {
            String[] result = river.split("\n");
            String msg = Ansi.Blue.format("%s", result[index]);
            return msg;
        }

    }



}

