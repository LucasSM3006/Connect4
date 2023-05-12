import java.util.Random;
import java.util.Scanner;

public class Connect4Server implements Connect4 {
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

        //checkHorizontal(board);

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
    }

    public static boolean winCheck(int[][] board) {
        boolean v = checkVertical(board);
        boolean h = checkHorizontal(board);
        boolean d = checkDiagonals(board);

        if(v || h || d) return true;
        return false;
    }

    public static boolean checkHorizontal(int[][] board) {
        boolean matched4 = false;

        for(int i = 5; i >= 0; i--) {
            if(matched4) break;
            for(int j = 0; j < 4; j++) {
                if(board[j][i] == 0) continue;
                if(board[j][i] == board[j + 1][i] &&
                board[j][i] == board[j + 2][i] &&
                board[j][i] == board[j + 3][i]) {
                    matched4 = true;
                    break;
                }
            }
        }

        return matched4;
    }

    public static boolean checkVertical(int[][] board) {
        boolean matched4 = false;

        for(int i = 0; i < 7 ;i++) {
            if(matched4) break;
            for(int j = 5; j > 2; j--) {
                if(board[i][j] == 0) continue;
                if(board[i][j] == board[i][j - 1] &&
                board[i][j] == board[i][j - 2] &&
                board[i][j] == board[i][j - 3]) {
                    matched4 = true;
                    break;
                }
            }
        }

        return(matched4);
    }

    public static boolean checkDiagonals(int[][] board) {
        return (checkDiagonalLeftToRight(board) || checkDiagonalRightToLeft(board));
    }

    public static boolean checkDiagonalLeftToRight(int[][] board) {
        boolean matched4 = false;

        // Lock the column, move row. Bottom of matrix is column 5. First row of matrix is 0.
        for(int i = 0; i < 4; i++) {
            if(matched4) break;
            for(int j = 5; j > 2 ;j--) {
                if(board[i][j] == 0) continue;
                if(board[i][j] == board[i + 1][j - 1] &&
                board[i][j] == board[i + 2][j - 2] &&
                board[i][j] == board[i + 3][j - 3]) {
                    matched4 = true;
                    break;
                }
            }
        }

        return(matched4);
    }

    public static boolean checkDiagonalRightToLeft(int[][] board) {
        boolean matched4 = false;

        //lock the column, move the row. Bottom of the left is 6th column, 5th row.
        for(int i = 6; i > 2 ;i--) {
            if(matched4) break;
            for(int j = 5; j > 2; j--) {
                if(board[i][j] == 0) continue;
                if(board[i][j] == board[i - 1][j - 1] &&
                board[i][j] == board[i - 2][j - 2] &&
                board[i][j] == board[i - 3][j - 3]) {
                    matched4 = true;
                    break;
                }
            }
        }

        return(matched4);
    }
}
