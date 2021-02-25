package DynamicProgramming;

/*Given a 2D matrix find longest increasing path length in this matrix.*/

public class LongestSubsequenceInMatrix {

	public static void main(String[] args) {

		int[][] input = { { 9, 9, 4 }, 
				          { 6, 6, 8 }, 
				          { 2, 1, 1 } };
		int[][] input1 = {{3, 4, 5}, 
				          {3, 2, 6}, 
				          {2, 2, 1}};
		System.out.println(getLongestPathInMatrix(input1));

	}

	public static int getLongestPathInMatrix(int[][] matrix) {

		int[][] dp = new int[matrix.length][matrix[0].length];
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				int val = getLongestPathInMatrix(matrix, i, j, dp);
				max = Math.max(max, val+1);
			}
		}
		return max;
	}

	private static int getLongestPathInMatrix(int[][] matrix, int i, int j, int[][] dp) {
		if (dp[i][j] !=0)
			return dp[i][j];
		int a = 0, b = 0, c = 0, d = 0;
		if (i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j]) {
			a = 1 + getLongestPathInMatrix(matrix, i - 1, j, dp);
		}
		if (j - 1 >= 0 && matrix[i][j - 1] > matrix[i][j]) {
			b = 1 + getLongestPathInMatrix(matrix, i, j - 1, dp);
		}
		if (i + 1 < matrix.length && matrix[i + 1][j] > matrix[i][j]) {
			c = 1 + getLongestPathInMatrix(matrix, i + 1, j, dp);
		}
		if (j + 1 < matrix[0].length && matrix[i][j + 1] > matrix[i][j]) {
			d = 1 + getLongestPathInMatrix(matrix, i, j + 1, dp);
		}
		dp[i][j] = Math.max(Math.max(a, b), Math.max(c, d));
		return dp[i][j];
	}

}
