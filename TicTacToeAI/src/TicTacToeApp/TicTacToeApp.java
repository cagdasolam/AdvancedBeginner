package TicTacToeApp;

import TicTacToeGame.GameBoard;
import TicTacToeGame.TicTacToeAI;

import java.util.Scanner;

public class TicTacToeApp {

    public static void main(String[] args) {

        label:
        while (true){
            GameBoard board = new GameBoard();
            TicTacToeAI ai = new TicTacToeAI();
            String[][] gameBoard = board.getBoard();

            System.out.println(
                    "PLAYER VS PLAYER : 0\n" +
                    "PLAYER VS AI : 1\n" +
                    "    AI VS AI : 2\n" +
                    "        EXIT : 3");

            Scanner scan = new Scanner(System.in);
            int option = scan.nextInt();

            switch (option) {
//            PLAYER VS PLAYER
                case 0: {
                    board.render(gameBoard);
                    int turn = 0;
                    while (!board.isFull(gameBoard)) {
                        String currentPlayer;
                        if (turn % 2 == 0) {
                            currentPlayer = "X";
                        } else {
                            currentPlayer = "O";
                        }
                        System.out.println("Player " + currentPlayer + " turn.");
                        System.out.println("Enter your X Cord: ");
                        int xCor = scan.nextInt();
                        System.out.println("Enter your y Cord: ");
                        int yCor = scan.nextInt();
                        board.makeMove(xCor, yCor, currentPlayer);
                        board.render(gameBoard);
                        if (board.getWinner(gameBoard)) {
                            System.out.println("PLAYER " + currentPlayer + " WON !!!!");
                            break;
                        }
                        if (board.isFull(gameBoard)) {
                            System.out.println("IT'S A DRAW!!!");
                            break;
                        }
                        turn++;
                    }
                    break;
                }
                //PLAYER VS AI
                case 1: {
                    board.render(gameBoard);
                    int turn = 0;
                    while (!board.isFull(gameBoard)) {
                        if (turn % 2 == 0) {
                            while (true) {
                                if (board.getInput()) {
                                    break;
                                }
                            }
                            board.render(gameBoard);
                            if (board.getWinner(gameBoard)) {
                                System.out.println("PLAYER WON !!!!");
                                break;
                            }
                        } else {
                            int[] aiMove = ai.winningMoveAI(gameBoard, "O");
                            board.makeMove(aiMove[0], aiMove[1], "O");
                            board.render(gameBoard);
                            if (board.getWinner(gameBoard)) {
                                System.out.println("PLAYER LOST !!!!");
                                break;
                            }
                        }
                        if (board.isFull(gameBoard)) {
                            System.out.println("IT'S A DRAW!!!");
                            break;
                        }
                        turn++;

                    }
                    break;
                }
                // AI VS AI
                case 2: {
                    board.render(gameBoard);
                    int turn = 0;
                    while (!board.isFull(gameBoard)) {
                        if (turn % 2 == 0) {
                            int[] aiMove = ai.winningMoveAI(gameBoard, "X");
                            board.makeMove(aiMove[0], aiMove[1], "X");
                            board.render(gameBoard);
                            if (board.getWinner(gameBoard)) {
                                System.out.println("X WON !!!!");
                                break;
                            }
                            if (board.isFull(gameBoard)) {
                                System.out.println("IT'S A DRAW!!!");
                                break;
                            }
                            turn++;
                        } else {
                            int[] aiMove = ai.winningMoveAI(gameBoard, "O");
                            board.makeMove(aiMove[0], aiMove[1], "O");
                            board.render(gameBoard);
                            if (board.getWinner(gameBoard)) {
                                System.out.println("O WON !!!!");
                                break;
                            }
                            if (board.isFull(gameBoard)) {
                                System.out.println("IT'S A DRAW!!!");
                                break;
                            }
                            turn++;
                        }
                    }
                    break;
                }
                default:
                    break label;
            }
        }


    }
}
