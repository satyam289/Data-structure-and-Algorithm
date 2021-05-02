package StackQueue;

import java.util.Deque;
import java.util.LinkedList;

// https://www.interviewbit.com/problems/largest-rectangle-in-histogram/
public class MaximumAreaHistogram {

    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 6, 2, 3};
        findMaxArea(arr);
    }

    public static void findMaxArea(int[] a) {
        // Deque<Integer> d2=new ArrayDeque(); // Double ended Queue (array)
        Deque<Integer> st = new LinkedList<>(); // Double ended Queue (Linkedlist)
        int max_area = 0, area = 0;
        int i;
        for (i = 0; i < a.length;) {
            if (st.isEmpty() || a[i] >= a[st.peek()]) {
                st.push(i);
                i++;
            } else {
                int top = st.pop();
                if (st.isEmpty()) {
                    area = a[top] * i;
                } else {
                    area = a[top] * (i - st.peek() - 1);
                }
                max_area = Math.max(max_area, area);
            }
        }

        while (!st.isEmpty()) {
            int top = st.pop();
            if (st.isEmpty()) {
                area = a[top] * i;
            } else {
                area = a[top] * (i - st.peek() - 1);
            }
            max_area = Math.max(max_area, area);
        }
        System.out.println("Max Area : " + max_area);
    }
}
