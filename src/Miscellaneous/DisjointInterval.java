package Miscellaneous;

import java.util.Arrays;
import java.util.Comparator;

//https://www.interviewbit.com/problems/disjoint-intervals/
class DisjointInterval {
    public int solve1(int[][] A) {
        int max = 0;
        // sort by start time
        Arrays.sort(A, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return a[1] - b[1];
                }
                return a[0] - b[0];
            }
        });

        int[] DisjntCnt = new int[A.length];
        Arrays.fill(DisjntCnt, 1);

        for (int i = 1; i < A.length; i++) {
            for (int j = 0; j < i; j++) {
                // Non operlapping condition
                if (A[i][0] > A[j][1] && DisjntCnt[j] + 1 > DisjntCnt[i]) {
                    DisjntCnt[i] = DisjntCnt[j] + 1;
                }
            }
            max = Math.max(max, DisjntCnt[i]);
        }
        return max;
    }

    // Optimal solution
    public int solve2(int[][] A) {
        int count = 1;
        // sort with End time
        Arrays.sort(A, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[1] == b[1]) {
                    return a[0] - b[0];
                }
                return a[1] - b[1];
            }
        });

        int preEndtime = A[0][1];
        for (int i = 1; i < A.length; i++) {
            if (A[i][0] > preEndtime) { // next_StartTime > pre_EndTime
                preEndtime = A[i][1];
                count++;
            }
        }
        return count;
    }
}