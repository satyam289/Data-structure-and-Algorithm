#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

//https://www.interviewbit.com/problems/max-distance/
// Given an array A of integers, find the maximum of j - i subjected to the constraint of A[i] <= A[j].
class MaxDistance {

public:
    int maximumGap(const vector<int> &num){
        if(num.size() == 0)
           return -1;
        if(num.size() == 1)
            return 0;
        vector<pair<int, int>> toSort;
        for(int i =0; i<num.size(); i++){
            toSort.push_back(make_pair(num[i], i));
        }       
        sort(toSort.begin(), toSort.end());
        int len = toSort.size();
        int maxIndex = toSort[len -1].second;
        int ans = 0;
        for(int i = len -2; i >=0; i--){
            ans = max(ans, maxIndex - toSort[i].second);
            maxIndex =max(maxIndex, toSort[i].second);
        }
        return ans;
    }
};   
/*     
    public int maximumGap(final List<Integer> A) {
        int n = A.size();
        int rightMax[] = new int[n];
        rightMax[n-1] = A.get(n-1);
        for(int i=n-2; i>=0; i--) {
            rightMax[i] = Math.max(rightMax[i+1], A.get(i));
        }
        int i=0, j=0, max=0;
        while(i < n && j < n) {
            if(A.get(i) <= rightMax[j]) {
                max = Math.max(max, j-i);
                j++;
            }
            else {
                i++;
            }
        }
        return max;
    }
    

    // second Apporach Time Complexity : 0(n) Space : 0(n)
    public int maximumGap2(final List<Integer> A) {
       int maxDiff;
       int i, j;
       int n = A.size();
       int RMax[] = new int[n];
       int LMin[] = new int[n];

       LMin[0] = A.get(0);
       for (i = 1; i < n; ++i) {
           LMin[i] = Math.min(A.get(i), LMin[i - 1]);
       }
       RMax[n - 1] = A.get(n - 1);
       for (j = n - 2; j >= 0; --j) {
           RMax[j] = Math.max(A.get(j), RMax[j + 1]);
       }

       i = 0; j = 0; maxDiff = 0;
       while (j < n && i < n) {
           if (LMin[i] <= RMax[j]) {
               maxDiff = Math.max(maxDiff, j - i);
               j++;
           }
           else {
               i++;
           }
       }
       return maxDiff;
   } */
