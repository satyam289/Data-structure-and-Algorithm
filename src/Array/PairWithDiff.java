package Array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

//https://www.interviewbit.com/problems/pair-with-given-difference/
/*
Given an one-dimensional unsorted array A containing N integers.
You are also given an integer B, find if there exists a pair of elements in the array whose difference is B.
Return 1 if any such pair exists else return 0.

Input Format
First argument is an integer array A of size N.
Second argument is an integer B.

Output Format
Return 1 if any such pair exists else return 0.

Example Input
Input 1:
A = [5, 10, 3, 2, 50, 80]
B = 78
Output: 1
Pair (80, 2) gives a difference of 78.

Input 2:
A = [-10, 20]
B = 30
Output: 1
Pair (20, -10) gives a difference of 30 i.e 20 - (-10) => 20 + 10 => 30
*/

public class PairWithDiff {

    public int solve(ArrayList<Integer> A, int B) {
        if (B == 0) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < A.size(); i++) {
            map.put(B + A.get(i), 0);
        }
        for (int i = 0; i < A.size(); i++) {
            if (map.containsKey(A.get(i))) {
                return 1;
            }
        }

        return 0;
    }

    public int solve2(ArrayList<Integer> A, int B) {
        if (B == 0) {
            return 0;
        }
        HashMap<Integer, Integer> myMap = new HashMap<>();
        for (int a : A) {
            myMap.put(a, 1);
        }
        for (int a : A) {
            if (myMap.containsKey(B + a) || myMap.containsKey(a - B)) {
                return 1;
            }
        }
        return 0;
    }

    public int solve3(ArrayList<Integer> A, int B) {
        Collections.sort(A);
        int start = 0;
        int end = 1;
        while (start < A.size() && end < A.size()) {
            int diff = A.get(end) - A.get(start);
            if (diff == B && start != end) {
                return 1;
            } else if (diff < B) {
                end++;
            } else {
                start++;
            }
        }
        return 0;
    }
}
