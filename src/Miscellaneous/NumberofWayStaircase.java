package Miscellaneous;

//https://www.geeksforgeeks.org/count-ways-reach-nth-stair/
public class NumberofWayStaircase {

    // Todo Need Optimised
    public static int noOfWaysToReachTop(int steps) {
        if (steps <= 0) {
            return 0;
        }
        if (steps == 1 || steps == 2) {
            return steps;
        }
        return noOfWaysToReachTop(steps - 1) + noOfWaysToReachTop(steps - 2);
    }

    public int climbStairs(int A) {
        if (A <= 0)
            return 0;
        if (A == 1) {
            return 1;
        }
        int[] dp = new int[A + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= A; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[A];
    }

    public static void main(String[] args) {
        System.out.println(noOfWaysToReachTop(4));
    }
}
