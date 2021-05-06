package Array;

import java.util.Arrays;

//https://www.geeksforgeeks.org/longest-bitonic-subsequence-dp-15/
class BiotonicSubSequence{

    public static int longestBiotonic(int[] arr) {
        int n = arr.length;
        int i, j;
        int[] lis = new int[n]; // increasing
        Arrays.fill(lis, 1);

        for (i = 1; i < n; i++) {
            for (j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    lis[i] = Math.max(lis[i], lis[j] + 1);
                }
            }
        }
        int[] lds = new int[n]; // decreasing
        Arrays.fill(lds, 1);
        for (i = n - 2; i >= 0; i--) {
            for (j = n - 1; j > i; j--) {
                if (arr[j] < arr[i]) {
                    lds[i] = Math.max(lds[i], lds[j] + 1);
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for (i = 1; i < n; i++)
            if (lis[i] + lds[i] - 1 > max)
                max = lis[i] + lds[i] - 1;

        return max;
    }

    public static void main (String[] args)
    {
        int arr[] = {2, -1, 4, 3, 5, -1, 3, 2};
        // lis = {1, 1, 2, 2, 3, 1, 2, 2}
        // lds = {2, 1, 3, 2, 3, 1, 2, 1}
        System.out.println("Length of LBS is "+ longestBiotonic( arr));
    }
}