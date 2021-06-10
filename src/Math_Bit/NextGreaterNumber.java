package Math_Bit;

import java.util.Arrays;
import java.util.Stack;

public class NextGreaterNumber {

    public static void main(String[] args) {
        int[] arr = { 11, 13, 21, 3 };
        findNextGreater(arr);
        findNextGreaterOptimised(arr);
    }

    // 0(n2)
    public static void findNextGreater(int[] arr) {

        int[] temp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (arr[j] > arr[i]) {
                    temp[i] = arr[j];
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(temp));
    }

    // 0(n)
    public static void findNextGreaterOptimised(int[] arr) {

        Stack<Integer> stack = new Stack<>();
        stack.push(arr[0]);
        for (int i = 1; i < arr.length; i++) {

            if (arr[i] < stack.peek()) {
                stack.push(arr[i]);
            } else {
                while ((!stack.isEmpty()) && (arr[i] >= stack.peek())) {
                    System.out.println(stack.pop() + " ->" + arr[i]);
                }
                stack.push(arr[i]);
            }
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop() + "-> 0");
        }
    }
}
