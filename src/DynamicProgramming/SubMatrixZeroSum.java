package DynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;

//https://www.geeksforgeeks.org/largest-rectangular-sub-matrix-whose-sum-0/
public class SubMatrixZeroSum {
    private static class Result {
        int up;
        int down;
        int sum;

        public Result(int up, int down, int sum) {
            this.up = up;
            this.down = down;
            this.sum = sum;
        }
    }

    public static void findMaxZeroSumLen(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] temp = new int[n];
        int gup = 0, gdown = 0, gleft = 0, gright = 0, maxArea = 0, count = 0;
        for (int left = 0; left < n; left++) {
            Arrays.fill(temp, 0);
            for (int right = left; right < n; right++) {

                for (int i = 0; i < m; i++) {
                    temp[i] += mat[i][right];
                }
                Result res = findZeroSum2(temp);
                if (res.sum == 0) {
                    count++;
                    int area = (res.down - res.up + 1) * (right - left + 1);
                    if (area > maxArea) {
                        gup = res.up;
                        gdown = res.down;
                        gleft = left;
                        gright = right;
                        maxArea = area;
                    }
                }
            }
        }
        System.out.println("The number of zero rectangle " + count);
        System.out.println("The maximum rectangle is area is " + maxArea + " Boundary corner points: (" + gup + ","
                + gleft + ")," + "(" + gdown + "," + gright + ")");
    }

    // Brute Force (Same apporach can use for targetSum)
    // Time Complexity : 0(n^2) And Space : 0(1)
    public static Result findZeroSum1(int[] arr) {
        int start = 0, end = 0;
        int maxlen = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i + 1; j < arr.length; j++) {
                sum += arr[j];
                if (sum == 0 && (end - start > maxlen)) {
                    start = i;
                    end = j;
                    maxlen = end - start;
                }
            }
        }
        if (maxlen == Integer.MIN_VALUE) {
            return new Result(start, end, -1);
        }
        return new Result(start + 1, end, 0);
    }

    // Time Complexity : 0(n) And Space : 0(n)
    public static Result findZeroSum2(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int max = Integer.MIN_VALUE;
        int start = 0;
        int end = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum) && i - map.get(sum) > max) {
                max = i - map.get(sum);
                start = map.get(sum);
                end = i;
            } else {
                map.put(sum, i);
            }
        }
        if (max == Integer.MIN_VALUE) {
            return new Result(start, end, -1);
        }
        return new Result(start + 1, end, 0);
    }

    public static void main(String[] args) {
        int[][] mat = { { 9, 7, 16, 5 }, { 1, -6, -7, 3 }, { 1, 8, 7, 9 }, { 7, -2, 0, 10 } };
        findMaxZeroSumLen(mat);
        System.out.println(noSubMatSumZero(mat));
    }

    // https://www.interviewbit.com/problems/sub-matrices-with-sum-zero/
    public static int noSubMatSumZero(int[][] matrix) {
        int count = 0;
        int m = matrix.length;
        if (m == 0)
            return count;
        int n = matrix[0].length;
        int[][] dp = calculateDpSum(matrix, m, n);

        for (int i0 = 0; i0 < m; i0++) {
            for (int i1 = i0; i1 < m; i1++) {

                for (int j0 = 0; j0 < n; j0++) {
                    for (int j1 = j0; j1 < n; j1++) {

                        if (sumMatrix(i0, i1, j0, j1, dp) == 0)
                            count++;
                    }
                }
            }
        }
        return count;
    }

    private static int[][] calculateDpSum(int[][] matrix, int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = matrix[0][0];
        for (int i = 1; i < m; i++)
            dp[i][0] = dp[i - 1][0] + matrix[i][0];
        for (int j = 1; j < n; j++)
            dp[0][j] = dp[0][j - 1] + matrix[0][j];

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = matrix[i][j] + dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];
            }
        }
        return dp;
    }

    public static int sumMatrix(int i0, int i1, int j0, int j1, int[][] dp) {
        int sum = dp[i1][j1];
        if (j0 > 0)
            sum -= dp[i1][j0 - 1];
        if (i0 > 0)
            sum -= dp[i0 - 1][j1];
        if (i0 > 0 && j0 > 0)
            sum += dp[i0 - 1][j0 - 1];
        return sum;
    }
}