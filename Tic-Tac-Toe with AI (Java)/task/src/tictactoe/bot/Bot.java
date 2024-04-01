package tictactoe.bot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Bot {
    protected char[][] field;

    protected int[] randomMove() {
        Random random = new Random();
        List<int[]> freeCells = new ArrayList<>();

        for (int x = 0; x < field.length; x++) {
            for (int y = 0; y < field[0].length; y++) {
                if (field[x][y] == ' ') {
                    freeCells.add(new int[]{x, y});
                }
            }
        }

        int[] selectedCell = freeCells.get(random.nextInt(freeCells.size()));

        return new int[]{selectedCell[0] + 1, selectedCell[1] + 1};
    }

    public abstract int[] makeMoveBot();
}
