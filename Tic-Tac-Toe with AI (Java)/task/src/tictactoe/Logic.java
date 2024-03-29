package tictactoe;

public class Logic {
    private char[][] field;

    public void update(char[][] field) {
        this.field = field;
    }

    public boolean isNotFinished() {
        return !(xWins() || oWins() || isDraw());
    }

    private boolean isDraw() {
        for (char[] chars : field) {
            for (int j = 0; j < field[0].length; j++) {
                if ((chars[j] == '_') || (chars[j] == ' ')) {
                    return false;
                }
            }
        }

        return !(xWins() || oWins());
    }

    private boolean xWins() {
        return checkRows('X') || checkColumns('X') || checkDiagonals('X');
    }

    private boolean oWins() {
        return checkRows('O') || checkColumns('O') || checkDiagonals('O');
    }

    private boolean checkRows(char input) {
        for (char[] row : field) {
            boolean rowMatches = true;

            for (char cell : row) {
                if (cell != input) {
                    rowMatches = false;
                    break;
                }
            }

            if (rowMatches) {
                return true;
            }
        }

        return false;
    }

    private boolean checkColumns(char input) {
        for (int j = 0; j < field[0].length; j++) {
            boolean columnMatches = true;

            for (char[] chars : field) {
                if (chars[j] != input) {
                    columnMatches = false;
                    break;
                }
            }

            if (columnMatches) {
                return true;
            }
        }

        return false;
    }

    private boolean checkDiagonals(char input) {
        boolean mainDiagonal = true;
        boolean secondaryDiagonal = true;

        for (int i = 0; i < field.length; i++) {
            if (field[i][i] != input) {
                mainDiagonal = false;
            }

            if (field[i][field.length - 1 - i] != input) {
                secondaryDiagonal = false;
            }
        }

        return mainDiagonal || secondaryDiagonal;
    }

    public boolean isValidMove(int[] move) {
        return isCorrectRange(move) && isEmpty(move);
    }

    public int[] convertToIntegers(String move) {
        try {
            String[] chars = move.split(" ");
            int x = Integer.parseInt(chars[0]);
            int y = Integer.parseInt(chars[1]);
            return new int[]{x, y};
        } catch (Exception e) {
            System.out.println("You should enter numbers!");
            return null;
        }
    }

    private boolean isCorrectRange(int[] move) {
        int x = move[0];
        int y = move[1];

        if (x <= 3 && x >= 1 && y <= 3 && y >= 1) {
            return true;
        } else {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }
    }

    private boolean isEmpty(int[] move) {
        int x = move[0];
        int y = move[1];

        if (field[x - 1][y - 1] == ' ' || field[x - 1][y - 1] == '_') {
            return true;
        } else {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
    }

    public void printResult() {
        System.out.println(isDraw() ? "Draw" : xWins() ? "X wins" : "O wins");
    }
}
