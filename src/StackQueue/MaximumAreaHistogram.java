package StackQueue;

import java.util.Deque;
import java.util.LinkedList;

public class MaximumAreaHistogram {


    public static void main(String[] args) {

        int[] arr = {2, 3, 6, 1, 3};
        findMaxArea(arr);
    }

    public static void findMaxArea(int[] a) {
        //Deque<Integer> d2=new ArrayDeque();   //(double ended Queue, array implementation)
        Deque<Integer> stack = new LinkedList<Integer>();  //double ended Queue, linkedlist implementation
        int max = 0, area = 0;
        int i;
        for (i = 0; i < a.length; ) {

            if (stack.isEmpty() || a[i] > a[stack.peek()]) {    //keep adding until we bigger value(bar) ,break when we find smaller from previous
                stack.push(i);
                i++;
            } else {
                int index = stack.pop();
                if (stack.isEmpty())

                    area = a[index] * (i);
                else
                    area = a[index] * (i - a[stack.peek()] - 1);
                if (area > max)
                    max = area;
            }

        }
        while (!stack.isEmpty()) {

            int index = stack.pop();
            if (stack.isEmpty())
                max = a[index] * (i);
            else
                max = a[index] * (i - a[stack.peek()] - 1);
            if (area > max)
                max = area;
        }
        System.out.println("Max Area : " + max);
    }
}
