#include <iostream>
#include <vector>
#include <map>

using namespace std;

class LargestSequenceZeroSum {

public:
    vector<int> isZero(vector<int> &A) {
        vector<int> aux;
        map<int, int> hash;
        int start = 0;
        int len = 0;

        for (int i = 0; i < A.size(); i++){
            if (i == 0)
                aux.push_back(A[0]);
            else
                aux.push_back(aux[i - 1] + A[i]);
                
            if (aux[i] == 0){
                len = i + 1;
                start = 0;
            }
        }

        for (int i = 0; i < aux.size(); i++){
            if (hash.count(aux[i]) == 1){
                if (i - hash[aux[i] > len]){
                    len = i - hash[aux[i]];
                    start = hash[aux[i]] + 1;
                }
            }
            else{
                hash[aux[i]] = i;
            }
        }

        vector<int> out;
        for (int i = start; i < start + len; i++){
            out.push_back(A[i]);
        }
        return out;
    }
};

/*
package Miscellaneous;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// https://www.interviewbit.com/problems/largest-continuous-sequence-zero-sum/
public class LargestSequenceZeroSum {

    // optimised
    public ArrayList<Integer> lszero2(ArrayList<Integer> A) {
        Map<Integer, Integer> store = new HashMap<>();
        int left = 1, right = -1, currentSum = 0;
        store.put(0, -1);
        for (int i = 0; i < A.size(); i++) {
            currentSum += A.get(i);
            if (store.containsKey(currentSum)) {
                int l = store.get(currentSum) + 1;
                if (i - l > right - left) {
                    left = l;
                    right = i;
                }
            } else
                store.put(currentSum, i);
        }
        ArrayList<Integer> result = new ArrayList<>();
        if (left == -1)
            return result;
        for (int i = left; i <= right; i++)
            result.add(A.get(i));
        return result;
    }

    // Brute Force
    public int[] lszero(int[] A) {
        int max = Integer.MIN_VALUE;
        int startptr = -1, endptr = -1;
        for (int i = 0; i < A.length; i++) {
            int sum = 0;
            for (int j = i; j < A.length; j++) {
                sum += A[j];
                if (sum == 0 && (j - i) > max) {
                    max = j - i;
                    startptr = i;
                    endptr = j;
                }
            }
        }

        if (startptr == -1) {
            return new int[0];
        }
        int[] result = new int[max + 1];
        int k = 0;
        for (int i = startptr; i <= endptr; i++) {
            result[k++] = A[i];
        }
        return result;
    }
}
*/