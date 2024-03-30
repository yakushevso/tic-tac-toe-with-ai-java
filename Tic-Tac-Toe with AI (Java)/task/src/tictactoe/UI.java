package tictactoe;

import java.util.*;

public class UI {
    private final Scanner SC = new Scanner(System.in);

    public void run() {
        Field field = new Field(3);
        Logic logic = new Logic();

        field.init();
        logic.update(field.getState());

        while (true) {
            String[] input = readInput();

            switch (input[0]) {
                case "start" -> {
                    field.print();

                    boolean playerTurn = true;

                    while (logic.isNotFinished()) {
                        int[] move;

                        if (playerTurn) {
                            move = readMove(field, logic, input[1]);
                        } else {
                            move = readMove(field, logic, input[2]);
                        }

                        if (move != null && logic.isValidMove(move)) {
                            if (playerTurn) {
                                makeMove(field, move, 'X');
                            } else {
                                makeMove(field, move, 'O');
                            }

                            logic.update(field.getState());
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
            Set<String> type = new HashSet<>(Arrays.asList("start", "user", "easy"));

            if (input.length == 1 && "exit".equals(input[0])
                    || input.length == 3 && type.contains(input[1]) && type.contains(input[2])) {
                return input;
            } else {
                System.out.println("Bad parameters!");
            }
        }
    }

    private int[] readMove(Field field, Logic logic, String player) {
        switch (player) {
            case "user" -> {
                return logic.convertToIntegers(readMoveUser());
            }
            case "easy" -> {
                return readMoveBot(new EasyBot(3), field.getState());
            }
        }

        return null;
    }

    public String readMoveUser() {
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
