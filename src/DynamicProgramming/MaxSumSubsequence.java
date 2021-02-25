package DynamicProgramming;

import java.util.Arrays;

public class MaxSumSubsequence {

	public static void main(String[] args) {

		int[] input = { 1, 101, 2, 3, 100, 4, 5};
		System.out.println(maximumSum(input));

	}

	public static int maximumSum(int[] arr) {
		int[] T = arr.clone();
		for (int i = 0; i < arr.length; i++) {
			
			for (int j = 0; j < i; j++) {
                
				if (arr[i] > arr[j]) {
					if(T[j]+arr[i]>T[i]) {
					   T[i] = T[j] + arr[i];
					}
				}
			}
		}
		int max = 0;
		System.out.println(Arrays.toString(T));
		for (int i = 0; i < T.length; i++) {
			max = Math.max(max, T[i]);
		}
		return max;
	}
}
