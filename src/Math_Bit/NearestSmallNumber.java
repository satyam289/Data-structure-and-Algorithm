package Math_Bit;

import java.util.Arrays;
import java.util.Stack;

public class NearestSmallNumber {

    public static void main(String[] args) {
        int[] arr = { 11, -8, 16, 24, -2, 3 };
        nearestSmall(arr);
        nearestSmallOptimised(arr);
    }

    // Time Complexity : 0(n2)
    public static void nearestSmall(int[] arr) {

        int[] result = new int[arr.length];
        Arrays.fill(result, -1);

        for (int i = 1; i < arr.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    min = arr[j];
                    result[i] = min;
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(result));
    }

    // Time Complexity : 0(n)
    public static void nearestSmallOptimised(int[] arr) {

        int[] result = new int[arr.length];
        Arrays.fill(result, -1);

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && (stack.peek() > arr[i])) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                result[i] = stack.peek();
            }
            stack.push(arr[i]);
        }
        System.out.println(Arrays.toString(result));
    }
}