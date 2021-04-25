package DynamicProgramming;

import java.util.Stack;

public class MaxRectangleBinaryMatrix {

    public int maximalRectangle(int[][] A) {
        int m = A.length;
        int n = A[0].length;

        int maxArea = Integer.MIN_VALUE;
        int[] temp = new int[n];

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {
                if (A[i][j] == 0) {
                    temp[j] = 0;
                } else {
                    temp[j] += A[i][j];
                }
            }
            maxArea = Math.max(maxArea, getHistogramArea(temp));
        }
        return maxArea;
    }

    private int getHistogramArea(int[] Arr) {
        Stack<Integer> st = new Stack<>();
        int area = 0;
        int i = 0;
        while (i < Arr.length) {
            if (st.isEmpty() || Arr[st.peek()] <= Arr[i]) {
                st.push(i);
                i++;
            } else {
                int popIndex = st.pop();
                if (st.isEmpty()) {
                    area = Math.max(area, Arr[popIndex] * i);
                } else {
                    area = Math.max(area, Arr[popIndex] * (i - 1 - st.peek()));
                }
            }
        }

        while (!st.isEmpty()) {
            int popIndex = st.pop();
            if (st.isEmpty()) {
                area = Math.max(area, Arr[popIndex] * i);
            } else {
                area = Math.max(area, Arr[popIndex] * (i - 1 - popIndex));
            }
        }
        return area;
    }
}
