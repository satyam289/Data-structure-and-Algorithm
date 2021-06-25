package StackQueue;

import java.util.LinkedList;
import java.util.Queue;

//https://www.interviewbit.com/problems/first-non-repeating-character-in-a-stream-of-characters/
//https://www.geeksforgeeks.org/queue-based-approach-for-first-non-repeating-character-in-a-stream/
//https://www.geeksforgeeks.org/find-first-non-repeating-character-stream-characters/
public class FirstNonRepeatCharStream {

    public String solve(String A) {
        char[] freq = new char[26];
        Queue<Character> q = new LinkedList<>();
        String res = "";
        for (int i = 0; i < A.length(); i++) {
            q.add(A.charAt(i));
            freq[A.charAt(i) - 'a']++;

            while (!q.isEmpty()) {
                if (freq[q.peek() - 'a'] > 1) { // remove till we find Non repeat
                    q.remove();
                } else {
                    res += q.peek(); // Found
                    break;
                }
            }
            if (q.isEmpty()) {
                res += "#"; // Not Found
            }
        }
        return res;
    }
}
