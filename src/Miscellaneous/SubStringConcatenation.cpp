#include <vector>
#include <unordered_map>

using namespace std;

vector<int> findSubstring(string input, const vector<string> &words)
{
    if (words.size() == 0)
        return {};

    unordered_map<string, int> freqmap;
    for (string s : words)
        freqmap[s]++;

    int num = words.size(), len = words[0].size();
    int total_len = num * len;
    if (input.size() < total_len)
        return {};

    unordered_map<string, int> temp;
    vector<int> result;
    for (int i = 0; i <= input.size() - total_len; i++)
    {
        int j = 0;
        while (j < num)
        {
            string r = input.substr(i + j * len, len);
            if (freqmap.find(r) == freqmap.end())
                break;
            temp[r]++;
            if (freqmap[r] < temp[r])
                break;
            j++;
        }
        if (j == num)
            result.push_back(i);
        temp.clear();
    }
    return result;
};

/* package Miscellaneous;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://www.geeksforgeeks.org/find-starting-indices-substrings-string-s-made-concatenating-words-listl/
// https://www.interviewbit.com/problems/substring-concatenation/
class SubStringConcatenation {

    public static ArrayList<Integer> findSubstring(String input, final List<String> words) {
        ArrayList<Integer> result = new ArrayList<>();
        if (input == null || input.length() == 0 || words == null || words.size() == 0) {
            return result;
        }
        int wordlen = words.get(0).length();
        int noOfWord = words.size();
        int subStringSize = wordlen * noOfWord;

        HashMap<String, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < noOfWord; i++) {
            freqMap.put(words.get(i), freqMap.getOrDefault(words.get(i), 0) + 1);
        }

        for (int i = 0; i < input.length() - subStringSize; i++) {
            HashMap<String, Integer> seenMap = (HashMap<String, Integer>) freqMap.clone();  // For duplicate word count
            int j = i;
            while (j < i + subStringSize) {
                String subWord = input.substring(j, j + wordlen);
                if (!freqMap.containsKey(subWord) || seenMap.get(subWord) == 0) { // not seen or exhasted word count
                    break;
                }
                seenMap.put(subWord, seenMap.getOrDefault(subWord, 0) - 1); // update the seen count 
                j = j + wordlen; // next word index
            }
            if (j == i + subStringSize) {
                result.add(i); // adding starting index , if we found substring
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String S = "barfoothefoobarman";
        ArrayList<String> L = new ArrayList<>(Arrays.asList("foo", "bar"));
        ArrayList<Integer> indices = findSubstring(S, L);
        for (Integer i : indices) {
            System.out.println(i);
        }
    }

    // Similiar Apporach
    public ArrayList<Integer> findSubstring2(String A, final List<String> B) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (B.size() == 0)
          return list;
        
        Map<String, Integer> dict = new HashMap<>();
        for (String s : B)
          dict.put(s, dict.getOrDefault(s, 0) + 1);
        int len = B.size() * (B.get(0).length());
        int wlen = B.get(0).length();
        
        for (int i = 0; i <= A.length() - len; i++) {
            String s = A.substring(i, i+len);
            Map<String, Integer> map = new HashMap<>();
            for (int j = 0; j < s.length(); j+=wlen) {
                String temp = s.substring(j, j+wlen);
                map.put(temp, map.getOrDefault(temp, 0) + 1);
            }
            
            if (dict.equals(map)) // comparaing two map
              list.add(i);
        }
        
        return list;
    }
}
 */