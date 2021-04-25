package Array;

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
}