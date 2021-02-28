package DynamicProgramming;

import java.util.Arrays;

/**
 * You are given n pairs of numbers. In every pair, the first number is always smaller than the second number. 
 * A pair (c, d) can follow another pair (a, b) if b < c. Chain of pairs can be formed in this fashion.
 *  Find the longest chain which can be formed from a given set of pairs.
For example, if the given pairs are {{5, 24}, {39, 60}, {15, 28}, {27, 40}, {50, 90} }, 
then the longest chain that can be formed is of length 3, and the chain is {{5, 24}, {27, 40}, {50, 90}}
 *
 *
 */
public class MaximumLengthChainPair {

	public static void main(String[] args) {
		Pair p = new Pair(5, 7);
		Pair p1 = new Pair(2, 3);
		Pair p2 = new Pair(2, 6);
		Pair p3 = new Pair(9, 11);
		Pair p4 = new Pair(8, 10);
		Pair p5 = new Pair(11, 14);
		Pair[] arr = { p, p1, p2, p3, p4, p5 };
		//Pair arr2[] = new Pair[] {new Pair(5,24), new Pair(15, 25), new Pair (27, 40), new Pair(50, 60)};
		int r = maxLength(arr);
		System.out.print(r);
	}

	// LIS implementation
	private static int maxLength(Pair[] arr) {
		Arrays.sort(arr);
		for(Pair pair: arr) {
			System.out.println(pair.toString());
		}
		System.out.println();
		int[] T = new int[arr.length];
		Arrays.fill(T, 1);
		for (int i =1; i < arr.length; i++) {
			for (int j = 0; j < i; j++) {
				
				if (arr[i].a > arr[j].b) {
					if (T[j] + 1 > T[i]) {
						T[i] = T[j] + 1;
					}
				}
			}
		}
		System.out.println(Arrays.toString(T));
		int max = 0;
		for (int i = 0; i < T.length; i++) {
			if (T[i] > max)
				max = T[i];
		}
		return max;
	}

	static class Pair implements Comparable<Pair> {
		int a;
		int b;
		
		public Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public int compareTo(Pair p) {
			if (this.a > p.a)
				return 1;
			else
				return -1;
		}

		@Override
		public String toString() {
			return "Pair [a=" + a + ", b=" + b + "]";
		}

	}	
}


