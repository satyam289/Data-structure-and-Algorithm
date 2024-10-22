package leetcode.medium;

import java.util.*;

public class InsertInterval {
    static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    public static void main(String[] args) {
        ArrayList<Interval> input = new ArrayList<>();
        input.add(new Interval(1, 2));
        input.add(new Interval(3, 5));
        input.add(new Interval(6, 7));
        input.add(new Interval(8, 10));
        input.add(new Interval(12, 16));
        insertInterval(input, new Interval(4, 9));
    }

    public static ArrayList<Interval> insertInterval(ArrayList<Interval> intervals, Interval newInterval) {

        ArrayList<Interval> result = new ArrayList<>();
        for (Interval interval : intervals) {

            if (interval.end < newInterval.start) {
                result.add(interval);
            } else if (interval.start > newInterval.end) {
                result.add(newInterval);
                newInterval = interval;
            } else {
                // (interval.end >= newInterval.start || interval.start <= newInterval.end)
                newInterval = new Interval(
                        Math.min(interval.start, newInterval.start),
                        Math.max(newInterval.end, interval.end));
            }
        }
        result.add(newInterval);

        for (Interval interval : result) {
            System.out.println(interval.start + "," + interval.end + "  /n");
        }
        return result;
    }
}
