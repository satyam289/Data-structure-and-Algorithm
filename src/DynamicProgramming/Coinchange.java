package DynamicProgramming;

//https://www.geeksforgeeks.org/find-minimum-number-of-coins-that-make-a-change/
//https://leetcode.com/problems/coin-change/
public class Coinchange {

    public static int minimumCoinRec(int[] coins, int total) {
        if (total == 0) {
            return 0;
        }
        int result = Integer.MAX_VALUE-1;
        for (int i = 0; i < coins.length; i++) { // starting from 0, means we can takes any coin infinite times in recusice call
            if (coins[i] <= total) { // contributing only  when denomination of coin is greater than total
                int subRecResult = minimumCoinRec(coins, total - coins[i]);
                if (subRecResult + 1 < result) {
                    result = subRecResult + 1;
                }
            }
        }
        return result;
    }

    // Infinite Supply of coin
    // Dp Space Complexity : 0(NM)
    public static int minimumCoin(int[] coins, int sum) {

        int[][] table = new int[coins.length][sum + 1];
        table[0][0] = 0;
        for (int j = 1; j <= sum; j++) { // first coin
            if (j >= coins[0]) {
                table[0][j] = table[0][j - coins[0]] + 1;
            }
        }
        for (int i = 1; i < coins.length; i++) { // starting from second coin
            for (int j = 0; j <= sum; j++) {
                if (j < coins[i]) {
                    table[i][j] = table[i - 1][j];
                } else {
                    table[i][j] = Math.min(table[i - 1][j], 1 + table[i][j - coins[i]]);
                }
            }
        }
        return table[coins.length - 1][sum];
    } 
    
    // Dp Space Complexity : 0(N)
    public static int minimumCoin2(int [] coins , int total){
        int T[] = new int [total+1];
        int R[] = new int [total+1];

        for (int i = 0; i <= total; i++) {
            T[i] = Integer.MAX_VALUE;
            R[i] = -1;
        }
        T[0]=0;
        for (int i = 0; i < coins.length; i++) {
            for (int j = 1; j <= total; j++) {
                if ((j >= coins[i]) && 1 + T[j-coins[i]] < T[j]) {
                    T[j] = 1 + T[j-coins[i]];
                    R[j] = i;
                }
            }
        }
        //printing coin 
        StringBuffer sb = new StringBuffer();
        int sum = total;
        int index = R[sum];
        if(index == -1){
            System.out.println("No coin is selected");
        }
        while (sum != 0) {
            index = R[sum];
            sb.append(" " + coins[index]);
            sum = sum - coins[index];
        }
        System.out.println("The selected coins are" + sb);

        return T[total - 1];
    }

    public static void main(String args[])
    {
        int total = 11;
        int coins[] = {1,5,6,8};
        
        System.out.println("The min number of coins using Recursion : " +  minimumCoinRec(coins,total));
        System.out.println("The min number of coins : " +  minimumCoin(coins,total));
        int total2 = 5;
        int coins2[] = {1,2,3};
        System.out.println("The number of ways using Recursion : " +  numofWayRec(coins2, 0, total2));
        System.out.println("The number of ways : " +  noOfWay(coins2,total2));
    }

    /*
     * https://www.geeksforgeeks.org/coin-change-dp-7/
     * https://leetcode.com/problems/coin-change-2/
     * Input: amount = 5, coins = [1,2,5] Output: 4
     * 5=5 5=2+2+1 5=2+1+1+1 5=1+1+1+1+1
     *  
     */
    public static int numofWayRec(int[] coins, int index, int total) {
        if (total == 0) { // if sum is zero, there is one sol i.e not taking any
            return 1;
        }
        if (total < 0) { // overflow
            return 0;
        }
        if (index >= coins.length) { // total > 0, no more coin left
            return 0;
        }
        return numofWayRec(coins, index + 1, total) + numofWayRec(coins, index, total - coins[index]);
    }
    
     //Dp Time Complexity: O(mn)
    public static int noOfWay(int[] coins, int total){
       
        int[][] table = new int[coins.length][total+1];
        for (int i = 0; i < coins.length; i++) { // there is one way for 0 sum i.e not selecting any coin
            table[i][0] = 1;
        }
        for (int j = 1; j <= total; j++) { // one coin
            if (j >= coins[0]) {
                table[0][j] = table[0][j - coins[0]];
            }
        }
        for (int i = 1; i < coins.length; i++) {
            for (int j = 0; j <= total; j++) {
                if(j < coins[i]){
                    table[i][j] = table[i-1][j];
                }else{
                    table[i][j] = table[i-1][j] + table[i][j-coins[i]]; 
                }
            }
        }
        return table[coins.length-1][total];
    }
   
    // Space Complexity : 0(N)
    public static int count(int coins[], int total) {
        int table[] = new int[total + 1];
        table[0] = 1;

        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= total; j++) {

                table[j] += table[j - coins[i]];
            }
        }
        return table[total];
    }
}
