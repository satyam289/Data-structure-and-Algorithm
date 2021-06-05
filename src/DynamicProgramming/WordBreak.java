package DynamicProgramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

//https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/BreakMultipleWordsWithNoSpaceIntoSpace.java
//https://www.geeksforgeeks.org/word-break-problem-dp-32/
public class WordBreak {

    public static void wordBreak(String words, Set<String> set) {

        int n = words.length();
        boolean[][] table = new boolean[n][n];

        for (int len = 1; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;

                String str = words.substring(i, j + 1);
                if (set.contains(str)) {
                    table[i][j] = true;
                } else {
                    for (int k = i; k < j; k++) {
                        table[i][j] = table[i][k] && table[k + 1][j];
                        if (table[i][j]) {
                            break;
                        }
                    }
                }
            }
        }

        // printing table
        for (int i = 0; i < words.length(); i++) {
            for (int j = 0; j < words.length(); j++) {
                char ch = table[i][j] ? 'T' : 'F';
                System.out.print(ch + " | ");
            }
            System.out.println("");
        }
    }

    public static String breakWordRecursion(char[] str, int low, Set<String> dict) {

        StringBuffer sb = new StringBuffer();
        for (int i = low; i < str.length; i++) {
            sb.append(str[i]);
            if (dict.contains(sb.toString())) {
                String result = breakWordRecursion(str, i + 1, dict);
                if (result != null) {
                    return sb.toString() + " " + result;
                }
            }
        }
        if (dict.contains(sb.toString())) {
            return sb.toString();
        }
        return null;
    }

    // Recursion with Memorization
    public int wordBreakRecursion2(String word, HashSet<String> dict, HashMap<String, Integer> map) {
        if (dict.contains(word)) {
            return 1;
        }
        if (map.containsKey(word)) {
            return 1;
        }
        for (int i = 0; i < word.length(); i++) {
            String leftStr = word.substring(0, i);
            if (dict.contains(leftStr) && wordBreakRecursion2(word.substring(i), dict, map) == 1) {
                map.put(word, 1); // Found
                return 1;
            }
        }
        map.put(word, 0); // Not Found
        return 0;
    }

    // Better Apporach
    public static String breakWordDp(String word, Set<String> dict) {
        int n = word.length();

        int T[][] = new int[n][n];
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;
                String str = word.substring(i, j + 1);
                if (dict.contains(str)) {
                    T[i][j] = i;
                    continue;
                }
                for (int k = i + 1; k <= j; k++) {
                    if (T[i][k - 1] != -1 && T[k][j] != -1) {
                        T[i][j] = k;
                        break;
                    }
                }
            }
        }
        if (T[0][word.length() - 1] == -1) {
            return null;
        }
        for (int i = 0; i < word.length(); i++) {
            for (int j = 0; j < word.length(); j++) {
                System.out.print(T[i][j] == -1 ? " -1 " : T[i][j] + " ");
            }
            System.out.println("");
        }
        StringBuffer sb = new StringBuffer();
        int i = 0;
        int j = word.length() - 1;
        while (i < j) {
            int k = T[i][j];
            if (i == k) {
                sb.append(word.substring(i, j + 1));
                break;
            }
            sb.append(word.substring(i, k) + " ");
            i = k;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Set<String> dictionary = new HashSet<String>();
        dictionary.add("I");
        dictionary.add("like");
        dictionary.add("had");
        dictionary.add("play");
        dictionary.add("to");
        String str = "Iliketoplay";
        wordBreak(str, dictionary);
        System.out.println(breakWordRecursion(str.toCharArray(), 0, dictionary));
        System.out.println(breakWordDp(str, dictionary));
    }

    /*
     * https://www.interviewbit.com/problems/word-break-ii/
     */
    public class Solution {

        ArrayList<String> result = new ArrayList<>();

        public ArrayList<String> wordBreak(String A, ArrayList<String> B) {
            wordBreakRec(A, 0, "", B);
            Collections.sort(result);
            return result;
        }

        public void wordBreakRec(String A, int index, String res, ArrayList<String> B) {
            if (index == A.length()) {
                if (res != "") {
                    result.add(res);
                }
                return;
            }
            StringBuffer sb = new StringBuffer();
            for (int i = index; i < A.length(); i++) {
                sb.append(A.charAt(i));

                if (B.contains(sb.toString())) {
                    wordBreakRec(A, i + 1, res + " " + sb.toString(), B);
                }
            }
        }
    }
}
