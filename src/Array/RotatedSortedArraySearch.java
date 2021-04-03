package Array;

public class RotatedSortedArraySearch {

	public static void main(String[] args) {
		int[] a = { 4, 5, 1, 2, 3 };
		int searchItem = 4;

		int rotationPts = findPivot(a, 0, a.length - 1);
		if (rotationPts == -1) {
			System.out.println("All sorted :search item at index :  " + binarySearch(a, 0, a.length - 1, searchItem));
		} else {
			System.out.println("Rotated at index " + rotationPts + " The min value is " + a[rotationPts]);
			if (a[0] < searchItem) // left part
				System.out.println("searched item at index " + binarySearch(a, 0, rotationPts - 1, searchItem));
			else // right part
				System.out.println("searched item at index " + binarySearch(a, rotationPts, a.length - 1, searchItem));
		}
	}

	public static int binarySearch(int[] arr, int low, int high, int key) {
		if (high < low)
			return -1;
		int mid = (low + high) / 2;
		if (key == arr[mid])
			return mid;
		if (key > arr[mid])
			return binarySearch(arr, mid + 1, high, key);
		return binarySearch(arr, low, mid - 1, key);
	}

	private static int findPivot(int[] arr, int low, int high) {
		// base cases
		if (high < low)
			return -1;
		if (high == low)
			return low;

		int mid = (low + high) / 2;
		if (mid < high && arr[mid] > arr[mid + 1])
			return mid + 1; // returning min value
		if (mid > low && arr[mid] < arr[mid - 1])
			return mid; // returning min value

		if (arr[low] >= arr[mid])
			return findPivot(arr, low, mid - 1);
		return findPivot(arr, mid + 1, high);
	}
}
