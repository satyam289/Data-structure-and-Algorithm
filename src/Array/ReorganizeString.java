package Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Map.Entry;

//https://leetcode.com/problems/reorganize-string/
/*
Given a string s, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
If possible, output any possible result.  If not possible, return the empty string.

Input: s = "aab"
Output: "aba"
*/
public class ReorganizeString {

    private static class Pair {
        char ch;
        int count;

        Pair(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
    }

    public static String reorganize(String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>((a, b) -> b.count - a.count);

        for (Entry<Character, Integer> entry : map.entrySet()) {
            maxHeap.add(new Pair(entry.getKey(), entry.getValue()));
        }

        String result = "";
        while (!maxHeap.isEmpty()) {
            ArrayList<Pair> list = new ArrayList<>();
            Pair pair = null;
            for (int i = 0; i < 2; i++) {
                if (!maxHeap.isEmpty()) {
                    pair = maxHeap.poll();
                    result += pair.ch;
                    pair.count--;
                    list.add(pair);
                } else {
                    return pair.count != 0 ? "" : result;
                }
            }
            for (int i = 0; i < list.size(); i++) {
                Pair usedpair = list.get(i);
                if (usedpair.count > 0) {
                    maxHeap.add(usedpair);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String str = "aaab";
        System.out.println(reorganize(str));
    }
}

/*
https://leetcode.com/problems/string-compression/
Input: chars = ["a","a","b","b","c","c","c"]
Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".

Input: chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
Output: Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
Explanation: The groups are "a" and "bbbbbbbbbbbb". This compresses to "ab12".
*/
class StringCompression{

    public int compress(char[] chars) {
        int n = chars.length;
        int i = 0;
        int index = 0;
        while (i < n) {
            int j = i;
            while (j < n && chars[i] == chars[j]) {
                j++;
            }
            chars[index++] = chars[i];
            if (j - i > 1) {
                String frequency = (j - i) + " ";
                for (char ch : frequency.toCharArray()) {
                    chars[index++] = ch;
                }
            }
            i = j;
        }
        return index;
    }
}
