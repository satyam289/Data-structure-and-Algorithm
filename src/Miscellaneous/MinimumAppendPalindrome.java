package Miscellaneous;

/*
https://www.interviewbit.com/problems/minimum-appends-for-palindrome/
Return a integer denoting the minimum characters to be appended (insertion at end) to make the string A a palindrome.
Input = "abede"
Output 1:
We can make string palindrome as "abedeba" by adding ba at the end of the string.
*/
public class MinimumAppendPalindrome {

  // Time Complexity: 0(n)
  public static int best(String A) {
    int unMatchedIndex = 0;
    int i = 0;
    int j = A.length() - 1;

    while (i < j) {
      if (A.charAt(i) != A.charAt(j)) {
        unMatchedIndex = i + 1; // update unmatched from begining
        j = A.length() - 1;    // reset to last position
      } else if (A.charAt(i) == A.charAt(j)) {
        j--;
      }
      i++;
    }
    return unMatchedIndex;
  }

  // Time Comeplxity : 0(N)
  public static int better(String input) {
    StringBuilder sb = new StringBuilder(input);
    int len = input.length();
    input = sb.reverse().toString() + "$" + input;
    int[] arr = getSuffixPrefixMatchCount(input.toCharArray());
    return len - arr[len-1];
  }

  private static int[] getSuffixPrefixMatchCount(char[] text) {
    int[] lps = new int[text.length];
    lps[0] = 0;
    int j = 0;
    for (int i = 1; i < text.length;) {
      if (text[i] == text[j]) {
        lps[i] = j + 1;
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

  // Time Complexity : 0(n2)
  public static int naive(String input) {
    int len = input.length();
    if (len <= 1) {
      return 0;
    }
    for (int i = 0; i < len; i++) {
      if (isPalindrome(i, input)) {
        return i;
      }
    }
    return len - 1;
  }

  private static boolean isPalindrome(int i, String input) {
    int j = input.length() - 1;
    while (i < j) {
      if (input.charAt(i) == input.charAt(j)) {
        i++;
        j--;
      } else {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    String A = "abede";
    System.out.println(best(A));
    System.out.println(better(A));
    System.out.println(naive(A));
  }

  /*
   * https://leetcode.com/problems/valid-palindrome-ii/
   * 
   * Given a string s, return true if the s can be palindrome after deleting at
   * most one character from it. Input: s = "abca" Output: true Explanation: You
   * could delete the character 'c'.
   * 
   * Input: s = "abc" Output: false
   */
  public boolean validPalindrome(String str) {
    int i = 0;
    int j = str.length() - 1;
    while (i < j) {
      if (str.charAt(i) != str.charAt(j)) {
        return isPalindrome2(str, i + 1, j) || isPalindrome2(str, i, j - 1);
      }
      i++;
      j--;
    }
    return true;
  }

  private boolean isPalindrome2(String str, int start, int end) {
    while (start < end) {
      if (str.charAt(start++) != str.charAt(end--)) {
        return false;
      }
    }
    return true;
  }
}
