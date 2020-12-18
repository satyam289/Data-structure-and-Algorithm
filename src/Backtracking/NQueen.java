package Backtracking;


public class NQueen {

    public static void main(String[] args) {
        solveNQueen();
    }

    public static void solveNQueen() {
        int[][] board = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        dosolveNQueen(board, 0);
        for (int[] ints : board) {
            for (int j = 0; j < ints.length; j++) {
                System.out.print(ints[j] + "  ");
            }
            System.out.println();
        }
    }

    public static boolean dosolveNQueen(int[][] board, int col) {

        if (col >= board.length)
            return true;
        for (int i = 0; i < board.length; i++) {

            if (safe(board, i, col)) {
                board[i][col] = 1;

                if (dosolveNQueen(board, col + 1))
                    return true;
                board[i][col] = 0;
            }
        }
        return false;
    }

    public static boolean safe(int[][] board, int row, int col) {

        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1)
                return false;
        }
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1)
                return false;
        }
        for (int i = row, j = col; i < board.length && j >= 0; i++, j--) {
            if (board[i][j] == 1)
                return false;
        }
        return true;
    }
}
