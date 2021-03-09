// https://www.interviewbit.com/problems/array-3-pointers/

#include <vector>
#include <bits/stdc++.h>

using namespace std;

int minimize(const vector<int> &A, const vector<int> &B, const vector<int> &C)
{

    int i = 0, j = 0, k = 0;
    int sol = INT_MAX;
    int min_abc, a_max, b_max, c_max;

    while (i < A.size() || j < B.size() || k < C.size())
    {
        sol = min(sol, checkMax(abs(A[i] - B[j]), abs(B[j] - C[k]), abs(C[k] - A[i])));

        if (i + 1 < A.size())
        {
            a_max = checkMax(abs(A[i + 1] - B[j]), abs(B[j] - C[k]), abs(C[k] - A[i + 1]));
        }
        else
        {
            a_max = INT_MAX;
        }
        if (j + 1 < B.size())
        {
            b_max = checkMax(abs(A[i] - B[j + 1]), abs(B[j + 1] - C[k]), abs(C[k] - A[i]));
        }
        else
        {
            b_max = INT_MAX;
        }
        if (k + 1 < C.size())
        {
            c_max = checkMax(abs(A[i] - B[j]), abs(B[j] - C[k + 1]), abs(C[k + 1] - A[i]));
        }
        else
        {
            c_max = INT_MAX;
        }

        min_abc = checkMin(a_max, b_max, c_max);

        if (min_abc == INT_MAX)
        {
            return sol;
        }
        else if (min_abc == a_max)
        {
            i++;
        }
        else if (min_abc == b_max)
        {
            j++;
        }
        else
        {
            k++;
        }
    }
    return sol;
};

int checkMax(int a, int b, int c)
{
    int maximum = max(a,b);
    maximum = max(maximum, c);
    return maximum;
}

int checkMin(int a, int b, int c)
{
    int minimum = min(a,b);
    minimum = min(minimum, c);
    return minimum;
}