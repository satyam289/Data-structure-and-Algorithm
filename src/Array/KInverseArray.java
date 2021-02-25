package Array;

import java.util.Arrays;

public class KInverseArray {

	public static void main(String[] args) {
		
		//kInverseArray(3,1);
		dynammmicApporach(4,2);
	}
	
	public static void kInverseArray(int n, int k) {
		int [] input=new int[n];
		for(int i=1; i<=n; i++) {
			input[i-1]=i;
		}
		KInverseArray(input,0,k);
	}
	
	public static void  KInverseArray(int [] arr, int start, int k) {
		if(start == arr.length) {
			System.out.print(Arrays.toString(getCountAnagram(arr, k)));
			return;
		}
		for(int i=start; i<arr.length; i++) {
		   swap(arr, start, i);
		   KInverseArray(arr,start+1,k);
		   swap(arr, i, start);
		}
	}

	private static int[] getCountAnagram(int[] arr, int k) {
		int count = 0;
		//System.out.println(Arrays.toString(arr));
		for (int i = 0; i < arr.length; i++) {
			for (int j = i; j < arr.length; j++) {
				if (i < j && arr[j] < arr[i])
					count++;
			}
		}
		if(count == k)
		    return arr;
		return new int[0]; 
	}

	private static void swap(int[] arr, int start, int i) {
		int temp= arr[i];
		arr[i]= arr[start];
		arr[start]=temp;
	}
	
	private static void dynammmicApporach(int n, int k) {
		int [][] table=new int[n][k];
		for (int i = 1; i <= k; i++) {
			table[0][i - 1] = i;
		}
		for(int i=1; i<n; i++) {
			for(int j=0; i<k; j++) {
				int sum=0;
				for(int p=0; p<k && j>=p; p++) {
					sum += table[i-1][j-p];
				}
				System.out.println(sum+" ");
				table[i][j]=sum;
			}
			System.out.println(" ");
		}
		
	}
}
