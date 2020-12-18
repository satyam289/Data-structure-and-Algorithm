package DynamicProgramming;

import java.util.Arrays;

public class BitonicSubsequence {

    public static void main(String[] args) {
        int[] arr = {1, 11, 2, 10, 4, 5, 2, 1};
        findLongest(arr);
    }

    public static void findLongest(int[] arr) {
        int[] increasing = LISIncreasingOrder(arr);
        int[] decreasing = LISDecreasingOrder(arr);
        int maxval = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((increasing[i] + decreasing[i] - 1) > maxval)
                maxval = increasing[i] + decreasing[i] - 1;
        }
        System.out.println(maxval);

    }

    public static int[] LISIncreasingOrder(int[] arr) {

        int[] temp = new int[arr.length];
        Arrays.fill(temp, 1);
        for (int i = 0; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] > arr[j])
                    temp[i] = Math.max(temp[j] + 1, temp[i]);
            }
        }
        System.out.println(Arrays.toString(temp));
        return temp;
    }

    public static int[] LISDecreasingOrder(int[] arr) {

        int[] temp = new int[arr.length];
        Arrays.fill(temp, 1);
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[i] > arr[j])
                    temp[i] = Math.max(temp[j] + 1, temp[i]);
            }
        }
        System.out.println(Arrays.toString(temp));
        return temp;
    }
}

