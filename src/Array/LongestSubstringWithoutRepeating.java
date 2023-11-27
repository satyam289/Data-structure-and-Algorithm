package Array;

import java.util.HashSet;

// https://leetcode.com/problems/longest-substring-without-repeating-characters/
public class LongestSubstringWithoutRepeating {
    
    // sliding window approach
    public static int lengthofLongestSubstring(String s){
        int startptr = 0, endptr = 0, currlen = 0, maxlen = 0;
        HashSet<Character> hs = new HashSet<>();
        while(endptr < s.length()){
            char ch = s.charAt(endptr);
            if(!hs.contains(ch)){
                hs.add(ch);
                endptr++;
                currlen = endptr - startptr;
                if(currlen > maxlen){
                    maxlen = currlen;
                }
            }else{
                while (startptr <= endptr && endptr < s.length()) {
                    if(s.charAt(startptr) != s.charAt(endptr)){
                        hs.remove(s.charAt(startptr));
                        startptr++;
                    } else {
                        startptr++;
                        endptr++;
                        break;
                    }
                }
            }
        }
        return maxlen;
    }

    
    public static void main(String[] args) {
        String arr = "pwwkew";
        System.out.println("length of Longest Substring "+ lengthofLongestSubstring(arr));        
    }
}
