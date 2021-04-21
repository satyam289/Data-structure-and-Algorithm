#include <string.h>
#include <string>

using namespace std;

class ReqularExpression {
    int isMatch(const string A, const string B){
        int m = A.size();
        int n = B.size();
        bool dp[m + 1][n + 1];
        memset(dp, false, sizeof(dp));

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if( i == 0 && j == 0) {
                    dp[i][j] = true;
                }
                else if(i == 0) {
                    dp[i][j] = B[j-1] == '*' && dp[i][j-2];
                }
                else if(j == 0) {
                    continue;
                }
                else {
                    if (B[j - 1] == '*'){
                        dp[i][j] = dp[i][j - 2] || ((B[j - 2] == A[i - 1] || B[j - 2] == '.') && dp[i - 1][j]);
                    }
                    else{
                        dp[i][j] = A[i - 1] == B[j - 1] || B[j - 1] == '.' ? dp[i - 1][j - 1] : 0;
                    }
                }
            }
        }
        return dp[m][n];
    }
};

/*** Java Implementation
package DynamicProgramming;

public class ReqularExpression {

    public static boolean isMatchRecursive(String A, String B){
        char[] Arr = A.toCharArray();
        char[] Brr = B.toCharArray();
        return isMatchRecursiveUtils(Arr, Brr, 0, 0);
    }

    private static boolean isMatchRecursiveUtils(char[] text, char[] pattern, int pos1, int pos2) {
        if(pos2 == pattern.length){ // if patern reach end
            return pos1 == text.length; //check text alos reach end
        }
        if(pos2 < pattern.length-1 && pattern[pos2+1] == '*'){
            if(isMatchRecursiveUtils(text, pattern, pos1, pos2+2)){
                return true;
            }
            if(text[pos1]==pattern[pos2] || pattern[pos2] == '.'){
                return isMatchRecursiveUtils(text, pattern, pos1+1, pos2);
            }
        }else{
            if(text[pos1]==pattern[pos2] || pattern[pos2] == '.'){
                return isMatchRecursiveUtils(text, pattern, pos1+1, pos2+1);
            }
        }
        return false;
    }

    
    public static int isMatch(final String A, final String B) {
        char[] Arr = A.toCharArray();
        char[] Brr = B.toCharArray();

        boolean[][] table = new boolean[Arr.length + 1][Brr.length + 1];
        table[0][0] = true;
        //Deals with patterns like a* or a*b* or a*b*c*
        for (int j = 1; j <= Brr.length; j++) {
            if (Brr[j - 1] == '*') {
                table[0][j] = table[0][j - 2];
            }
        }

        for (int i = 1; i <= Arr.length; i++) {
            for (int j = 1; j <= Brr.length; j++) {

                if (Arr[i - 1] == Brr[j - 1] || Brr[j - 1] == '.') {
                    table[i][j] = table[i - 1][j - 1];
                } else {
                    if (Brr[j - 1] == '*') {
                        table[i][j] = table[i][j - 2];
                        if (Brr[j - 2] == '.' || Arr[i - 1] == Brr[j - 2]) {
                            table[i][j] = table[i][j] || table[i - 1][j];
                        }
                    } else {
                        table[i][j] = false;
                    }
                }
            }
        }
        return table[Arr.length][Brr.length] ? 1 : 0;
    }

    public static void main(String [] args){
        System.out.println(isMatch("aab", "c*a*b"));
        System.out.println(isMatchRecursive("aab", "c*a*b"));
    }
}
*/