package Math_Bit;

import java.util.HashMap;

class TwoSum {

    // https://leetcode.com/problems/two-sum/
    public int[] twoSum(final int[] A, int B) {
        int[] result = new int[2];
        int n = A.length;
        int[] res = {};
        if (n < 2) {
            return res;
        }
        HashMap<Integer, Integer> hm = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int rem = B - A[i];
            if (hm.containsKey(rem)) {
                result[0] = i + 1;
                result[1] = hm.get(rem) + 1;
                return result;
            }
            hm.put(A[i], i);
        }
        return res;
    }

    /*
     * If multiple solutions exist, output the one where index2 is minimum. If there
     * are multiple solutions with the minimum index2, choose the one with minimum
     * index1 For Ex: 1, 7, 11, 15, 5, 4, 2 target=9
     * 
     * @here return (5 , 4) instead of (7,2)
     */
    // https://www.interviewbit.com/problems/2-sum/
    public int[] twoSum2(final int[] A, int B) {
        int[] result = new int[2];
        int n = A.length;
        int[] res = {};
        if (n < 2) {
            return res;
        }
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int rem = B - A[i];

            if (!hm.containsKey(rem)) {
                if (!hm.containsKey(A[i])) {
                    hm.put(A[i], i);
                }
            } else {
                result[0] = hm.get(rem) + 1;
                result[1] = i + 1;
                return result;
            }
        }
        return res;
    }
}
