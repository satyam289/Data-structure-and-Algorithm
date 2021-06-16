package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

//https://www.interviewbit.com/problems/merge-overlapping-intervals/
//https://www.geeksforgeeks.org/merging-intervals/
public class MergeInterval {

    public static class Interval {
        int start;
        int end;

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    // Brute Force O(n^2): Start from the one interval with all other intervals for
    // overlapping, if it overlaps, then remove the other interval and merge into
    // the first interval.
    public static ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        if (intervals == null || intervals.size() == 0 || intervals.size() == 1) {
            return intervals;
        }
        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval int1, Interval int2) {
                return int1.start - int2.start;
            }
        });
        Stack<Interval> st = new Stack<>();
        st.push(intervals.get(0));

        for (int i = 1; i < intervals.size(); i++) {
            Interval past = st.peek();
            if (past.end >= intervals.get(i).start) {
                Interval newInterval = new Interval(past.start, Math.max(past.end, intervals.get(i).end));
                st.pop();
                st.push(newInterval);
            } else {
                st.push(intervals.get(i));
            }
        }
        ArrayList<Interval> result = new ArrayList<>();
        while (!st.isEmpty()) {
            Interval intval = st.pop();
            result.add(intval);
        }
        Collections.reverse(result);
        return result;
    }

    // Space optimisied (In-place)
    public static void mergeIntervals(Interval arr[]) {
        Arrays.sort(arr, new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                return i2.start - i1.start;
            }
        });

        int index = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[index].end >= arr[i].start) {
                // Merge previous and current Intervals
                arr[index].end = Math.max(arr[index].end, arr[i].end);
                arr[index].start = Math.min(arr[index].start, arr[i].start);
            } else {
                index++;
                arr[index] = arr[i];
            }
        }
        Arrays.fill(arr, index, arr.length, -1);
    }

    // https://www.interviewbit.com/problems/merge-intervals/
    // Brute Force
    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {

        ArrayList<Interval> result = new ArrayList<Interval>();
        if (intervals == null || intervals.size() == 0) {
            result.add(newInterval);
            return result;
        }

        intervals.add(newInterval);
        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval int1, Interval int2) {
                return int1.start - int2.start;
            }
        });

        int index = 0;
        result.add(intervals.get(0));
        for (int i = 1; i < intervals.size(); i++) {
            if (result.get(index).end > intervals.get(i).start) {
                // We know start index th start always shorted(sorted array), only check end
                // here
                result.get(index).end = Math.max(result.get(index).end, intervals.get(i).end);
            } else {
                index++;
                result.add(intervals.get(i));
            }
        }
        return result;
    }

    public ArrayList<Interval> insert2(ArrayList<Interval> intervals, Interval newInterval) {
        Interval current = newInterval;
        int i = 0;
        while (i < intervals.size()) {
            Interval in = intervals.get(i);
            if (in.end < current.start) {
                i++;
            } else if (in.start > current.end) {
                intervals.add(i, current);
                break;
            } else {
                current.start = Math.min(in.start, current.start);
                current.end = Math.max(in.end, current.end);
                intervals.remove(i);
            }
        }
        if (i == intervals.size()) {
            intervals.add(current);
        }
        return intervals;
    }

    // best
    public ArrayList<Interval> insert3(ArrayList<Interval> intervals, Interval newInterval) {

        Comparator<Interval> inRangeCompA = (a, b) -> {
            return (a.end < b.start) ? -1 : ((a.start > b.end) ? 1 : 0);
        };
        Interval newStart = new Interval(newInterval.start, newInterval.start);
        int i = Collections.binarySearch(intervals, newStart, inRangeCompA);
        if (i < 0) { // Not found
            i = -(i + 1); // insertion point
            intervals.add(i, newStart); // insert at ith index
        }
        Interval newEnd = new Interval(newInterval.end, newInterval.end);
        int j = Collections.binarySearch(intervals, newEnd, inRangeCompA);
        if (j < 0) { // Not found
            j = -(j + 1); // insertion point
            intervals.add(j, newEnd);
        }
        if (i != j) {
            Interval startInterval = intervals.get(i); // first found overlapping or newly added
            startInterval.end = intervals.get(j).end; // update end value
            intervals.set(i, startInterval); // replace with start index
            intervals.subList(i + 1, j + 1).clear(); // delete all b/w i+1 and j
        }
        return intervals;
    }
}
