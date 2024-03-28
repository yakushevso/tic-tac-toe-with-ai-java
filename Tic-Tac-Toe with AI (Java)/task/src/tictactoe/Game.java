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
        field.initField(ui.testField());
        field.printField();
        logic.update(field.getState());

        char userChar = ui.userChar(field.getState());

        while (logic.isNotFinished()) {
            String move = ui.readMove();

            if (logic.isValidMove(move)) {
                field.makeMove(move, userChar);
                logic.update(field.getState());

                if (logic.isNotFinished()) {
                    System.out.println("Game not finished");
                }

                field.printField();
                logic.printResult();

                userChar = userChar == 'X' ? 'O' : 'X';
            }
        }
    }
}