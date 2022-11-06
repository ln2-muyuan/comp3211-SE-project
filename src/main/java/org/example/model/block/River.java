package org.example.model.block;

import org.example.model.Block;
import org.example.model.Chess;
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
    public String print(Integer index) {
        String msg = Ansi.Blue.format("%s", river);
        String[] result = msg.split("\n");
        return result[index];
    }


}

