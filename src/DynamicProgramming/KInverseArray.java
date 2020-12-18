package DynamicProgramming;

import java.util.Arrays;

//https://leetcode.com/articles/k-inverse-pairs-array/

public class KInverseArray {

    public static void main(String[] args) {
        kInverseArraySimple(3, 1);
        dynamicApporach(3, 1);
    }

    public static void kInverseArraySimple(int n, int k) {
        int[] input = new int[n];
        for (int i = 1; i <= n; i++) {
            input[i - 1] = i;
        }
        KInverseArraySimple(input, 0, k);
    }

    public static void KInverseArraySimple(int[] arr, int start, int k) {
        if (start == arr.length) {
            int[] result = getCountAnagram(arr, k);
            if (result.length > 0)
                System.out.print(Arrays.toString(result));
            return;
        }
        for (int i = start; i < arr.length; i++) {
            swap(arr, start, i);
            KInverseArraySimple(arr, start + 1, k);
            swap(arr, i, start);
        }
    }

    private static int[] getCountAnagram(int[] arr, int k) {
        int count = 0;
        //System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (i < j && arr[j] < arr[i])
                    count++;
            }
        }
        if (count == k)
            return arr;
        return new int[0];
    }

    private static void swap(int[] arr, int start, int i) {
        int temp = arr[i];
        arr[i] = arr[start];
        arr[start] = temp;
    }

    private static void dynamicApporach(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                if (j == 0)
                    dp[i][j] = 1;
                else {
                    for (int p = 0; p <= Math.min(j, i - 1); p++)
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - p]) % 1000000007;
                }
            }
            System.out.println(Arrays.toString(dp[i]));
        }
        System.out.println(dp[n][k]);
    }
}
