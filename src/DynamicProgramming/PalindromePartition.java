package DynamicProgramming;

import java.util.ArrayList;

public class PalindromePartition {

    public static void main(String[] args) {
        System.out.println(minCut("ababb"));
        System.out.println(minCutOptimised("ababb"));
    }

    /*
     * Given a string s, partition s such that every substring of the partition is a palindrome.
     * Return the minimum cuts needed for a palindrome partitioning of s.
     * https://leetcode.com/problems/palindrome-partitioning-ii/
     */
    public static int minCut(String A) {
        
        int n = A.length();
        int[][] table = new int[n][n];
        boolean[][]  isPalindromeTable = new boolean[n][n];
        
        for (int i = 0; i < n; i++) {
            isPalindromeTable[i][i] = true;
        }
        for(int len = 2; len <= n; len++){
            for(int i = 0; i < n - len + 1; i++){
                int j = i + len -1;
                
                if(len == 2){
                    if(A.charAt(i) == A.charAt(j)){
                        isPalindromeTable[i][j] = true;
                        //table[i][j] = 0; // default value
                    }else{
                        //isPalindromeTable[i][j] = false; //default value
                        table[i][j] = 1;
                    }
                } else {
                    if (A.charAt(i) == A.charAt(j)) {
                        isPalindromeTable[i][j] = isPalindromeTable[i + 1][j - 1];
                    }
                    if (isPalindromeTable[i][j]) {
                        table[i][j] = 0;
                    } else {
                        int min = Integer.MAX_VALUE;
                        for (int k = i; k < j; k++) {
                            min = Math.min(min, 1 + table[i][k] + table[k + 1][j]);
                        }
                        table[i][j] = min;
                    }
                }
            }
        }
        return table[0][n-1];
    }


    public static int minCutOptimised(String A) {
        int n = A.length();
        boolean[][] pal = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            pal[i][i] = true;
        }
        for (int l = 2; l <= n; l++) {
            for (int i = 0; i < n - l + 1; i++) {
                int j = i + l - 1;
                if (l == 2) {
                    pal[i][j] = A.charAt(i) == A.charAt(j) ? true : false;
                } else {
                    pal[i][j] = (A.charAt(i) == A.charAt(j) && pal[i + 1][j - 1]) ? true : false;
                }
            }
        }

        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            if (pal[0][i]) {
                result[i] = 0;
            } else {
                result[i] = Integer.MAX_VALUE;
                for (int j = 0; j < i; j++) {
                    if (pal[j + 1][i] && result[j] + 1 < result[i]) {
                        result[i] = result[j] + 1;
                    }
                }
            }
        }
        return result[n - 1];
    }
    /*
     * Different Question:
     * https://www.interviewbit.com/problems/palindrome-partitioning/
     * https://www.geeksforgeeks.org/given-a-string-print-all-possible-palindromic-
     * partition/ Given a string s, partition s such that every string of the
     * partition is a palindrome. Return all possible palindrome partitioning of s.
     * input = "aab", Output: [ ["a","a","b"] ["aa","b"], ]
     */

    ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();

    public ArrayList<ArrayList<String>> partition(String a) {
        ArrayList<String> arr = new ArrayList<String>();
        partitionRec(a, 0, arr);
        return result;
    }

    public void partitionRec(String a, int start, ArrayList<String> arr) {
        if (start >= a.length()) {
            result.add(new ArrayList<>(arr));
            return;
        }
        for (int i = start; i < a.length(); i++) {
            if (isPalindrome(a, start, i)) {
                arr.add(a.substring(start, i + 1));
                partitionRec(a, i + 1, arr);
                arr.remove(arr.size() - 1);
            }
        }
    }

    public boolean isPalindrome(String str, int start, int end) {
        while (start < end) {
            if (str.charAt(start++) != str.charAt(end--)) {
                return false;
            }
        }
        return true;
    }
}