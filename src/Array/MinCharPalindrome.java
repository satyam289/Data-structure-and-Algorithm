package Array;

// https://www.interviewbit.com/problems/minimum-characters-required-to-make-a-string-palindromic/
public class MinCharPalindrome {

    public int solve(String A) {
        int n = A.length();
        int ans = n;
        while (n > 1 && !isPalindrome(A, n)) {
            n--;
        }
        return ans - n;
    }

    public boolean isPalindrome(String A, int len) {
        int i = 0, j = len - 1;
        while (i <= j && (A.charAt(i) == A.charAt(j))) {
            i++;
            j--;
        }
        if (i > j) {
            return true;
        }
        return false;
    }

    public int solve2(String a) {
        StringBuilder s = new StringBuilder(a);
        s = s.reverse();
        String str = a + "$" + s.toString();
        int[] lps = new int[str.length()];
        computeLPS(str, lps);
        return (a.length() - lps[str.length() - 1]);
    }

    void computeLPS(String s, int[] lps) {
        int m = s.length();
        int len = 0;
        lps[0] = 0;
        int i = 1;
        while (i < m) {
            if (s.charAt(i) == s.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }

    public int solve3(String A) {
        int i, j, l, ctr = 0;
        l = A.length();
        i = 0;
        j = l - 1;
        while (i <= j) {
            if (A.charAt(i) != A.charAt(j)) {
                ctr++;
                j = l - ctr - 1;
                i = 0;
            } else {
                i++;
                j--;
            }
        }
        return ctr;
    }
}
