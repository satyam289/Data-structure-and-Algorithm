package Miscellaneous;


public class LongestAP {

	public static void main(String[] args) {
		System.out.println(getLongestAp(new int[] {2, 4, 10, 11, 16,22,28}));
		System.out.println(getLongestGp(new int[] {4, 10, 12, 36}));  // 4*3^0 , 4*3^1 , 4*3^2
	}
	//even length
	public static int getLongestAp(int [] arr) {
		int max=0;
		for(int j=1; j<arr.length; j++) {
			int i=j-1;
			int k=j+1;
			while(i >= 0 && k < arr.length) {
				if(2 * arr[j] == (arr[k] + arr[i])) {
					if((k-i) > max) 
						max = k-i;
					k++;
					i--;
				}
				else if(2*arr[j] > arr[i]+arr[k]) {
					k++;
				}else{
					i--;
				}
			  }
			}
		return max;
	}
	
	public static int getLongestGp(int [] arr) {
		int max=0;
		for(int j=1; j<arr.length; j++) {
			int i=j-1;
			int k=j+1;
			while(i >= 0 && k < arr.length) {
				if(arr[j]*arr[j] == arr[i]*arr[k]) {
					if((k-i) > max) 
						max = k-i;
					k++;
					i--;
				}
				else if(arr[j]*arr[j] > arr[i]*arr[k]) {
					k++;
				}else{
					i--;
				}
			  }
			}
		return max;
	}
}
