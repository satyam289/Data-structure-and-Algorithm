package DynamicProgramming;

/**
 * http://www.geeksforgeeks.org/how-to-print-maximum-number-of-a-using-given-four-keys/
 * Test cases
 * Negative number
 * Number less than 7
 * Number greater than equal to 7
 */
public class CountAs {

	public static void main(String[] args) {
		System.out.println(getMaximumOccurance(11));
		System.out.println(dynammicApporach(11));
	}
	
	public static int getMaximumOccurance(int N) {
		if(N<=0)
			return 0;
		if(N<=6)
			return N;
		int max=Integer.MIN_VALUE;
		for(int i=N-3; i>=0; i--) {
			max=Math.max(max, (N-i-1)*getMaximumOccurance(i));
		}
		return max;
	} 
	
	public static int dynammicApporach(int N) {
		if (N <= 6)
			return N;
		int[] T = new int[N + 1];
		for (int i = 0; i <= 6; i++) {
			T[i] = i;
		}
		for (int i = 7; i <= N; i++) {
			int max = Integer.MIN_VALUE;
			for (int j = i - 3; j >= 0; j--) {
				max = Math.max(max, (i - j - 1) * T[j]);
			}
			T[i] = max;
		}
		return T[N];
	}

}
