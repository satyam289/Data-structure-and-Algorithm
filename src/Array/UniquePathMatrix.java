package Array;

import java.util.Arrays;

// https://www.interviewbit.com/problems/unique-paths-in-a-grid/
public class UniquePathMatrix {

    /*
     * Given a grid of size m * n, lets assume you are starting at (1,1) and your
     * goal is to reach (m,n). At any instance, if you are on (x,y), you can either
     * go to (x, y + 1) or (x + 1, y). Now consider if some obstacles are added to
     * the grids. Find unique paths. [ [0,0,0], [0,1,0], [0,0,0] ] The total number
     * of unique paths is 2.
     */
    public int uniquePathsWithObstacles(int[][] A) {

        int m = A.length;
        int n = A[0].length;
        if (m == 1 && n == 1 && A[0][0] == 0) {
            return 1;
        }
        int[][] table = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            table[i][0] = 0;
        }

        for (int j = 0; j < n; j++) {
            table[0][j] = 0;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                if (A[i - 1][j - 1] == 1) {
                    table[i][j] = 0;
                } else {
                    if (i == 1 && j == 1) {
                        table[i][j] = 1;
                    } else {
                        table[i][j] = table[i - 1][j] + table[i][j - 1];
                    }
                }
            }
        }
        return table[m][n];
    }

    // https://leetcode.com/problems/unique-paths/
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    // https://www.interviewbit.com/problems/increasing-path-in-matrix/
    /*
     * Given a 2D integer matrix A of size N x M. From A[i][j] you can move to
     * A[i+1][j], if A[i+1][j] > A[i][j], or can move to A[i][j+1] if A[i][j+1] >
     * A[i][j]. The task is to find and output the longest path length if we start
     * from (0, 0)
     */
    public int solve(int[][] A) {
        int n = A.length;
        int m = A[0].length;

        int[][] T = new int[n][m];
        for (int[] t : T) {
            Arrays.fill(t, -1);
        }
        T[0][0] = 1;
        for (int i = 1; i < n; i++) {
            if (A[i][0] > A[i - 1][0]) {
                if (T[i - 1][0] > 0) {
                    T[i][0] = 1 + T[i - 1][0];
                }
            }
        }
        for (int j = 1; j < m; j++) {
            if (A[0][j] > A[0][j - 1]) {
                if (T[0][j - 1] > 0) {
                    T[0][j] = 1 + T[0][j - 1];
                }
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                int max = -1;
                if (A[i][j] > A[i - 1][j]) {
                    max = T[i - 1][j];
                }
                if (A[i][j] > A[i][j - 1]) {
                    max = Math.max(max, T[i][j - 1]);
                }
                if (max != -1) {
                    T[i][j] = 1 + max;
                }
            }
        }
        if (T[n - 1][m - 1] == -1) {
            return -1;
        }
        return T[n - 1][m - 1];
    }
}