package Miscellaneous;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/*
https://leetcode.com/problems/group-anagrams/
https://www.geeksforgeeks.org/given-a-sequence-of-words-print-all-anagrams-together/
Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
*/
public class GroupAnagram {

    // Time Complexity : 0(NMLogM)
    public static List<List<String>> groupAnagrams(ArrayList<String> list) {

        List<List<String>> result = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();

        for (String str : list) {

            char[] chArr = str.toCharArray();
            Arrays.sort(chArr);
            String sortedStr = new String(chArr);
            if (!map.containsKey(sortedStr)) {
                map.put(sortedStr, new ArrayList<>());
            }
            map.get(sortedStr).add(str);
        }
        result.addAll(map.values());
        return result;
    }
   
    // Time Complexity : 0(NM)  N words and each word has maximum M characters
    public static List<List<String>> groupAnagrams2(ArrayList<String> list) {
        List<List<String>> result = new ArrayList<>();

        HashMap<HashMap<Character, Integer>, List<String>> freqMap = new HashMap<>();
        for (String str : list) {
            HashMap<Character, Integer> tempMap = new HashMap<>();
            for (int i = 0; i < str.length(); i++) {
                tempMap.put(str.charAt(i), tempMap.getOrDefault(str.charAt(i), 0) + 1);
            }

            if (!freqMap.containsKey(tempMap)) {
                freqMap.put(tempMap, new ArrayList<>());
            }
            freqMap.get(tempMap).add(str);
        }
        result.addAll(freqMap.values());
        return result;
    }

    //https://www.geeksforgeeks.org/given-a-sequence-of-words-print-all-anagrams-together-set-2/
    
    // 26 slots each for 'a' to 'z'
    static final int NO_OF_CHARACTER = 26;
    
    private static class TrieNode {
        boolean isEnd;
        TrieNode[] child = new TrieNode[NO_OF_CHARACTER];
        LinkedList<Integer> head;

        public TrieNode() {
            isEnd = false;
            head = new LinkedList<>();
            for (int i = 0; i < NO_OF_CHARACTER; i++) {
                child[i] = null;
            }
        }
    }

    public static void solveAnagrams(ArrayList<String> list) {
        TrieNode root = new TrieNode();
        for (int i = 0; i < list.size(); i++) {
            char[] buffer = list.get(i).toCharArray();
            Arrays.sort(buffer);
            root = insert(root, new String(buffer), 0, i);
        }
        printTrieNode(root, list);
    }

    private static TrieNode insert(TrieNode root, String word, int wordIndex, int ArrWrodIndex) {
        if (root == null) {
            root = new TrieNode();
        }
        if (wordIndex < word.length()) {
            int charIndex = word.charAt(wordIndex) - 'a';
            root.child[charIndex] = insert(root.child[charIndex], word, wordIndex + 1, ArrWrodIndex);
        } else {
            if (!root.isEnd) {
                root.isEnd = true;
            }
            root.head.add(ArrWrodIndex);
        }
        return root;
    }

    private static void printTrieNode(TrieNode root, ArrayList<String> words) {
        if (root == null) {
            return;
        }
        if (root.isEnd) {
            for (int index : root.head) {
                System.out.print(words.get(index) + " ");
            }
            System.out.println("");
        }
        for (TrieNode childNode : root.child) {
            printTrieNode(childNode, words);
        }
    }

    public static void main(String[] args)
    {
        ArrayList<String> list = new ArrayList<>();
        list.add("cat");
        list.add("dog");
        list.add("ogd");
        list.add("god");
        list.add("atc");
 
       // System.out.println(groupAnagrams(list));
        solveAnagrams(list);
    }
}
