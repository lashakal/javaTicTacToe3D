import java.util.Scanner;

public class TicTacToe {
    private static char[][][] board = new char[3][3][3];
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        initializeBoard();
        char winner = '\0';

        while (winner == '\0') {
            displayBoard();
            getPlayerMove();
            winner = checkWinner();
            switchPlayer();
        }

        displayBoard();
        if (winner == 'D') {
            System.out.println("It's a draw!");
        } else {
            System.out.println("Player " + winner + " wins!");
        }
    }

    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    board[i][j][k] = ' ';
                }
            }
        }
    }

    private static void displayBoard() {
        for (int k = 0; k < 3; k++) {
            System.out.println("Layer " + k);
            System.out.println("  0 1 2");
            for (int i = 0; i < 3; i++) {
                System.out.print(i + " ");
                for (int j = 0; j < 3; j++) {
                    System.out.print(board[i][j][k]);
                    if (j < 2) {
                        System.out.print("|");
                    }
                }
                System.out.println();
                if (i < 2) {
                    System.out.println("  -+-+-");
                }
            }
            System.out.println();
        }
    }

    private static void getPlayerMove() {
        Scanner scanner = new Scanner(System.in);
        int row, col, depth;
        do {
            System.out.print("Player " + currentPlayer + ", enter row (0, 1, or 2): ");
            row = scanner.nextInt();
            System.out.print("Player " + currentPlayer + ", enter column (0, 1, or 2): ");
            col = scanner.nextInt();
            System.out.print("Player " + currentPlayer + ", enter depth (0, 1, or 2): ");
            depth = scanner.nextInt();
        } while (!isValidMove(row, col, depth));

        board[row][col][depth] = currentPlayer;
    }

    private static boolean isValidMove(int row, int col, int depth) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3 || depth < 0 || depth >= 3 || board[row][col][depth] != ' ') {
            System.out.println("Invalid move! Try again.");
            return false;
        }
        return true;
    }

    private static char checkWinner() {
        // Check rows and columns in each layer, as well as diagonals
        for (int k = 0; k < 3; k++) {
            // Check rows and columns in the layer
            for (int i = 0; i < 3; i++) {
                if (board[i][0][k] == currentPlayer && board[i][1][k] == currentPlayer && board[i][2][k] == currentPlayer) {
                    return currentPlayer; // Row win
                }
                if (board[0][i][k] == currentPlayer && board[1][i][k] == currentPlayer && board[2][i][k] == currentPlayer) {
                    return currentPlayer; // Column win
                }
            }

            // Check diagonals
            if (board[0][0][k] == currentPlayer && board[1][1][k] == currentPlayer && board[2][2][k] == currentPlayer) {
                return currentPlayer; // Diagonal win
            }
            if (board[0][2][k] == currentPlayer && board[1][1][k] == currentPlayer && board[2][0][k] == currentPlayer) {
                return currentPlayer; // Diagonal win
            }
        }

        // Check lines going in depth
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j][0] == currentPlayer && board[i][j][1] == currentPlayer && board[i][j][2] == currentPlayer) {
                    return currentPlayer;
                }
            }
        }

        // Check cross layer diagonals
        if ((board[0][0][0] == currentPlayer && board[1][1][1] == currentPlayer && board[2][2][2] == currentPlayer) ||
                (board[0][2][0] == currentPlayer && board[1][1][1] == currentPlayer && board[2][0][2] == currentPlayer)) {
            return currentPlayer;
        }

        // Check for a draw
        boolean isBoardFull = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (board[i][j][k] == ' ') {
                        isBoardFull = false;
                        break;
                    }
                }
                if (!isBoardFull) break;
            }
            if (!isBoardFull) break;
        }

        return isBoardFull ? 'D' : '\0'; // 'D' for Draw, '\0' for no winner yet
    }

    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
}

