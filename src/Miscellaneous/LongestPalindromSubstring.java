package Miscellaneous;

public class LongestPalindromSubstring {

    //Time Complexity:  0(N3)
    public static String longestPalindrome1(String A) {
        int n = A.length();
        if(n == 1){
            return A;
        }
        int max = 0;
        String result = (""+A.charAt(0));
        for(int i=0 ; i<n; i++){
            for(int j=i+1; j<n; j++){
                if(isPalindrome(A, i , j)){
                    if(max < (j - i)){
                        max = j - i;
                        result = A.substring(i, j+1);
                    }
                }
            }
        }
        return result;
    }
    
    private static boolean isPalindrome(String A, int start, int end){
        int i = start;
        int j = end;
        while(i < j){
            if(A.charAt(i) == A.charAt(j)){
                i++;
                j--;
            }else{
                return false;
            }
        }
        return true;
    }

    //0(n2)
    private static int start = 0, end = 0, maxlen = 0;

    public static String longestPalindrome2(String s) {
        if (s == null || s.length() < 1)
            return "";
        for (int i = 0; i < s.length(); i++) {
             expandFromMiddle(s, i, i); // odd
             expandFromMiddle(s, i, i + 1); // even
        }
        //System.out.println(maxlen);
        return s.substring(start, end);
    }

    private static void expandFromMiddle(String s, int left, int right) {
        if (s == null || left > right)
            return;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            if (right - left + 1 > maxlen) {
                start = left;
                end = right + 1;
                maxlen = right - left + 1;
            }
            left--;
            right++;
        }
    }

     // Time Complexity 0(n2) : Dynamic Programming
     public static int longestPalindromeDynamic(char []str){
        boolean T[][] = new boolean[str.length][str.length];
        
        for(int i=0; i < T.length; i++){
            T[i][i] = true;
        }

        int max = 1;
        for(int l = 2; l <= str.length; l++){
            int len = 0;
            for(int i=0; i < str.length-l+1; i++){
                int j = i + l-1;
                len = 0;
                if(l == 2){
                    if(str[i] == str[j]){
                        T[i][j] = true;
                        len = 2;
                    }
                }else{
                    if(str[i] == str[j] && T[i+1][j-1]){
                        T[i][j] = true;
                        len = j -i + 1;
                    }
                }
                if(len > max){
                    max = len;
                }
            }
        }
        return max;
    }

    
    /**
     * Linear time Manacher's algorithm to find longest palindromic substring.
     * There are 4 cases to handle
     * Case 1 : Right side palindrome is totally contained under current palindrome. In this case do not consider this as center.
     * Case 2 : Current palindrome is proper suffix of input. Terminate the loop in this case. No better palindrom will be found on right.
     * Case 3 : Right side palindrome is proper suffix and its corresponding left side palindrome is proper prefix of current palindrome. Make largest such point as
     * next center.
     * Case 4 : Right side palindrome is proper suffix but its left corresponding palindrome is be beyond current palindrome. Do not consider this
     * as center because it will not extend at all.
     *
     * To handle even size palindromes replace input string with one containing $ between every input character and in start and end.
     */

    public static String longestPalindromicSubstringLinear(String str) {
        int index = 0;
        //preprocess the input to convert it into type abc -> $a$b$c$ to handle even length case.// (Work for both Even - odd case)
        //Total size will be 2*n + 1 of this new array.
        char input[] = str.toCharArray();
        char newInput[] = new char[2*input.length + 1];
        for(int i=0; i < newInput.length; i++) {
            if(i % 2 != 0) {
                newInput[i] = input[index++];
            } else {
                newInput[i] = '$';
            }
        }

        //create temporary array for holding largest palindrome at every point. There are 2*n + 1 such points.
        int T [] = new int[newInput.length];
        int start = 0;
        int end = 0;
        //here i is the center.
        for(int i = 0; i < newInput.length; ) {
            //expand around i
            while(start >0 && end < newInput.length-1 && newInput[start-1] == newInput[end+1]) {
                start--;
                end++;
            }
            //set the longest value of palindrome around center i at T[i]
            T[i] = end - start + 1;

            //this is case 2. Current palindrome is proper suffix of input. No need to proceed. Just break out of loop.
            if(end == T.length -1) {
                break;
            }
            //Mark newCenter to be either end or end + 1 depending on if we dealing with even or old number input.
            int newCenter = end + (i % 2 == 0 ? 1 : 0);

            for(int j = i + 1; j <= end; j++) {

                //i - (j - i) is left mirror. Its possible left mirror might go beyond current center palindrome.
                // So take minimum of either left side palindrome or distance of j to end.
                T[j] = Math.min(T[i - (j - i)], 2 * (end - j) + 1);
                // Only proceed if we get case 3 . As soon as we find a center lets break out of this inner while loop.
                if(j + T[i - (j - i)]/2 == end) {
                    newCenter = j;
                    break;
                }
            }
            //make i as newCenter. Set right and left to atleast the value we already know should be matching
            i = newCenter;
            end = i + T[i]/2;
            start = i - T[i]/2;
        }

        //find the max palindrome in T and return it.
        int max = Integer.MIN_VALUE;
        String result = "";
        for(int i = 0; i < T.length; i++) {
            int val = T[i]/2;
            if(max < val) {
                max = val;
                result = str.substring(i/2 - (val/2), i/2 + (val/2)+1);
            }
        }
        //System.out.println(max);
        return result;
    }

    public static void main (String [] args){
        String s = "abbababba";
        System.out.println(longestPalindromicSubstringLinear(s));
        System.out.println(longestPalindrome1(s));
        System.out.println(longestPalindrome2(s));
        System.out.println(longestPalindromeDynamic(s.toCharArray()));
    }
}

