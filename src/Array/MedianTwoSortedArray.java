package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// https://www.geeksforgeeks.org/median-of-two-sorted-arrays-of-different-sizes/
public class MedianTwoSortedArray {

	public static void main(String[] args) {
		int[] arr1 = { 2, 5, 6, 8, 9, 11 };
		int[] arr2 = { 1, 3, 4, 7, 10, 12 };
		int[] ar1 = { 900 };
		int[] ar2 = { 5, 8, 10, 20 };
		System.out.println("The median of two sorted array : " + findMedian(arr1, arr2));
		System.out.println("The median of two  sorted array : " + findMedianOptimised(ar1, ar2));
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
	// https://www.youtube.com/watch?v=LPFhl65R7ww
	public static double findMedianOptimised(int[] first, int[] second) {
		if (second.length < first.length) {
			return findMedianOptimised(second, first);
		}
		int x = first.length; // smaller
		int y = second.length; // large
		int low = 0;
		int high = x;

		while (low <= high) {
			int partitionX = (high + low) / 2;
			int partitionY = ((x + y + 1) / 2) - partitionX;

			int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : first[partitionX - 1];
			int minRightX = (partitionX == x) ? Integer.MAX_VALUE : first[partitionX];

			int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : second[partitionY - 1];
			int minRightY = (partitionY == y) ? Integer.MAX_VALUE : second[partitionY];

			if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
				if (x + y % 2 == 0) { // even
					return (double) (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2;
				}
				return (double) Math.max(maxLeftX, maxLeftY); // odd
			} else if (maxLeftX > minRightY) {
				high = partitionX - 1; // move toward left
			} else {
				low = partitionX + 1; // move toward right
			}
		}
		throw new IllegalArgumentException("No median : Given array is not sorted");
	}

	// https://www.geeksforgeeks.org/find-median-row-wise-sorted-matrix/
	// https://www.interviewbit.com/problems/matrix-median/
	// Assume N*M is odd , so median will always (N*M+1/2)
	// Brute Force
	public int findMedian1(int[][] A) {
		ArrayList<Integer> a = new ArrayList<>();
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[0].length; j++) {
				a.add(A[i][j]);
			}
		}
		Collections.sort(a);
		return a.get(a.size() / 2);
	}

	// Optimised
	public int findMedian2(int[][] A) {

		int row = A.length;
		int col = A[0].length;
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < row; i++) {
			min = Math.min(min, A[i][0]); // first col
			max = Math.max(max, A[i][col - 1]); // last col
		}

		int desired = (row * col + 1) / 2; // odd length (row * col), only middle element is median here
		while (min < max) {
			int mid = (min + max) / 2;
			int count = countLessThanEqual(A, mid);
			if (count < desired) {
				min = mid + 1;
			} else {
				max = mid;
			}
		}
		return min;
	}

	// Since array is sorted rowise, count all number less than or equal to given
	// number using binary search.
	private int countLessThanEqual(int[][] A, int num) {
		int count = 0;
		for (int i = 0; i < A.length; i++) {
			int index = Arrays.binarySearch(A[i], num);
			if (index < 0) {
				index = Math.abs(index) - 1;
			} else {
				while (index < A[0].length && A[i][index] == num) { // duplicate
					index++;
				}
			}
			count += index;
		}
		return count;
	}
}
