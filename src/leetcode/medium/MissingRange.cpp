#include <iostream>
#include <vector>

using namespace std;

class Solution
{
public:
    vector<string> findMissingRange(vector<int> &nums, int lower, int upper)
    {
        if (lower > upper)
        {
            return {};
        }
        if (nums.empty())
        {
            if (lower == upper)
            {
                return {to_string(lower)};
            }
            else
            {
                return {to_string(lower) + "->" + to_string(upper)};
            }
        }
        long long curr = nums.back();
        nums.pop_back();
        vector<string> ranges;
        if (curr - 1 >= lower)
        {
            ranges = findMissingRange(nums, lower, curr - 1);
        }
        if (curr + 1 < upper)
        {
            ranges.push_back(to_string(curr + 1) + "->" + to_string(upper));
        }
        else if (curr + 1 == upper)
        {
            ranges.push_back(to_string(curr + 1));
        }
        return ranges;
    }

    int main()
    {
        vector<int> input = {0, 1, 3, 50, 75};
        int lower = 0;
        int upper = 99;
        auto result = findMissingRange(input, lower, upper);
        for (string data : result)
        {
            cout << data;
            // ["2", "4->49", "51-74", "76-99"]
        }
        return 0;
    }
};