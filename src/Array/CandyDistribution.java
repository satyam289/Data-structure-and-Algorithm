package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class CandyDistribution {

   public static int candyRecursion(int[] A) {
       int n = A.length;
       int[] candies = new int[n];
       for (int i = 0; i < n; i++) {
           candyRecursion(A, candies, i);
       }
       
       int sum = 0;
       for (int i = 0; i < n; i++) {
           System.out.println(candies[i] + " ->");
           sum += candies[i];
       }
       return sum;
   }
   
   public static int candyRecursion(int[] A, int[] candies, int index) {
       
       if (candies[index] != 0) {
           return candies[index];
       }
       int v =0;
       if (index < (A.length -1) && A[index] > A[index + 1]) {
           v= Math.max(v, candyRecursion(A, candies, index + 1)) ;
       }
       if (index > 0 && A[index] > A[index - 1]) {
           v = Math.max(v,candyRecursion(A, candies, index - 1));
       }
       return candies[index] = v + 1; 
   }

   // optimise (forward pass + backward pass)
   public int candy(List<Integer> A) {
       int n = A.size();
       int[] candies = new int[n];
       Arrays.fill(candies, 1);
       for (int i = 0; i < n - 1; i++) {
           if (A.get(i + 1) > A.get(i))
               candies[i + 1] = candies[i] + 1;
       }
       for (int i = n - 1; i > 0; i--) {
           if (A.get(i - 1) > A.get(i) && candies[i - 1] <= candies[i])
               candies[i - 1] = candies[i] + 1;
       }
       int sum = 0;
       for (int i = 0; i < n; i++) {
           sum += candies[i];
       }
       return sum;
   }

   public static void main(String[] args) {
       CandyDistribution cd = new CandyDistribution();
       List<Integer> list = new ArrayList<>();
       list.add(1);
       list.add(5);
       list.add(2);
       list.add(1);
       System.out.println(cd.candy(list));

       int[] arr = { 1, 5, 2, 1 };
       System.out.println(candyRecursion(arr));
    }
}
