
#include <vector>
#include <algorithm>

using namespace std;
//https://www.interviewbit.com/problems/subset/

void subset(vector<int> &A, vector<vector<int>> &ans, vector<int> temp, int index)
{
    ans.push_back(temp);
    for (int i = index; i < A.size(); i++)
    {
        temp.push_back(A[i]);
        subset(A, ans, temp, i + 1);
        temp.pop_back();
    }
}

vector<vector<int>> solution(vector<int> &A)
{
    vector<vector<int>> ans;
    sort(A.begin(), A.end());
    vector<int> temp;
    subset(A, ans, temp, 0);
}
/*
public class Solution {
    
    ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
    
    public ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> A) {
       if(A ==  null){
           return result;
       }
        Collections.sort(A);
        result.add(new ArrayList<Integer>());
        generate(A, result, new ArrayList<Integer>(),0);
        return result;
    }
    
    public void generate(ArrayList<Integer> a, ArrayList<ArrayList<Integer>> output, ArrayList<Integer> temp, int index)
	{
		for (int i = index; i < a.size(); i++)
		{
			temp.add(a.get(i));
			output.add(new ArrayList<Integer>(temp));
			generate(a, output, temp, i+1);
			temp.remove(temp.size() - 1);
		}
	}
}
*/