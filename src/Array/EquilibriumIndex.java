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

			/* if leftsum and rightsum are same,
			then we are done */
			if (leftsum == rightsum)
				return i;
		}

		/* return -1 if no equilibrium index is found */
		return -1;
	}

	public static void main(String[] args)
	{
		EquilibriumIndex equi = new EquilibriumIndex();
		int arr[] = { -7, 1, 5, 2, -4, 3, 0 };
		int arr_size = arr.length;
		System.out.println(equi.equilibrium(arr, arr_size));
	}
}

class EquilibriumIndex {
	int equilibrium(int arr[], int n)
	{
		int sum = 0; // initialize sum of whole array
		int leftsum = 0; // initialize leftsum

		/* Find sum of the whole array */
		for (int i = 0; i < n; ++i)
			sum += arr[i];

		for (int i = 0; i < n; ++i) {
			sum -= arr[i]; // sum is now right sum for index
						// i

			if (leftsum == sum)
				return i;

			leftsum += arr[i];
		}

		/* If no equilibrium index found, then return 0 */
		return -1;
	}

	// Driver code
	public static void main(String[] args)
	{
		EquilibriumIndex equi = new EquilibriumIndex();
		int arr[] = { -7, 1, 5, 2, -4, 3, 0 };
		int arr_size = arr.length;

		// Function call
		System.out.println(
			"First equilibrium index is "
			+ equi.equilibrium(arr, arr_size));
	}
}


class GFG {

	static int equilibrium(int a[], int n)
	{
		if (n == 1)
			return (0);

		int[] front = new int[n];
		int[] back = new int[n];

		// Taking the prefixsum from front end array
		for (int i = 0; i < n; i++) {
			if (i != 0) {
				front[i] = front[i - 1] + a[i];
			}
			else {
				front[i] = a[i];
			}
		}

		// Taking the prefixsum from back end of array
		for (int i = n - 1; i > 0; i--) {
			if (i <= n - 2) {
				back[i] = back[i + 1] + a[i];
			}
			else {
				back[i] = a[i];
			}
		}

		// Checking for equilibrium index by
		// comparing front and back sums
		for (int i = 0; i < n; i++) {
			if (front[i] == back[i]) {
				return i;
			}
		}

		// If no equilibrium index found,then return -1
		return -1;
	}

	public static void main(String[] args)
	{
		int arr[] = { -7, 1, 5, 2, -4, 3, 0 };
		int arr_size = arr.length;

		System.out.println("First Point of equilibrium "
						+ "is at index "
						+ equilibrium(arr, arr_size));
	}
}

// o(N)
class Solution {
	public int pivotIndex(List<Integer> nums) {
		int left = 0, pivot = 0, right = 0;
		for (int i = 1; i < nums.size(); i++) {
			right += nums.get(i);
		}
		while (pivot < nums.size() - 1 && right != left) {
			pivot++;
			right -= nums.get(pivot);
			left += nums.get(pivot - 1);
		}
		return (left == right) ? pivot : -1;
	}

	public static void main(String[] args) {
		List<Integer> nums = List.of(1, 7, 3, 6, 5, 6);
		int result = new Solution().pivotIndex(nums);
		System.out.println(result);
	}
}
