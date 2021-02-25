package DynamicProgramming;

public class divideTwoEqualSubarray { //divide two subarrary in such way that their sum is equal.

	public static void main(String [] args){
		int [] a={1, 5, 11, 5};
		int sum=0;
		for(int i:a){
			sum+=i;
		}
		if(sum%2 !=0)
			System.err.println("cant be partion into two subarrary as sum is odd");
		else
			//System.out.println(dopartition(a,a.length,sum/2));
			System.out.println(solveByDynamicProgramming(a,sum/2));
	}

    private static boolean dopartition(int[] a, int length, int sum) {
	     if(sum==0)
	    	 return true;
	     if(length==0 && sum !=0)
	    	 return false;
	     if(a[length-1]>sum)
	    	 return dopartition(a,length-1, sum);
	     	 
	     return dopartition(a, length-1, sum) || dopartition(a , length-1, sum-a[length-1]);
	     
    }	
    
    private static boolean solveByDynamicProgramming(int [] a, int sum){
    	 int n=a.length;
    	 boolean subset[][] = new boolean[sum+1][n+1];
        
         // If sum is 0, then answer is true
         for (int i = 0; i <= n; i++)
           subset[0][i] = true;
       
         // If sum is not 0 and set is empty, then answer is false
         for (int i = 1; i <= sum; i++)
           subset[i][0] = false;
       
          // Fill the subset table in botton up manner
          for (int i = 1; i <= sum; i++)
          {
            for (int j = 1; j <= n; j++)
            {
              subset[i][j] = subset[i][j-1];
              if (i >= a[j-1])
                subset[i][j] = subset[i][j] || 
                                           subset[i - a[j-1]][j-1];
            }
          }
       
         /* // uncomment this code to print table
          for (int i = 0; i <= sum; i++)
          {
            for (int j = 0; j <= n; j++)
               printf ("%4d", subset[i][j]);
            printf("n");
          } */
       
          return subset[sum][n];
    }
}
