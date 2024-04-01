package tictactoe;

import tictactoe.bot.Bot;
import tictactoe.bot.EasyBot;
import tictactoe.bot.MediumBot;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class UI {
    private final Scanner SC = new Scanner(System.in);
    private final Field field = new Field(3);
    private final Logic logic = new Logic();

    public void run() {
        while (true) {
            field.init();
            logic.update(field.getFieldCopy());

            String[] input = readInput();
            String mode = input[0];

            switch (mode) {
                case "start" -> {
                    field.print();

                    String fPlayer = input[1];
                    String sPlayer = input[2];
                    boolean playerTurn = true;

                    while (logic.isNotFinished()) {
                        int[] move;

                        if (playerTurn) {
                            move = readMove(fPlayer, 'X');
                        } else {
                            move = readMove(sPlayer, 'O');
                        }

                        if (move != null && logic.isValidMove(move)) {
                            if (playerTurn) {
                                makeMove(move, 'X');
                            } else {
                                makeMove(move, 'O');
                            }

                            logic.update(field.getFieldCopy());
                            field.print();

                            playerTurn = !playerTurn;
                        }
                    }

                    logic.printResult();
                }
                case "exit" -> {
                    return;
                }

                default -> System.out.println("Bad parameters!");
            }
        }
    }

    public String[] readInput() {
        while (true) {
            System.out.print("Input command: ");
            String[] input = SC.nextLine().split("\\s+");
            Set<String> type = new HashSet<>(Arrays.asList("start", "user", "easy", "medium"));

            if (input.length == 1 && "exit".equals(input[0])
                    || input.length == 3 && type.contains(input[1]) && type.contains(input[2])) {
                return input;
            } else {
                System.out.println("Bad parameters!");
            }
        }
    }

    private int[] readMove(String player, char moveChar) {
        switch (player) {
            case "user" -> {
                return readMoveUser();
            }
            case "easy" -> {
                return readMoveBot(new EasyBot(field.getFieldCopy()));
            }
            case "medium" -> {
                return readMoveBot(new MediumBot(logic, field.getFieldCopy(), moveChar));
            }
        }

        return null;
    }

    public int[] readMoveUser() {
        while (true) {
            System.out.print("Enter the coordinates: ");
            String input = SC.nextLine();

            try {
                String[] parts = input.split("\\s+");
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);

                return new int[]{x, y};
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
            }
        }
    }

    public void makeMove(int[] move, char moveChar) {
        int x = move[0];
        int y = move[1];

        field.add(x, y, moveChar);
    }

    public int[] readMoveBot(Bot bot) {
        return bot.makeMoveBot();
    }

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
}
