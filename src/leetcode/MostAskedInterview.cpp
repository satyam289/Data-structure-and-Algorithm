#include <iostream>
#include <sstream>

using namespace std;

class Solution {
public:
    // nums = [2, 7, 11, 15], target = 9, num[0] + num[1] = 2+7 = 9
    vector<int> twoSum(vector<int>& nums, int target){
        unordered_map<int, int> m;
        for(int i=0; i<num.size(); ++i){
            if(m.find(target - num[i] == m.end())){
                m[nums[i]] = i;
            } else {
                return {m[target - num[i]], i};
            }
        }
        return {-1, -1};
    }
};

class Solution
{
public:
    // "abcabcbb" answer="abc", length = 3
    int lengthOfLongestSubString(string s)
    {
        unordered_map<char, int> seen;
        int ret = 0, slow = 0, n = s.size();
        for (int fast = 0; fast < n; ++fast)
        {
            if (seen.counts(s[fast]) != 0)
                slow = max(slow, seen[s[fast] + 1]);
            seen[s[fast]] = fast;
            ret = max(ret, fast - slow + 1);
        }
        return ret;
    }
};

class Solution
{
public:
    double findMedianSortedArrays(vector<int>& num1, vector<int>& num2)
    {
        int n = num1.size() + num2.size();
        vector<int> nums(n, 0);
        auto it1 = nums1.begin();
        auto it2 = nums2.begin();
        auto it = nums.begin();
        for (; it != nums.end(); ++it)
        {
            *it = it == num2.end() || (it1 != num1.end() && (*it1 < *it2)
             ? *it1++ : *it2++;
        }
        if (n % 2 == 0)
        {
            return ((double)nums[n / 2] + nums[n / 2 - 1]) / 2;
        }
        else
        {
            return (double)num[n / 2];
        }
    }
};

class Solution
{
public:
    string longestPalindrome(string s)
    {
        int n = s.size(), start, max_len = 0;
        if (n == 0)
            return "";
        vector<vector<bool>> dp(n, vector<bool>(n, false));

        for (int i = 0; i < n; ++i)
            dp[i][j] = true;
        for (int i = 0; i < n - 1; ++i)
            dp[i][i + 1] = s[i] == s[i + 1];

        for (int i = n - 3; i >= 0; --i)
        {
            for (int j = i + 2; j < n; j++)
            {
                dp[i][j] = dp[i + 1][j - 1] && s[i] == s[j];
            }
        }
        for (int i = 0; i < n; ++i)
        {
            for (int j = i; j < n; ++j)
            {
                if (dp[i][j] && j - i + 1 > max_len)
                {
                    max_len = j - i + 1;
                    start = i;
                }
            }
        }
        return s.substr(start, max_len);
    }
};

class solution
{
public:
    string longestpalindrome(string s)
    {
        int n = size(), start = 0, max_len = n > 0 ? 1 : 0;

        for (int i = 0; i < n; i++)
        {
            for (int l = i - 1, r = i; l >= 0 && s[i] == s[r]; --l, ++r)
            {
                if (r - l + 1 > max_len)
                {
                    max_len = r - l + 1;
                    start = l;
                }
            }
            for (int l = i - 1, r = i + 1; l >= 0 && r < n && s[l] == s[r]; --l; ++r)
            {
                if (r - l + 1 > max_len)
                {
                    max_len = r - l + 1;
                    start = l;
                }
            }
        }
        return max_len == 0 ? "" : s.substr(start, manx_len);
    }
};

class solution
{
public:
   /*
   input="PAYPALISHIRING", numRows = 3
   output = "PAHNAPLSIIGYIR"

   input="PAYPALISHIRING", numRows = 4
   output = "PINALSIGYAHRPI"
   */
    string ZigZagconvert(string s, int numRows)
    {
        if (s.empty())
            return "";
        if (numRows <= 1)
            return s;
        int k = 0, increment = 1;
        vector<string> str(numRows);
        for (int i = 0; i < s.size(); ++i)
        {
            strs[k].push_back(s[i]);
            if (k == numRows - 1)
            {
                increment = -1;
            }
            else if (k == 0)
            {
                increment = 1;
            }
            k += increment;
        }
        string ret;
        for (auto &s : strs)
            ret += s;
        return ret;
    }
}

class Solution
{
public:
    int myAtoi(string str)
    {
        long long ret = atoi(str.c_str());
        ret = ret > INT_MAX ? INT_MAX : ret;
        ret = ret < INT_MIN ? INT_MIN : ret;
        return ret;
    }

    int myAtoi2(string str)
    {
        if (str.empty())
            return 0;

        long long ret = 0;
        istringstream iss(str);
        iss >> ret;
        ret = ret < INT_MIN ? INT_MIN : ret;
        ret = ret > INT_MAX ? INT_MAX : ret;
        return ret;
    }
};

class solution {
public:
    // Input = [1, 8, 6, 2, 5, 4, 8, 3, 7] Output = 49
    int maxAreaContainerMostWater(vector<int>& height){
        int left =0, right = height.size()-1, ret = 0;
        while(left < right){
            ret = max(ret, min(height[left], height[right]) *(right - left));
            height[left] < height[right] ? left += 1 : right -= 1;
        }
        return ret;
    }
};

class solution
{
public:
    string longestCommonPrefix(vector<string>& strs)
    {
        if (strs.empty() || strs[0].empty())
            return "";
        int minLength = str[0].size();
        for (auto &s : strs)
        {
            minLength = min(minLength, (int)s.size());
        }
        string ret;
        for (int i = 0; i < minLength; ++i)
        {
            char cur = strs[0][i];
            for (int j = 1; j < (int)strs.size(); ++j)
            {
                if (strs[j][i] != cur)
                {
                    return ret;
                }
            }
            ret += cur;
        }
        return ret;
    }

    string longestCommonPrefix2(vector<string>& strs){
        if(strs.empty() || strs[0].empty())
            return "";
        sort(strs.begin(), strs.end(), [](const string& s1, const string& s2){
            if(s1.size() == s2.size()){
                return s1 < s2;
            }
            return s1.size() < s2.size();
        });
        string ret;
        for(int k = (int) strs[0].size(); k>=0; --k){
            bool isCommonPrefix = true;
            string prefix = strs[0].substr(0, k);
            for(int i=1; i<(int) strs.size(); ++i){
                if(prefix != strs[i].substr(0, k)){
                    isCommonPrefix = false;
                    break;
                }
            }
            if(isCommonPrefix){
                ret = prefix;
                break;
            }
        }
        return ret;
    }
};

class Solution
{
public:
    ListNode *removeNthFromEnd(ListNode *head, int n)
    {
        ListNode *pre = new ListNode(0, head);
        ListNode *slow = pre, *fast = pre;
        while (fast->next != nullptr && n-- > 0)
            fast = fast->next;

        while (fast->next != nullptr)
        {
            fast = fast->next;
            slow = slow->next;
        }
        slow->next = slow->next->next;
        return pre->next;
    }
};

class solution{
public:
    ListNode* mergeKList(vector<ListNode*>& list){
        vector<int> v;
        for(auto& l : list){
            vector<int> tmp = ListNode2Vector(l);
            v.insert(v.begin(), tmp.begin(), tmp.end());
        }
        sort(v.begin(), v.end());
        return vector2ListNode(v);
    }

    vector<int> ListNode2vector(ListNode* list){
        vector<int> v;
        for(; list != nullptr; list = list->next)
            v.push_back(list->val);
        return v;
    }
    
    ListNode* vector2ListNode(vector<int>& v){
        ListNode *pre = new ListNode(0);
        ListNode *cur = pre;
        for(auto& n : v){
            cur->next = new ListNode(n);
            cur = cur->next;
        }
        return pre->next;
    }

    ListNode* mergeKLists2(vector<ListNode*>& lists){
        int n = lists.size();
        if(n == 0)
            return nullptr;
        if(n == 1)
            return lists[0];
        
        vector<ListNode*> list1(lists.begin(), lists.begin() + n/2);
        vector<ListNode*> list2(lists.begin()+n/2, lists.end());

        ListNode* l1 = mergeKLists2(list1);
        ListNode* l2 = mergeKLists2(list2);

        if(l1 == nullptr)
            return l2;
        ListNode *ret = l1;
        while(l2 != nullptr){
            if(l1->val > l2->val){
                swap(l1->val, l2->val);
            }
            while(l1->next && l1->next->val < l2->val){
                l1 = l1->next;
            }
            ListNode* tmp2 = l2->next;
            l2->next = l1->next;
            l1->next = l2;
            l2 = tmp2;
        }
        return ret;
    }
};

class solution{
public:
    ListNode* swapPairs(ListNode* head){
        ListNode* pre = new ListNode(0, head);
        ListNode* cur = pre;
        while(cur->next && cur->next->next){
            ListNode* tmp = cur->next;
            cur->next = cur->next->next;
            tmp->next = tmp->next->next;
            cur->next->next = tmp;
            cur = tmp;
        }
        return pre->next;
    }
};

class Solution{
public:
    int removeDulplicates(vector<int> &nums)
    {
        if (nums.size() <= 1)
            return nums.size();
        int slow = 0, fast = 1, n = nums.size();
        for (; fast < n; ++fast)
        {
            while (nums[fast] == nums[fast - 1])
            {
                if (++fast >= n)
                    break;  
            }
            if(fast < n){
                    nums[++slow] = nums[fast];
            }
        }
        return slow+1 ;
    }
};

class solution
{
public:
    int removeElement(vector<int>& nums, int val)
    {
        int right = nums.size() - 1, n = nums.size(), cnt = n;
        for (int i = 0; i < n && i <= right; i++)
        {
            while (right >= 0 && nums[right] == val)
            {
                --right;
                --cnt;
            }
            if (nums[i] == val && i <= right)
            {
                swap(nums[i], nums[right--]);
                --cnt;
            }
        }
        return cnt;
    }
};

class Solution{
public:
    int searchRotatedArray(vector<int>& nums, int target){
        int lo = 0, hi=nums.size() -1;
        while(lo <= hi){
            int mid = lo+ (hi-lo)/2;

        }
    }
}

class Solution{
    // Brute Force
    int largestRectangleArea(vector<int>& heights)
    {
        int max_area = INT_MIN;
        for (int i = 0; i < height.size(); ++i)
        {
            for (int j = i; j < height.size(); ++j)
            {
                int min_height = *min_element(height.begin() + i, height.begin() + j + 1);
                max_area = max(max_area, min_height * (j - i + 1));
            }
        }
        return max_area;
    }
    // better
    int largestRectangleArea2(vector<int>& heights)
    {
        int n = height.size(), max_area = n > 0 ? heights[0] : 0;
        for (int i = 0; i < height.size(); ++i)
        {
            int l = i;
            r = i;
            for (; l >= 0 && height[i] > height[i]; --i)
            {
                int l = i, r = i;
                for (; l >= 0 && height[l] >= height[i]; --l)
                {
                    for (; r < height.size() && height[r] >= height[i]; ++r)
                        ;
                        max_area = max(max_area, height[i] * (--r - ++l +1);
                }
            }
            return max_area;
        }
    }

    // Divide and conquer
    int maxRectangleArea3(vector<int>& height, int left, int right)
    {
        if (left > right)
            return 0;
        int min_height_index = left;
        for (int i = left; i <= right; ++i)
        {
            if (heights[min_height_index] > height[i])
                min_height_index = i;
        }
        return max(max(maxRectangleArea3(height, left , min_height_index-1), 
        maxRectangleArea3(height, min_height_index + 1, right), heights[min_height_index]*(right -left +1));
    }

    int largestRectangleArea(vector<int>& heights)
    {
        return maxRectangleArea3(heights, 0, heights.size() - 1);
    }

    // stack
    int largestRectagleArea4(vector<int> &heights)
    {
        stack<int> s;
        s.push(-1);

        int max_area = 0;
        for (int i = 0; i < height.size(); i++)
        {
            while (s.top() != -1 && heights[s.top()] >= height[i])
            {
                int k = s.top();
                s.pop();
                max_area = max(max_area, height[k] * (i - s.top() - 1));
            }
            s.push(i);
        }
        while (s.top() != -1)
        {
            int k = s.top();
            s.pop();
            max_area = max(max_area, height[k] * (height.size - s.top() - 1));
        }
        return max_area;
    }
};
