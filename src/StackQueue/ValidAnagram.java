package StackQueue;

import java.util.Arrays;

public class ValidAnagram {
    
    // Time Complexity : 0(n)
    public static boolean isValid(char[] arr1, char[] arr2) {

        if (arr1.length != arr2.length) {
            return false;
        }
        int[] ch = new int[26];
        for (int i = 0; i < arr1.length; i++) {
            ch[arr1[i] - 'a']++;
            ch[arr2[i] - 'a']--;
        }
        for (int c : ch) {
            if (c != 0) {
                return false;
            }
        }
        return true;
    }

    // TIme Complexity : 0(nlogn)
    public static boolean isValid2(char[] arr1, char[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String [] args){
        // assume lower case from a to z only
        System.out.println(isValid("cricket".toCharArray(), "triccek".toCharArray()));
        System.out.println(isValid2("cricket".toCharArray(), "triccek".toCharArray()));
    }
}
