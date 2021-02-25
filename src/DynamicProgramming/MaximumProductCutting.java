package DynamicProgramming;

import java.util.Arrays;

/**Given a rope of length n meters, cut the rope in different parts of 
 * integer lengths in a way that maximizes product of lengths of all parts.
 *  You must make at least one cut. Assume that the length of rope is more than 2 meters.

Examples:
Input: n = 2
Output: 1 (Maximum obtainable product is 1*1)
Input: n = 3
Output: 2 (Maximum obtainable product is 1*2)
Input: n = 4
Output: 4 (Maximum obtainable product is 2*2)
Input: n = 5
Output: 6 (Maximum obtainable product is 2*3)
Input n =6
Output: 9  (Maximum obtainable product is 3*3)
Input: n = 10
Output: 36 (Maximum obtainable product is 3*3*4)
**/

public class MaximumProductCutting {

	public static void main(String[] args) {
		System.out.println(getmax(10));
		System.out.println(getMaxFromDP(10));
		
	}
	public static int getmax(int n) {
		int [] T=new int [n+1];
		Arrays.fill(T, 0);
		return getmaxRecursion(n,T);
	}
	
	public static int getmaxRecursion(int n,int [] T) {
			
		if (n == 0 || n == 1)
			return 0;
		if(T[n] !=0)
			return T[n];
		else {
			int max = Integer.MIN_VALUE;
			for (int i = 1; i < n; i++) {
				int val =Math.max(i*(n-i), getmaxRecursion(i,T)*getmaxRecursion(n - i,T));
				if (max < val) {
					max = val;
				}
			}
			T[n]=max;
			return max;
		}
	}
	
	public static int getMaxFromDP(int n) {
		int [] T = new int[n+1];
		Arrays.fill(T, 0);
		int max=0;
		for (int i = 1; i <= n; i++) {
			 max = Integer.MIN_VALUE;
			for (int j= 1; j <= i; j++) {
				max=Math.max(max,Math.max(j*(i-j), T[j]*T[i-j]));
			}
			T[i]=max;
		}
		System.out.println(Arrays.toString(T));
		return max;
	}
	
	
	/*
	 * A Tricky Solution:
	If we see some examples of this problems, we can easily observe following pattern.
	The maximum product can be obtained be repeatedly cutting parts of size 3 while size is greater than 4,
 	keeping the last part as size of 2 or 3 or 4. For example, n = 10, 
	maximum product is obtained by 3, 3, 4. For n = 11, the maximum product is obtained by 3, 3, 3, 2. 
	Following is the implementation of this approach.
	 */
	
    static int maxProd(int n)
    {
     
    // n equals to 2 or 3 must be handled
    // explicitly
    if (n == 2 || n == 3) return (n-1);
 
    // Keep removing parts of size 3 
    // while n is greater than 4
    int res = 1;
    while (n > 4)
    {
        n -= 3;
         
        // Keep multiplying 3 to res
        res *= 3; 
    }
     
    // The last part multiplied by 
    // previous parts
    return (n * res); 
    }

}
