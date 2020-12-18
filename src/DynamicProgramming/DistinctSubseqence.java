package DynamicProgramming;

/*https://www.geeksforgeeks.org/count-distinct-occurrences-as-a-subsequence/*/

/*
 * Input  : S = banana, T = ban
Output : 3
T appears in S as below three subsequences.
[ban], [ba  n], [b   an]

Input  : S = geeksforgeeks, T = ge
Output : 6
T appears in S as below three subsequences.
[ge], [     ge], [g e], [g    e] [g     e]
and [     g e]

 */
public class DistinctSubseqence {

    public static void main(String[] args) {
        // System.out.println("No of distinct ways :"+numDistinct("rabbbit", "rabbit"));
        System.out.println("No of distinct ways :" + numDistinct("banana", "ban"));
    }

    public static int numDistinct(String s, String t) {
        if (s.length() == 0 || t.length() == 0) {
            return 0;
        }
        int[][] table = new int[t.length() + 1][s.length() + 1];
        for (int i = 0; i < table[0].length; i++) {
            table[0][i] = 1;
        }
        for (int i = 1; i < table.length; i++) {
            for (int j = 1; j < table[0].length; j++) {
                if (s.charAt(j - 1) == t.charAt(i - 1))
                    table[i][j] = table[i][j - 1] + table[i - 1][j - 1];
                else
                    table[i][j] = table[i][j - 1];
            }
        }
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                System.out.print(table[i][j] + ",");
            }
            System.out.println();
        }
        System.out.println();
        return table[t.length()][s.length()];
    }
}
