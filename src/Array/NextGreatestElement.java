package Array;

// https://www.geeksforgeeks.org/replace-every-element-with-the-greatest-on-right-side/

import java.util.Arrays;

public class NextGreatestElement {

    public static void main(String[] args) {
        int[] arr = {16, 17, 4, 3, 5, 2};
        nextGreatestElement(arr, arr.length);
    }

    // brute : Look for all right element for every element 0(n2)
    // tricky : Track the maximum by starting right 0(n)
    public static void nextGreatestElement(int[] arr, int n) {

        int[] output = new int[n];
        output[n - 1] = -1;
        int maxtillhere = arr[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            output[i] = maxtillhere;
            if (maxtillhere < arr[i])
                maxtillhere = arr[i];
        }
        System.out.println(Arrays.toString(output));
    }
}
