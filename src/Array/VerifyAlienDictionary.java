package Array;

// https://leetcode.com/problems/verifying-an-alien-dictionary/
/*
Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.

Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.

Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character 
*/
public class VerifyAlienDictionary {

    public static boolean isAlienSorted2(String[] words, String order) {
        int[] alphabet = new int[26];
        for (int i = 0; i < order.length(); i++) {
            alphabet[order.charAt(i) - 'a'] = i;
        }
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {

                int minlen = Math.min(words[i].length(), words[j].length());
                for (int k = 0; k < minlen; k++) {
                    int idiff = words[i].charAt(k) - 'a';
                    int jdiff = words[j].charAt(k) - 'a';
                    if (alphabet[idiff] < alphabet[jdiff]) { // replace of indexOf string method
                        break;
                    } else if (alphabet[idiff] > alphabet[jdiff]) {
                        return false;
                    } else if (k == minlen - 1 && words[i].length() > words[j].length()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean isAlienSorted(String[] words, String order) {

        String preWord = words[0];
        int orderWordCount = 1;
        for (int i = 1; i < words.length; i++) {
            int min = Math.min(preWord.length(), words[i].length());
            String currWord = words[i];

            for (int j = 0; j < min; j++) {

                int preIndex = order.indexOf(preWord.charAt(j));
                int currIndex = order.indexOf(currWord.charAt(j));
                if (preIndex < currIndex) {
                    orderWordCount++;
                    break;
                } else if (preIndex > currIndex) {
                    return false;
                } else if (j == min - 1) { // end of matched character
                    if (preWord.length() > currWord.length()) {
                        return false;
                    } else {
                        orderWordCount++;
                    }
                }
            }
            preWord = currWord;
        }
        return orderWordCount == words.length;
    }

    public static void main(String[] args) {
        String [] words = {"hello","leetcode"};
        String order = "hlabcdefgijkmnopqrstuvwxyz";
        System.out.print(isAlienSorted(words, order));
        System.out.print(isAlienSorted2(words, order));
    }
}
