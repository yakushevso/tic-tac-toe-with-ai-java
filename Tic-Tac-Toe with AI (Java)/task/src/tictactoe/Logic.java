package tictactoe;

public class Logic {
    private char[][] field;
    private int x;
    private int y;

    public void update(char[][] field) {
        this.field = field;
    }

    public boolean isNotFinished() {
        return !(isWinner() || isDraw());
    }

    private boolean isWinner() {
        return xWins() || oWins();
    }

    public boolean isDraw() {
        for (char[] chars : field) {
            for (int j = 0; j < field[0].length; j++) {
                if ((chars[j] == '_') || (chars[j] == ' ')) {
                    return false;
                }
            }
        }

        return !isWinner();
    }

    private boolean xWins() {
        return checkRows('X') || checkColumns('X') || checkDiagonals('X');
    }

    private boolean oWins() {
        return checkRows('O') || checkColumns('O') || checkDiagonals('O');
    }

    public boolean checkRows(char input) {
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

    public boolean checkColumns(char input) {
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

    public boolean checkDiagonals(char input) {
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

    public boolean isValidMove(String move) {
        return isConvertToIntegers(move) && isCorrectRange() && isEmpty();
    }

    private boolean isConvertToIntegers(String move) {
        try {
            String[] chars = move.split(" ");
            this.x = Integer.parseInt(chars[0]);
            this.y = Integer.parseInt(chars[1]);
            return true;
        } catch (Exception e) {
            System.out.println("You should enter numbers!");
            return false;
        }
    }

    private boolean isCorrectRange() {
        if (x <= 3 && x >= 1 && y <= 3 && y >= 1) {
            return true;
        } else {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }
    }

    private boolean isEmpty() {
        if (field[x - 1][y - 1] == ' ' || field[x - 1][y - 1] == '_') {
            return true;
        } else {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
    }

    public String winner() {
        if (isWinner()) {
            if (xWins()) {
                return "X";
            }
            return "O";
        }
        return "There is no winner!";
    }

    private boolean fieldIsLegal() {
        if (xWins() && oWins()) {
            return false;
        }

        int xCount = 0;
        int oCount = 0;

        for (char[] inputArr : field) {
            for (char anInputArr : inputArr) {
                if (anInputArr == 'X') {
                    xCount++;
                } else if (anInputArr == 'O') {
                    oCount++;
                }
            }
        }

        return xCount - oCount < 2 && xCount - oCount > -2;
    }

    public void printResult() {
        if (isDraw()) {
            System.out.println("Draw");
        } else if (!fieldIsLegal()) {
            System.out.println("Impossible");
        } else if (isNotFinished()) {
            System.out.println("Game not finished");
        } else {
            System.out.println(winner() + " wins");
        }
    }
}
