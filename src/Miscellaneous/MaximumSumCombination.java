package Miscellaneous;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;

//https://www.interviewbit.com/problems/maximum-sum-combinations/
public class MaximumSumCombination {

    public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<Integer> B, int C) {
        int size = A.size();
        PriorityQueue<pair> pq = new PriorityQueue<>((a, b) -> b.val - a.val);
        HashSet<Integer> hs = new HashSet<>();
        Collections.sort(A, Collections.reverseOrder());
        Collections.sort(B, Collections.reverseOrder());

        ArrayList<Integer> ans = new ArrayList<>();
        pq.add(new pair(0, 0, A.get(0) + B.get(0)));
        hs.add(0);
        while (C-- != 0) {
            pair pair1 = pq.poll();
            ans.add(pair1.val);
            int a, b;
            a = pair1.poia;
            b = pair1.poib;

            if (b + 1 < size && !hs.contains(a * size + (b + 1))) {
                hs.add(a * size + b + 1);
                pq.add(new pair(a, b + 1, A.get(a) + B.get(b + 1)));
            }
            if (a + 1 < size && !hs.contains((a + 1) * size + b)) {
                hs.add((a + 1) * size + b);
                pq.add(new pair(a + 1, b, A.get(a + 1) + B.get(b)));
            }
        }
        return ans;
    }
}

class pair {
    int poia, poib, val;

    public pair(int a, int b, int sum) {
        poia = a;
        poib = b;
        val = sum;
    }
}