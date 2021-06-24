package Array;

import java.util.HashMap;
import java.util.HashSet;

//https://leetcode.com/problems/longest-substring-without-repeating-characters/
//https://www.interviewbit.com/problems/longest-substring-without-repeat/
public class LongestDistinctSubString {

    // Time Complexity : 0(2N) Space : 0(N)
    public int lengthOfLongestSubstring(String A) {
        HashSet<Character> set = new HashSet<>();
        int n = A.length();
        int startptr = 0;
        int maxLen = 0;
        for (int currPtr = 0; currPtr < n; currPtr++) {
            char ch = A.charAt(currPtr);
            while (set.contains(ch)) { // remove from start till ch
                set.remove(A.charAt(startptr));
                startptr++;
            }
            set.add(ch);
            maxLen = Math.max(maxLen, currPtr - startptr + 1);
        }
        return maxLen;
    }

    // Optimised 0(N)
    public int lengthOfLongestSubstring2(String A) {
        HashMap<Character, Integer> map = new HashMap<>();
        int startptr = 0;
        int maxLen = 0;
        if (A.length() == 1) {
            return 1;
        }
        for (int i = 0; i < A.length(); i++) {
            char ch = A.charAt(i);
            if (map.containsKey(ch)) {
                // Move just ahead last seen position or if already moved ahead, then ignore
                startptr = Math.max(startptr, map.get(ch) + 1);
            }
            map.put(ch, i); // Update new position if present
            maxLen = Math.max(maxLen, i - startptr + 1);
        }
        return maxLen;
    }
}
