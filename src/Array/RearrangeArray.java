package Array;

import java.util.ArrayList;

// https://www.geeksforgeeks.org/rearrange-given-array-place/
// Convert arr[i] to arr[arr[i]]
public class RearrangeArray {
    /* 
      Time Complexity : 0(n) Space : 0(1)
      Apporach : swap(arr[i] , arr[arr[i]])
       int combineValue = a + b*n
       int oldValue = combineValue % n;
       int newValue = combineValue / n;
       As we know all the element is between 1, 2, 3...n, we can extract the 'b' old value from combineValue using % n  
       @ Here we are adding, a + (b % n)*n  
    */
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
}
