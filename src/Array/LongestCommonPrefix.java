package Array;

/*
https://www.geeksforgeeks.org/longest-common-prefix-using-character-by-character-matching/
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

    // Similar Time Complexity : Taking two at a time.
    public static String longestCommonPrefix2(String[] A) {
        if (A == null || A.length == 0) {
            return "";
        }
        if (A.length == 1) {
            return A[0];
        }
        String result = getCommon(A[0], A[1]); // first tow
        for (int i = 2; i < A.length; i++) {
            result = getCommon(result, A[i]);
            if (result.equals("")) {
                break;
            }
        }
        return result;
    }

    private static String getCommon(String A, String B) {
        String comm = "";
        for (int i = 0; i < Math.min(A.length(), B.length()); i++) {
            if (A.charAt(i) == B.charAt(i)) {
                comm += A.charAt(i);
            } else {
                break;
            }
        }
        return comm;
    }

    public static void main(String[] args) {
        String[] input = { "flower", "flow", "flight" };
        System.out.println(longestCommonPrefix(input));
        System.out.println(longestCommonPrefix2(input));
        System.out.print(longestCommonPrefix3(input));
    }

    private static class Trie {
        char ch;
        Trie[] childs;
        boolean isLeaf;

        Trie(char ch) {
            this.ch = ch;
            isLeaf = false;
            childs = new Trie[26]; // number character is 26
        }
    }

    // https://www.geeksforgeeks.org/longest-common-prefix-using-trie/
    public static String longestCommonPrefix3(String[] arr) {
        Trie root = constructTrie(arr);
        return getCommonPrefix(root);
    }

    private static Trie constructTrie(String[] arr) {
        Trie root = new Trie('1');
        for (String str : arr) {
            Trie curnode = root;
            for (int i = 0; i < str.length(); i++) {
                int index = str.charAt(i) - 'a';
                if (curnode.childs[index] == null) {
                    curnode.childs[index] = new Trie(str.charAt(i));
                }
                curnode = curnode.childs[index];
            }
            curnode.isLeaf = true;
        }
        return root;
    }

    private static String getCommonPrefix(Trie root) {
        Trie curNode = root;
        String result = "";
        while (!curNode.isLeaf) {
            Trie child = getOnlyChild(curNode.childs);
            if (child == null) {
                break;
            }
            result += child.ch;
            curNode = child;
        }
        return result;
    }

    // Since prefix search, we need to find all common character,
    // So, there must be only one character present, otherwise no common prefix
    private static Trie getOnlyChild(Trie[] childs) {
        int count = 0;
        Trie onlyChild = null;
        for (int i = 0; i < childs.length; i++) {
            if (childs[i] != null) {
                count++;
                onlyChild = childs[i];
            }
        }
        return count == 1 ? onlyChild : null;
    }
}
