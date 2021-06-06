package DynamicProgramming;

public class LongestCommonSubsequence {

    // Brute Force : Each Character has 2 option(either include or exclude) in
    // subsequence and then compare, Time Complexity : 0(2^n1+n2)
    // Time Complexity : 0(N^2)
    public static int numberOfCommonElement(String str1, String str2) {
        int[][] table = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    table[i][j] = table[i - 1][j - 1] + 1;
                } else {
                    table[i][j] = Math.max(table[i - 1][j], table[i][j - 1]);
                }
            }
        }
        return table[str1.length()][str2.length()];
    }

    public static int LCSRec(String str1, String str2, int strIdx1, int strIdx2) {
        if (strIdx1 >= str1.length() || strIdx2 >= str2.length()) {
            return 0;
        } else if (str1.charAt(strIdx1) == str2.charAt(strIdx2)) {
            return 1 + LCSRec(str1, str2, strIdx1 + 1, strIdx2 + 1);
        } else {
            return Math.max(LCSRec(str1, str2, strIdx1 + 1, strIdx2), LCSRec(str1, str2, strIdx1, strIdx2 + 1));
        }
    }

    public static void main(String[] args) {
        String str1 = "abcdaf";
        String str2 = "acbcf";
        System.out.println(numberOfCommonElement(str1, str2));
        System.out.println(LCSRec(str1, str2, 0, 0));
    }
}

// https://www.geeksforgeeks.org/longest-repeating-subsequence/
// https://www.interviewbit.com/problems/repeating-subsequence/
class LongestRepeatingSubSequence {

    public int repeatingSubSeq(String A) {
        int n = A.length();
        int[][] table = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (A.charAt(i - 1) == A.charAt(j - 1) && i != j) {
                    table[i][j] = 1 + table[i - 1][j - 1];
                } else {
                    table[i][j] = Math.max(table[i - 1][j], table[i][j - 1]);
                }
            }
        }
        return table[n][n];
    }
}
