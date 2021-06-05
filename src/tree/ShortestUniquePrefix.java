package tree;

import java.util.ArrayList;

//https://www.interviewbit.com/problems/shortest-unique-prefix/
public class ShortestUniquePrefix {

    private static class Trie {
        char data;
        int occurance;
        boolean isLast;
        ArrayList<Trie> children;

        Trie(char data) {
            this.data = data;
            isLast = false;
            children = new ArrayList<>();
            occurance = 1;
        }
    }

    public ArrayList<String> prefix(ArrayList<String> input) {
        Trie root = new Trie('0'); // dummy root node

        for (String word : input) {
            addToTrie(root, word.toCharArray(), 0);
        }
        ArrayList<String> result = new ArrayList<String>();
        for (String word : input) {
            result.add(findPrefix(root, word));
        }
        return result;
    }

    private String findPrefix(Trie root, String word) {
        int i;
        boolean isPrefixFound = false;
        for (i = 0; i < word.length(); i++) {

            for (Trie child : root.children) {
                if (child.data == word.charAt(i)) {
                    if (child.occurance == 1) {
                        isPrefixFound = true;
                    }
                    root = child; // next level
                    break;
                }
            }
            if (isPrefixFound) {
                break;
            }
        }
        return word.substring(0, i + 1);
    }

    private void addToTrie(Trie root, char[] word, int wordIndex) {
        Trie foundChild = null;

        for (Trie child : root.children) {
            if (child.data == word[wordIndex]) { // Found
                foundChild = child;
                foundChild.occurance++; // increase the count
                break;
            }
        }
        if (wordIndex < word.length) {
            if (foundChild == null) {
                foundChild = new Trie(word[wordIndex]);
                root.children.add(foundChild); // adding new child
            }
            addToTrie(foundChild, word, wordIndex + 1); //
        } else {
            if (!root.isLast) {
                root.isLast = true;
            }
        }
    }
}

// similar apporach (Minor Optimised)
class Solution {

    static class Trie {
        int count;
        Trie[] arr = new Trie[26]; // If we use TreeMap<Character, Trie>, it would be further optimised

        Trie() {
            this.count = 1;
            for (int i = 0; i < 26; i++) {
                arr[i] = null;
            }
        }
    }

    static Trie root;

    void insert(String s) {
        Trie temp = root;

        for (int i = 0; i < s.length(); i++) {
            if (temp.arr[s.charAt(i) - 'a'] == null) {
                temp.arr[s.charAt(i) - 'a'] = new Trie();
                temp = temp.arr[s.charAt(i) - 'a'];
            } else {
                temp = temp.arr[s.charAt(i) - 'a'];
                temp.count++;
            }
        }
    }

    String search(String s) {
        String ans = s;
        Trie temp = root;
        int v = 0;
        for (int i = 0; i < s.length(); i++) {
            temp = temp.arr[s.charAt(i) - 'a'];
            v++;
            if (temp.count == 1) {
                break;
            }
        }
        ans = s.substring(0, v);
        return ans;
    }

    public ArrayList<String> prefix(ArrayList<String> A) {
        root = new Trie();
        for (int i = 0; i < A.size(); i++) {
            insert(A.get(i));
        }
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < A.size(); i++) {
            String s = search(A.get(i));
            list.add(s);
        }
        return list;
    }
}