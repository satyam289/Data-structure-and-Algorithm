package DynamicProgramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//https://www.interviewbit.com/problems/equal-average-partition/
class EqualAvgPartition {

    byte[][][] mem;

    public ArrayList<ArrayList<Integer>> avgset(ArrayList<Integer> a) {
        int n = a.size();
        if (n <= 1)
            return new ArrayList<ArrayList<Integer>>();
        Collections.sort(a);
        int sum = 0;
        for (int v : a)
            sum += v;
        mem = new byte[n][n / 2 + 1][sum + 1];
        for (int i = 1; i * 2 <= a.size(); ++i) {
            if (sum * i % n != 0)
                continue;
            int needSum = sum * i / n;
            if (pos(a, 0, i, needSum)) {
                ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
                res.add(new ArrayList<Integer>());
                res.add(new ArrayList<Integer>());
                for (int j = 0; j < n; ++j) {
                    int v = a.get(j);
                    if (res.get(0).size() < i) {
                        if (v == needSum) {
                            res.get(0).add(v);
                            needSum -= v;
                        } else {
                            if (pos(a, j + 1, i - res.get(0).size() - 1, needSum - v)) {
                                res.get(0).add(v);
                                needSum -= v;
                            } else {
                                res.get(1).add(v);
                            }
                        }
                    } else {
                        res.get(1).add(a.get(j));
                    }
                }
                return res;
            }
        }
        return new ArrayList<ArrayList<Integer>>();
    }

    boolean pos(List<Integer> a, int from, int n, int sum) {
        if (sum == 0 && n == 0 && from <= a.size())
            return true;
        if (sum <= 0 || from >= a.size() || n > a.size() - from || n <= 0)
            return false;
        if (mem[from][n][sum] != 0) {
            return mem[from][n][sum] == 1 ? true : false;
        }
        if (pos(a, from + 1, n, sum) || pos(a, from + 1, n - 1, sum - a.get(from))) {
            mem[from][n][sum] = 1;
            return true;
        }
        mem[from][n][sum] = 2;
        return false;
    }
}