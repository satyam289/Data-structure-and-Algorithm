package DynamicProgramming;

import java.util.Arrays;

public class LongestIncreasingSubseqence {

    public static void main(String[] args) {
        int[] input = { 10, 22, 9, 33, 21, 50, 41, 60 };
        findLIS(input);
        LisOptimised(input);
    }
    
    //Time Complexity : 0(n2)
    public static void findLIS(int[] arr) {

        int[] temp = new int[arr.length];
        int[] seqIndex = new int[arr.length];  // storing the parent index (jump)
        Arrays.fill(temp, 1);

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j--) {

                if (arr[i] > arr[j] && temp[j] + 1 > temp[i]) {
                    temp[i] = temp[j] + 1;
                    seqIndex[i] = j;
                    max = Math.max(max, temp[i]);
                }
            }
        }
        int index = -1;
        for(int i=0; i<arr.length; i++){
            if(max == temp[i]){
                index = i;
            }
        }
        System.out.println("The maximum length subsequence : " + max);
        //printing the sequence
        while (index != 0) {
            System.out.print(arr[index] + " ");
            index = seqIndex[index];
        }
        System.out.println(arr[index]);
    }
   
    //Time Complexity: 0(nlogn)
    public static void LisOptimised(int[] input) {
        int n = input.length;
        int[] T = new int[n]; // Intermediate Result
        int[] R = new int[n]; // result
        for (int i = 0; i < n; i++) {
            R[i] = -1;
        }
        T[0] = 0;
        int len = 0;
        for (int i = 1; i < n; i++) {
            int val = input[i];
            if (val < input[T[0]]) {
                T[0] = i;
            } else if (val > input[T[len]]) {
                len++;
                T[len] = i;
                R[i] = T[len - 1];
            } else {
                int index = getCeiling(input, len, val);
                T[index] = i;
                R[i] = T[index-1];
            }
        }
        System.out.println("The length of Subsequence " + (len + 1));
        int index = T[len];
        while (index != -1) {
            System.out.print(input[index] + " ");
            index = R[index];
        }
    }

    private static int getCeiling(int[] input, int len, int val) {
        int start = 0;
        int end = len;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (val > input[mid] && val <= input[mid + 1]) {
                return mid + 1;
            } else if (val > input[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }
}


/*
 //https://www.interviewbit.com/problems/length-of-longest-subsequence/ 
 public int longestSubsequenceIncresingDecresingLength(final int[] A) {
        int n = A.length;
        if(n == 0) {
            return 0;
        }
        int[] temp1 = new int[n];
        int[] temp2 = new int[n];
        Arrays.fill(temp1, 1);
        Arrays.fill(temp2, 1);

        int max = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            for (int j = i-1; j >= 0; j--) {
                if (A[i] > A[j] && temp1[j] + 1 > temp1[i]) {
                    temp1[i] = temp1[j] + 1;
                }
            }
        }

        for (int i = n-2; i >= 0; i--) {
            for (int j = n-1; j > i; j--) {
                if (A[i] > A[j] && temp2[j] + 1 > temp2[i]) {
                    temp2[i] = temp2[j] + 1;
                }
            }
        }
        
        for(int i=0; i<n; i++){
            if(temp1[i] + temp2[i] > max){
                max = temp1[i] + temp2[i] -1;
            }
        }
        return max;
    }

*/