package Backtracking;

import java.util.Arrays;

public class Sudoku {

    public static void main(String[] args) {

        int[][] board = new int[][]
                {
                        {3, 0, 6, 5, 0, 8, 4, 0, 0},
                        {5, 2, 0, 0, 0, 0, 0, 0, 0},
                        {0, 8, 7, 0, 0, 0, 0, 3, 1},
                        {0, 0, 3, 0, 1, 0, 0, 8, 0},
                        {9, 0, 0, 8, 6, 3, 0, 0, 5},
                        {0, 5, 0, 0, 9, 0, 6, 0, 0},
                        {1, 3, 0, 0, 0, 0, 2, 5, 0},
                        {0, 0, 0, 0, 0, 0, 0, 7, 4},
                        {0, 0, 5, 2, 0, 6, 3, 0, 0}
                };
        if (!solveSudoku(board, board.length)) {
            System.out.println("Puzzle couldn't be solved, please check input");
        } else {
            System.out.println("Solved");
            for (int i = 0; i < board.length; i++) {
                System.out.print(Arrays.toString(board[i]));
                System.out.println();
            }
        }
    }

    public static boolean solveSudoku(int[][] board, int N) {
        boolean isFound = false;
        int row = -1, col = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) {
                    row = i;
                    col = j;
                    isFound = true;
                    break;
                }
            }
            if (isFound)
                break;
        }
        if (!isFound)
            return true;

        for (int num = 1; num <= N; num++) {
            if (isSafe(board, num, row, col)) {
                board[row][col] = num;
                if (solveSudoku(board, N)) {
                    return true;
                } else {
                    board[row][col] = 0;
                }
            }
        }
        return false;
    }

    private static boolean isSafe(int[][] board, int num, int row, int col) {

        for (int i = 0; i < board.length; i++) {   //check in row
            if (board[i][col] == num)
                return false;
        }
        for (int i = 0; i < board.length; i++) {   // check in column
            if (board[row][i] == num)
                return false;
        }
        int sqrt = (int) Math.sqrt(board.length);
        int initialRow = row - (row % sqrt);
        int initialCol = col - (col % sqrt);
        for (int i = initialRow; i < initialRow + sqrt; i++) {       // check in the block
            for (int j = initialCol; j < initialCol + sqrt; j++) {

                if (board[i][j] == num)
                    return false;
            }
        }
        return true;
    }
}
