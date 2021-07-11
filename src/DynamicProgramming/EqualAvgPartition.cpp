#include <bits/stdc++.h>
using namespace std;

//https://www.interviewbit.com/problems/equal-average-partition/
bool check(int ind, int sum, int element, vector<int>&A,  vector<vector<vector<bool>>> &dp, vector<int> &res)
{
    if(element == 0) 
        return (sum==0); 
    if(ind>=A.size()) 
        return false; 

    if(dp[ind][sum][element] == false) 
        return false; 
        
    if(A[ind]<=sum) {
        res.push_back(A[ind]); 
        
        if(check(ind+1, sum - A[ind], element - 1, A, dp, res)) 
            return dp[ind][sum][element] = true; 
        
        res.pop_back();
    }
        
    if(check(ind+1, sum, element, A, dp, res))
        return dp[ind][sum][element] = true; 
        
    return dp[ind][sum][element] = false; 
}
int main() {
	vector<int> A = {1, 2, 3, 4, 5, 6};
	int n = 6; 
	int sum = 0;
	for(auto it:A) 
        sum += it; 
    vector<vector<vector<bool>>>dp(n, vector<vector<bool>>(sum+1, vector<bool>(n, true)));

    for(int i = 1;i<=n-1;i++)
    {
        if( (sum * i) % n == 0) {
            int ss = i * sum; 
            ss /= n; 
            vector<int>res; 
            if(check(0, ss, i, A, dp, res)) {
                for(auto it:res)
                    cout << it << " "; 
                return 0; 
            }
        }
    }       
    cout << -1; 
}