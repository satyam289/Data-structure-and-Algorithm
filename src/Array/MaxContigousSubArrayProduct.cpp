#include <vector>

using namespace std;

int maxProduct(const vector<int> &A)
{
    int maxCount = A[0];
    int minCount = A[0];
    int ans = A[0];
    for (int i = 1; i < A.size(); i++)
    {
        if (A[i] < 0)
            swap(maxCount, minCount);
        maxCount = max(A[i], maxCount * A[i]);
        minCount = min(A[i], minCount * A[i]);
        ans = max(ans, maxCount);
    }
    return ans;
}
/*
package Array;

import java.util.List;

// https://www.interviewbit.com/problems/max-product-subarray/
public class MaxContigousSubArrayProduct {

    // Time Complexity : 0(N)
    public int maxProduct1(final int[] A) {

        if (A.length == 1) {
            return A[0];
        }
        int max_ending_here = 1;
        int min_ending_here = 1;
        int max_so_far = 0;
        int positiveflag = 0;
        for (int i = 0; i < A.length; i++) {

            if (A[i] == 0) {
                max_ending_here = 1;
                min_ending_here = 1;
            } else if (A[i] > 0) {
                max_ending_here = max_ending_here * A[i];
                min_ending_here = Math.min(min_ending_here * A[i], 1);
                positiveflag = 1;
            } else {
                int temp = max_ending_here;
                max_ending_here = Math.max(min_ending_here * A[i], 1);
                min_ending_here = temp * A[i];
            }

            if (max_so_far < max_ending_here) {
                max_so_far = max_ending_here;
            }
        }
        if (positiveflag == 0 && max_so_far == 1) {
            return 0;
        }
        return max_so_far;
    }

    // Similar way
    public int maxProduct2(final List<Integer> A) {
        if (A.size() == 0) {
            return 0;
        }
        int[] posProduct = new int[A.size()];
        int[] negProduct = new int[A.size()];
        int[] maxProduct = new int[A.size()];
        posProduct[0] = negProduct[0] = maxProduct[0] = A.get(0);
        for (int i = 1; i < A.size(); i++) {
            int a = posProduct[i - 1] * A.get(i);
            int b = negProduct[i - 1] * A.get(i);
            posProduct[i] = Math.max(Math.max(a, b), A.get(i));
            negProduct[i] = Math.min(Math.min(a, b), A.get(i));
            maxProduct[i] = Math.max(posProduct[i], maxProduct[i - 1]);
        }
        return maxProduct[A.size() - 1];
    }

    // better
    public int maxProduct3(final List<Integer> A) {
        int curr_max, curr_min, prev_max, prev_min, ans;
        curr_max = curr_min = prev_max = prev_min = ans = A.get(0);
        int n = A.size();
        for (int i = 1; i < n; i++) {
            int num = A.get(i);
            curr_max = Math.max(Math.max(prev_max * num, prev_min * num), num);
            curr_min = Math.min(Math.min(prev_max * num, prev_min * num), num);
            ans = Math.max(ans, curr_max);
            prev_max = curr_max;
            prev_min = curr_min;
        }
        return ans;
    }
}
*/