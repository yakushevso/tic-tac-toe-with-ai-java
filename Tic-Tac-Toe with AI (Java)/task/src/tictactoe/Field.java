package tictactoe;

import java.util.Arrays;

public class Field {
    private char[][] field;
    private final int size;
    private final String horLine;
    private final String verLineLeft;
    private final String verLineRight;

    public Field(int size) {
        this.size = size;
        horLine = "-".repeat(size * 2 + 3);
        verLineLeft = "| ";
        verLineRight = "|";
    }

    public char[][] getFieldCopy() {
        char[][] fieldCopy = new char[size][size];

        for (int i = 0; i < size; i++) {
            System.arraycopy(field[i], 0, fieldCopy[i],
                    0, field[i].length);
        }

        return fieldCopy;
    }

    public void init() {
        field = new char[size][size];

        for (char[] row : field) {
            Arrays.fill(row, ' ');
        }
    }

    public void print() {
        System.out.println(horLine);

        for (char[] inputArr : field) {
            System.out.print(verLineLeft);

            for (char anInputArr : inputArr) {
                System.out.print(anInputArr + " ");
            }

            System.out.println(verLineRight);
        }

        System.out.println(horLine);
    }

    public void add(int x, int y, char moveChar) {
        field[x - 1][y - 1] = moveChar;
    }
}
