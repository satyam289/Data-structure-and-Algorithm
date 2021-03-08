package Array;

import java.util.ArrayList;
import java.util.Collections;

//https://www.geeksforgeeks.org/find-a-triplet-in-an-array-whose-sum-is-closest-to-a-given-number/

public class FindClosestTriplet {

    public int threeSumClosest(ArrayList<Integer> A, int B) {

        Collections.sort(A);
        int n = A.size();
        int closetsum = 0;
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < n - 2; i++) {
            int ptr1 = i + 1;
            int ptr2 = n - 1;

            while (ptr1 < ptr2) {

                int tripletsum = A.get(i) + A.get(ptr1) + A.get(ptr2);
                if (tripletsum == B) {
                    return B;
                }
                if (Math.abs(B - tripletsum) < minDiff) {
                    closetsum = tripletsum;
                    minDiff = Math.abs(B - tripletsum);
                }
                if (tripletsum > B) {
                    ptr2--;
                } else {
                    ptr1++;
                }
            }
        }
        return closetsum;
    }
}
