package DynamicProgramming;

/*
http://www.geeksforgeeks.org/dynamic-programming-set-24-optimal-binary-search-tree/
*/
public class OptimalBST {

    public static int minCostRec(int[] input, int[] freq) {
        return minCostRec(input, freq, 0, input.length - 1, 1);
    }

    private static int minCostRec(int[] input, int[] freq, int low, int high, int level) {
        if (low > high) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for (int i = low; i <= high; i++) {
            int sum = minCostRec(input, freq, low, i - 1, level + 1) + freq[i] * level
                    + minCostRec(input, freq, i + 1, high, level + 1);
            min = Math.min(min, sum);
        }
        return min;
    }

    public static int minCostDp(int[] input, int[] frequency) {
        int n = input.length;
        int[][] table = new int[n][n];
        for (int i = 0; i < n; i++) {
            table[i][i] = frequency[i];
        }
        for (int len = 2; len <= n; len++) {

            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;
                int freqSum = 0;
                for (int p = i; p <= j; p++) {
                    freqSum += frequency[p];
                }
                int min = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    int val = (k == i) ? 0 : table[i][k - 1];
                    val += (k == j) ? 0 : table[k + 1][j];
                    min = Math.min(min, val);
                }
                table[i][j] = min + freqSum;
            }
        }
        return table[0][input.length - 1];
    }

    public static void main(String args[]) {
        int input[] = { 10, 12, 16, 21 };
        int freq[] = { 4, 2, 6, 3 };
        System.out.println(minCostDp(input, freq));
        System.out.println(minCostRec(input, freq));
    }
}
