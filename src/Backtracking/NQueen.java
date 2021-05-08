package Backtracking;

public class NQueen {

    public static void main(String[] args) {
        solveNQueen(4);
        solveNQueen2(4);
    }

    public static void solveNQueen(int n) {
        int[][] board = new int[n][n];
        if (dosolveNQueen(board, 0)) {
            printTable(board);
        } else {
            System.out.println(" No solution");
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
        for (int i = row, j = col; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 1)
                return false;
        }
        return true;
    }

    // Space Complexity : 0(N)
    public static void solveNQueen2(int n) {
	    int[] table = new int[n];
        solve(table, 0);
        printArr(table);
	}

    private static boolean solve(int[] table, int row) {
        if (row == table.length) {
            return true;
        } else {
            for (int col = 0; col < table.length; col++) {
                table[row] = col;
                if (isSafe(table, row) && solve(table, row + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

	private static boolean isSafe(int[] table, int n) {
        for (int i = 0; i < n; i++) {
            if ((table[i] == table[n])   // downward
                || (table[i] - table[n]) == (n - i)  // left diagonal
                || (table[n] - table[i]) == (n - i) // right diagonal
               )  
            return false;
        }
        return true;
    }

    private static void printTable(int[][] table) {
        for (int[] row : table) {
            for (int j = 0; j < row.length; j++) {
                System.out.print(row[j] + "  ");
            }
            System.out.println();
        }
    }

    private static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            String s = "";
            for (int j = 0; j < arr.length; j++) {
                s += (arr[i] == j) ? " Q " : " 0 ";
            }
            System.out.println(s);
        }
    }
}
