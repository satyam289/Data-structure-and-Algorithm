#include <iostream>

using namespace std;

class solution
{
public:
    vector<int> searchRange(vector<int>& num, int target)
    {
        if (num.empty())
            return {-1, -1};

        int n = num.size();
        int lower = -1, upper = -1;

        // find lower boud
        int lo = 0, hi = n - 1;
        while (lo < hi)
        {
            int mid = lo + (hi - lo) / 2;
            if (num[mid] >= target)
            {
                hi = mid;
            }
            else
            {
                lo = mid + 1;
            }
        }
        if (num[lo] < target)
            ++lo;
        lower = lo;

        if (lower > hi)
            return {-1, 1};

        // find upper bound
        lo = 0, hi = n - 1;
        while (lo < hi)
        {
            int mid = lo + (hi - lo) / 2;
            if (num[mid] > target)
            {
                hi = mid;
            }
            else
            {
                lo = mid + 1;
            }
        }
        if (num[hi] > target)
            --hi;
        upper = hi;
        if (lower > upper)
            return {-1, 1};

        return {lower, upper};
    }
};
