package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Kadane Algorithm
// https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
class MaxContigousSubArraySum {

    // Time Complexity: 0(n)
    public static void maxSubArray(final List<Integer> A) {

        int max_so_far = Integer.MIN_VALUE;
        int globalStart = 0, end = 0, sum_till_here = 0, intermediateStart = 0;

        for (int i = 0; i < A.size(); i++) {
            sum_till_here += A.get(i);
            if (max_so_far < sum_till_here) {
                max_so_far = sum_till_here;
                globalStart = intermediateStart;
                end = i;
            }
            if (sum_till_here < 0) {
                sum_till_here = 0;
                intermediateStart = i + 1;
            }
        }
        System.out.println("Maximum contiguous sum is " + max_so_far);
        System.out.println("Starting Index :" + globalStart);
        System.out.println("Ending Index : " + end);
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(-2, -3, 4, -1, -2, 1, 5, -3);
        maxSubArray(list);
    }

    // https://www.interviewbit.com/problems/max-non-negative-subarray/
    /*
     * Maximum sub-array is defined in terms of the sum of the elements in the
     * sub-array. Find and return the required subarray.If there is a tie, then
     * compare with segment's length and return segment which has maximum length. If
     * there is still a tie, then return the segment with minimum starting index.
     */
    public ArrayList<Integer> maxset(ArrayList<Integer> A) {

        ArrayList<Integer> result = new ArrayList<Integer>();
        int left = 0, aleft = -1, aright = -1;
        long sum = 0, max = 0;
        for (int i = 0; i < A.size(); i++) {
            if (A.get(i) >= 0) {
                sum += A.get(i);
                if (sum > max || ((sum == max) && (i - left > aright - aleft))) {
                    max = sum;
                    aleft = left;
                    aright = i;
                }
            } else {
                sum = 0;
                left = i + 1;
            }
        }
        if (aleft == -1) {
            return result;
        }
        for (int i = aleft; i <= aright; i++) {
            result.add(A.get(i));
        }
        return result;
    }
}