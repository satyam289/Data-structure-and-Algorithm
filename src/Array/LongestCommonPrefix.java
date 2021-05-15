package Array;

/*
https://leetcode.com/problems/longest-common-prefix/
Write a function to find the longest common prefix string amongst an array of strings.
If there is no common prefix, return an empty string "".

Input: strs = ["flower","flow","flight"]
Output: "fl"
*/
public class LongestCommonPrefix {

    // Time Complexity 0(MN)
    public static String longestCommonPrefix(String[] strs) {
        String commonPrefix = "";
        if (strs == null || strs.length == 0) {
            return commonPrefix;
        }
        for (int i = 0; i < strs[0].length(); i++) {
            char ch = strs[0].charAt(i);

            for (int j = 1; j < strs.length; j++) {
                if (i > strs[j].length() || ch != strs[j].charAt(i)) {
                    return commonPrefix;
                }
            }
            commonPrefix += ch;
        }
        return commonPrefix;
    }

    public static void main(String[] args) {
        String[] strs = { "flower", "flow", "flight" };
        System.out.print(longestCommonPrefix(strs));
    }
}
