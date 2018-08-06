package Array;

public class TrappingOfRainWater {

	public static void main(String[] args) {
		int arr[] = { 3, 1, 0, 2, 0, 4 };
		calculateWaterTrap(arr);
		calculateWaterTrap2(arr);
	}

	public static void calculateWaterTrap(int[] arr) {    // O(n2)
		int leftmax = 0, rightmax = 0, sum = 0;

		for (int i = 0; i < arr.length; i++) {           //getting Max left 
			for (int j = i - 1; j >= 0; j--) {
				if (arr[j] > leftmax)
					leftmax = arr[j];
			}

			for (int j = i + 1; j < arr.length; j++) {    //getting Max right
				if (arr[j] > rightmax)  
					rightmax = arr[j];
			}

			int min = rightmax > leftmax ? leftmax : rightmax;
			if (min == 0)                              // min = 0 , water will fall down (can't be stored)
				continue;
			sum += (min - arr[i]);                     // amount of water is stored 
			leftmax = 0;
			rightmax = 0;
		}
		System.out.println("Unit of water trapped by apporach 1: " + sum);
	}

	public static void calculateWaterTrap2(int[] arr) {  //0(n)
		int leftArray[] = new int[arr.length];
		int rightArray[] = new int[arr.length];
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			int value = arr[i];
			if (value > max) {
				leftArray[i] = max;
				max = value;
				continue;
			}
			leftArray[i] = max;
		}
		max = 0;
		for (int i = arr.length - 1; i >= 0; i--) {
			int value = arr[i];
			if (value > max) {
				rightArray[i] = max;
				max = value;
				continue;
			}
			rightArray[i] = max;
		}
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			int value = rightArray[i] > leftArray[i] ? leftArray[i] : rightArray[i];
			if (value == 0)
				continue;
			sum += value - arr[i];
		}
		System.out.println("Unit of water trapped 2nd Apporach " + sum);

	}

}
