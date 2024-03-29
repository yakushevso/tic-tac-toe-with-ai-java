package tictactoe;

public class Game {
    private final Field field;
    private final Logic logic;
    private final UI ui;

    public Game() {
        this.ui = new UI();
        this.field = new Field(3);
        this.logic = new Logic();
    }

    public static void main(String[] args) {
        new Game().start();
    }

    private void start() {
        field.init();
        field.print();
        logic.update(field.getState());

        Bot bot = new EasyBot(3);
        boolean playerTurn = true;

        while (logic.isNotFinished()) {
            int[] move;

            if (playerTurn) {
                move = logic.convertToIntegers(ui.readMove());

                if (move == null) {
                    continue;
                }
            } else {
                move = ui.readMoveBot(bot, field.getState());
            }

            if (logic.isValidMove(move)) {
                if (playerTurn) {
                    ui.makeMove(field, move, 'X');
                } else {
                    ui.makeMove(field, move, 'O');
                }

                logic.update(field.getState());
                field.print();

                playerTurn = !playerTurn;
            }
        }

        logic.printResult();
    }
}