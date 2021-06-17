import java.lang.reflect.Array;

public class GameOfLife {
    public static void main(String[] args) throws InterruptedException {
        Board board = new Board(10,10);
        int[][] start = board.getBoard();
        board.render(start);
        while (true) {
            Thread.sleep(1000);
            int[][] next = board.getBoard();
            board.setBoard(board.nextBoardState(next));
            board.render(board.getBoard());
        }
    }
}
