package DynamicProgramming;

import java.util.Arrays;

public class LeastIncreasingSubseqence {

    public static void main(String[] args) {
        int[] input = {10, 22, 9, 33, 21, 50, 41, 60 };
        findLIS(input);
    }

    public static void findLIS(int [] arr){

        int [] temp = new int[arr.length];
        Arrays.fill(temp, 1);

        for(int i=0; i<arr.length; i++) {
            for(int j=i-1; j>=0; j--) {

                if(arr[i] > arr[j])
                  temp[i] = Math.max(temp[j] +1, temp[i]);
            }
        }
        int max=Integer.MIN_VALUE;
        for(int val: temp)
            if(val> max)
                max = val;

        System.out.println(max);

    }

}
