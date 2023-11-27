package Array;

import java.util.List;
import java.io.*;

//Ref: https://www.geeksforgeeks.org/equilibrium-index-of-an-array/
class EquilibriumIndex {

	int equilibrium(int arr[], int n)
	{
		int i, j;
		int leftsum, rightsum;
		/* Check for indexes one by one until
		an equilibrium index is found */
		for (i = 0; i < n; ++i) {

			/* get left sum */
			leftsum = 0;
			for (j = 0; j < i; j++)
				leftsum += arr[j];

			/* get right sum */
			rightsum = 0;
			for (j = i + 1; j < n; j++)
				rightsum += arr[j];

			/* if leftsum and rightsum are same, then we are done */
			if (leftsum == rightsum)
				return i;
		}
		/* return -1 if no equilibrium index is found */
		return -1;
	}


	int equilibriumOptimal(int arr[], int n)
	{
		int sum = 0; // initialize sum of whole array
		int leftsum = 0; // initialize leftsum
		/* Find sum of the whole array */
		for (int i = 0; i < n; ++i)
			sum += arr[i];
		for (int i = 0; i < n; ++i) {
			sum -= arr[i]; // sum is now right sum for index // i
			if (leftsum == sum)
				return i;
			leftsum += arr[i];
		}
		/* If no equilibrium index found, then return 0 */
		return -1;
	}

	public static void main(String[] args)
	{
		EquilibriumIndex equi = new EquilibriumIndex();
		int arr[] = { -7, 1, 5, 2, -4, 3, 0 };
		int arr_size = arr.length;
		System.out.println("Brute Force equilibrium index is "+ equi.equilibrium(arr, arr_size));
		System.out.println("Optimal equilibrium index is "+ equi.equilibriumOptimal(arr, arr_size));
	}
}
