package Miscellaneous;

// https://www.interviewbit.com/problems/minimum-appends-for-palindrome/
public class MinimumAppendPalindrome {

    public static int solve0(String A) {
        int n =A.length();
        int c=0;
        int i=0;
        int j=n-1;
        while(i<j){

            if(A.charAt(i)!=A.charAt(j)){
                c=i+1;
                j=n-1;
            }
            else if(A.charAt(i)==A.charAt(j)){
                j--;
            }
            i++;
        }
        return c;
    }

    // Time Comeplxity : 0(n)
    public static int solve(String A) {
        StringBuilder sb = new StringBuilder(A);
        int n = A.length();
        A = sb.reverse().toString()+"$"+ A;
        int [] arr = getSuffixPrefixMatchCount(A.toCharArray());
        
        return n - arr[arr.length -1]; 
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
    
    // Time Complexity : 0(n2)
    public static int solve2(String input) {
        int length = input.length();
        if (length <= 1) {
          return 0;
        }
        for (int i = 0; i < length; i++) {
          if (isPalindrome(i, input)) {
            return i;
          }
        }
        return length - 1;
      }
    
      private  static boolean isPalindrome(int i, String input) {
        int length = input.length();
        int j = length - 1;
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

    public static void main(String [] args){
        String A = "abede";
        System.out.println(solve0(A));
        System.out.println(solve(A));
        System.out.println(solve2(A));
    }
}
