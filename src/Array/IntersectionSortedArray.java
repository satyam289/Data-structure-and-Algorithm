package Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IntersectionSortedArray {
    
    //Best Solution:  0(n) Two Pointer Apporach without using space 
    public ArrayList<Integer> intersect1(final List<Integer> A, final List<Integer> B) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (A == null || A.size() == 0 || B == null || B.size() == 0) {
            return res;
        }
        int indexA = 0;
        int indexB = 0;
        while (indexA < A.size() && indexB < B.size()) {
            if (A.get(indexA).equals(B.get(indexB))) {
                res.add(A.get(indexA));
                indexA++;
                indexB++;
            } else if (A.get(indexA) < B.get(indexB)) {
                indexA++;
            } else {
                indexB++;
            }
        }
        return res;
    }
    
    // Brute Force: 0(n*m) Search linearly character by character
    // Better Solution : 0(m*log n) search using binary search 
    // Optimial Solutiuon: 0(n) Using Hash Map 
    public ArrayList<Integer> intersect2(final List<Integer> A, final List<Integer> B) {

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : A) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        ArrayList<Integer> result = new ArrayList<Integer>();

        for (int j : B) {
            if (map.containsKey(j) && map.get(j) > 0) {
                result.add(j);
                map.put(j, map.get(j) - 1);
            }
        }
        return result;
    }
}

//https://www.interviewbit.com/problems/diffk/
class DiffK{

    //0(n)
    public int diffPossible1(ArrayList<Integer> A, int B) {
        int i = 0;
        int j = 1;
        while (j < A.size() && i < A.size()) {
            int diff = (A.get(j) - A.get(i));
            if (diff == B && i != j)
                return 1;
            if (diff < B)
                j++;
            else
                i++;
        }
        return 0;
    }

    // 0(nlogn)
    public int diffPossible2(ArrayList<Integer> a, int b) {
        int n = a.size();
        for (int i = 0; i < n - 1; i++)
            if (dobinarySearch(a, a.get(i) + b, i + 1, n - 1))
                return 1;
        return 0;
    }

	private boolean dobinarySearch(ArrayList<Integer> a, int b, int lo, int hi) {
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (a.get(mid) == b)
                return true;
            else if (a.get(mid) > b)
                hi = mid - 1;
            else
                lo = mid + 1;
        }
        return false;
    }
}