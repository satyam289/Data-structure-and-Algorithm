package Array;

import java.util.ArrayList;

//https://www.geeksforgeeks.org/find-the-nth-row-in-pascals-triangle/
//https://www.interviewbit.com/problems/kth-row-of-pascals-triangle/
public class KPascalTraingle {

    // Optimised time Complexity : 0(n)
    public ArrayList<Integer> getRow1(int a) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        res.add(1);
        for (int i = 1; i <= a; i++) {
            int num = (a + 1 - i) * res.get(i - 1) / i;
            res.add(num);
        }
        return res;
    }

    // Better
    public ArrayList<Integer> getRow(int a) {
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i <= a; i++) {
            ArrayList<Integer> tempAns = new ArrayList<Integer>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i)
                    tempAns.add(1);
                else
                    tempAns.add(ans.get(j - 1) + ans.get(j));
            }
            ans = tempAns;
        }
        return ans;
    }

    // Brute Force
    public ArrayList<Integer> getRow2(int A) {
        ArrayList<Integer> first = new ArrayList<>();
        first.add(1);
        ArrayList<Integer> second = new ArrayList<>();
        second.add(1);
        second.add(1);
        if (A == 1) {
            return second;
        }
        boolean isFirst = false;
        for (int i = 0; i < A; i++) {
            if (isFirst) {
                second.clear();
                second.add(1);
                for (int j = 1; j < first.size(); j++) {
                    second.add(first.get(j - 1) + first.get(j));
                }
                second.add(1);
            } else {
                first.clear();
                first.add(1);
                for (int j = 1; j < second.size(); j++) {
                    first.add(second.get(j - 1) + second.get(j));
                }
                first.add(1);
            }
            isFirst = !isFirst;
        }
        if (isFirst) {
            return second;
        }
        return first;
    }

    // https://www.interviewbit.com/problems/pascal-triangle/
    public ArrayList<ArrayList<Integer>> solve(int A) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < A; i++) {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    temp.add(1);
                } else {
                    temp.add(result.get(i - 1).get(j - 1) + result.get(i - 1).get(j));
                }
            }
            result.add(temp);
        }
        return result;
    }
}
