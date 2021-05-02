#include <iostream>
#include <vector>
#include <set>
#include <unordered_map>
#include <algorithm>

using namespace std;

//https://www.interviewbit.com/problems/combination-sum-ii/
class CombinationSum
{
private:
    void solve(vector<int> &A, int start, int end, vector<int> &temp, set<vector<int>> &ans, int sum, unordered_map<int, int> &used)
    {
        if (sum < 0 || start > end)
            return;

        if (sum == 0)
        {
            ans.insert(temp); // No duplicate here, since it set
            return;
        }

        solve(A, start + 1, end, temp, ans, sum, used);
        if (used[A[start]] > 0) // only duplicate which is avialable in input
        {
            temp.push_back(A[start]);
            used[A[start]]--;
            solve(A, start, end, temp, ans, sum - A[start], used);
            temp.pop_back();
            used[A[start]]++;
        }
    }

    void func(vector<int> &A, int B, int start, int rem, vector<int> &temp, vector<vector<int>> &res)
    {
        if (rem == 0)
        {
            if(!checkDuplicate(res, temp)){
                res.push_back(temp); // not found
            }
            return;
        }
        int i;
        for (int i = start; i < A.size(); i++)
        {
            if (A[i] > rem)
                break;
            else
            {
                temp.push_back(A[i]);
                func(A, B, i + 1, rem - A[i], temp, res);
                temp.pop_back();
            }
        }
        return;
    }

    int checkDuplicate(vector<vector<int>> &res, vector<int> &temp)
    {
        int i, j, flag;
        for (int i = 0; i < res.size(); i++)
        {
            flag = 1;
            for (int j = 0; j < res[i].size() && flag; j++)
            {
                if (temp.size() != res[i].size() || temp[j] != res[i][j])
                    flag = 0;
            }
            if (flag) // 0 mean false 1 mean true, if we found, return
                return 1;
        }
        return 0;
    }

public:
    vector<vector<int>> combinationSum1(vector<int> &A, int B)
    {
        sort(A.begin(), A.end());
        unordered_map<int, int> used;
        for (int a : A)
            used[a]++;

        vector<int> temp;
        set<vector<int>> ans;
        vector<vector<int>> ret;
        int n = A.size();

        solve(A, 0, n - 1, temp, ans, B, used);
        for (set<vector<int>>::iterator it = ans.begin(); it != ans.end(); it++)
        {
            ret.push_back(*it);
        }
        return ret;
    }

    vector<vector<int>> combinationSum2(vector<int> &A, int B)
    {
        sort(A.begin(), A.end());
        vector<int> temp;
        vector<vector<int>> res;
        func(A, B, 0, B, temp, res);
        return res;
    }

    // https://www.interviewbit.com/problems/combination-sum/
    vector<vector<int>> combinationSum3(vector<int> &A, int B)
    {
        sort(A.begin(), A.end());
        //A.erase(unique(A.begin(), A.end()), A, end());
        vector<vector<int>> ans;
        choose(A, 0, B, {}, ans);
    }

    void choose(const vector<int> &A, int index, int target, vector<int> current, vector<vector<int>> &ans)
    {
        if (target == 0)
            ans.push_back(current);

        for (auto i = index; i < A.size(); i++)
        {
            if (A[i] > target)
                break;
            current.push_back(A[i]);
            choose(A, i, target - A[i], current, ans);
            current.pop_back();
        }
    }
};

/*
package Backtracking;

import java.util.ArrayList;
import java.util.Collections;

public class CombinationSum {
    
    public ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> a, int b) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        Collections.sort(a);
        ArrayList<Integer> inter = new ArrayList<Integer>();
        combinationSumUtil(a, b, 0, inter, result);
        return result;
    }
    
    public void combinationSumUtil(ArrayList<Integer> a, int b, int start,  ArrayList<Integer> path, ArrayList<ArrayList<Integer>> res){
        if (b == 0) {
            ArrayList<Integer> p = new ArrayList<Integer>(path);
            res.add(p);
            return;
        }
        for (int i = start; i < a.size() && b >= a.get(i); ++i) {
            if (i!=start && a.get(i-1) == a.get(i)) 
            continue;
            path.add(a.get(i));
            combinationSumUtil(a, b-a.get(i), i+1, path, res);
            path.remove(path.size() - 1);
        }
    }
}
*/