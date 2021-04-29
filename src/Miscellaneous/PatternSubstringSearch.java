package Miscellaneous;

public class PatternSubstringSearch {
    //https://www.youtube.com/watch?v=GTJr8OvyEVQ
    // KMP Search (Knuth Morris Pratt) String Matching
    //Time Complexity : 0(m+n)
    public static boolean KMP(char[] text, char[] pattern) {

        int[] lps = getSuffixPrefixMatchCount(text);
        int i = 0;
        int j = 0;
        while (i < text.length && j < pattern.length) {
            if (text[i] == pattern[j]) {
                i++;
                j++;
            } else {
                if (j != 0) { 
                    /* Get suffix-prefix match count of previous index, 
                    previously we had found the match till (j-1) pattern index, we will use previous compute value(index) for next */
                    j = lps[j - 1];   
                } else {   // No match from begining
                    i++;
                }
            }
        }
        return j == pattern.length;
    }

    private static int[] getSuffixPrefixMatchCount(char[] text) {
     
        int n = text.length;
        int[] lps = new int[n];
        lps[0] = 0;
        int j = 0;
        for (int i = 1; i < n;) {
            if(text[i] == text[j]){
                lps[i] = j+1;
                i++;
                j++;
            } else {
                if (j == 0) { // At begining
                    lps[i] = 0;
                    i++;
                } else { // previously found match till now
                    j = lps[j - 1];
                }
            }
        }
        return lps;
    }

    //Brute Force Time Complexity : 0(mn)
    public static boolean isMatch(char[] text, char[] pattern) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < text.length && j < pattern.length) {
            if (text[i] == pattern[j]) {
                i++;
                j++;
            } else {
                j = 0;
                k++;
                i = k;
            }
        }
        return j == pattern.length;
    }

    public static void main(String args[]){
        String str = "abcxabcdabcdabcy";
        String subString = "abcdabcy";
        System.out.println(KMP(str.toCharArray(), subString.toCharArray()));
        System.out.println(isMatch(str.toCharArray(), subString.toCharArray()));   
    }
}
