#include <vector>

using namespace std;

// https://www.interviewbit.com/problems/equal/
// https://www.geeksforgeeks.org/find-four-elements-a-b-c-and-d-in-an-array-such-that-ab-cd/
class Solution
{
public:
    vector<int> equal(vector<int> &vec)
    {
        int N = vec.size();
        // With every sum, we store the lexicographically first occuring pair of integers.
        map<int, pair<int, int>> Hash;
        vector<int> Ans;

        for (int i = 0; i < N; ++i)
        {
            for (int j = i + 1; j < N; ++j)
            {

                int Sum = vec[i] + vec[j];

                if (Hash.find(Sum) == Hash.end())
                {
                    Hash[Sum] = make_pair(i, j);
                    continue;
                }

                pair<int, int> p1 = Hash[Sum];
                if (p1.first != i && p1.first != j && p1.second != i && p1.second != j)
                {
                    vector<int> ans;
                    ans.push_back(p1.first);
                    ans.push_back(p1.second);
                    ans.push_back(i);
                    ans.push_back(j);

                    if (Ans.size() == 0)
                        Ans = ans;
                    else
                    {
                        // compare and assign Ans
                        bool shouldReplace = false;
                        for (int i1 = 0; i1 < Ans.size(); i1++)
                        {
                            if (Ans[i1] < ans[i1])
                                break;
                            if (Ans[i1] > ans[i1])
                            {
                                shouldReplace = true;
                                break;
                            }
                        }
                        if (shouldReplace)
                            Ans = ans;
                    }
                }
            }
        }

        return Ans;
    }
};

/* package Array;

import java.util.Arrays;

// https://www.geeksforgeeks.org/find-four-elements-a-b-c-and-d-in-an-array-such-that-ab-cd/
public class FourElementInArray {

    public ArrayList<Integer> equal(ArrayList<Integer> A) {
       Map<Integer,ArrayList<Integer>> map=new HashMap<>();
        ArrayList<Integer> result=new ArrayList<>();
        for(int i=0;i<A.size()-1;i++){
            for(int j=i+1;j<A.size();j++){
                int sum=A.get(i)+A.get(j);
                if(map.containsKey(sum)){
                    int f=map.get(sum).get(0);
                    int s=map.get(sum).get(1);
                    if(f==i||f==j||s==i||s==j) continue;
                    map.get(sum).addAll(Arrays.asList(i,j));
                }
                else map.put(sum,new ArrayList<>(Arrays.asList(i,j)));
            }
        }
         for(int i=0;i<A.size()-1;i++){
            for(int j=i+1;j<A.size();j++){
                int sum=A.get(i)+A.get(j);
                if(map.get(sum).size()>2){
                    result.addAll(Arrays.asList(i,j,map.get(sum).get(2),map.get(sum).get(3)));
                    return result;
                }
            }
        }
        return new ArrayList<>();
    }
    
    //https://www.geeksforgeeks.org/find-four-numbers-with-sum-equal-to-given-sum/
	void find4Numbers(int A[], int n, int X)
	{
		int l, r;
		Arrays.sort(A);
		for (int i = 0; i < n - 3; i++)
		{
			for (int j = i + 1; j < n - 2; j++)
			{
				l = j + 1;
				r = n - 1;
				while (l < r)
				{
					if (A[i] + A[j] + A[l] + A[r] == X)
					{
						System.out.println(A[i]+" "+A[j]+" "+A[l]+" "+A[r]);
						l++;
						r--;
					}
					else if (A[i] + A[j] + A[l] + A[r] < X)
						l++;
					else // A[i] + A[j] + A[l] + A[r] > X
						r--;
				}
			}
		}
	}
}
 */