package DynamicProgramming;

import java.util.Arrays;

public class BestTimeBuySellStock {

    // https://www.interviewbit.com/problems/best-time-to-buy-and-sell-stocks-i/
    // At most one transaction, find the maximum possible profit.
    public int maxProfit(final int[] A) {
        if (A.length == 0) {
            return 0;
        }
        int minUtilNow = A[0];
        int profit = 0;
        for (int i = 1; i < A.length; i++) {
            minUtilNow = Math.min(minUtilNow, A[i]);
            profit = Math.max(profit, A[i] - minUtilNow);
        }
        return profit;
    }

    // https://www.interviewbit.com/problems/best-time-to-buy-and-sell-stocks-ii/
    // Any number of transaction (subsequent buy only after selling old), maximum profit
    public int maxProfitBruteForce(final int[] price) {
        if (price.length == 0) {
            return 0;
        }
        int[] table = new int[price.length];
        for (int i = 1; i < price.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                max = Math.max(max, table[j] + price[i] - price[j]);
            }
            table[i] = Math.max(table[i - 1], max);
        }
        return table[price.length - 1];
    }

     //https://www.interviewbit.com/problems/best-time-to-buy-and-sell-stocks-ii/
     // Any number of transaction 
     public int maxProfitOptimised(final int[] price) {
         if (price.length == 0) {
             return 0;
         }
         int[] table = new int[price.length];
         int max = -price[0];
         for (int i = 1; i < price.length; i++) {
             table[i] = Math.max(table[i - 1], max + price[i]);
             max = Math.max(max, (table[i] - price[i]));
         }
         return table[price.length - 1];
     }

     ////https://www.interviewbit.com/problems/best-time-to-buy-and-sell-stocks-ii/
     // Any number of transaction 
     public int maxProfitMoreOptimised(final int[] price) {
         int n = price.length;
         if (n == 0)
             return 0;
         int profit = 0;
         for (int i = 1; i < n; ++i) {
             if (price[i] > price[i - 1]) {
                 profit += (price[i] - price[i - 1]);
             }
         }
         return profit;
     }
 
     //https://www.interviewbit.com/problems/best-time-to-buy-and-sell-stocks-iii/
     // two transaction allowed
     // Time Complexity: 0(1)
     public int maxProfitBest(int[] A) {
         if (A.length == 0)
             return 0;
         int firstBuy = Integer.MIN_VALUE, secondBuy = Integer.MIN_VALUE;
         int firstSell = 0, secondSell = 0;

         for (int i = 0; i < A.length; i++) {
             firstBuy = Math.max(firstBuy, -A[i]);
             firstSell = Math.max(firstSell, firstBuy + A[i]);
             secondBuy = Math.max(secondBuy, firstSell - A[i]);
             secondSell = Math.max(secondSell, secondBuy + A[i]);
         }
         return secondSell;
     }

     //https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
     public int maxProfit(int k, int[] prices) {
         int len = prices.length;
         if (len <= 1 || k <= 0) {
             return 0;
         }
         int profit = 0;
         if (k >= len / 2) {  // unlimited case
             for (int i = 0; i < len; i++) {
                 if (prices[i] < prices[i + 1]) {
                     profit += prices[i + 1] - prices[i];
                 }
             }
             return profit;
         }
         int[] buy = new int[k];
         Arrays.fill(buy, Integer.MIN_VALUE);
         int[] sell = new int[k];

         for (int i = 0; i < prices.length; i++) {
             for (int j = 0; j < k; j++) {
                 buy[j] = Math.max(buy[j], j == 0 ? 0 - prices[i] : sell[j - 1] - prices[i]);
                 sell[j] = Math.max(sell[j], buy[j] - prices[i]);
             }
         }
         return sell[k-1];
     }

    // https://leetcode.com/discuss/15153/a-clean-dp-solution-which-generalizes-to-k-transactions
     public int maxProfitKTransaction(int prices[], int K) {
         if (K == 0 || prices.length == 0) {
             return 0;
         }
         int T[][] = new int[K + 1][prices.length];
         for (int i = 1; i < T.length; i++) { // transaction
             int maxDiff = -prices[0];
             for (int j = 1; j < T[0].length; j++) { //days
                 T[i][j] = Math.max(T[i][j - 1], prices[j] + maxDiff);
                 maxDiff = Math.max(maxDiff, T[i - 1][j] - prices[j]);
             }
         }
         return T[K][prices.length - 1];
     }
}
