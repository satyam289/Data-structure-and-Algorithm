package DynamicProgramming;

//https://www.interviewbit.com/problems/arrange-ii/
public class ArrangeBlackWhiteHorse {
    /*
     * You are given a sequence of black and white horses, and a set of K stables
     * numbered 1 to K. You have to accommodate the horses into the stables in such
     * a way that the following conditions are satisfied:
     * 
     * You fill the horses into the stables preserving the relative order of horses.
     * For instance, you cannot put horse 1 into stable 2 and horse 2 into stable 1.
     * You have to preserve the ordering of the horses. No stable should be empty
     * and no horse should be left unaccommodated. Take the product (number of white
     * horses * number of black horses) for each stable and take the sum of all
     * these products. This value should be the minimum among all possible
     * accommodation arrangements
     */

    /*
     * Input: {WWWB} , K = 2 Output: 0
     * 
     * Explanation: We have 3 choices {W, WWB}, {WW, WB}, {WWW, B} for first choice
     * we will get 1*0 + 2*1 = 2. for second choice we will get 2*0 + 1*1 = 1. for
     * third choice we will get 3*0 + 0*1 = 0.
     * 
     * Of the 3 choices, the third choice is the best option.
     */

    public int arrange(String horses, int stable) {
        // dp[i][p] = min product from 0 to i with p stables
        // dp[i][p] = min(dp[j][p - 1] + calc(j, n, 1))
        if (horses == null || horses.length() == 0 || stable == 0)
            return 0;
        if (stable > horses.length())
            return -1;
        int maxHorses = horses.length();

        int[][] dp = new int[maxHorses][stable + 1];
        for (int i = 0; i < maxHorses; i++) {
            dp[i][1] = getProduct(0, i, horses);
        }
        for (int p = 2; p <= stable; p++) {
            dp[0][p] = 0;
        }
        for (int i = 1; i < maxHorses; i++) {
            for (int p = 2; p <= stable; p++) {
                dp[i][p] = Integer.MAX_VALUE;
                for (int k = 0; k < i; k++) {
                    dp[i][p] = Math.min(dp[i][p], dp[k][p - 1] + getProduct(k + 1, i, horses));
                }
            }
        }
        return dp[maxHorses - 1][stable];
    }

    private int getProduct(int start, int end, String horses) {
        int w = 0;
        int b = 0;
        while (start <= end) {
            char c = horses.charAt(start);
            if (c == 'W') {
                w++;
            } else {
                b++;
            }
            start++;
        }
        return w * b;
    }

    // recursive
    public int arrange2(String horses, int K) {
        int N = horses.length();
        int[][] table = new int[N][K];
        for (int n = 0; n < N; n++) {
            for (int k = 0; k < K; k++) {
                table[n][k] = -1;
            }
        }
        int tmp = arrange(0, 0, horses, K, table);
        return tmp == Integer.MAX_VALUE ? -1 : tmp;
    }

    private int arrange(int start, int stables, String horses, int maxStables, int[][] table) {
        if (start >= horses.length()) {
            if (stables == maxStables)
                return 0;
            else // reach the end but did not allocate all stables
                return Integer.MAX_VALUE; // no solution,
        } else {
            if (stables == maxStables) {
                // have not finish allocating all the horses...
                return Integer.MAX_VALUE;
            }
            if (table[start][stables] != -1)
                return table[start][stables];
        }

        int WhiteCount = 0;
        int BlackCount = 0;
        int min = Integer.MAX_VALUE;
        for (int i = start; i < horses.length(); i++) {
            if (horses.charAt(i) == 'W')
                WhiteCount++;
            else
                BlackCount++;
            if (WhiteCount * BlackCount > min)
                break;

            int result = arrange(i + 1, stables + 1, horses, maxStables, table);
            if (result != Integer.MAX_VALUE)
                min = Math.min(min, result + (WhiteCount * BlackCount));
            table[start][stables] = min;
        }
        return min;
    }
}
