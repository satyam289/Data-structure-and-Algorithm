package DynamicProgramming;

//https://www.geeksforgeeks.org/minimum-sum-path-triangle/
public class MinimumTraingleSum {

    public static int getMinSumTraingle(int[][] trangle) {
        int n = trangle.length - 1;
        int[] table = new int[n + 1];
        // bottom row
        for (int j = 0; j < trangle[n].length; j++) {
            table[j] = trangle[n][j];
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < trangle[i].length; j++) {
                table[j] = trangle[i][j] + Math.min(table[j], table[j + 1]);
            }
        }
        return table[0];
    }

    public static void main(String[] args) {
        int A[][] = { { 2 }, { 3, 9 }, { 1, 6, 7 } };
        System.out.println(getMinSumTraingle(A));
    }
}
