package DynamicProgramming;

public class LongestCommonSubsequence {

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

    public static void main(String[] args) {
        String str1 = "abcdaf";
        String str2 = "acbcf";
        System.out.println(numberOfCommonElement(str1, str2));
    }
}
