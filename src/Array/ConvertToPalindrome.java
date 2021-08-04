package Array;

//https://www.interviewbit.com/problems/convert-to-palindrome/
/*
Given a string A consisting only of lowercase characters, we need to check whether it is possible to make this string a palindrome after removing exactly one character from this.
If it is possible then return 1 else return 0.
Example Input
Input 1:
A = "abcba"
Output: 1
We can remove character ‘c’ to make string palindrome

Input 2:
A = "abecbea"
Output: 0
It is not possible to make this string palindrome just by removing one character
*/

public class ConvertToPalindrome {

    public int solve(String A) {
        int l = 0;
        int r = A.length() - 1;
        boolean removed = false;
        while (l < r) {
            if (A.charAt(l) == A.charAt(r)) {
                l++;
                r--;
            } else {
                if (removed) {
                    return 0;
                }
                removed = true;
                if (A.charAt(l + 1) == A.charAt(r)) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return 1;
    }

    public int solve2(String A) {
        int n = A.length();
        int i = 0, j = n - 1;
        int c = 0;
        while (i < j) {
            if (A.charAt(i) != A.charAt(j)) {
                ++c;
                i++;
            } else {
                i++;
                j--;
            }
        }
        if (c > 1) {
            return 0;
        }
        return 1;
    }

    public int solve3(String a) {
        int res = 1;
        int i = 0, j = a.length() - 1;
        while (i < j) {
            if (a.charAt(i) == a.charAt(j)) {
                i++;
                j--;
            } else {
                // res = Math.max(isP(new StringBuilder(a).deleteCharAt(i).toString(), i, j-1),
                // isP(new StringBuilder(a).deleteCharAt(j).toString(), i, j-1));
                res = Math.max(isP(a, i, j - 1), isP(a, i + 1, j));
                break;
            }
        }
        return res;
    }

    private int isP(String s, int i, int j) {
        while (i < j)
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else
                return 0;
        return 1;
    }
}
