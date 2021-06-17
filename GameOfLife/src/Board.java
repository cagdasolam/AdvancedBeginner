public class Board {
    private int[][] board;
    private int height;
    private int width;

    public Board(int height, int width) {
        this.height = height;
        this.width = width;
        this.board = new int[height][width];
        randomState();
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

//    this is for create an empty matrix
    public int[][] deadState(){
        int[][] deadBoard = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                deadBoard[i][j] = 0;
            }
        }
        return deadBoard;
    }

//    create random start state
    public void randomState(){
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                board[i][j] = (int)(Math.random() * 2); // 0 or 1
            }
        }
    }

//    print current state if there is a living cell in it prints "\u2588"
    public void render(int[][] board){
        String[][] renderedBoard = new String[height][width];
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                if (board[i][j] == 1){
                    renderedBoard[i][j] = "\u2588";
                }else {
                    renderedBoard[i][j] = " ";
                }
            }
        }

        System.out.println("-------" );
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(renderedBoard[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-------" );
    }

    //it calculates next state according to games rules
    public int[][] nextBoardState(int[][] board){
        //create an empty state
        int[][] nextBoardState = deadState();
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++) {
                int liveNeighbor = liveNeighbor(j, i, board);
//                check current cell
                if (board[i][j] == 1){
                    // check number of live neighbor
                    // number of live neighbors 2 or 3 than save the cell
                    if (liveNeighbor == 2 || liveNeighbor == 3){
                        nextBoardState[i][j] = 1;
                    }
                }else{
                    // number of live neighbors 3 tha reproduce
                    if (liveNeighbor == 3){
                        nextBoardState[i][j] = 1;
                    }
                }
            }
        }
        return nextBoardState;
    }

//    returns number of live neighbors current cell
    private int liveNeighbor(int x, int y,int[][] board){
        int liveNeighbor = 0;
        int currentCellY = y;
        int currentCellX = x;
        for (int i = currentCellY-1; i < currentCellY+2; i++){
            if (i >= 0 && i <= height-1){
                for (int j = currentCellX-1; j < currentCellX+2; j++){
                    if (j >= 0 && j <= width-1){
                        if (!(i == currentCellY && j == currentCellX)){
                            if (board[i][j] == 1){
                                liveNeighbor++;
                            }
                        }
                    }
                }
            }
        }
        return liveNeighbor;
    }

}
