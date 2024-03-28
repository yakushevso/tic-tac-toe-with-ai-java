package tictactoe;

import java.util.Scanner;

public class UI {
    private final Scanner SC = new Scanner(System.in);

    public int readFieldSize() {
        while (true) {
            if (SC.hasNextInt()) {
                return SC.nextInt();
            } else {
                System.out.println("You should enter numbers!");
                SC.next();
            }
        }
    }

    public String readMove() {
        System.out.print("Enter the coordinates: ");
        return SC.nextLine();
    }

    public char[][] testField() {
        System.out.println("Enter the cells:");
        char[][] inputField = new char[3][3];
        String input;

        do {
            input = SC.nextLine();

            if (input.length() == 9) {
                for (int i = 0; i < 9; i++) {
                    int row = i / 3;
                    int col = i % 3;
                    inputField[row][col] = input.charAt(i);
                }
            } else {
                System.out.println("The string length is incorrect. 9 characters are expected.");
            }
        } while (input.length() != 9);

        return inputField;
    }

    public char userChar(char[][] field) {
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

        return  xCount <= oCount ? 'X' : 'O';
    }
}
