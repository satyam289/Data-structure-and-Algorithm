package Array;

// count negative number and find the searched item in sorted array


public class Count_Matrix_NegativeNumber {  //sorted row wise and column wise (best apporach)

	public static void main(String[] args) {
		int [][] m={{-3,-2, 0},
					{-2,-1, 2},
					{-1, 5, 6},
					{ 3, 7, 9}}; 
		int i=0;
		int j=m[0].length-1;   // rows length or no of column
		int count=0;
		while(j>=0 && i<m[0].length){
			
			if(m[i][j]>=0)
				j--;
			else{
				count += (j+1);                     //including that number
				i++;
			}
		
		}
		System.out.println("Number of negative "+count);
		System.out.println("No of positive " +(m[0].length*m.length-count));
        search(-90);
	}

	
	
	public static void search(int value ){
		
		int [][] m={{-3,-2,0},{-2,-1,2},{-1,5,6},{3,7,9}};
		int i=0;
		int j=m[0].length-1;
		
		while(j>=0 && i<m[0].length){
			if(m[i][j]==value){
			   System.err.println("found");	
				return;
			}
			
			if(m[i][j]>value)
				j--;
			else{
				i++;
			}
		
		}
		System.err.println("Not Found");
		
	}
}
