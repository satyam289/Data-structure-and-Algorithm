package Array;

// https://www.geeksforgeeks.org/median-of-two-sorted-arrays-of-different-sizes/
public class MedianTwoSortedArray {

	public static void main(String[] args) {
		int[] arr1 = { 2, 5, 6, 8, 9, 11 };
		int[] arr2 = { 1, 3, 4, 7, 10, 12 };
		int[] ar1 = { 900 };
		int[] ar2 = { 5, 8, 10, 20 };
		System.out.println("The median of two sorted array : " + findMedian(arr1, arr2));
		System.out.println("The median of two  sorted array : " + findMedian(ar1, ar2));
	}
    
	// Time Complexity : 0(n1+n2)
	public static int findMedian(int[] first, int[] second) {
		int len1 = first.length, len2 = second.length;
		int mid1 = -1, mid2 = -1, i = 0, j = 0;

		for (int count = 0; count <= (len1 + len2) / 2; count++) {
			mid1 = mid2;
			if (i < len1 && j < len2) {
				if (first[i] < second[j]) {
					mid2 = first[i++];
				} else {
					mid2 = second[j++];
				}
			} else if (i < len1) {
				mid2 = first[i++];
			} else {
				mid2 = second[j++];
			}
		}
		return (len1 + len2) % 2 != 0 ? mid2 : (mid1 + mid2) / 2;
	}

	// Time Complexity: O(min(log m, log n))
	public static int findMedianOptimised(int[] first, int[] second) {
		
	}
}
