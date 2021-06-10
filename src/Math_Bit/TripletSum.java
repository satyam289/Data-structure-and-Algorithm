package Math_Bit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

class TripletSum {

    // Time Complexity = 0(n2), space Complexity = 0(n)
    public static void findAllTripletSum(int[] input, int target) {

        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < input.length - 2; i++) {
            int reduceSum = target - input[i];
            for (int j = i + 1; j < input.length; j++) {
                if (set.contains(reduceSum - input[j])) {
                    System.out.println(input[i] + "," + input[j] + "," + (reduceSum - input[j]));
                    return;
                } else {
                    set.add(input[j]);
                }
            }
        }
    }

    // Time Complexity = 0(n2) , space Complexity = 0(1)
    public static void findAllTripletSumOptimized(int[] input, int target) {

        Arrays.sort(input);
        for (int i = 0; i < input.length - 2; i++) {
            int reduceSum = target - input[i];
            int j = i + 1;
            int k = input.length - 1;
            while (j < k) {
                if ((reduceSum - input[j] - input[k]) == 0) {
                    System.out.println(input[i] + "," + input[j] + "," + input[k]);
                    return;
                } else if (reduceSum > (input[j] + input[k])) {
                    j++;
                } else {
                    k--;
                }
            }
        }
    }

    // https://www.interviewbit.com/problems/3-sum-zero/
    // Find all unique triplets in the array which gives the sum of zero.
    public ArrayList<ArrayList<Integer>> threeSum(ArrayList<Integer> A) {
        Collections.sort(A);
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        int n = A.size();
        for (int i = 0; i < n - 2; i++) {

            if (i == 0 || (long) A.get(i) != (long) A.get(i - 1)) { // consider only first index of duplicate for
                                                                    // possible sum
                int j = i + 1;
                int k = n - 1;
                while (j < k) {

                    if ((long) A.get(i) + (long) A.get(j) + (long) A.get(k) == 0L) {
                        ArrayList<Integer> arr = new ArrayList<Integer>();
                        arr.add(A.get(i));
                        arr.add(A.get(j));
                        arr.add(A.get(k));
                        // if(!result.contains(arr)){ // alternate option for duplicate
                        result.add(arr);
                        // }
                        while (j < k && (long) A.get(j) == (long) A.get(j + 1)) { // skip duplicate lower end
                            j++;
                        }
                        while (j < k && (long) A.get(k) == (long) A.get(k - 1)) { // skip duplicate hiher end
                            k--;
                        }
                        j++;
                        k--;
                    } else if ((0L - (long) A.get(i)) > ((long) A.get(j) + (long) A.get(k))) {
                        j++;
                    } else {
                        k--;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int A[] = { 1, 4, 45, 6, 10, 8 };
        int sum = 22;
        findAllTripletSum(A, sum);
        findAllTripletSumOptimized(A, sum);
    }
}