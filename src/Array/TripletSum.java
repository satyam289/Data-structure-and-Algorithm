package Array;

import java.util.Arrays;
import java.util.HashSet;

class TripletSum {
    
    //Time Complexity = 0(n2), space Complexity = 0(n)
    public static void findAllTripletSum(int[] input, int target){

        HashSet<Integer> set = new HashSet<>();
        for(int i=0; i<input.length-2; i++) {
            int reduceSum = target - input[i];
            for(int j=i+1; j<input.length; j++) {
                if(set.contains(reduceSum - input[j])){
                   System.out.println(input[i] + "," + input[j] + "," + (reduceSum - input[j]));
                   return;
                }else{
                  set.add(input[j]);
                }
            }
        }
    }
    
    //Time Complexity = 0(n2) , space Complexity = 0(1)
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


    public static void main(String[] args) 
    { 
        int A[] = { 1, 4, 45, 6, 10, 8 }; 
        int sum = 22; 
        findAllTripletSum(A, sum); 
        findAllTripletSumOptimized(A, sum);
    } 
}