package Array;

import java.util.Arrays;
import java.util.List;

//https://www.interviewbit.com/problems/meeting-rooms/

public class MeetingRoom {

    public int solve(List<List<Integer>> A) {
        int n = A.size();
        int[] first = new int[n];
        int[] second = new int[n];
        int k = 0;
        for (int i = 0; i < n; i++) {
            first[k] = A.get(i).get(0);
            second[k] = A.get(i).get(1);
            k++;
        }
        Arrays.sort(first);
        Arrays.sort(second);
        k = 0;
        for (int i = 0; i < n; i++) {
            if (first[i] >= second[k]) {
                k++;
            }
        }
        return n - k;
    }

    /*
    int Solution::solve(vector<vector<int> > &A) {
        map<int, int> delta;
        for (const vector<int> &v: A) {
            delta[v[0]]++;
            delta[v[1]]--;
        }
        int concurrencyCount = 0;
        int maxConcurrencyCount = 0;
        for (const pair<int, int> &kv: delta) {
            concurrencyCount += kv.second;
            maxConcurrencyCount = max(maxConcurrencyCount, concurrencyCount);
        }
        return maxConcurrencyCount;
    }
    */
}
