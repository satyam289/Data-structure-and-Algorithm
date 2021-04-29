#include <vector>
#include <unordered_map>
#include <queue>

using namespace std;

class TaskSchedular{

    public:
        int leastInteger(vector<char> &tasks, int n)
        {
            unordered_map<char, int> counts;
            for (char c : tasks)
                counts[c]++;

            priority_queue<int> pq;
            for (auto c : counts){
                pq.push(c.second);
            }

            int result = 0;
            while (!pq.empty()){
                vector<int> tmp;
                for (int i = 0; i < n + 1; i++){
                    if (!pq.empty()){
                        tmp.push_back(pq.top() - 1);
                        pq.pop();
                    }
                }
                for (auto t : tmp){
                    if (t)
                        pq.push(t);
                }
                result += (pq.empty() ? tmp.size() : n + 1);
            }
            return result;
        }
};

/*
package Miscellaneous;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

// https://leetcode.com/problems/task-scheduler/
public class TaskSchedular {

    public static int leastInterval(char[] tasks, int n) {

        HashMap<Character, Integer> map = new HashMap<>();
        for (char task : tasks) {
            map.put(task, map.getOrDefault(task, 0) + 1);
        }
        // PriorityQueue<Integer> q = new PriorityQueue<>(tasks.length, Collections.reverseOrder());
        PriorityQueue<Integer> maxheap = new PriorityQueue<>((a, b) -> b - a);
        maxheap.addAll(map.values());

        int cycle = 0;
        while (!maxheap.isEmpty()) {

            ArrayList<Integer> temp = new ArrayList<>();
            for (int i = 0; i < n + 1; i++) {
                if (!maxheap.isEmpty()) {
                    temp.add(maxheap.remove() - 1);
                }
            }
            for (int i : temp) {
                if (i > 0)
                    maxheap.add(i);
            }
            cycle += maxheap.isEmpty() ? temp.size() : n + 1;
        }
        return cycle;
    }

    public static void main(String[] args) {
        char[] tasks = { 'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        int n = 2;
        System.out.println(leastInterval(tasks, n));
    }
}
*/