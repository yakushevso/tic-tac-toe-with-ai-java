package tictactoe.bot;

public class EasyBot extends Bot {
    public EasyBot(char[][] field) {
        this.field = field;
    }

    @Override
    public int[] makeMoveBot() {
        System.out.println("Making move level \"easy\"");

        return findRandomMove();
    }
}
