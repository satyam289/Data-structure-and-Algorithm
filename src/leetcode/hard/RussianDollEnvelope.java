package leetcode.hard;

import java.util.*;

// Given (width, height) of envelopes, maximum number of envelopes fits one inside other for russian doll, Fit criteria: Both (h1, w1) > (h2, w2)
public class RussianDollEnvelope {

    public static void main(String[] args) {
        int[][] envelopes = { { 5, 4 }, { 6, 4 }, { 6, 7 }, { 2, 3 } };
        int res = maxEnvelopes(envelopes);
        System.out.println(res);
        // Output = 3 ([2,3], [5,4], [6,7])
    }

    public static int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 ||
                envelopes[0] == null || envelopes[0].length != 2) {
            return 0;
        }
        Arrays.sort(envelopes, (arr1, arr2) -> {
            if (arr1[0] == arr2[0]) {
                return arr2[1] - arr1[1];
            } else {
                return arr1[0] - arr2[0];
            }
        });
        int[] dp = new int[envelopes.length];
        int len = 0;

        for (int[] envelope : envelopes) {
            int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
            if (index < 0) {
                index = -(index + 1);
                dp[index] = envelope[1];
            }
            if (index == len) {
                len++;
            }
        }
        return len;
    }
}
