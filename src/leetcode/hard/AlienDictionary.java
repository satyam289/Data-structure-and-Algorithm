package leetcode.hard;

import java.util.*;

// latin alphabet (words are sorted lexicographically by rules of new language)
public class AlienDictionary {

    public static void main(String[] args) {
        String[] input1 = { "wrt", "wrf", "er", "ett", "rftt" }; // wertf
        String[] input2 = { "z", "x" }; // zx
        String[] input3 = { "z", "x", "z" }; // ""
        String result = alienOrder(input1);
        System.out.println(result);
    }

    private static String alienOrder(String[] words) {
        if (words == null || words.length == 0)
            return "";
        Map<Character, Integer> inDegrees = new HashMap<>();
        for (String s : words) {
            for (char c : s.toCharArray()) {
                inDegrees.put(c, 0);
            }
        }

        Map<Character, Set<Character>> graph = new HashMap<>();
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];
            if (w1.startsWith(w2) && w1.length() > w2.length()) {
                return "";
            }
            for (int j = 0; j < w1.length() && j < w2.length(); j++) {
                char c1 = w1.charAt(j);
                char c2 = w2.charAt(j);
                if (c1 != c2) {
                    if (!graph.containsKey(c1)) {
                        graph.put(c1, new HashSet<>());
                    }
                    if (graph.get(c1).add(c2)) {
                        inDegrees.put(c2, inDegrees.get(c2) + 1);
                    }
                    break;
                }
            }
        }

        Queue<Character> queue = new ArrayDeque<>();
        for (Map.Entry<Character, Integer> e : inDegrees.entrySet()) {
            if (e.getValue() == 0) {
                queue.offer(e.getKey());
            }
        }
        StringBuilder order = new StringBuilder();
        while (!queue.isEmpty()) {
            char n = queue.poll();
            order.append(n);

            if (graph.containsKey(n)) {
                for (char neighbour : graph.get(n)) {
                    inDegrees.put(neighbour, inDegrees.get(neighbour) - 1);
                    if (inDegrees.get(neighbour) == 0) {
                        queue.offer(neighbour);
                    }
                }
            }
        }
        return order.length() == inDegrees.size() ? order.toString() : "";
    }
}
