package DynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.
For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
The number of ways decoding "12" is 2.
 *
 */
public class DecodeWay {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getNoofWays("12"));
		
	}
	
	public static int getNoofWays(String str) {
		Map<Integer, Integer> hm=new HashMap<>();
		return getNoofWaysUtil(str, 0, hm);
	}
	
	public static int getNoofWaysUtil(String s, int start, Map<Integer, Integer> count) {
        if (s.length() == start) {
            return 1;
        }
        if (count.containsKey(start)) {
            return count.get(start);
        }
        String s1 = s.substring(start, start + 1);
        if (s1.equals("0")) {
            count.put(start, 0);
            return 0;
        }
        int c1 = getNoofWaysUtil(s, start + 1, count);
        int c2 = 0;
        if ((start + 2) <= s.length()) {
            s1 = s.substring(start, start + 2);
            if (Integer.valueOf(s1) <= 26) {
                c2 = getNoofWaysUtil(s, start + 2, count);
            }
        }
        count.put(start, c1 + c2);
        return c1 + c2;
    }
}

