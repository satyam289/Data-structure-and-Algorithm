package Array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

//https://www.geeksforgeeks.org/find-duplicates-in-on-time-and-constant-extra-space/
//https://www.interviewbit.com/problems/find-duplicate-in-array/
public class findDuplicate {

    public void printRepeating(int arr[], int size) {
        int i;
        System.out.println("The repeating elements are : ");
        for (i = 0; i < size; i++) {
            int j = Math.abs(arr[i]);
            if (arr[j] >= 0)
                arr[j] = -arr[j];
            else
                System.out.print(j + " ");
        }
    }

    public void printRepeating(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            arr[arr[i] % n] = arr[arr[i] % n] + n;
        }
        System.out.println("The repeating elements are : ");
        for (int i = 0; i < n; i++) {
            if (arr[i] >= n * 2) {
                System.out.println(i + " ");
            }
        }
    }

    public int findFirstRepeat(int[] A) {
        int i, xor1 = 0, xor2 = 1;
        int n = A.length;
        for (i = 0; i < n; i++) {
            xor1 ^= A[i];
        }

        for (i = 2; i <= (n - 1); i++) {
            xor2 ^= i;
        }
        int repeat = xor1 ^ xor2;
        return repeat;
    }

    // optimal sol: find repeat and missing number
    public ArrayList<Integer> repeatedNumber(final List<Integer> A) {
        long diff = 0;
        long squareDiff = 0;
        int len = A.size();
        for (int i = 1; i <= len; i++) {
            long curr = A.get(i - 1);
            diff += curr - i;
            squareDiff += (curr - i) * (curr + i);
        }
        long sum = squareDiff / diff;

        long repeated = (diff + sum) / 2;
        long missing = (sum - diff) / 2;
        ArrayList<Integer> res = new ArrayList<Integer>();
        res.add((int) repeated);
        res.add((int) missing);
        return res;
    }

    // Better solution
    public int[] repeatedNumber(final int[] A) {
        int[] res = new int[2];
        int i = 0;
        // swap sort
        for (i = 0; i < A.length; i++) {
            if (A[i] != i + 1 && A[A[i] - 1] != A[i]) {
                int temp = A[A[i] - 1];
                A[A[i] - 1] = A[i];
                A[i] = temp;
                i--;
            }
        }
        // find repeating and missing
        for (i = 0; i < A.length; i++) {
            if (A[i] != i + 1) {
                res[0] = A[i]; // repeating
                res[1] = i + 1; // missing
                break;
            }
        }
        return res;
    }

    // Brute Force Apporach
    public ArrayList<Integer> repeatedNumber3(final List<Integer> A) {
        ArrayList<Integer> out = new ArrayList<Integer>();
        double l = A.size();
        double sum = (l * (l + 1)) / 2;
        long sumA = 0;
        int a = 0;
        HashSet<Integer> ASet = new HashSet<Integer>();
        for (int i = 0; i < A.size(); i++) {
            if (ASet.contains(A.get(i))) {
                a = A.get(i);
            }
            ASet.add(A.get(i));
            sumA = sumA + A.get(i);
        }
        double diff = sumA - sum;
        int b = a - (int) diff;
        out.add(a);
        out.add(b);
        return out;
    }
}
