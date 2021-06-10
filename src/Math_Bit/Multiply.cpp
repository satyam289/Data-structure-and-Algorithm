#include <iostream>
#include <algorithm>

using namespace std;

class Multiply
{
    string multiply(string A, string B)
    {
        int m = A.size(), n = B.size();
        string ans(m + n, '0');

        for (int i = m - 1; i >= 0; --i)
        {
            for (int j = n - 1; j >= 0; --j)
            {
                int sum = (A[i] - '0') * (B[j] - '0') + (ans[i + j + 1] - '0');
                ans[i + j + 1] = (sum % 10) + '0';
                ans[i + j] += (sum / 10);
            }
        }

        for (int i = 0; i < m + n; ++i)
        {
            if (ans[i] != '0')
                return ans.substr(i);
        }
        return "0";
    }

    string multiply(string A, string B)
    {
        reverse(A.begin(), A.end());
        reverse(B.begin(), B.end());
        string res;
        res.resize(A.size() + B.size(), '0');
        for (int i = 0; i < A.size(); ++i)
        {
            int rem = 0;
            for (int j = 0; j < B.size() || rem; ++j)
            {
                int Aval = (A[i] - '0');
                int BVal = 0;
                if (j < B.size())
                {
                    BVal = (B[j] - '0');
                }
                int sum = Aval * BVal + (res[i + j] - '0') + rem;
                res[i + j] = (sum % 10 + '0');
                rem = sum / 10;
            }
        }
        while ((res.size() > 1) && (res[res.size() - 1] == '0'))
            res.pop_back();
        reverse(res.begin(), res.end());
        return res;
    }
};
/*
//https://www.interviewbit.com/problems/multiply-strings/

package Array;
public class Multiply {
    
    public static String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] pos = new int[m + n];
   
    for(int i = m - 1; i >= 0; i--) {
        for(int j = n - 1; j >= 0; j--) {
            int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0'); 
            int p1 = i + j, p2 = i + j + 1;
            int sum = mul + pos[p2];

            pos[p1] += sum / 10;
            pos[p2] = (sum) % 10;
        }
    }  
    
    StringBuilder sb = new StringBuilder();
    for(int p : pos) {
        if(!(sb.length() == 0 && p == 0)){
             sb.append(p);
        }
    }        
    return sb.length() == 0 ? "0" : sb.toString();
    }

    public static void main(String [] args){
        System.out.println(multiply("135", "12"));
    }
}
*/