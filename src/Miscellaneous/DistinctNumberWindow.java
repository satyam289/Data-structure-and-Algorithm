package Miscellaneous;

import java.util.ArrayList;
import java.util.HashMap;

//https://www.interviewbit.com/problems/distinct-numbers-in-window/
public class DistinctNumberWindow {
    public ArrayList<Integer> dNums(ArrayList<Integer> A, int B) {
        ArrayList<Integer> result = new ArrayList<>();
        if (A == null || A.size() == 0 || B == 0) {
            return result;
        }
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int distinctCount = 0;
        for (int i = 0; i < B; i++) {
            if (!hashMap.containsKey(A.get(i))) {
                hashMap.put(A.get(i), 1);
                distinctCount++;
            } else {
                int temp = hashMap.get(A.get(i));
                hashMap.put(A.get(i), temp + 1);
            }
        }
        result.add(distinctCount);
        for (int i = B; i < A.size(); i++) {
            int previous = A.get(i - B);
            if (hashMap.get(previous) == 1) {
                distinctCount--;
            }
            hashMap.put(previous, hashMap.get(previous) - 1);
            int current = A.get(i);
            if (!hashMap.containsKey(current) || hashMap.get(current).intValue() == 0) {
                hashMap.put(current, 1);
                distinctCount++;
            } else {
                int temp = hashMap.get(A.get(i));
                hashMap.put(A.get(i), temp + 1);
            }
            result.add(distinctCount);
        }
        return result;
    }
}
