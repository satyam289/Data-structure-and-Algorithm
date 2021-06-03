package DynamicProgramming;

//http://www.geeksforgeeks.org/dynamic-programming-set-8-matrix-chain-multiplication/
public class ChainMatrixMultiplication {

    private static class Pair {
        int first;
        int second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public static int chainMatrixMul(Pair[] pairs) {
        int n = pairs.length;
        int[][] table = new int[n][n];

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;
                int min = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int val = table[i][k] + table[k + 1][j] + pairs[i].first * pairs[k].second * pairs[j].second;
                    min = Math.min(min, val);
                }
                table[i][j] = min;
            }
        }
        return table[0][n - 1];
    }

    public static void main(String[] args) {
        int arr[] = { 2, 3, 6, 4, 5 };
        System.out.println(chainMatrixMul(makePair(arr)));
    }

    private static Pair[] makePair(int[] arr) {
        int n = arr.length;
        Pair[] pairs = new Pair[n - 1];
        for (int i = 1; i < n; i++) {
            pairs[i - 1] = new Pair(arr[i - 1], arr[i]);
        }
        return pairs;
    }
}
