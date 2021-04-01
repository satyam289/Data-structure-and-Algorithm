/* ----JAVA Implementation---
package Array;

import java.util.ArrayList;
import java.util.List;


public class RangeSearchSortedArray {
    
    public static ArrayList<Integer> searchRange(final List<Integer> input, int  element){
        ArrayList<Integer> result = new ArrayList<>();
        result.add(searchRange(input, element, true));
        result.add(searchRange(input, element, false));
        return result;
    }

    private static int searchRange(List<Integer> input, int element, boolean isLeftBiased) {
        int low = 0;
        int high = input.size()-1;
        int ret = -1;
        while (low <= high) {

            int mid = (low + high) / 2;
            if (input.get(mid) == element) {
                ret = mid;
                if (isLeftBiased)
                    high = mid - 1;
                else
                    low = mid + 1;
            } else if (input.get(mid) < element) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ret;
    }

    public static void main(String [] argrs){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(5);
        list.add(5);
        list.add(5);
        searchRange(list, 5);
    }
}
*/

#include <iostream>
#include <vector>

using namespace std;

//https://www.interviewbit.com/problems/search-for-a-range/
class RangeSearch {

public:
    vector<int> searchRange(const vector<int> &A, int target){
        int n = A.size();
        int i = 0, j = n-1;
        vector<int> ret(2, -1);

        while (i < j)
        {
            int mid = (i + j) / 2;
            if (A[mid] < target)
                i = mid + 1;
            else
                j = mid; // left biased
        }

        if (A[i] != target) // can't find the searched element
            return ret;
        else
            ret[0] = i; // leftmost boundary

        j = n-1; // we dont need to i to zero
        while (i < j)
        {
            int mid = (i + j) / 2 + 1; // making right biased
            if (A[mid] > target)
                j = mid - 1;
            else
                i = mid;
        }
        ret[1] = j; //rightmost boundary
        return ret;
    }
};    