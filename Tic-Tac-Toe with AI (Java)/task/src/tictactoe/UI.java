package tictactoe;

import java.util.Scanner;

public class UI {
    private final Scanner SC = new Scanner(System.in);

    @SuppressWarnings("unused")
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

    public void makeMove(Field field, int[] move, char userChar) {
        int x = move[0];
        int y = move[1];

        field.add(x, y, userChar);
    }

    public int[] readMoveBot(Bot bot, char[][] field) {
        System.out.println("Making move level \"easy\"");

        return bot.makeMove(field);
    }
}
