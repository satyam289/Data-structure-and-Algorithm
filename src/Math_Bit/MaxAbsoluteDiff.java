package Math_Bit;

import java.util.ArrayList;

//https://www.interviewbit.com/problems/maximum-absolute-difference/
public class MaxAbsoluteDiff {

    public int maxArr(int[] A) {
        int n = A.length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                max = Math.max(max, getAbsolute(A[i], A[j], i, j));
            }
        }
        return max;
    }

    private int getAbsolute(int a, int b, int c, int d) {
        return Math.abs(a - b) + Math.abs(c - d);
    }

    public int maxArr2(ArrayList<Integer> A) {

        int maxSums = A.get(0);
        int minSums = A.get(0);
        for (int i = 0; i < A.size(); i++) {
            maxSums = Math.max(maxSums, A.get(i) + i);
            minSums = Math.min(minSums, A.get(i) + i);
        }
        int sumsRes = maxSums - minSums;

        int maxDif = A.get(0);
        int minDif = A.get(0);
        for (int i = 0; i < A.size(); i++) {
            maxDif = Math.max(maxDif, A.get(i) - i);
            minDif = Math.min(minDif, A.get(i) - i);
        }
        int difsRes = maxDif - minDif;
        return Math.max(difsRes, sumsRes);
    }

    // optimal
    public int maxArr3(ArrayList<Integer> A) {
        int max1 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE;
        int max2 = Integer.MIN_VALUE;
        int min2 = Integer.MAX_VALUE;
        for (int i = 0; i < A.size(); i++) {
            max1 = Math.max(max1, A.get(i) + i);
            min1 = Math.min(min1, A.get(i) + i);
            max2 = Math.max(max2, A.get(i) - i);
            min2 = Math.min(min2, A.get(i) - i);
        }
        return Math.max((max1 - min1), (max2 - min2));
    }
}
