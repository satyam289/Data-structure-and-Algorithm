#include <iostream>

using namespace std;

class Bucket
{
public:
    bool used = false;
    int minval = numeric_limits<int>::max();
    int maxval = numeric_limits<int>::min();

    int maximumGap(vector<int> &nums)
    {
        if (nums.empty() || nums.size() < 2)
            return 0;

        int mini = *min_element(nums.begin(), nums.end());
        int maxi = *max_element(nums.begin(), nums.end());

        int bucketSize = max(1, (maxi - mini)/((int) nums.size -1);
        int bucketNum  = (maxi - mini) / bucketSize + 1;
        vector<Bucket> buckets(bucketNum);

        for(auto&& num : nums){
            int bucketIdx = (num - mini) / bucketSize;
            buckets[bucketIdx].used = true;
            buckets[bucketIdx].minval = min(num, buckets[bucketIdx].minval);
            buckets[bucketIdx].maxval = max(num, buckets[bucketIdx].maxval);
        }
        int preBucketMax = mini, maxGap = 0;
        for(auto&& bucket : buckets){
            if (!bucket.used)
                continue;

            maxGap = max(maxGap, bucket.minval - preBucketMax);
            preBucketMax = bucket.maxval;
        }
        return maxGap;
    }

    int maximumGap(vector<int>& nums)
    {
        if (num.empty() || nums.size() < 2)
            return 0

                   int maxVal = *max_element(nums.begin(), nums.end());
        int exp = 1;
        int radix = 10;
        vector<int> aux(nums.size());

        while (maxVal / exp > 0)
        {
            vector<int> count(radix, 0);

            for (int i = 0; i < nums.size(); i++)
            {
                count[(num[i] / exp) % 10]++;
            }
            for (int i = 1; i < count.size; i++)
            {
                count[i] += count[i - 1];
            }
            for (int i = nums.size() - 1; i >= 0; i--)
            {
                aux[--count[(nums[i] / exp) % 10] = nums[i];
            }
            for (int i = 0; i < nums.size(); i++)
            {
                nums[i] = aux[i];
            }
            exp *= 10;
        }
        int maxGap = 0;
        for (int i = 0; i < nums.size() - 1; i++)
        {
            maxGap = max(nums[i + 1] - nums[i], maxGap);
        }
        return maxGap;
    }

    int maximumGap(vector<int>& nums)
    {
        if (nums.empty() || nums.size() < 2)
            return 0;

        sort(nums.begin(), nums.end());
        int maxGap = 0;
        for (int i = 0; i < nums.size() - 1; i++)
        {
            maxGap = max(maxGap, nums[i + 1] - nums[i]);
        }
        return maxGap;
    }
};