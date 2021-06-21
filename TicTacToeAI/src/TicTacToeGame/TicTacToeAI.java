package TicTacToeGame;

import java.util.ArrayList;

public class TicTacToeAI {
    GameBoard gameBoard = new GameBoard();

    public int[] randomAI(String[][] board){
        ArrayList<int[]> empty = new ArrayList<int[]>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals(" ")){
                    int[] cord = {j, i};
                    empty.add(cord);
                }
            }
        }
        int random = (int)(Math.random() * empty.toArray().length);
        return empty.get(random);
    }

    public int[] winningMoveAI(String[][] board, String X){
        ArrayList<String[]> currentState = gameBoard.getState(board);
        int[] move = new int[2];
//        check row
//        check column
//        check diagonals
//        if there is a wining move make that move
//        else make random move
        if (checkRow(board, X) != null){
            move = checkRow(board, X);
            return move;
        }
        if (checkColumn(board, X) != null){
            move = checkColumn(board, X);
            return move;
        }
        if (checkDiagonals(board, X) != null){
            move = checkDiagonals(board, X);
            return move;
        }
        return randomAI(board);
    }

    private int[] checkRow(String[][] board, String X){
        ArrayList<String[]> currentState = gameBoard.getState(board);
        int[] row = new int[2];
//        check winning Move
        for (int i = 0; i < 3; i ++){
            int emptySpace = 0;
            int currentScore = 0;
            for (int j = 0; j < 3; j++){
                if (currentState.get(i)[j].equals(" ")){
                    emptySpace ++;
                }
                if (currentState.get(i)[j].equals(X)){
                    currentScore++;
                }
            }
            if (emptySpace == 1 && currentScore == 2){
                for (int j = 0; j < 3; j++){
                    if (currentState.get(i)[j].equals(" ")){
                        row[0] = j;
                        row[1] = i;
                        return row;
                    }
                }
            }
        }
//        check block move
        for (int i = 0; i < 3; i ++){
            int emptySpace = 0;
            int enemyScore = 0;
            for (int j = 0; j < 3; j++){
                if (currentState.get(i)[j].equals(" ")){
                    emptySpace ++;
                }
                else if (!currentState.get(i)[j].equals(X)){
                    enemyScore++;
                }
            }
            if (emptySpace == 1 && enemyScore == 2){
                for (int j = 0; j < 3; j++){
                    if (currentState.get(i)[j].equals(" ")){
                        row[0] = j;
                        row[1] = i;
                        return row;
                    }
                }
            }
        }
        return null;
    }

    private int[] checkColumn(String[][] board, String X){
        ArrayList<String[]> currentState = gameBoard.getState(board);
        int[] row = new int[2];
//        check winning move
        for (int i = 0; i < 3; i ++){
            int emptySpace = 0;
            int currentScore = 0;
            for (int j = 0; j < 3; j++){
                if (currentState.get(j)[i].equals(" ")){
                    emptySpace ++;
                }
               if (currentState.get(j)[i].equals(X)){
                    currentScore++;
                }
            }
            if (emptySpace == 1 && currentScore == 2){
                for (int j = 0; j < 3; j++){
                    if (currentState.get(j)[i].equals(" ")){
                        System.out.println("kazandıran");
                        row[0] = i;
                        row[1] = j;
                        return row;
                    }
                }
            }
        }
//        check block move
        for (int i = 0; i < 3; i ++){
            int emptySpace = 0;
            int enemyScore = 0;
            for (int j = 0; j < 3; j++){
                if (currentState.get(j)[i].equals(" ")){
                    emptySpace ++;
                }
                else if (!currentState.get(j)[i].equals(X)){
                    enemyScore ++;
                }
            }
            if (emptySpace == 1 && enemyScore == 2){
                for (int j = 0; j < 3; j++){
                    if (currentState.get(j)[i].equals(" ")){
                        System.out.println("engellenen");
                        row[0] = i;
                        row[1] = j;
                        return row;
                    }
                }
            }
            System.out.println(enemyScore);
        }
        return null;
    }

    private int[] checkDiagonals(String[][] board, String X){
        ArrayList<String[]> currentState = gameBoard.getState(board);
        int[] row = new int[2];
        String[][] diagonals = {{currentState.get(0)[0] , currentState.get(1)[1], currentState.get(2)[2]},
                                {currentState.get(0)[2], currentState.get(1)[1], currentState.get(2)[0]}};
//        check winning move
        for (int i = 0; i < 2 ; i++){
            String[] diagonal = diagonals[i];
            int emptySpace = 0;
            int currentScore = 0;
            for (String currentCell :
                    diagonal) {
                if (currentCell.equals(" ")) {
                    emptySpace++;
                }
                if (currentCell.equals(X)){
                    currentScore++;
                }
            }
            if (emptySpace == 1 && currentScore == 2){
                for (int j = 0; j < 3; j++){
                    if (i == 0){
                        if (diagonal[j].equals(" ")){
                            row[0] = j;
                            row[1] = j;
                            return row;
                        }
                    }else{
                        if (diagonal[j].equals(" ")){
                            row[0] = 2 - j;
                            row[1] = j;
                            return row;
                        }
                    }

                }
            }
        }
//        check block move
        for (int i = 0; i < 2 ; i++){
            String[] diagonal = diagonals[i];
            int emptySpace = 0;
            int enemyScore = 0;
            for (String currentCell :
                    diagonal) {
                if (currentCell.equals(" ")) {
                    emptySpace++;
                }
                else if (!currentCell.equals(X)){
                    enemyScore++;
                }
            }
            if (emptySpace == 1 && enemyScore == 2){
                for (int j = 0; j < 3; j++){
                    if (i == 0){
                        if (diagonal[j].equals(" ")){
                            row[0] = j;
                            row[1] = j;
                            return row;
                        }
                    }else{
                        if (diagonal[j].equals(" ")){
                            row[0] = 2 - j;
                            row[1] = j;
                            return row;
                        }
                    }
                }
            }
        }
        return null;
    }

}
