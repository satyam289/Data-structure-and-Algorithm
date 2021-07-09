package DynamicProgramming;

//https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
public class KnapSack {

    public static int knapSackDP(int[] wt, int[] val, int sum) {
        int[][] table = new int[wt.length + 1][sum + 1];

        for (int i = 1; i <= wt.length; i++) {
            for (int j = 1; j <= sum; j++) {

                if (j < wt[i - 1]) {
                    table[i][j] = table[i - 1][j];
                } else {
                    table[i][j] = Math.max(table[i - 1][j], table[i - 1][j - wt[i - 1]] + val[i - 1]);
                }
            }
        }
        return table[wt.length][sum];
    }

    public static void main(String[] args) {
        int[] wt = { 1, 3, 4, 5 };
        int[] val = { 1, 4, 5, 7 };
        int sum = 7;
        System.out.print(knapSackDP(wt, val, sum));
    }

    public int solve(int[] wt, int[] val, int sum) {
        int[][] dp = new int[wt.length + 1][sum + 1];
        return knapTopToDownApproach(dp, wt, val, sum, 0);
    }

    public int knapTopToDownApproach(int[][] dp, int[] wt, int[] val, int maxCapacity, int currentIndex) {
        if (maxCapacity <= 0 || currentIndex >= wt.length)
            return 0;

        if (dp[currentIndex][maxCapacity] != 0)
            return dp[currentIndex][maxCapacity];

        int profit = 0;
        if (wt[currentIndex] <= maxCapacity)
            profit = Math.max(knapTopToDownApproach(dp, val, wt, maxCapacity - wt[currentIndex], currentIndex + 1)
                    + val[currentIndex], knapTopToDownApproach(dp, val, wt, maxCapacity, currentIndex + 1));
        dp[currentIndex][maxCapacity] = profit;
        return dp[currentIndex][maxCapacity];
    }
}
