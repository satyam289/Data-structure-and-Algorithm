package DynamicProgramming;

/*
Input: str = "123123"
Output: 6
The complete string is of even length and sum of first and second
half digits is same

Input: str = "1538023"
Output: 4
The longest substring with same first and second half sum is "5380"
 */
public class LongestEvenLengthString {

	public static void main(String[] args) {
		
		System.out.println(longestEvenLength("1538023"));
		
		System.out.println(findLength("1538023"));
	    
		System.out.println(findLength3apporach("1538023"));
		
		System.out.println(findLength4apporach("1538023"));
	}
	
	//time O(n3)
	public static int longestEvenLength(String s) {
		int n = s.length();
		int max = Integer.MIN_VALUE;
		String str=null;
		for (int i = 0; i < n; i++) {
  
			for (int j = i + 1; j < n; j += 2) {
				
				int len = j - i + 1;
				int leftsum = 0, rightsum = 0;
				for (int k = 0; k < len / 2; k++) {
					leftsum += (s.charAt(i + k) - '0');
					rightsum += (s.charAt(i+len/2+k) - '0');
				}
				if (leftsum == rightsum && len > max) {
					max = len;
				str=s.substring(i, len+1);	
				}

			}
		}
		System.out.println(str);
		return max;
	}
	
	//O(n2) , space O(n2)
	public static int findLength(String str) {
		int n = str.length();
		int max = Integer.MIN_VALUE;
		int[][] table = new int[n][n];
		for (int i = 0; i < n; i++) {
			table[i][i] = str.charAt(i) - '0';
		}
		for (int len = 2; len <= n; len++) {

			for (int i = 0; i < n - len + 1; i++) {

				int j = i + len - 1;
				int k = len / 2 -1;
				table[i][j] = table[i][i+k] + table[i+k+1][j];
				
				if (len % 2 == 0 && table[i][i+ k] == table[i+k+1][j] && len > max) {
					max = len;
				}
			}
		}
		return max;
	}
	
	//0(n) space 
	public static int findLength3apporach(String str) {
		int n = str.length();
		int max = Integer.MIN_VALUE;
		int[] sum = new int[n + 1];
		sum[0]=0;
		for (int i = 1; i <= n; i++) {
			sum[i] = sum[i - 1] + (str.charAt(i - 1) - '0');
		}
		for (int len = 2; len <= n; len+=2) {
			for (int i = 0; i < n - len + 1; i++) {
				//int j = i + len - 1;
				if ((sum[i + len / 2] - sum[i]) == (sum[i+len] - sum[i + len / 2]) && len > max) {
					max = len;
				}
			}
		}
		return max;
	}
	
	
	//0(1) space
	public static int findLength4apporach(String str) {
		int n = str.length();
		int max = Integer.MIN_VALUE;
		for(int i=0;i<n-1; i++) {
			int low=i,high=i+1;
			int leftsum=0, rightsum=0;
			while(high<n && low>=0) {
				leftsum += str.charAt(low)-'0';
			    rightsum += str.charAt(high)-'0';
			    if(leftsum == rightsum)
			    	max=Math.max(max, high-low+1);
			    low--;
			    high++;
			}
		}
		return max;
	}
}
	
