package Practice;

import java.util.Arrays;

public class ConvertZigZag {

	public static void main(String[] args) {
	 // int [] input= { 5,3,1};
		int [] input= {4, 3, 7, 8, 6, 2, 1};
      System.out.println(Arrays.toString(convertZigZag(input)));
	}
	
	public static int[] convertZigZag(int [] arr) {
		boolean flag=true;
		for(int i=0; i<arr.length-1; i++) {
			if(flag) {
				if(arr[i]>arr[i+1]) 
					swap(i, i+1 ,arr);
			}else {
				if(arr[i]<arr[i+1]) 
					swap(i, i+1, arr);
			}
			flag=!flag;
		} 
		return arr;
	}
	
	private static void swap(int a, int b, int [] arr) {
		int temp=arr[a];
		arr[a]=arr[b];
		arr[b]=temp;
	}

}
