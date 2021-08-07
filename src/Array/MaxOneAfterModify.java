package Array;

import java.util.ArrayList;

/*
Given a binary array A and a number B, we need to find length of the longest subsegment of ‘1’s possible by changing at most B ‘0’s.



Problem Constraints
 1 <= N, B <= 105

 A[i]=0 or A[i]=1



Input Format
First argument is an binary array A.

Second argument is an integer B.



Output Format
Return a single integer denoting the length of the longest subsegment of ‘1’s possible by changing at most B ‘0’s.



Example Input
Input 1:
 A = [1, 0, 0, 1, 1, 0, 1]
 B = 1
Output:
 4
Here, we should only change 1 zero(0). Maximum possible length we can get is by changing the 3rd zero in the array, we get a[] = {1, 0, 0, 1, 1, 1, 1}

Input 2:
 A = [1, 0, 0, 1, 0, 1, 0, 1, 0, 1]
 B = 2
Output :
 5
 Here, we can change only 2 zeros. Maximum possible length we can get is by changing the 3rd and 4th (or) 4th and 5th zeros.
*/

//https://www.interviewbit.com/problems/maximum-ones-after-modification/
public class MaxOneAfterModify {

    public int solve(ArrayList<Integer> A, int B) {
        int i = 0, j;
        for (j = 0; j < A.size(); ++j) {
            if (A.get(j) == 0)
                B--;
            if (B < 0 && A.get(i++) == 0)
                B++;
        }
        return j - i;
    }

    public int solve2(ArrayList<Integer> A, int B) {
        int i = 0;
        int j = 0;
        int max = 0;
        for (i = 0; i < A.size(); i++) {
            if (A.get(i) == 0) {
                B--;
            }
            while (j <= i && B < 0) {
                if (A.get(j) == 0) {
                    B++;
                }
                j++;
            }
            max = Math.max(max, i - j + 1);
        }

        return max;
    }

    public int solve3(ArrayList<Integer> A, int B) {

        int start = 0, end = 0;
        int result = Integer.MIN_VALUE;
        int counter = B;

        while (end < A.size()) {
            if (A.get(end) == 1) {
                end++;
            } else if (counter > 0) {
                end++;
                counter--;
            } else {
                if (A.get(start) == 0)
                    counter++;
                start++;

            }
            result = Math.max(result, end - start);
        }
        return result;
    }
}
