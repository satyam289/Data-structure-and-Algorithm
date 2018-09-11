package DynamicProgramming;

import java.util.Arrays;
import java.util.LinkedList;

public class ChocolateDistribution {

	public static void main(String[] args) {

		// askingnext(new int[]{5, 4, 3, 2});
		// distribution(new int[]{5,4,3,6,1,7,9});
	}

	/**
	 *day at which till all class room stable(the next element always grater than previous) 
	 *if less then remove that guy
	 * @param arr(fee of student)
	 */
	
	public static void askingnext(int[] arr) {

		LinkedList<Integer> ls = new LinkedList<Integer>();
		for (int i : arr) {
			ls.add(i);
		}
		int count = 0;
		while (ls.size() > 0) {
			int size = ls.size();
			for (int i = 0; i < ls.size() - 1; i++) {
				if (ls.get(i) > ls.get(i + 1)) {
					ls.remove(i);
				}
			}
			if (ls.size() == 1 || ls.size() == size)
				break;
			count++;
		}
		System.out.println("Day "+count);
	}
    
	/**
     * distribute the min no of chocolate such that 
     * the if marks is greater will grater than neighbour then he will get maximum of neighbour or vice versa
     * @param  marks distributed among student
     */
	public static void distribution(int[] arr) {

		int[] result = new int[arr.length];
		result[0] = 1;
		for (int i = 1; i < arr.length; i++) {

			if (arr[i] > arr[i - 1]) {
				result[i] = result[i - 1] + 1;
			} else if (arr[i] < arr[i - 1]) {
				result[i] = 1;
				if (result[i - 1] == 1) {
					int k = i - 1;
					while (k >= 0) {
						if (arr[k] > arr[k + 1] && result[k] >= result[k + 1]) {
							result[k] = result[k + 1] + 1;
							k = k - 1;
						} else {
							break;
						}
					}
				}
			} else {
				result[i] = result[i - 1];
			}
		}
		int total = 0;
		for (int i = 0; i < result.length; i++) {
			total += result[i];
		}
		System.out.println("distribution " + Arrays.toString(result));
		System.out.println("Min Total chocolate distribution " + total);
	}
}
