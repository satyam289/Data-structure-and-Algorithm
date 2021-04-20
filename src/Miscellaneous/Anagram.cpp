#include <iostream>
#include <vector>
#include <map>
#include <algorithm>

using namespace std;

// https://www.interviewbit.com/problems/anagrams/
class Anagram {
public:
    vector<vector<int>> anagram1(const vector<string> &str)
    {
        vector<vector<int>> ans;
        map<string, vector<int>> groups;

        for (int i = 0; i < str.size(); i++)
        {
            string copystr = str[i];
            sort(copystr.begin(), copystr.end());
            groups[copystr].push_back(i + 1);
        }

        for (map<string, vector<int>>::iterator it = groups.begin(); it != groups.end(); it++)
        {
            ans.push_back(it->second);
        }
        return ans;
    }

    vector<vector<int>> anagram2(const vector<string> &A)
    {
        vector<vector<int>> ans;
        map<int, vector<int>> B;
        for (int i = 0; i < A.size(); i++)
        {
            int sum = 0;
            for (int j = 0; j < A[i].size(); j++)
            {
                sum += A[i][j];
            }
            B[sum].push_back(i);
        }
        for (auto it = B.begin(); it != B.end(); it++)
        {
            ans.push_back(it->second);
        }
        return ans;
    }
};

/***** Java Implementaion 
package Miscellaneous;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

// https://www.interviewbit.com/problems/anagrams
public class Anagram {
    
    public ArrayList<ArrayList<Integer>> anagrams(final List<String> a) {

        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        HashMap<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();
        int n = a.size();
        for (int i = 0; i < n; i++) {
            char[] arr = a.get(i).toCharArray();
            Arrays.sort(arr);
            String str = new String(arr);
            if (map.get(str) != null) {
                map.get(str).add(i + 1);
            } else {
                ArrayList<Integer> arrList = new ArrayList<>();
                arrList.add(i + 1);
                map.put(str, arrList);
            }
        }
        for (ArrayList<Integer> list : map.values()) {
            result.add(list);
        }
        return result;
    }
    
    public ArrayList<ArrayList<Integer>> anagrams2(final List<String> a) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < a.size(); i++) {
            int hashVal = hash(a.get(i));
            ArrayList<Integer> list = map.getOrDefault(hashVal, new ArrayList<Integer>());
            list.add(i + 1);
            map.put(hashVal, list);
        }
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
        for (Integer key : map.keySet()) {
            list.add(map.get(key));
        }
        return list;
    }

    private int hash(String word){
        int [] map = new int[26];
        for(int i=0; i<word.length(); i++){
            map[word.charAt(i)]++;
        }
        int hash = 0;
        for(int i=0; i<26; i++){
            hash = 31 *hash + map[i];
        }
        return hash;
    }

}
*/