package DynamicProgramming;

import java.util.Arrays;

//https://www.geeksforgeeks.org/maximum-sum-rectangle-in-a-2d-matrix-dp-27/
public class MaximumRectangleSum {

    private static class Result {
        int maxSum;
        int lowbound;
        int upbound;
        int leftbound;
        int rightbound;

        Result(int maxSum, int lowbound, int upbound, int leftbound, int rightbound) {
            this.maxSum = maxSum;
            this.lowbound = lowbound;
            this.upbound = upbound;
            this.leftbound = leftbound;
            this.rightbound = rightbound;
        }
    }

    public static Result maxSum(int[][] input) {
        int rows = input.length;
        int column = input[0].length;
        int[] temp = new int[rows];
        Result result = null;

        for (int left = 0; left < column; left++) {

            Arrays.fill(temp, 0);
            for (int right = left; right < column; right++) {
                for (int i = 0; i < rows; i++) {
                    temp[i] += input[i][right];
                }
                Result kresult = Kadane(temp);

                if (result == null || kresult.maxSum > result.maxSum) {
                    result = kresult;
                    result.leftbound = left;
                    result.rightbound = right;
                }
            }
        }
        return result;
    }

    private static Result Kadane(int[] temp) {
        int localStartpointer = 0, max_so_far = 0;
        int globalStartpointer = 0, globalEndpointer = 0, globalMax = 0;

        for (int i = 0; i < temp.length; i++) {
            max_so_far += temp[i];
            if (max_so_far < 0) {
                max_so_far = 0;
                localStartpointer = i + 1;
            }
            if (max_so_far > globalMax) {
                globalStartpointer = localStartpointer;
                globalEndpointer = i;
                globalMax = max_so_far;
            }
        }
        return new Result(globalMax, globalStartpointer, globalEndpointer, -1, -1);
    }

    public static void main(String[] args) {
        int input[][] = new int[][] { { 1, 2, -1, -4, -20 }, { -8, -3, 4, 2, 1 }, { 3, 8, 10, 1, 3 },
                { -4, -1, 1, 7, -6 } };
        Result res = maxSum(input);
        System.out.println("Maximum sum " + res.maxSum + " Leftbound: " + res.leftbound + " Rightbound: "
                + res.rightbound + " lowbound: " + res.lowbound + " upbound: " + res.upbound);
    }
}