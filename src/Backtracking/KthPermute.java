package Backtracking;

import java.util.ArrayList;
import java.util.Collections;

//https://www.interviewbit.com/problems/kth-permutation-sequence/
//https://www.geeksforgeeks.org/find-the-k-th-permutation-sequence-of-first-n-natural-numbers/
public class KthPermute {

    ArrayList<String> res = new ArrayList<>();

    // Naive Apporach : Time Complexity : 0(N!)
    public String getPermutation(int n, int k) {
        char[] arr = new char[n];
        for (int i = 1; i <= n; i++) {
            arr[i - 1] = (char) (i + '0');
        }
        getPermutationRec(arr, 0, n - 1);
        Collections.sort(res);
        return res.get(k - 1);
    }

    private void getPermutationRec(char[] arr, int start, int end) {
        if (start == end) {
            res.add(new String(arr));
            return;
        }
        for (int i = start; i <= end; i++) {
            swap(arr, start, i);
            getPermutationRec(arr, start + 1, end);
            swap(arr, i, start);
        }
    }

    private void swap(char[] arr, int pos1, int pos2) {
        char temp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = temp;
    }

    // Optimised
    public String getPermutation2(int n, int k) {
        int fact = 1;
        ArrayList<Integer> number = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            fact *= i;
            number.add(i);
        }

        fact = fact / n; // (n-1)!
        String result = "";
        k = k - 1;
        while (true) {
            result += number.get(k / fact);
            number.remove(k / fact);
            if (number.size() == 0) {
                break;
            }
            k = k % fact;
            fact /= number.size();
        }
        return result;
    }
}
