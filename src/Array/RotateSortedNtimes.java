package Array;

import java.util.Arrays;

public class RotateSortedNtimes {

	
	public static void main(String [] args){
		int [] a = {2, 3, 6, 7, 8};
	  /* for(int i=0;i<2;i++){             //time complexity 0(n*2)  // where 2 is no. of rotation
		   rotate(a);
	   }*/
		
		 System.out.println(Arrays.toString(a));
		//doOneRotation(a,2);
		 doRotationUsingReverseMethod(a,2);
	     System.out.println(Arrays.toString(a));	
	}
	
	public static void rotate(int [] a){       
		int temp= a[0],i;
		
		for( i=0;i<a.length-1;i++)
			a[i]=a[i+1];
		a[i]=temp;
	}
	
	public static void doOneRotation(int [] a, int n){   //same as rotate() but time complexity is 0(n)
		int i, j;
		for( j=0;j<n;j++){                      //No of shifting
			int temp =a[j];
			
			for( i=j; i<(a.length-n); i+=n){    //shifting nth element till end
				a[i]=a[i+n];
			}
			a[i]=temp;
		}
	}
	
	
	public static void doRotationUsingReverseMethod(int [] a, int n){    //3rd approach , time complexity 0(n)
		 
		reverse(a, 0, n-1);                 //first part
		reverse(a, n, a.length-1);          //second part
		reverse(a, 0, a.length-1);          //full
		 
			 
	}
	
	public static void reverse(int [] a , int start, int end){
		
		while(start<end){
			int temp=a[start];
			a[start]=a[end];
			a[end]=temp;
			start++;
			end--;
		}
		
	}
	
	
}
