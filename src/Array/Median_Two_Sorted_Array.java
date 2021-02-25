package Array;

public class Median_Two_Sorted_Array {

	public static void main(String[] args) {
		int []  n1={12,15,16,18,19,11};
		int []  n2={11,13,14,17,20,22};
		
		if(n1.length==n2.length)
			System.out.println(findMedian(n1,n2,n1.length));
		else 
			System.out.println("Arrays are unequal in length");

	}
	
	public static int findMedian(int [] n1 , int [] n2, int length){  //without merging , justing counting
		
		int i=0,j=0, m1=0,m2=0;
		for(int count=0; count<=length;count++){    //counting till half
		  
			
			if(n1[i]<n2[j]){	
				m1=m2;
				m2=n1[i++];
			}
			else{
			   m1=m2;
			   m2=n2[j++];
			}
		}
		return (m1+m2)/2;                       

 }
}
