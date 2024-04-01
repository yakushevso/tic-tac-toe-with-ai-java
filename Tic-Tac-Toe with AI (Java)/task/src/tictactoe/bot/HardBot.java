package tictactoe.bot;

import tictactoe.Logic;

public class HardBot extends Bot {
    private final Logic logic;
    private final char moveChar;

    public HardBot(Logic logic, char[][] field, char moveChar) {
        this.logic = logic;
        this.field = field;
        this.moveChar = moveChar;
    }

    @Override
    public int[] makeMoveBot() {
        System.out.println("Making move level \"hard\"");
        return minimax(field, true, Integer.MIN_VALUE, Integer.MAX_VALUE).move;
    }

    private Result minimax(char[][] field, boolean isMaximizing, int alpha, int beta) {
        logic.update(field);

        if (logic.xWins()) {
            return new Result(moveChar == 'X' ? 1 : -1, null);
        } else if (logic.oWins()) {
            return new Result(moveChar == 'X' ? -1 : 1, null);
        } else if (logic.isDraw()) {
            return new Result(0, null);
        }

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int[] bestMove = null;

        for (int x = 0; x < field.length; x++) {
            for (int y = 0; y < field[0].length; y++) {
                if (field[x][y] == ' ') {
                    field[x][y] = isMaximizing ? moveChar : moveChar == 'X' ? 'O' : 'X';
                    int score = minimax(field, !isMaximizing, alpha, beta).score;
                    field[x][y] = ' ';

                    if (isMaximizing) {
                        if (score > bestScore) {
                            bestScore = score;
                            bestMove = new int[]{x + 1, y + 1};
                        }

                        alpha = Math.max(alpha, score);
                    } else {
                        if (score < bestScore) {
                            bestScore = score;
                            bestMove = new int[]{x + 1, y + 1};
                        }

                        beta = Math.min(beta, score);
                    }

                    if (beta <= alpha) {
                        break;
                    }
                }
            }

            if (isMaximizing && alpha >= beta) {
                break;
            }
        }

        return new Result(bestScore, bestMove);
    }

    static class Result {
        int score;
        int[] move;

        Result(int score, int[] move) {
            this.score = score;
            this.move = move;
        }
    }
}
