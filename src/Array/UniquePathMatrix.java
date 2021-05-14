package Array;

// https://www.interviewbit.com/problems/unique-paths-in-a-grid/
public class UniquePathMatrix {
    
    /*
    Given a grid of size m * n, lets assume you are starting at (1,1) and your goal is to reach (m,n). 
    At any instance, if you are on (x,y), you can either go to (x, y + 1) or (x + 1, y).
    Now consider if some obstacles are added to the grids. Find unique paths.
    [
        [0,0,0],
        [0,1,0],
        [0,0,0]
    ]
    The total number of unique paths is 2.
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

    //https://leetcode.com/problems/unique-paths/
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
}
