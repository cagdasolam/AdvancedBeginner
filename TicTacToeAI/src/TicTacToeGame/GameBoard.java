package TicTacToeGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GameBoard {
    String[][] board ;
    Scanner scan = new Scanner(System.in);

    public GameBoard() {
        this.board = new String[3][3];
        this.board = startBoard();
    }

    public String[][] getBoard() {
        return board;
    }

    public void setBoard(String[][] board) {
        this.board = board;
    }

    public boolean getInput(){
        System.out.println("Player turn.");
        System.out.println("Enter your X Cord: ");
        int xCor = scan.nextInt();
        System.out.println("Enter your y Cord: ");
        int yCor = scan.nextInt();
        if (isValid(xCor, yCor)){
            makeMove(xCor, yCor,"X");
            return true;
        }
        return false;
    }

    private String[][] startBoard(){
        String[][] startBoard = new String[3][3];
        for (String[] strings : startBoard) {
            Arrays.fill(strings, " ");
        }
        return startBoard;
    }

    public void render(String[][] board){
        System.out.println("   0 1 2");
        System.out.println("  -------" );
        for (int i = 0; i < 3; i++) {
            System.out.print(i + "|");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }

            System.out.println("|");
        }
        System.out.println("  -------" );
    }

    public void makeMove(int x, int y, String currentPlayer){
        if (isValid(x, y)){
            board[y][x] = currentPlayer;
        }
    }

    private boolean isValid(int x, int y){
        if (board[y][x].equals("X") || board[y][x].equals("O")){
            System.out.println("Square is already taken!");
            return false;
        }else if (x < 3 && y < 3){
            return board[y][x].equals(" ");
        }
        else {
            System.out.println("Invalid move pls enter valid cord");
            return false;
        }
    }

    public boolean isFull(String[][] board) {
        for (String[] strings: board) {
            for (String string: strings) {
                if ((string.equals(" "))){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean getWinner(String[][] board){
        ArrayList<String[]> currentState = getState(board);

        for (String[] strings: currentState){
            String currentString = strings[0];
            int currentScore = 0;
            for (String string: strings){
                if (!string.equals(" ")){
                    if (currentString.equals(string)){
                        currentScore ++;
                    }
                }
            }
            if (currentScore == 3){
                return true;
            }
        }
        return false;
    }

    public ArrayList<String[]> getState(String[][] board){
        ArrayList<String[]> allStates = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            String[] raw = new String[3];
            for (int j = 0; j < 3; j++) {
                raw[j] = board[i][j];
            }
            allStates.add(raw);
        }
        for (int i = 0; i < 3; i++) {
            String[] raw = new String[3];
            for (int j = 0; j < 3; j++) {
                raw[j] = board[j][i];
            }
            allStates.add(raw);
        }
        String[][] diagonals = {{board[0][0] , board[1][1], board[2][2]},{board[2][0],board[1][1],board[0][2]}};
        allStates.add(diagonals[0]);
        allStates.add(diagonals[1]);
        return allStates;
    }

}
