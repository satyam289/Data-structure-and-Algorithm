package DynamicProgramming;

//https://www.interviewbit.com/problems/interleaving-strings/
//https://leetcode.com/problems/interleaving-string/

public class StringInterLeaving {

    public static boolean isInterLeaving(String s1, String s2, String s3) {
        int s1len = s1.length();
        int s2len = s2.length();
        if (s3.length() != s1len + s2len) {
            return false;
        }
        boolean[][] table = new boolean[s1len + 1][s2len + 1];
        table[0][0] = true;

        for (int j = 1; j <= s1len; j++) {
            table[0][j] = s3.charAt(j - 1) == s1.charAt(j - 1);
        }
        for (int i = 1; i <= s2len; i++) {
            table[i][0] = s3.charAt(i - 1) == s2.charAt(i - 1);
        }

        for (int i = 1; i <= s2len; i++) {
            for (int j = 1; j <= s1len; j++) {

                table[i][j] = (s3.charAt(i + j - 1) == s1.charAt(j - 1) && table[i][j - 1])
                        || (s3.charAt(i + j - 1) == s2.charAt(i - 1) && table[i - 1][j]);
            }
        }
        return table[s1len][s2len];
    }

    public static boolean isInterleaveRec(char[] s1, char[] s2, char[] s3, int idx1, int idx2, int idx3) {

        // base case recursion will here only when all character of s3 matching either
        // of s1 and s2
        if (idx1 == s1.length && idx2 == s2.length && idx3 == s1.length + s2.length) {
            return true;
        }
        // base case when length of s3 is less than sum of s1 and s2
        if (idx3 >= s3.length) {
            return false;
        }

        boolean answer = false;
        if (idx1 < s1.length && s1[idx1] == s3[idx3])
            answer |= isInterleaveRec(s1, s2, s3, idx1 + 1, idx2, idx3 + 1);

        if (idx2 < s2.length && s2[idx2] == s3[idx3])
            answer |= isInterleaveRec(s1, s2, s3, idx1, idx2 + 1, idx3 + 1);

        return answer;
    }

    public static void main(String[] args) {
        String s1 = "aab";
        String s2 = "axy";
        String s3 = "aaxayb";
        System.out.println(isInterLeaving(s1, s2, s3));
        System.out.println(isInterleaveRec(s1.toCharArray(), s2.toCharArray(), s3.toCharArray(), 0, 0, 0));
    }
}
