package Array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

//https://www.interviewbit.com/problems/longest-consecutive-sequence/
public class LongestConsecutiveSeq {

    // Brute Force: 0(nlogn)
    public static int longestConsecutive(final int[] A) {
        if (A.length == 0 || A.length == 1) {
            return A.length;
        }
        Arrays.sort(A);
        int max = 0;
        int count = 1;
        for (int i = 0; i < A.length - 1; i++) {
            if (A[i] == A[i + 1]) {
                continue;
            }
            if (A[i] + 1 == A[i + 1]) {
                count++;
            } else {
                count = 1;
            }
            max = Math.max(max, count);
        }
        return max;
    }

    // Time Complexity :0(n)
    public static int longestConsecutive2(final int[] A) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int a : A) {
            int left = map.containsKey(a - 1) ? map.get(a - 1) : 0;
            int right = map.containsKey(a + 1) ? map.get(a + 1) : 0;
            int sum = left + right + 1;
            map.put(a, sum);
            max = Math.max(max, sum);
            // Updating extreme boundary of subsequence
            map.put(a - left, sum);
            map.put(a + right, sum);
        }
        return max;
    }

    public static int longestConsecutive3(final int[] A) {
        HashSet<Integer> visited = new HashSet<>();
        int max = 0;
        for (int a : A) {
            int count = 1;
            int decre = a - 1;
            while (visited.contains(decre)) {
                count++;
                decre--;
            }
            int incre = a + 1;
            while (visited.contains(incre)) {
                count++;
                incre++;
            }
            max = Math.max(max, count);
            visited.add(a);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] input = { 100, 4, 200, 1, 3, 2 };
        System.out.print(longestConsecutive(input));
        System.out.print(longestConsecutive2(input));
        System.out.print(longestConsecutive3(input));
    }
}
