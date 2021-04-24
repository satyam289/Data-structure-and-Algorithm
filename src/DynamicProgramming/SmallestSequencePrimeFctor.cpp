#include <iostream>
#include <vector>

using namespace std;

//https://www.interviewbit.com/problems/smallest-sequence-with-given-primes/
class SmallSeqPrime{

    vector<int> solve(int A, int B, int C, int D){
        vector<int> ans;
        ans.push_back(1);
        int i=0, j=0, k=0;
        while(D--){
            int x = min(ans[i]*A, ans[j]*B, ans[k]*C);
            if(x==ans[i]*A)
                i++;
            if(x==ans[j]*B)
                j++;
            if(x==ans[k]*C)
                k++;
            ans.push_back(x);
        }
        ans.erase(ans.begin());
        return ans;
    }

    int main(){
        vector<int> res = solve(2,3,5,8);
        for(int i : res){
            cout << i << endl;
        }
    }
};

/*
package DynamicProgramming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

public class SmallestSequencePrimeFctor {
    
    public ArrayList<Integer> solve1(int A, int B, int C, int D) {

        ArrayList<Integer> res = new ArrayList<>();
        res.add(1);
        int a, b, c;
        a = b = c = 0;

        for (int i = 0; i < D; i++) {
            
            int mA = res.get(a) * A;
            int mB = res.get(b) * B;
            int mC = res.get(c) * C;
            int min = mA;
            min = Math.min(min, mB);
            min = Math.min(min, mC);
            res.add(min);

            if (min == mA)
                a++;
            if (min == mB)
                b++;
            if (min == mC)
                c++;
        }
        res.remove(0); // removing first element
        return res;
    }

    // 0(nlogn)
    public ArrayList<Integer> solve(int A, int B, int C, int D) {
        ArrayList<Integer> res = new ArrayList<>();

        TreeSet<Integer> treeSet = new TreeSet<>();  // 
        treeSet.add(A);
        treeSet.add(B);
        treeSet.add(C);

        for (int i = 0; i < D; i++) {
            int temp = treeSet.first(); // Minimum
            treeSet.remove(temp);
            res.add(temp);

            treeSet.add(temp * A);
            treeSet.add(temp * B);
            treeSet.add(temp * C);
        }
        return res;
    }
    
    // 0(nlogn) + Extra space 
    public int[] solve2(int A, int B, int C, int D) {
        int[] result = new int[D];
        int index = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(A);
        minHeap.offer(B);
        minHeap.offer(C);
        Set<Integer> visited = new HashSet<>();
        while (index < D) {
            int nextMin = minHeap.poll(); // minimum 
            if (visited.add(nextMin)) { // If this set already contains the element, the set unchanged and returns false
                result[index++] = nextMin;
                minHeap.offer(nextMin * A);
                minHeap.offer(nextMin * B);
                minHeap.offer(nextMin * C);
            }
        }
        return result;
    }
}
*/