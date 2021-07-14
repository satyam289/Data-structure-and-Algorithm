package Math_Bit;

import java.util.ArrayList;

/*
https://www.geeksforgeeks.org/find-the-missing-number/
  1 2 3 _ 5  
  1 5 2 _ 3
  @here missing number is 4
*/
public class MissingElement {

    public int findMissing(int[] arr, int n) {
        int total = (n + 1) * (n + 2) / 2;

        for (int i = 0; i < arr.length; i++) {
            total -= arr[i];
        }
        return total;
    }

    public int findMissing2(int[] arr, int n) {
        int x1 = arr[0];
        int x2 = 1;
        for (int i = 1; i < n; i++) {
            x1 = x1 ^ arr[i];
        }
        for (int i = 2; i < n + 1; i++) {
            x2 = x2 ^ i;
        }
        return x1 ^ x2;
    }
  
   //https://www.interviewbit.com/problems/first-missing-integer/
   public int firstMissingPositive(ArrayList<Integer> A) {
        for (int i = 0; i < A.size(); i++) {
            int num = A.get(i);
            int pos = num - 1;
            
            if (pos >= 0 && pos < A.size() && A.get(pos) != num) {
                A.set(i, A.get(pos));
                A.set(pos, num);
                i--;
            }
        }
        
        for (int i = 0; i < A.size(); i++)
            if (A.get(i) != i + 1)
                return i + 1;
        
        return A.size() + 1;
    }
}
