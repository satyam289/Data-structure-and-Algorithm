package Miscellaneous;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

//https://www.geeksforgeeks.org/find-the-longest-string-that-can-be-made-up-of-other-strings-from-the-array/
public class LongestWordMadeOtherWord {

    public static String getlongestWord1(String arr[]) {
        HashMap<String, Boolean> map = new HashMap<String, Boolean>();
        for (String str : arr) {
            map.put(str, true);
        }
        Arrays.sort(arr, new Comparator<String>() {
            public int compare(String str1, String str2) {
                return str2.length() - str1.length();
            }
        });

        for (String s : arr) {
            if (canBuildWord(s, true, map)) {
                // System.out.println(s);
                return s;
            }
        }
        return "";
    }

    private static boolean canBuildWord(String str, boolean isOriginalWord, HashMap<String, Boolean> map) {
        if (map.containsKey(str) && !isOriginalWord) {
            return map.get(str);
        }
        for (int i = 1; i < str.length(); i++) {
            String left = str.substring(0, i);
            String right = str.substring(i);
            if (map.containsKey(left) && map.get(left) == true && canBuildWord(right, false, map)) {
                return true;
            }
        }
        map.put(str, false);
        return false;
    }

    public static void main(String[] args) {
        String[] listOfWords = { "ala", "ma", "kota", "aa", "aabbb", "bbb", "cccc", "aabbbmacccc", "aabbbmaxxcccc" };
        System.out.println(getlongestWord1(listOfWords));
    }
}
