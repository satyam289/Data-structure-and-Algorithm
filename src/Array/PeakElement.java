package Array;

/*
https://www.interviewbit.com/problems/find-a-peak-element/
https://www.geeksforgeeks.org/find-a-peak-in-a-given-array/
Given an array of integers A, find and return the peak element in it.
An array element is peak if it is NOT smaller than its neighbors.
*/
public class PeakElement {

    public static void main(String[] args) {
        int[] arr = { 1, 3, 20, 4, 1, 0 };
        int n = arr.length;
        System.out.println("Index of a peak point is " + findPeak(arr, n));
        System.out.println("peak point is " + findPeak2(arr));
        System.out.println("peak point is " + findPeak3(arr));
    }

    private static int findPeak(int[] arr, int n) {
        return findPeakUtil(arr, 0, n - 1, n); // binary search
    }

    // Time Complexity : 0(logN)
    private static int findPeakUtil(int[] arr, int low, int high, int n) {
        int mid = high + low / 2;
        if ((mid == 0 || arr[mid - 1] <= arr[mid]) && (mid == n - 1 || arr[mid + 1] <= arr[mid]))
            return mid;

        else if (mid > 0 && arr[mid - 1] > arr[mid]) { // if not peak, then left neighbour is greater , then peak will
                                                       // be left.
            return findPeakUtil(arr, low, (mid - 1), n);
        } else {
            return findPeakUtil(arr, (mid + 1), high, n); // go to right neighbour
        }
    }

    // Time Complexity : 0(logN) Similiar
    public static int findPeak2(int[] arr) {
        if (arr.length == 1) {
            return arr[0];
        }
        if (arr[0] > arr[1]) {
            return arr[0];
        }
        if (arr[arr.length - 1] > arr[arr.length - 2]) {
            return arr[arr.length - 1];
        }
        int start = 1;
        int end = arr.length - 2;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid] >= arr[mid - 1] && arr[mid] >= arr[mid + 1]) { // equal neighbours also consider peak element
                return arr[mid];
            }
            if (arr[mid] < arr[mid + 1]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    // Brute Force - Time Complexity :0(N)
    public static int findPeak3(int[] A) {
        int n = A.length;
        if (n == 1) {
            return A[0];
        }
        if (n == 2) {
            return Math.max(A[0], A[1]);
        }
        for (int i = 1; i < n - 1; i++) {
            if (A[i - 1] <= A[i] && A[i] >= A[i + 1]) {
                return A[i];
            }
        }
        // Now we know the given is either strictly increasing or decreasing, checking
        // extreme end
        if (A[0] > A[1]) {
            return A[0];
        } else {
            return A[n - 1];
        }
    }
}
