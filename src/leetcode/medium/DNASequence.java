package leetcode.medium;

import java.util.*;

public class DNASequence {

    /*
     * DNA compose of nucleotides like A, C, G and T
     * find all 10 letter long sequence
     */
    public static void main(String[] args) {
        String input = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        // ["AAAAACCCCC", "CCCCCAAAAA"]
        List<String> res = findRepeatingDnaSequences2(input);
        for (String s : res) {
            System.out.println(s);
        }
    }

    public static List<String> findRepeatingDnaSequences(String s) {
        if (s == null || s.length() < 10) {
            return Collections.emptyList();
        }
        List<String> res = new ArrayList<>();
        Map<Integer, Boolean> map = new HashMap<>();

        for (int t = 0, i = 0; i < s.length(); i++) {
            t = (t << 3 & 0x3FFFFFFF) | (s.charAt(i) & 7);
            if (map.containsKey(t)) {
                if (map.get(t)) {
                    res.add(s.substring(i - 9, i + 1));
                    map.put(t, false);
                }
            } else {
                map.put(t, true);
            }
        }
        return res;
    }

    public static List<String> findRepeatingDnaSequences2(String s) {
        if (s == null || s.length() < 10) {
            return Collections.emptyList();
        }
        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>();

        for (int i = 0; i < s.length() - 10; i++) {
            String sub = s.substring(i, i + 10);
            if (set.contains(sub)) {
                res.add(sub);
            }
            set.add(sub);
        }
        return res;
    }
}
