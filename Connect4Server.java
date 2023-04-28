import java.util.Random;
import java.util.Scanner;

public class Connect4Server {
    public static final int ROWS = 6;
    public static final int COLUMNS = 7;
    public static int PLAYS = 0;
    public static final char YELLOW = 'Y';
    public static final char BLUE = 'B';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        final int[][] board = new int[COLUMNS][ROWS];

        for(int i = 0; i < COLUMNS; i++) {
            for(int j = 0; j < ROWS; j++) {
                board[i][j] = 0;
            }
        }

        String turn = "Yellow";
        boolean gameWon = false;

        while(!gameWon) {
            buildBoard(board);

            System.out.println("Provide the row (1 to 7) to put a number in or type 8 to end the game.");
            System.out.print(turn + "'s turn: ");
            int chosen_row = scanner.nextInt();
            System.out.println("Input for " + turn + " : " + chosen_row);

            if (chosen_row == 8) {
                PLAYS = -1;
                System.out.println("Ending game.");
                break;
            }

            if(turn == "Yellow") {
                putIntoBoard(board, chosen_row, 1);
                turn = "Blue";
            } else {
                putIntoBoard(board, chosen_row, 2);
                turn = "Yellow";
            }

            gameWon = winCheck(board);

            if(gameWon) {
                if(turn == "Yellow") turn = "Blue";
                else turn = "Yellow";
                break;
            }
            else if(PLAYS >= 42) {
                gameWon = true;
            }
        }

        buildBoard(board);
        if(winCheck(board)) System.out.println("Winner is: " + turn);
        else if(PLAYS >= 42) System.out.println("Tie.");
        else if(PLAYS == -1) System.out.println("Game ended by: " + turn);
    }

    public static void buildBoard(int[][] board) {
        String b = "";

        for (int i = 0; i < ROWS; i++) {
            for(int j = 0; j < COLUMNS; j++) {
                b += "|";
                if(board[j][i] == 0) {
                    b += " ";
                } else if(board[j][i] == 1) {
                    b += YELLOW;
                } else if(board[j][i] == 2) {
                    b += BLUE;
                }
            }
            b += "|\n";
        }
        System.out.println(b);
    }

    public static void putIntoBoard(int[][] board, int row, int value) {
        row = row - 1;

        if(board[row][0] != 0) { // If the first value of the row is anything other than zero, then there is no need to run the loop.
            System.out.println("Row is full. Pick another.");
        } else {
            for(int i = (ROWS - 1); i >= 0; i--) {
                if(board[row][i] == 0) {
                    board[row][i] = value;
                    break;
                }
            }
            System.out.println("Placed value into row.");
            PLAYS++;
        }

        //return board;
    }

    public static boolean winCheck(int[][] board) {
        return checkVertical(board);
    }

    public static boolean checkHorizontal(int[][] board) {
        int nOfMatchingConsecutiveValues = 0;

        return true;
        // ...To implement.
    }

    public static boolean checkVertical(int[][] board) {
        int nOfMatchingConsecutiveValues = 0;

        for(int i = 0; i < COLUMNS; i++) {
            if(board[i][5] == 0) continue;
            if(nOfMatchingConsecutiveValues >= 4) break; // No point in running the loop if the necessary value has been hit.
            nOfMatchingConsecutiveValues = 0;

            for(int j = (ROWS - 1); j >= 0 ; j--) {
                if (board[i][j] == 0) continue;
                if(nOfMatchingConsecutiveValues >= 4) break;

                try {
                    if(j-1 == (-1)) break;
                    if (board[i][j] == board[i][j - 1]) {
                        if(nOfMatchingConsecutiveValues == 0) nOfMatchingConsecutiveValues++;
                        nOfMatchingConsecutiveValues = nOfMatchingConsecutiveValues + 1;
                    } else {
                        nOfMatchingConsecutiveValues = 0;
                    }
                } catch (Exception ignored) {

                }

            }
        }

        if(nOfMatchingConsecutiveValues >= 4) {
            return true;
        }
        return false;
    }

    public static boolean checkDiagonal(int[][] board) {
        return true;
        // ...To implement.
    }
}
