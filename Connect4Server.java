import java.util.Random;
import java.util.Scanner;

public class Connect4Server {
    public static final int ROWS = 6;
    public static final int COLUMNS = 7;
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
//        String b = "";

        while(true) {
            buildBoard(board);

            System.out.print("Provide the row (1 to 7) to put a number in or type 8 to end the game: ");
            int chosen_row = scanner.nextInt();
            System.out.println("Input: " + chosen_row);

            if (chosen_row == 8) {
                System.out.println("Ending game.");
                break;
            }
            putIntoBoard(board, chosen_row, 1);
            checkVertical(board);
        }


//        switch(chosen_row) {
//            case 1:
//
//                break;
//            case 2:
//
//                break;
//            default:
//        }

//        for (int i = 0; i < COLUMNS; i++) {
//            for(int j = 0; j < ROWS; j++) {
//                b += "|" + board[j][i];
//            }
//            b += "|\n";
//        }

//        System.out.println("Value at row 4, column 1: " + board[3][0]);
//        System.out.println("Value at row 2, column 4: " + board[1][3]);

//        System.out.println(b);



//        System.out.println("Rows: " + board.length);
//        System.out.println("Columns: " + board[0].length);


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

    public static int[][] putIntoBoard(int[][] board, int row, int value) {
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
        }

        return board;
    }

    public static void winCheck(int[][] board) {
        checkHorizontal(board);
        checkVertical(board);
        checkDiagonal(board);
    }

    public static void checkHorizontal(int[][] board) {
        int nOfMatchingConsecutiveValues = 0;

        // ...To implement.
    }

    public static void checkVertical(int[][] board) {
        int nOfMatchingConsecutiveValues = 0;

        for(int i = 0; i < COLUMNS; i++) {
            if(nOfMatchingConsecutiveValues == 4) break;
            if(board[i][5] == 0) continue;

            nOfMatchingConsecutiveValues = 0;

            for(int j = (ROWS - 1); j >= 0 ; j--) {
                if(board[i][j] == 0) continue;
                try {
                    if(nOfMatchingConsecutiveValues == 4) break;

                    if (board[i][j] != board[i][j - 1]) {
                        if(nOfMatchingConsecutiveValues == 4) break;
                        nOfMatchingConsecutiveValues = 0;
                    } else {
                        nOfMatchingConsecutiveValues++;
                    }
                } catch (Exception ignored) {
                }
                if(nOfMatchingConsecutiveValues == 4) break;
            }
        }

        if(nOfMatchingConsecutiveValues == 4) {
            System.out.println("Win!");
        }

    }

    public static void checkDiagonal(int[][] board) {

        // ...To implement.
    }
}
