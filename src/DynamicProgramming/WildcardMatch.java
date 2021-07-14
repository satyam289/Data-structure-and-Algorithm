package DynamicProgramming;

public class WildcardMatch {

    public int isMatch(final String A, final String B) {

        char[] Arr = A.toCharArray();
        char[] Brr = B.toCharArray();
        int patternPtr = 0;
        boolean isFirst = true;
        for (int i = 0; i < Brr.length; i++) {
            if (Brr[i] == '*') {
                if (isFirst) {
                    Brr[patternPtr++] = Brr[i];
                    isFirst = false;
                }
            } else {
                Brr[patternPtr++] = Brr[i];
                isFirst = true;
            }
        }
        boolean[][] table = new boolean[Arr.length + 1][patternPtr + 1];
        table[0][0] = true;
        if (patternPtr > 0 && Brr[0] == '*') {
            table[0][1] = true;
        }

        for (int i = 1; i <= Arr.length; i++) {
            for (int j = 1; j <= patternPtr; j++) {

                if (Brr[j - 1] == Arr[i - 1] || Brr[j - 1] == '?') {
                    table[i][j] = table[i - 1][j - 1];
                } else if (Brr[j - 1] == '*') {
                    table[i][j] = table[i - 1][j] || table[i][j - 1];
                } else {
                    table[i][j] = false;
                }
            }
        }
        return table[Arr.length][patternPtr] ? 1 : 0;
    }

    public boolean isMatchRecursive(String a, String b) {
        return isMatchRecursiveUtil(a.toCharArray(), b.toCharArray(), 0, 0);
    }

    private boolean isMatchRecursiveUtil(char[] text, char[] pattern, int pos1, int pos2) {
        if (pos2 == pattern.length) {
            return pos1 == text.length;
        }
        if (pattern[pos2] != '*') {
            if (pos1 < text.length && (text[pos1] == pattern[pos2] || pattern[pos2] == '?')) {
                return isMatchRecursiveUtil(text, pattern, pos1 + 1, pos2 + 1);
            } else {
                return false;
            }
        } else {
            // a***b
            while (pos2 < pattern.length -1 && pattern[pos2 + 1] == '*') {
                pos2++;
            }
            pos1--;
            while (pos1 < text.length) {
                if (isMatchRecursiveUtil(text, pattern, pos1 + 1, pos2 + 1)) {
                    return true;
                }
            }
            return false;
        }
    }

    public int isMatch3(final String s, final String p) {
        if (p.length() == 0) return s.length() == 0?1:0;
           int si = 0, pi = 0, match = 0, star = -1;
           int sl = s.length(), pl = p.length();
           char[] sc = s.toCharArray(), pc = p.toCharArray();
           while (si < sl) {
               if (pi < pl && (pc[pi] == sc[si] || pc[pi] == '?')) {
                   si++;
                   pi++;
               } else if (pi < pl && pc[pi] == '*') {
                   star = pi++;
                   match = si;
               } else if (star != -1) {
                   si = ++match;
                   pi = star + 1;
               } else return 0;
           }
           while (pi < pl && pc[pi] == '*') pi++;
           return pi == pl?1:0;
       }
}