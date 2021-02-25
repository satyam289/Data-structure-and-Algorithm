package DynamicProgramming;

public class DiceSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DiceSum diceSum=new DiceSum();
		System.out.println(diceSum.no_of_ways(2, 6, 9));
		
	}
	
	public int no_of_ways(int noOfDice, int face, int sum) {
		
		/*int [][] table =new int[noOfDice][sum+1];
		
		for(int j=0;j<=sum ;j++){ 
			table[0][j] = 1;
		}
		for(int i=1;i<noOfDice ;i++) {
			
			for(int j=0; j<=sum ;j++) {  
				 
				if(i == j)
				    table[i][j]=1;
				if(j<i)
				    table[i][j]=0;
				else if(j>face*i)
					table[i][j]=0;
				else{
					for(int k=1; k<=face ; k++) {
						if(j>k)
							table[i][j] += table[i-1][j-k]; 
					}
				}
			    
			}
		}
		return table[noOfDice-1][sum];
	}
*/
		
		
		 //Skip not reachable cases: sum is too low or too high
	    if (sum < face /*min value*/ || sum > noOfDice * face /*max value*/) 
	    	return -1;
	    
	    int[][] table = new int[noOfDice + 1][sum + 1];

	    // Table entries for only one dices
	    for (int i = 1; i <= face && i <= sum; i++)
	        table[1][i] = 1;

	    // Fill rest of the entries in table using recursive relation
	    // i: number of dices, j: sum
	    for (int d = 2; d <= noOfDice; d++) {
	        for (int s = 1; s <= sum; s++) {
	            for (int f = 1; f <= face && f < s; f++)
	                table[d][s] += table[d - 1][s - f];
	        }
	    }
	    return table[noOfDice][sum];
   }
}
