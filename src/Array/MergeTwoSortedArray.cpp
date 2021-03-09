#include <vector>
using namespace std;

// https://www.interviewbit.com/problems/merge-two-sorted-lists-ii/

void merge(vector<int> &A, vector<int> &B){
    int i = A.size() -1;
    int j = B.size() -1;
    int lastpos = A.size() + B.size() -1;
    A.resize(A.size() + B.size());
    while(j >= 0)
    {
        if(i >=0 and A[i] > B[i])
            A[lastpos --] = A[i--];
        else
           A[lastpos--] = B[j--];    
    }
}

