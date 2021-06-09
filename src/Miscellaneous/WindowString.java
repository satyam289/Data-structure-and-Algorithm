package Miscellaneous;

import java.util.HashMap;

//https://www.interviewbit.com/problems/window-string/
public class WindowString {

    // Time Complexity : 0(N^2)
    public String minWindow(String A, String B) {
        if (A.equals(B)) {
            return A;
        }
        int minWin = B.length();
        int strlen = A.length();
        if (strlen < minWin) {
            return "";
        }
        for (int window = minWin; window < strlen; window++) {
            String res = find_B_in_A(A, B, window);
            if (res != null) {
                return res;
            }
        }
        return "";
    }

    private String find_B_in_A(String A, String B, int window) {

        for (int i = 0; i <= A.length() - window; i++) {
            String subStr = A.substring(i, i + window);
            if (containAllCharacter(subStr, B)) {
                return subStr;
            }
        }
        return null;
    }

    private boolean containAllCharacter(String mainStr, String BStr) {

        HashMap<Character, Integer> map = new HashMap<>();
        for (char ch : mainStr.toCharArray()) {
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
            }
        }
        for (char ch : BStr.toCharArray()) {
            if (!map.containsKey(ch)) {
                return false;
            } else {
                int count = map.get(ch);
                count--;
                if (count == 0) {
                    map.remove(ch);
                } else {
                    map.put(ch, count);
                }
            }
        }
        return true;
    }

    // Time Complexity: 0(n)
    public String minWindow2(String A, String B) {
        int hasFound[] = new int[256];
        int toFind[] = new int[256];
        int i, count;
        for (i = 0; i < B.length(); i++) {
            toFind[B.charAt(i)]++;
        }
        count = 0;
        for (i = 0; i < toFind.length; i++) {
            if (toFind[i] == 0) {
                toFind[i] = -1;
            } else {
                count += toFind[i];
            }
        }
        int start, end, len;
        char ch;
        start = end = 0;

        len = Integer.MAX_VALUE;
        String window = null;
        while (end != A.length()) {
            ch = A.charAt(end);
            if (toFind[ch] == -1) {
                end++;
                continue;
            }
            hasFound[ch]++;
            if (hasFound[ch] <= toFind[ch]) {
                count--;
            }
            if (count == 0) {
                while (toFind[A.charAt(start)] == -1 || hasFound[A.charAt(start)] > toFind[A.charAt(start)]) {
                    if (toFind[A.charAt(start)] != -1)
                        hasFound[A.charAt(start)]--;
                    start++;
                }
                if (end - start + 1 < len) {
                    len = end - start + 1;
                    window = A.substring(start, start + len);
                }
            }
            end++;
        }
        if (window == null) {
            return "";
        }
        return window;
    }
}