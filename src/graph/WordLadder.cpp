#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <unordered_map>
#include <unordered_set>

using namespace std;

/*
https://www.interviewbit.com/problems/word-ladder-ii/

Given two words (start and end), and a dictionary, 
find the shortest transformation sequence from start to end, such that
Each intermediate word must exist in the dictionary

Constrait:
1) All words have the same length.
2) All words contain only lowercase alphabetic characters.

*/
class WordLadder {

public:
    vector<vector<string>> findLadders(string start, string end, vector<string> &dictV) {        
        unordered_set<string> dict(dictV.begin(), dictV.end());
        vector<vector<string>> answer; 
        unordered_map<string, int> distance; // store the distance from start to the current word
        queue<string> q; // FIFO for bfs purpose
        unordered_map<string, vector<string>> parents;
        swap(start, end); // We do this because we construct the path later from end to start 
        distance[start] = 1;
        q.push(start);

        while (!q.empty()) {
            string word = q.front(); 
            q.pop();
            if (word == end) //ensure shortest path
                break;
            for (int i = 0; i < word.size(); i++) {
                for (int j = 0; j < 26; j++) {
                    string newWord = word;
                    newWord[i] = 'a' + j; //trying out from a to z
                    if (dict.find(newWord) != dict.end()) {
                        if (distance.find(newWord) == distance.end()) { // seen for the first time
                            distance[newWord] = distance[word] + 1;
                            q.push(newWord);
                            parents[newWord].push_back(word);
                        } else if (distance[newWord] == distance[word] + 1) {
                            parents[newWord].push_back(word);
                        }
                    }
                }
            }
        }
        if (distance.find(end) == distance.end()) 
            return answer; // not found

        // backtrack and construct all possible paths now that we know possible optimal parents.  
        vector<string> current;
        current.push_back(end);
        constructPaths(end, start, parents, current, answer);
        return answer; 
    }

    void constructPaths(string &start, string &end, unordered_map<string, vector<string>> &parents, vector<string> &current, vector<vector<string> > &answer) {
        if (start == end) {
            answer.push_back(current);
            return;
        }
        for (int i = 0; i < parents[start].size(); i++) {
            current.push_back(parents[start][i]);
            constructPaths(parents[start][i], end, parents, current, answer);
            current.pop_back();
        }
        return;
    }

};



/**  java Implementation 
public class WordLadder {
    
    public ArrayList<ArrayList<String>> findLadders(String start, String end, ArrayList<String> dictionary) {
        HashSet<String> set = new HashSet<>();
        for (String s : dictionary) {
            set.add(s);
        }
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        int level = minHops(start, end, set);
        dfs(level-1, start, end, set, res, new ArrayList<>());
        return res;
    }
    
    private void dfs(int n, String curr, String end, HashSet<String> dict, ArrayList<ArrayList<String>> result, ArrayList<String> path) {
        if (n == 0) {
            if (curr.equals(end)) {
                ArrayList<String> toAdd = new ArrayList<>(path);
                toAdd.add(curr);
                result.add(toAdd);
            }
            return;
        } 

        path.add(curr); // add the path

        for (String next : dict) {
            if (isDifferenceOne(curr, next) && !path.contains(next)) { // ignoring the visited path
               dfs(n - 1, next, end, dict, result, path);
            }
        }
        
        path.remove(path.size() - 1); // drop the path
    }
    
    private int minHops(String start, String end, HashSet<String> dict) {
        int level = 1;
        HashSet<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.add(start);
        visited.add(start);
        
        while (!q.isEmpty()) {
            int n = q.size();
            for (int i = 0; i < n; i++) { // level order
                String curr = q.poll();
                if (curr.equals(end)) {  
                    return level;   // end found at short path
                }

                for (String next : dict) {
                    if (isDifferenceOne(curr, next) && !visited.contains(next)) {
                        q.add(next);
                        visited.add(next);
                    }
                }
            }
            level++;
        }
        return -1;
    }
    
    private boolean isDifferenceOne(String s1, String s2) {
        int count = 0;
        for(int i = 0; i < s1.length(); i++) {
            if(s1.charAt(i) != s2.charAt(i))
                count++;
        }
        return count == 1 ? true : false;
    } 
}
*/
