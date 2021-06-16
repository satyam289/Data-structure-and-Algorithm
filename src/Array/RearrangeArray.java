package Array;

import java.util.ArrayList;
import java.util.Collections;

// https://www.geeksforgeeks.org/rearrange-given-array-place/
// Convert arr[i] to arr[arr[i]]
public class RearrangeArray {

    // Time Complexity : 0(n) Space Complexity : 0(1) Approach / Technique :
    // swap(arr[i] , arr[arr[i]])
    // int combineValue = a + b*n
    // int oldValue = combineValue % n;
    // int newValue = combineValue / n;
    // As we know all the element is between 1, 2, 3...n,
    // we can extract the 'b' old value from combineValue using % n
    // @ Here we are adding, a + (b % n)*n
    public void arrange(ArrayList<Integer> a) {

        int n = a.size();
        for (int i = 0; i < n; i++) {
            int otherCellVal = (a.get(a.get(i)) % n) * n;
            a.set(i, a.get(i) + otherCellVal);
        }
        for (int i = 0; i < n; i++) {
            a.set(i, (int) a.get(i) / n);
        }
    }

    // https://www.geeksforgeeks.org/sort-array-wave-form-2/
    // https://www.interviewbit.com/problems/wave-array/
    public ArrayList<Integer> wave(ArrayList<Integer> A) {
        Collections.sort(A);
        for (int i = 0; i < A.size() - 1; i += 2) {
            if (A.get(i) < A.get(i + 1)) {
                int temp = A.get(i);
                A.set(i, A.get(i + 1));
                A.set(i + 1, temp);
            }
        }
        return A;
    }
}
