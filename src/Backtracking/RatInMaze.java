package Backtracking;

import java.util.Arrays;

public class RatInMaze {

    public static void main(String[] args) {
        int maze[][] = {{1, 0, 0, 0},
                {1, 1, 0, 1},
                {0, 1, 0, 0},
                {1, 1, 1, 1}
        };
        solveMaze(maze);

    }

    public static void solveMaze(int[][] board) {
        int[][] solution = new int[board.length][board.length];
        if (solveMazeUtil(solution, board, 0, 0)) {
            for (int k = 0; k < solution.length; k++)
                System.out.println(Arrays.toString(solution[k]));
        } else {
            System.out.println("No solution exist");
        }

    }


    private static boolean solveMazeUtil(int[][] solution, int[][] board, int row, int col) {
        if (row == board.length-1 && col == board.length-1) {
            solution[row][col] = 1;
            return true;
        }

        if(isSafe(board, row, col)) {

            solution[row][col] = 1;
            if (solveMazeUtil(solution, board, row, col + 1))
                return true;

            if (solveMazeUtil(solution, board, row + 1, col))
                return true;

            solution[row][col] = 0;
        }
        return false;
    }

    private static boolean isSafe(int[][] board, int row, int col) {
        if (row >= board.length || col >= board[0].length || board[row][col] != 1)
            return false;
        return true;
    }
}
