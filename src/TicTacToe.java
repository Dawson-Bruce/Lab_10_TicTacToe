import java.util.Scanner;

public class TicTacToe {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String board [][] = new String[ROWS][COLS];
    private static int moves = 0;

    public static void main(String[] args) {
        String player = "O";
        boolean done = false;
        boolean gameOver = false;
        boolean moveSuccess = false;
        Scanner in = new Scanner(System.in);
        int curRowMove = 1;
        int curColMove = 1;

        do { // Main game loop
            // Reset game
            clearBoard();
            System.out.println();
            player = "O";
            gameOver = false;
            moves = 0;

            do { // Individual round loop
                // Swap players
                if (player == "O") {
                    player = "X";
                } else {
                    player = "O";
                }

                System.out.println("---   Player " + player + "   ---");
                display();
                moveSuccess = false;

                do { // Prompt for moves until successful
                    curRowMove = SafeInput.getRangedInt(in, "Please enter the row of your move", 1, ROWS) - 1;
                    curColMove = SafeInput.getRangedInt(in, "Please enter the column of your move", 1, COLS) - 1;
                    if (isValidMove(curRowMove, curColMove)) {
                        board[curRowMove][curColMove] = player;
                        moveSuccess = true;
                        moves += 1;
                    } else {
                        System.out.println("The entered move is not valid!");
                    }
                } while (!moveSuccess);

                System.out.println("");

                // Check if round continues
                if (isWin(player)) {
                    System.out.println("---              ---");
                    System.out.println("");
                    System.out.println("Player " + player + " has won!");
                    gameOver = true;
                } else if (isTie()) {
                    System.out.println("---              ---");
                    System.out.println("");
                    System.out.println("There was a tie!");
                    gameOver = true;
                }
            } while (!gameOver);

            display();

            // Prompt to play again
            done = !(SafeInput.getYNConfirm(in, "Player X: Continue? [Y/N]") && SafeInput.getYNConfirm(in, "Player O: Continue? [Y/N]"));
        } while (!done);
    }

    private static void clearBoard() {
        board = new String[ROWS][COLS];
    }

    private static void display() {
        for (int i = 0; i < ROWS; i++) {
            System.out.println();
            for (int v = 0; v < COLS; v++) {
                if (board[i][v] == null) {
                    System.out.print("-");
                } else {
                    System.out.print(board[i][v]);
                }
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    private static boolean isValidMove(int row, int col) {
        return board[row][col] == null;
    }

    private static boolean isWin(String player) {
        return isColWin(player) || isRowWin(player) || isDiagnalWin(player);
    }

    private static boolean isColWin(String player) {
        if (moves > 4) {
            for (int i = 0; i < COLS; i++) {
                boolean colCheck = true;
                for (int v = 0; v < ROWS; v++) {
                    if (board[v][i] != player) {
                        colCheck = false;
                    }
                }
                if (colCheck) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isRowWin(String player) {
        if (moves > 4) {
            for (int i = 0; i < ROWS; i++) {
                boolean rowCheck = true;
                for (int v = 0; v < COLS; v++) {
                    if (board[i][v] != player) {
                        rowCheck = false;
                    }
                }
                if (rowCheck) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isDiagnalWin(String player) {
        if (moves > 4) {
            boolean diagCheck = true;
            for (int i = 0; i < ROWS; i++) {
                if (board[i][i] != player) {
                    diagCheck = false;
                }
            }
            if (diagCheck) {
                return true;
            }

                boolean reverseDiagCheck = true;
                for (int i = 0; i < ROWS; i++) {
                    if (board[i][COLS - 1 - i] != player) {
                        reverseDiagCheck = false;
                    }
                }

                if (reverseDiagCheck) {
                    return true;
                }

        }

        return false;
    }

    private static boolean isTie() {
        if (moves > 8) {
            return true;
        } else {
            // Check to see if win vectors have both X and O
            for (int i = 0; i < COLS; i++) {
                boolean hasX = false;
                boolean hasO = false;
                for (int v = 0; v < ROWS; v++) {
                    if (board[v][i] == "X") {
                        hasX = true;
                    } else if (board[v][i] == "O") {
                        hasO = true;
                    }
                }
                if (!(hasX && hasO)) {
                    return false;
                }
            }
            for (int i = 0; i < ROWS; i++) {
                boolean hasX = false;
                boolean hasO = false;
                for (int v = 0; v < COLS; v++) {
                    if (board[i][v] == "X") {
                        hasX = true;
                    } else if (board[i][v] == "O") {
                        hasO = true;
                    }
                }
                if (!(hasX && hasO)) {
                    return false;
                }
            }
            boolean hasX = false;
            boolean hasO = false;
            for (int i = 0; i < ROWS; i++) {
                if (board[i][i] == "X") {
                    hasX = true;
                } else if (board[i][i] == "O") {
                    hasO = true;
                }
            }
            if (!(hasX && hasO)) {
                return false;
            }
            hasX = false;
            hasO = false;
            for (int i = 0; i < ROWS; i++) {
                if (board[i][COLS - 1 - i] == "X") {
                    hasX = true;
                } else if (board[i][COLS - 1 - i] == "O") {
                    hasO = true;
                }
            }
            if (!(hasX && hasO)) {
                return false;
            }

            return true;
        }
    }
}