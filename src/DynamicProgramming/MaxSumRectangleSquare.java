package DynamicProgramming;

import java.util.Arrays;
import java.util.Stack;

public class MaxSumRectangleSquare {

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

    // https://www.geeksforgeeks.org/maximum-sum-rectangle-in-a-2d-matrix-dp-27/
    public static Result maxRectangleSum(int[][] input) {
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
                Result kresult = kadane(temp);

                if (result == null || kresult.maxSum > result.maxSum) {
                    result = kresult;
                    result.leftbound = left;
                    result.rightbound = right;
                }
            }
        }
        return result;
    }

    private static Result kadane(int[] temp) {
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

    private static class Cell {
        int x;
        int y;

        Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // https://www.geeksforgeeks.org/given-matrix-o-x-find-largest-subsquare-surrounded-x/
    public static int findXSquare(char[][] input) {

        int row = input.length;
        int col = input[0].length;
        Cell[][] countXTable = new Cell[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (input[i][j] == 'X') {
                    int xcountFromTop = i == 0 ? 1 : countXTable[i - 1][j].x + 1;
                    int xCountFromLeft = j == 0 ? 1 : countXTable[i][j - 1].y + 1;
                    countXTable[i][j] = new Cell(xcountFromTop, xCountFromLeft);
                } else {
                    countXTable[i][j] = new Cell(0, 0);
                }
            }
        }
        int maxArea = 1;
        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                int min = Math.min(countXTable[i][j].x, countXTable[i][j].y);
                for (int k = min; k > 1; k--) {
                    if (countXTable[i][j - k + 1].x >= k && countXTable[i - k + 1][j].y >= k) {
                        maxArea = Math.max(maxArea, k);
                    }
                }
            }
        }
        return maxArea;
    }

    // https://www.geeksforgeeks.org/maximum-size-sub-matrix-with-all-1s-in-a-binary-matrix/
    public static int maxSubSquareOne(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] table = new int[row + 1][col + 1];
        int max = 0;
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (matrix[i - 1][j - 1] == 1) {
                    table[i][j] = 1 + Math.min(Math.min(table[i - 1][j], table[i][j - 1]), table[i - 1][j - 1]);
                    max = Math.max(max, table[i][j]);
                } else {
                    table[i][j] = 0;
                }
            }
        }
        return max;
    }

    // https://www.geeksforgeeks.org/maximum-size-rectangle-binary-sub-matrix-1s/
    public static int maxRectangleOne(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        int[] arr = new int[col];
        int maxArea = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    arr[j] = 0;
                } else {
                    arr[j] += matrix[i][j];
                }
            }
            maxArea = Math.max(maxArea, getHistogramArea(arr));
        }
        return maxArea;
    }

    private static int getHistogramArea(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int area = 0;
        int i = 0;
        while (i < arr.length) {
            if (st.isEmpty() || arr[st.peek()] <= arr[i]) {
                st.push(i);
                i++;
            } else {
                int popIndex = st.pop();
                if (st.isEmpty()) {
                    area = Math.max(area, arr[popIndex] * i);
                } else {
                    area = Math.max(area, arr[popIndex] * (i - 1 - st.peek()));
                }
            }
        }

        while (!st.isEmpty()) {
            int popIndex = st.pop();
            if (st.isEmpty()) {
                area = Math.max(area, arr[popIndex] * i);
            } else {
                area = Math.max(area, arr[popIndex] * (i - 1 - st.peek()));
            }
        }
        return area;
    }

    public static void main(String[] args) {
        int input[][] = new int[][] { { 1, 2, -1, -4, -20 }, { -8, -3, 4, 2, 1 }, { 3, 8, 10, 1, 3 },
                { -4, -1, 1, 7, -6 } };
        Result res = maxRectangleSum(input);
        System.out.println("Maximum sum : " + res.maxSum + " Leftbound: " + res.leftbound + " Rightbound: "
                + res.rightbound + " lowbound: " + res.lowbound + " upbound: " + res.upbound);

        char[][] input2 = { { '0', '0', '0', '0', 'X' }, { 'X', '0', 'X', 'X', 'X' }, { 'X', '0', 'X', '0', 'X' },
                { 'X', 'X', 'X', 'X', 'X' }, { '0', '0', 'X', 'X', 'X' } };
        System.out.println("The max X boundary sub square of size: " + findXSquare(input2));

        int input3[][] = { { 0, 1, 1, 0, 1 }, { 1, 1, 0, 1, 0 }, { 0, 1, 1, 1, 0 }, { 1, 1, 1, 1, 0 },
                { 1, 1, 1, 1, 1 }, { 0, 0, 0, 0, 0 } };
        System.out.println("The max all 1s sub square of size: " + maxSubSquareOne(input3));

        int input4[][] = { { 1, 0, 0, 1, 1, 1 }, { 1, 0, 1, 1, 0, 1 }, { 0, 1, 1, 1, 1, 1 }, { 0, 0, 1, 1, 1, 1 } };
        System.out.print("The max all 1s sub rectangle of size " + maxRectangleOne(input4));
    }
}