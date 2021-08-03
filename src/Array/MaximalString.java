package Array;

//https://www.interviewbit.com/problems/maximal-string/
/*
Given a string A and integer B, what is maximal lexicographical stringthat can be made from A if you do atmost B swaps.
Input Format
First argument is string A.
Second argument is integer B.

Output Format
Return a string, the naswer to the problem.

Input:
A = "254"
B = 1
Output:
524
Swap 2 and 5.

Input:
A = "254'
B = 2
Output:
542
Swap 2 and 5 then swap 4 and 2.

*/
public class MaximalString {

    public String solve(String A, int B) {
        char[] arr = A.toCharArray();
        sol = A;
        gen(arr, 0, B);
        return sol;
    }

    String sol;

    public void gen(char[] arr, int ind, int B) {
        if (ind >= arr.length)
            return;
        if (B == 0) {
            String str = String.valueOf(arr);
            if (str.compareTo(sol) > 0) {
                sol = str;
            }
            return;
        }

        for (int i = ind + 1; i < arr.length; i++) {
            if (arr[i] > arr[ind]) {
                swap(arr, ind, i);
                String str = String.valueOf(arr);
                if (str.compareTo(sol) > 0) {
                    sol = str;
                }
                gen(arr, ind + 1, B - 1);
                swap(arr, ind, i);
            }
        }
        gen(arr, ind + 1, B);
    }

    public void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    int max = 0;

    void generate(char[] A, int B) {
        if (B == 0) {
            return;
        }

        for (int i = 0; i < A.length - 1; i++) {
            for (int j = i + 1; j < A.length; j++) {
                if (A[j] > A[i]) {
                    swap(A, j, i);

                    int temp = Integer.parseInt(String.valueOf(A));
                    max = Math.max(max, temp);

                    generate(A, B - 1);
                    swap(A, j, i);
                }
            }
        }
    }

    public String solve2(String s, int B) {
        char[] A = s.toCharArray();
        generate(A, B);
        return String.valueOf(max);
    }
}
