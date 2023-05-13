import java.io.Serializable;

public class Board implements Serializable {
    private final int ROWS = 6;
    private final int COLUMNS = 7;
    private Symbol[][] state;
    private Symbol turn;
    private Symbol winner;

    public Board() {
        state = new Symbol[COLUMNS][ROWS];
        turn = Symbol.Y;
        winner = Symbol.EMPTY;

        reset();
    }

    public Symbol getTurn() {
        return this.turn;
    } // getTurn

    public Symbol getWinner() {
        return this.winner;
    } // getWinner

    public void reset() {
        for (int column = 0; column < COLUMNS; column++) {
            for (int row = 0; row < ROWS; row++) {
                this.state[column][row] = Symbol.EMPTY;
            }
        }
    } // reset

    public boolean mark(int column) {
        column = column - 1;

        boolean valid = this.state[column][0] == Symbol.EMPTY;

        if(valid) {
            for(int i = ROWS - 1; i >= 0; i--) {
                if(this.state[column][i] == Symbol.EMPTY) {
                    this.state[column][i] = this.turn;
                    checkWinner();
                    this.turn = (this.turn == Symbol.Y) ? Symbol.B : Symbol.Y;
                    break;
                }
            }
        } else {
            System.out.println("Row is full. Pick another.");
        }

        return valid;
    } // mark(int)

    @Override
    public String toString() {
        String b = " 1 2 3 4 5 6 7\n";

        for (int i = 0; i < ROWS; i++) {
            for(int j = 0; j < COLUMNS; j++) {
                b += "|";
                b += this.state[j][i].label;
            }
            b += "|\n";
        }

        return(b);
    } // toString

    private void checkWinner() {
        boolean v = checkVertical(this.state);
        boolean h = checkHorizontal(this.state);
        boolean d = checkDiagonals(this.state);

        if(v || h || d) {
            this.winner = this.turn;
        }
    } // checkWinner

    public static boolean checkHorizontal(Symbol[][] board) {
        boolean matched4 = false;

        for(int i = 5; i >= 0; i--) {
            if(matched4) break;
            for(int j = 0; j < 4; j++) {
                if(board[j][i] == Symbol.EMPTY) continue;
                if(board[j][i] == board[j + 1][i] &&
                        board[j][i] == board[j + 2][i] &&
                        board[j][i] == board[j + 3][i]) {
                    matched4 = true;
                    break;
                }
            }
        }

        return matched4;
    } // checkHorizontal(Symbol[][])

    public static boolean checkVertical(Symbol[][] board) {
        boolean matched4 = false;

        for(int i = 0; i < 7 ;i++) {
            if(matched4) break;
            for(int j = 5; j > 2; j--) {
                if(board[i][j] == Symbol.EMPTY) continue;
                if(board[i][j] == board[i][j - 1] &&
                        board[i][j] == board[i][j - 2] &&
                        board[i][j] == board[i][j - 3]) {
                    matched4 = true;
                    break;
                }
            }
        }

        return(matched4);
    } // checkVertical(Symbol[][])

    public static boolean checkDiagonals(Symbol[][] board) {
        return (checkDiagonalLeftToRight(board) || checkDiagonalRightToLeft(board));
    } // checkDiagonals(Symbol[][])

    public static boolean checkDiagonalLeftToRight(Symbol[][] board) {
        boolean matched4 = false;

        // Lock the column, move row. Bottom of matrix is column 5. First row of matrix is 0.
        for(int i = 0; i < 4; i++) {
            if(matched4) break;
            for(int j = 5; j > 2 ;j--) {
                if(board[i][j] == Symbol.EMPTY) continue;
                if(board[i][j] == board[i + 1][j - 1] &&
                        board[i][j] == board[i + 2][j - 2] &&
                        board[i][j] == board[i + 3][j - 3]) {
                    matched4 = true;
                    break;
                }
            }
        }

        return(matched4);
    } // checkDiagonalLeftToRight(Symbol[][])

    public static boolean checkDiagonalRightToLeft(Symbol[][] board) {
        boolean matched4 = false;

        //lock the column, move the row. Bottom of the left is 6th column, 5th row.
        for(int i = 6; i > 2 ;i--) {
            if(matched4) break;
            for(int j = 5; j > 2; j--) {
                if(board[i][j] == Symbol.EMPTY) continue;
                if(board[i][j] == board[i - 1][j - 1] &&
                        board[i][j] == board[i - 2][j - 2] &&
                        board[i][j] == board[i - 3][j - 3]) {
                    matched4 = true;
                    break;
                }
            }
        }

        return(matched4);
    } // checkDiagonalRightToLeft(Symbol[][])
} // Board class.
