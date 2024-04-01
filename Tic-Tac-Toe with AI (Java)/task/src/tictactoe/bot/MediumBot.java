package tictactoe.bot;

import tictactoe.Logic;

public class MediumBot extends Bot {
    private final Logic logic;
    private final char moveChar;

    public MediumBot(Logic logic, char[][] field, char moveChar) {
        this.logic = logic;
        this.field = field;
        this.moveChar = moveChar;
    }

    @Override
    public int[] makeMoveBot() {
        System.out.println("Making move level \"medium\"");

        int[] winningMove = findWinningMove(moveChar);

        if (winningMove != null) {
            return winningMove;
        }

        char opponentChar = (moveChar == 'X') ? 'O' : 'X';
        int[] blockingMove = findWinningMove(opponentChar);

        if (blockingMove != null) {
            return blockingMove;
        }

        return findRandomMove();
    }

    private int[] findWinningMove(char moveChar) {
        for (int x = 0; x < field.length; x++) {
            for (int y = 0; y < field[0].length; y++) {
                if (field[x][y] == ' ' && tryMove(x, y, moveChar)) {
                    return new int[]{x + 1, y + 1};
                }
            }
        }

        return null;
    }

    private boolean tryMove(int x, int y, char moveChar) {
        field[x][y] = moveChar;
        logic.update(field);
        boolean winning = !logic.isNotFinished();
        field[x][y] = ' ';

        return winning;
    }
}
