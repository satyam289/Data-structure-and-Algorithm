package Array;

import java.util.Arrays;
import java.util.List;

class MaxContigousSubArraySum {

    public static int maxSubArray(final List<Integer> A) {
        int maxSofar = 0;
        int globalmaximum = -1;
        int minNeg = Integer.MIN_VALUE;
        for(int i=0; i<A.size(); i++){
            int val = A.get(i);
             maxSofar += val;
            if(val > minNeg){
                minNeg = val;
            }
            if(maxSofar < 0){
                maxSofar = 0;
            }else{
               if(maxSofar > globalmaximum){
                  globalmaximum = maxSofar;
                }
            }
        }
        if(globalmaximum == -1){  // when all the values is negative
            return minNeg;
        }
        return globalmaximum;
    }

    public static void main(String [] args) {
        List<Integer> list = Arrays.asList(-2, 1, -3, 4, -1, 2, 1, -5, 4);
        int result = maxSubArray(list);      
        System.out.println(result);
    }
}