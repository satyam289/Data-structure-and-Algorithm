package DynamicProgramming;

//https://leetcode.com/problems/interleaving-string/

public class StringInterLeaving {

    public static boolean isInterLeaving(String str1, String str2, String str3) {

        boolean[][] table = new boolean[str1.length() + 1][str2.length() + 1];
        table[0][0] = true;

        for (int j = 1; j <= str1.length(); j++) {
            table[0][j] = str3.charAt(j - 1) == str1.charAt(j - 1);
        }
        for (int i = 1; i <= str2.length(); i++) {
            table[i][0] = str3.charAt(i - 1) == str2.charAt(i - 1);
        }

        for (int i = 1; i <= str2.length(); i++) {
            for (int j = 1; j <= str1.length(); j++) {

                table[i][j] = (str3.charAt(i + j - 1) == str1.charAt(j - 1) && table[i][j - 1])
                        || (str3.charAt(i + j - 1) == str2.charAt(i - 1) && table[i - 1][j]);
            }
        }
        return table[str1.length()][str2.length()];
    }

    public static void main(String[] args) {
        String s1 = "aab";
        String s2 = "axy";
        String s3 = "aaxaby";
        System.out.println(isInterLeaving(s1, s2, s3));
    }
}
