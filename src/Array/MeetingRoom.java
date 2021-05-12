package Array;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

//https://www.interviewbit.com/problems/meeting-rooms/
/*
Given an 2D integer array A of size N x 2 denoting time intervals of different meetings.
Where:
A[i][0] = start time of the ith meeting.
A[i][1] = end time of the ith meeting.

Find the minimum number of conference rooms required so that all meetings can be done.

C++ Solution: 
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

public class MeetingRoom {

    // Time Complexity: NlogN
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

    //https://leetcode.com/accounts/login/?next=/problems/meeting-rooms-ii/

    private static class Interval{
        int start;
        int end;
        //Inteval(int s, int e){ start = s; end = e;}
    }

    public int minMeetingRooms(Interval [] intervals){
        if(intervals == null || interval.length == 0){
            return 0;
        }
        Arrays.sort(intervals, (a, b) -> a.start - b.start); // sort by start time
        PriorityQueue<Interval> minHeap = new PriorityQueue<>((a, b) -> a.end - b.end);
        minHeap.add(intervals[0]);

        for(int i = 1; i< intervals.length; i++){
            Interval current = intervals[i];
            Interval earliest = minHeap.remove();
            if(current.start >= earliest.end){ // earlier task can be completed before the current task begin, so removing earlier from min heap, pushing current one.
                minHeap.add(current);
            }else{
                minHeap.add(current); // earlier task can't be completed, need extra meeting room, so maintaining both in min heap
                minHeap.add(earliest);
            }
        }
        return minHeap.size();
    }
}
