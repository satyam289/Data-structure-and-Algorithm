package graph;

import java.util.Arrays;

//https://leetcode.com/problems/surrounded-regions/
//https://www.interviewbit.com/problems/capture-regions-on-board/
public class CaptureRegionBoard {

    public static void solve1(char[][] board) {
        int m = board.length;
        int n = board[0].length;

        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    if (!dfs(board, i, j, visited)) {
                        dfsMark(board, i, j, 'X');
                    }
                }
            }
        }
        for (char[] arr : board) {
            System.out.println(Arrays.toString(arr));
        }
    }

    public static void solve2(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                dfsMark(board, i, 0, '1');
            }
            if (board[i][n - 1] == 'O') {
                dfsMark(board, i, n - 1, '1');
            }
        }
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') {
                dfsMark(board, 0, j, '1');
            }
            if (board[m - 1][j] == 'O') {
                dfsMark(board, m - 1, j, '1');
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '1') {
                    board[i][j] = 'O';
                }
            }
        }
        for (char[] arr : board) {
            System.out.println(Arrays.toString(arr));
        }
    }

    private static boolean dfs(char[][] board, int row, int col, boolean[][] visited) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return true;
        }
        if (board[row][col] == 'X') {
            return false;
        }
        if (visited[row][col]) {
            return false;
        }
        visited[row][col] = true;
        boolean isOpen = false;
        isOpen |= dfs(board, row + 1, col, visited);
        isOpen |= dfs(board, row - 1, col, visited);
        isOpen |= dfs(board, row, col + 1, visited);
        isOpen |= dfs(board, row, col - 1, visited);
        return isOpen;
    }

    private static void dfsMark(char[][] board, int row, int col, char markCh) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return;
        }
        if (board[row][col] == 'X' || board[row][col] == markCh) {
            return;
        }
        board[row][col] = markCh;
        dfsMark(board, row + 1, col, markCh);
        dfsMark(board, row - 1, col, markCh);
        dfsMark(board, row, col + 1, markCh);
        dfsMark(board, row, col - 1, markCh);
    }

    public static void main(String[] args) {
        char[][] input = { { 'X', 'X', 'X', 'X' }, { 'X', 'O', 'O', 'X' }, { 'X', 'X', 'O', 'X' },
                { 'X', 'O', 'X', 'X' } };
        solve1(input);
        solve2(input);
    }
}
