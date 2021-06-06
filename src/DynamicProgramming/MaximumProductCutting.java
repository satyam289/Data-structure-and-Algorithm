package DynamicProgramming;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/maximum-product-cutting-dp-36/ Given a rope of
 * length n meters, cut the rope in different parts of integer lengths in a way
 * that maximizes product of lengths of all parts. You must make at least one
 * cut.
 **/
public class MaximumProductCutting {

	public static void main(String[] args) {
		System.out.println(getmax(10));
		System.out.println(getMaxFromDP(10));
	}

	public static int getmax(int n) {
		int[] T = new int[n + 1];
		Arrays.fill(T, 0);
		return getmaxRecursion(n, T);
	}

	private static int getmaxRecursion(int n, int[] T) {
		if (n == 0 || n == 1)
			return 0;
		if (T[n] != 0)
			return T[n];
		else {
			int max = Integer.MIN_VALUE;
			for (int i = 1; i < n; i++) {
				int val = Math.max(i * (n - i), getmaxRecursion(i, T) * getmaxRecursion(n - i, T));
				if (max < val) {
					max = val;
				}
			}
			T[n] = max;
			return max;
		}
	}

	public static int getMaxFromDP(int n) {
		int[] T = new int[n + 1];
		Arrays.fill(T, 0);
		int max = 0;
		for (int i = 1; i <= n; i++) {
			max = Integer.MIN_VALUE;
			for (int j = 1; j <= i; j++) {
				max = Math.max(max, Math.max(j * (i - j), T[j] * T[i - j]));
			}
			T[i] = max;
		}
		System.out.println(Arrays.toString(T));
		return max;
	}

	/*
	 * A Tricky Solution: If we see some examples of this problems, we can easily
	 * observe following pattern. The maximum product can be obtained be repeatedly
	 * cutting parts of size 3 while size is greater than 4, keeping the last part
	 * as size of 2 or 3 or 4. For example, n = 10, maximum product is obtained by
	 * 3, 3, 4. For n = 11, the maximum product is obtained by 3, 3, 3, 2.
	 */
	public static int maxProd(int n) {
		if (n == 2 || n == 3)
			return (n - 1);

		int res = 1;
		while (n > 4) { // Keep removing parts of size 3 while n is greater than 4
			n -= 3;
			res *= 3;
		}
		return (n * res);
	}

	// http://www.geeksforgeeks.org/dynamic-programming-set-13-cutting-a-rod/
	public int maxRodCutDPVal(int price[]) {
		int max[] = new int[price.length + 1];
		for (int i = 1; i <= price.length; i++) {
			for (int j = i; j <= price.length; j++) {
				max[j] = Math.max(max[j], max[j - i] + price[i - 1]);
			}
		}
		return max[price.length];
	}

	public int maxRodCutRecVal(int price[], int len) {
		if (len <= 0) {
			return 0;
		}
		int maxValue = 0;
		for (int i = 0; i < len; i++) {
			int val = price[i] + maxRodCutRecVal(price, len - (i + 1));
			if (maxValue < val) {
				maxValue = val;
			}
		}
		return maxValue;
	}
}

// Todo: Min Cost at rod weak point
// https://www.interviewbit.com/problems/rod-cutting/