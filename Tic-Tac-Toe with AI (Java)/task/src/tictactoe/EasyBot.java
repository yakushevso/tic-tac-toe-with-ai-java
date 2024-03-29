package tictactoe;

import java.util.Random;

public class EasyBot implements Bot {
    private final int fieldSize;

    public EasyBot(int fieldSize) {
        this.fieldSize = fieldSize;
    }

    @Override
    public int[] makeMove(char[][] field) {
        int x;
        int y;

        Random random = new Random();

        do {
            x = 1 + random.nextInt(fieldSize);
            y = 1 + random.nextInt(fieldSize);
        } while (field[x - 1][y - 1] != ' ' && field[x - 1][y - 1] != '_');

        return new int[] {x, y};
    }
}
