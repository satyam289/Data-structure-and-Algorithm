package DynamicProgramming;

import java.util.HashMap;
import java.util.List;

//https://www.interviewbit.com/problems/maximum-path-in-triangle/
public class MaxPathInTraingle {

    public int solve(int[][] A) {
        int cols = A[0].length;
        int rows = A.length;

        int dp[] = new int[cols];
        for (int i = 0; i < cols; i++) {
            dp[i] = A[rows - 1][i];
        }
        for (int i = rows - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = A[i][j] + Math.max(dp[j], dp[j + 1]);
            }
        }
        return dp[0];
    }

    public int solve2(int[][] A) {
        int n = A.length;
        if (n == 0)
            return 0;

        int[][] dp = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = A[i][j] + Math.max(dp[i + 1][j], dp[i + 1][j + 1]);
            }
        }
        return dp[0][0];
    }

    HashMap<String, Integer> map = new HashMap<>();

    public int solve3(int[][] A) {
        return maxSum(A, 0, 0);
    }

    private int maxSum(int[][] A, int row, int col) {
        // base case, if we reached the end
        if (row == A.length - 1 && col == A[0].length - 1)
            return A[row][col];
        // boundary condition
        if (row > A.length - 1 || col > A[0].length - 1)
            return 0;

        if (map.containsKey(row + ":" + col))
            return map.get(row + ":" + col);

        int childPathsum = Math.max(maxSum(A, row + 1, col), maxSum(A, row + 1, col + 1));

        map.put(row + ":" + col, childPathsum + A[row][col]);
        return childPathsum + A[row][col];
    }

    //TriangleMinSumAdjacentFromTopToBottom
	public static int getMinSumTriangle(List<List<Integer>> traingle) {

		int N = traingle.size();
		int[] table = new int[N];

		for (int j = 0; j < traingle.get(N - 1).size(); j++) {
			table[j] = traingle.get(N - 1).get(j);
		}
		for (int i = N - 2; i >= 0; i--) {
			for (int j = 0; j < traingle.get(i).size(); j++) {
				table[j] = traingle.get(i).get(j) + Math.min(table[j], table[j + 1]);
			}
		}
		return table[0];
	}
}
