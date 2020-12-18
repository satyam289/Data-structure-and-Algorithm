package DynamicProgramming;

public class PalindromePartition {

    public static void main(String[] args) {
        String str = "ababbabbababa";
        noofsplit(str);
    }

    private static void noofsplit(String str) {
        int n = str.length();
        int[][] partitiontable = new int[n][n];
        boolean[][] booleantable = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            partitiontable[i][i] = 0;
            booleantable[i][i] = true;
        }
        for (int len = 1; len < n; len++) {
            for (int i = 0; i < n - len; i++) {
                int j = i + len;
                if (str.charAt(i) == str.charAt(j))
                    booleantable[i][j] = len == 2 || booleantable[i + 1][j - 1];
                else
                    booleantable[i][j] = false;
                if (booleantable[i][j])
                    partitiontable[i][j] = 0;
                else {
                    partitiontable[i][j] = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        partitiontable[i][j] = Math.min(partitiontable[i][j], partitiontable[i][k] + partitiontable[k + 1][j] + 1);
                    }
                }
            }
        }
        System.out.println(partitiontable[0][n - 1]);
    }
}
