package Array;

import java.util.ArrayList;

/*
https://www.interviewbit.com/problems/max-continuous-series-of-1s/
You are given with an array of 1s and 0s. And you are given with an integer M, which signifies number of flips allowed.
Find the position of zeros which when flipped will produce maximum continuous series of 1s.
For this problem, return the indices of maximum continuous series of 1s in order.

Input : 
Array = {1 1 0 1 1 0 0 1 1 1 } 
M = 1

Output : 
[0, 1, 2, 3, 4] 
*/

public class MaxContinousOnes {

    public ArrayList<Integer> maxone(ArrayList<Integer> A, int B) {
        int index = 0, count = 0;
        int left = 0, right = 0;
        int flips = B;

        while (right < A.size()) {
            if (A.get(right) == 1) {
                right++;
            } else if (flips > 0) { // A.get(right) == 0
                right++;
                flips--;
            } else if (left < right) { // A.get(right) == 0 && flips == 0
                flips += 1 - A.get(left);
                left++;
            } else { // A.get(right) == 0 && flips == 0 && left == right
                left++;
                right++;
            }

            if (right - left > count) {
                index = left;
                count = right - left;
            }
        }

        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < count; i++)
            result.add(index + i);
        return result;
    }

    public ArrayList<Integer> maxone2(ArrayList<Integer> A, int B) {
        int maxCount = Integer.MIN_VALUE;
        int allowedCount = B;
        int i = 0;
        int count = 0;
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> tempList = new ArrayList<>();
        boolean firstZero = true;
        int j = 0;
        while (i <= A.size() - 1) {
            if (A.get(i) == 1) {
                tempList.add(i);
                i++;
                count++;
            } else {
                if (firstZero) {
                    j = i + 1;
                    firstZero = false;
                }
                if (allowedCount > 0) {
                    tempList.add(i);
                    i++;
                    count++;
                    allowedCount--;
                } else {
                    if (count > maxCount) {
                        result = new ArrayList<>();
                        maxCount = count;
                        result.addAll(tempList);
                    }
                    count = 0;
                    allowedCount = B;
                    tempList = new ArrayList<>();
                    firstZero = true;
                    i = j;
                }
            }
        }
        if (count > maxCount) {
            result = new ArrayList<>();
            result.addAll(tempList);
        }
        return result;
    }
}
