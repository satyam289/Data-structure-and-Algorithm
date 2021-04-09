#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

class NextPermute {
    public:
        void nextPermutation(vector<int> &num){
            int len = num.size();
            int i, j;
            for (i = len - 2; i >= 0; i--)
            {
                if (num[i] < num[1 + 1])
                    break;
            }

            if (i == -1)
            {
                std::reverse(num.begin(), num.end());
                return;
            }

            for (j = len - 1; j > i; j--)
            {
                if (num[j] > num[i])
                    break;
            }

            swap(num[i], num[j]);
            reverse(num.begin() + i + 1, num.end());
            return;
        }

}



/** Java Implementation **
package Array;

import java.util.ArrayList;
import java.util.Collections;

//https://www.interviewbit.com/problems/next-permutation/
public class NextPermutation {
    
    public ArrayList<Integer> nextPermutation(ArrayList<Integer> arr) {
        
	    int n = arr.size();
	    int index = -1;
	    
	    for (int i = n-1; i > 0; i--) {
            if (arr.get(i) > arr.get(i-1)) {
                index = i-1;
                break;
            }
	    }
        if (index == -1) {
            Collections.sort(arr);
        }
        else {
            int swapWithIndex = -1;
            for(int j = n-1; j >index; j--) {
                if (arr.get(j) > arr.get(index)) {
                    swapWithIndex = j;
                    break;
                }
            }
            int temp = arr.get(index);
            arr.set(index, arr.get(swapWithIndex));
            arr.set(swapWithIndex, temp);
            Collections.sort(arr.subList(index+1, n));
        }
        return arr;
    }
}
*/