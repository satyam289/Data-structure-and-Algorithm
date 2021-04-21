package DynamicProgramming;

import java.util.Arrays;

/*
https://www.hackerrank.com/challenges/coin-change/problem
*/

public class CountChange {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        System.out.println(coinchange2(arr, 4));
        System.out.println(coinChangeDP(arr, 4));
        System.out.println(coinChangeRecursive(arr, 4));
    }
    
    // https://www.interviewbit.com/problems/coin-sum-infinite/ (duplicate allowed)
    public static int coinchange2(int [] input, int target) {
        int dp[] = new int[target + 1];
        Arrays.fill(dp, 0);
        dp[0] = 1;

        for (int i = 0; i < input.length; i++) {
            for (int j = input[i]; j <= target; j++) {
                dp[j] = dp[j] + dp[j - input[i]];
                dp[j] = dp[j] % 1000007;
            }
        }

        return dp[target];
    }

    public static int coinChangeDP(int[] arr, int targetSum) {
        int index = arr.length;
        int[][] temp = new int[targetSum + 1][index];

        for (int i = 0; i < index; i++)
            temp[0][i] = 1;
        for (int i = 1; i <= targetSum; i++) {
            for (int j = 0; j < index; j++) {
                int x = (i - arr[j] >= 0) ? temp[i - arr[j]][j] : 0;
                int y = (j >= 1) ? temp[i][j - 1] : 0;
                temp[i][j] = x + y;
            }
        }
        return temp[targetSum][index - 1];
    }


    public static int coinChangeRecursive(int[] arr, int target) {
        int[][] temp = new int[target + 1][arr.length];
        for (int i = 0; i < arr.length; i++)
            temp[0][i] = 1;
        return coinChangeRecursive(temp, arr, 4, 0);
    }

    public static int coinChangeRecursive(int[][] temp, int[] arr, int targetSum, int index) {
        if (targetSum == 0)
            return 1;
        if (targetSum < 0 || index >= arr.length)
            return 0;
        if (temp[targetSum][index] != 0)
            return temp[targetSum][index];
        temp[targetSum][index] = coinChangeRecursive(temp, arr, targetSum - arr[index], index) +
                coinChangeRecursive(temp, arr, targetSum, index + 1);
        return temp[targetSum][index];
    }
}
