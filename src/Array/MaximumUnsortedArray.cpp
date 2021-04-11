#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

class subUnsort{
    public:
        vector<int> findUnortedIndex(vector<int> &A){
            vector<int> ans;
            int n = A.size();
            int i = 0, j = n-1;

            while(i < n-1 and A[i] <= A[i+1]){
                i++;
            }
            while(j > 0 and A[j] >= A[j-1]){
                j--;
            }
            if(i == n-1) {
                ans.push_back(-1);
                return ans;
            }
            int minimum = A[i+1];
            int maximum = A[i+1];
            for(int k = i; k <= j; k++){
                minimum = min(minimum, A[k]);
                maximum = max(maximum, A[k]);
            }
            int startpts = 0 , endptr = n-1;
            while(A[startpts] <= minimum and startpts <= i){
                startpts++;
            }
            while(A[endptr] >= maximum and endptr >= j){
                endptr--;
            }
            ans.push_back(startpts);
            ans.push_back(endptr);
            return ans;
        }
};

/*   Java Implementation 
package Array;

import java.util.ArrayList;

public class MaximumUnsortedArray {
    
    public ArrayList<Integer> subUnsort(ArrayList<Integer> A) {
        int n = A.size();
        int[] mins = new int[n];
        int[] maxs = new int[n];
        maxs[0] = A.get(0);
        for(int i = 1; i < n; i++) {
            maxs[i] = Math.max(A.get(i), maxs[i-1]);
        }

        mins[n-1] = A.get(n-1);
        for(int i = n - 2; i >= 0; i--) {
            mins[i] = Math.min(A.get(i), mins[i+1]);
        }
        ArrayList<Integer> result = new ArrayList<Integer>();
        
        int start = 0;
        while (start < n && mins[start] == A.get(start)) {
            start++;
        }

        int end = n - 1;
        while (end >= 0 && maxs[end] == A.get(end)) {
            end--;
        }
        
        if(start == n) 
            result.add(Integer.valueOf(-1));
        else {
            result.add(Integer.valueOf(start));
            result.add(Integer.valueOf(end));
        }
        return result;
    }

    // Second Apporach
    public int[] subUnsort(int[] arr) {
        int[] result = new int[2];
        int start = 0, end = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                start = i - 1;
                break;
            }
        }
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i + 1] < arr[i]) {
                end = i + 1;
                break;
            }
        }
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = start; i <= end; i++) {
            min = Math.min(arr[i], min);
            max = Math.max(arr[i], max);
        }
        for (int i = 0; i < start; i++) {
            if (arr[i] > min) {
                start = i;
                break;
            }
        }
        for (int i = arr.length - 1; i > end; i--) {
            if (arr[i] < max) {
                end = i;
                break;
            }
        }
        result[0] = start;
        result[1] = end;
        if (start == end) {
            return new int[] { -1 };
        }
        return result;
    }
}
*/