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
            if(nums[mid] == target){
                return mid;
            } else {
                if(nums[mid] < nums[lo]){
                    if(target > nums[lo] && target > nums[hi]){
                        hi = mid - 1;
                    } else {
                        lo = mid + 1;
                    }
                } else {
                    if(target > nums[lo] && target > nums[hi]){
                        hi = mid - 1;
                    }else {
                        lo = mid + 1;
                    }
                }
            }
        }
        return -1;
    }
};

class Solution
{
public:
    int searchInsertPosition(vector<int> &nums, int target)
    {
        int lo = 0, hi = nums.size() - 1;
        while (lo <= hi)
        {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target)
            {
                return mid;
            }
            else if (nums[mid] > target)
            {
                hi = mid - 1;
            }
            else
            {
                lo = mid + 1;
            }
        }
        return lo;
    }
};

class solution {
public:
    string countAndSay(int n){
        if(n == 1)
            return "1";
        string s = countAndSay(n-1);
        string ret;
        int cnt = 1;
        char cur = s[0];
        for(int i=1; i<(int)s.size(); ++i){
            if(s[i] == cur){
                ++cnt;
            } else {
                ret += to_string(cnt) + cur;
                cur = s[i];
                cnt = 1;
            }
        }
        if(cnt > 0){
            ret += to_string(cnt) + cur;
        }
        return ret;
    }
};

class Solution{
public:
    int firstMissingPositive(vector<int> & nums){
        unordered_set<int> seen;
        for(auto& i : nums){
            if(i > 0){
                seen.insert(i);
            }
        }
        int i =l, n = nums.size();
        while(i <= n){
            if(seen.count(i) == 0)
                return i;
            ++i;
        }
        return i;
    }
}

class Solution
{
public:
    vector<vector<int>> permute(vector<int> &nums)
    {
        sort(nums.begin(), nums.end());
        vector<vector<int>> ret{nums};
        while (next_permutation(nums.begin(), nums.end()))
            ret.push_back(nums);
        return ret;
    }

    vector<vector<int>> permute2(vector<int> &nums)
    {
        int n = nums.size(), numOfChosen = 0;
        vector<bool> chosen(n, false);
        vector<vector<int>> ret;
        vector<int> permutation;
        backtrack(nums, chosen, numOfChosen, permutation, ret);
        return ret;
    }

    void backtrack(vector<int> &nums, vector<bool> &chosen, int numOfChosen,
                   vector<int> &permutation, vector<vector<int>> &ret)
    {
        if (numOfChosen == (int)nums.size())
        {
            ret.push_back(permutation);
            return;
        }
        for (int i = 0; i < (int)nums.size(); ++i)
        {
            if (chosen[i] == true)
                continue;
            chosen[i] = true;
            permutation.push_back(nums[i]);
            backtrack(nums, chosen, numOfChosen + 1, permutation, ret);
            chosen[i] = false;
            permutation.pop_back();
        }
    }

    vector<vector<int>> permute(vector<int>& nums)
    {
        int n = nums.size();
        if (n <= 1)
        {
            return {nums};
        }
        vector<vector<int>> ret;
        for (int i = 0; i < n; ++i)
        {
            int cur = nums[i];
            swap(num[i], nums[n - 1]);
            nums.pop_back();
            vector<vector<int>> sub = permute(nums);
            for (auto &s : sub)
            {
                s.push_back(cur);
                ret.push_back(s);
            }
            nums.push_back(cur);
            swap(nums[i], nums[n - 1]);
        }
        return ret;
    }
};

class Solution
{
public:
    double myPow(double x, int n)
    {
        long double ret = 1.0, pow_x = x;
        for (long m = n >= 0 ? (long)n : -1 * (long)n; m != 0; m >>= 1)
        {
            if (m & 1)
                ret *= pow_x;
            pow_x = pow_x * pow_x;
        }
        return n >= 0 ? ret : 1.0L / ret;
    }
};

class Solution
{
public:
    unordered_set<string> seen;
    vector<vector<string>> solveNQueens(int n)
    {
        vector<vector<string>> ret;
        vector<string> board(n, string(n, '.'));
        vector<bool> col(n, false);
        vector<bool> diag1(2 * n - 1, false);
        vector<bool> diag2(2 * n - 1, false);
        backtrack(0, n, board, col, diag1, diag2, ret);
        return ret;
    }
    void backtrack(int r, int n, vector<string> &board, vector<bool> &col,
                   vector<bool> &diag1, vector<bool> &diag2, vector<vector<string>> &ret)
    {
        if (r == n)
        {
            ret.push_back(board);
            return;
        }
        for (int c = 0; c < n; ++c)
        {
            if (!col[c] && !diag1[r + c] && !diag2[r - c + n - 1])
            {
                board[r][c] = 'Q';
                col[c] = diag1[r + c] = diag2[r - c + n - 1] = true;
                backtrack(r + 1, n, board, col, diag1, diag2, ret);
                col[c] = diag1[r + c] = diag2[r - c + n - 1] = false;
                board[r][c] = '.';
            }
        }
    }
};

class Solution
{
public:
    int maxSubArray(vector<int> &nums)
    {
        int n = nums.size(), max_sum = nums[0];
        for (int i = 1; i < n; ++i)
        {
            if (nums[i - 1] > 0)
                nums[i] += nums[i - 1];
            max_sum = max(max_sum, nums[i]);
        }
        return max_sum;
    }
};

class Solution
{
public:
    vector<int> spiralOrder(vector<vector<int>>& matrix)
    {
        int m = matrix.size();
        if (m == 0)
            return {};
        int n = matrix[0].size();
        if (n == 0)
        {
            return {};
        }
        vector<vector<int>> dir{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        vector<int> ret;
        int N = m * n, d = 0, i = 0, j = -1;
        while (ret.size() < N)
        {
            int ni = i + dir[d][0];
            int nj = j + dir[d][1];
            while (ni < 0 || ni >= m || nj < 0 || nj >= n || matrix[ni][nj] == INT_MIN)
            {
                d = (d + 1) % 4;
                ni = i + dir[d][0];
                nj = j + dir[d][1];
            }
            ret.push_back(matrix[ni][nj]);
            matrix[ni][nj] = INT_MIN;
            i = ni;
            j = nj;
        }
        return ret;
    }
};

class Solution
{
public:
    bool canJump(vector<int> &nums)
    {
        int ret = 0, n = nums.size();
        for (int i = 0; i < n; ++i)
        {
            if (i <= ret)
            {
                ret = max(i + nums[i], ret);
            }
            else
            {
                break;
            }
        }
        return ret >= n - 1;
    }
};

class Solution
{
public:
    vector<vector<int>> generateMatrix(int n)
    {
        if (n <= 0)
            return {};
        vector<vector<int>> dir{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        vector<vector<int>> matrix(n, vector<int>(n, 0));
        int i = 0, j = -1, d = 0, N = n * n, cnt = 0;
        while (cnt < N)
        {
            int ni = i + dir[d][0], nj = j + dir[d][1];
            while (ni < 0 || ni >= n || nj < 0 || nj >= n || matrix[ni][nj] != 0)
            {
                d = (d + 1) % 4;
                ni = i + dir[d][0];
                nj = j + dir[d][1];
            }
            i = ni;
            j = nj;
            matrix[i][j] = ++cnt;
        }
        return matrix;
    }
};

class Solution
{
public:
    string getPermutation(int n, int k)
    {
        string s;
        for (int i = 0; i < n; ++i)
            s += to_string(i + 1);
        while (--k > 0 && next_permutation(s.begin(), s.end()))
            ;
        return s;
    }
};

class Solution
{
public:
    vector<int> plusOne(vector<int> &digits)
    {
        if (digits.empty())
            return {1};
        int carry = 1, n = digits.size();
        ;
        for (int i = n - 1; i >= 0; --i)
        {
            carry += digits[i];
            digits[i] = carry % 10;
            carry /= 10;
        }
        if (carry > 0)
            digits.insert(digits.begin(), carry);
        return digits;
    }
};

class Solution
{
public:
    string addBinary(string a, string b)
    {
        int i = a.size() - 1, j = b.size() - 1;
        int carry = 0;
        string ret;
        for (; i >= 0 || j >= 0; --i, --j)
        {
            if (i >= 0)
                carry += a[i] - '0';
            if (j >= 0)
                carry += b[j] - '0';
            ret += (carry & 1) + '0';
            carry >>= 1;
        }
        if (carry > 0)
            ret += carry + '0';
        reverse(ret.begin(), ret.end());
        return ret;
    }
};

class Solution
{
public:
    int mySqrt(int x)
    {
        int lo = 0, hi = x;
        while (lo <= hi)
        {
            long long mid = lo + (hi - lo) / 2;
            long long sq = mid * mid;
            if (sq == x)
            {
                return mid;
            }
            else if (sq > x)
            {
                hi = mid - 1;
            }
            else
            {
                lo = mid + 1;
            }
        }
        return hi;
    }
};

class Solution
{
public:
    int climbStairs(int n)
    {
        int s1 = 1, s2 = 2;
        for (int i = 2; i < n; ++i)
        {
            s1 = s1 + s2;
            swap(s1, s2);
        }
        return n >= 2 ? s2 : s1;
    }
};

class Solution
{
public:
    int minDistance(string word1, string word2)
    {
        int m = word1.size(), n = word2.size();
        vector<vector<int>> dp(m + 1, vector<int>(n + 1, INT_MAX));
        for (int i = 0; i <= m; ++i)
        {
            for (int j = 0; j <= n; ++j)
            {
                if (i == 0)
                {
                    dp[i][j] = j;
                }
                else if (j == 0)
                {
                    dp[i][j] = i;
                }
                else if (word1[i - 1] == word2[j - 1])
                {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else
                {
                    dp[i][j] = min({dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]}) + 1;
                }
            }
        }
        return dp[m][n];
    }
};

class Solution
{
public:
    bool searchMatrix(vector<vector<int>> &matrix, int target)
    {
        if (matrix.empty() || matrix[0].empty())
            return false;
        int m = matrix.size(), n = matrix[0].size();
        int
            lo = 0,
            hi = m * n - 1;
        while (lo <= hi)
        {
            int mid = lo + (hi - lo) / 2;
            int i = mid / n, j = mid % n;
            if (matrix[i][j] == target)
            {
                return true;
            }
            else if (matrix[i][j] < target)
            {
                lo = mid + 1;
            }
            else
            {
                hi = mid - 1;
            }
        }
        return false;
    }
};

class Solution
{
public:
    void sortColors(vector<int> &nums)
    {
        sort(nums.begin(), nums.end());
    }
};

class Solution
{
public:
    vector<vector<int>> combine(int n, int k)
    {
        vector<int> combination;
        vector<vector<int>> ret;
        vector<bool> chosen(n + 1, false);
        backtrack(1, n, k, chosen, combination, ret);
        return ret;
    }
    void backtrack(int j, int n, int k, vector<bool> &chosen, vector<int> &combination, vector<vector<int>> &ret)
    {
        if (combination.size() == k)
            ret.push_back(combination);
        for (int i = j; i <= n; ++i)
        {
            if (chosen[i] == true || (!combination.empty() && i < combination.back()))
                continue;
            chosen[i] = true;
            combination.push_back(i);
            backtrack(j + 1, n, k, chosen, combination, ret);
            chosen[i] = false;
            combination.pop_back();
        }
    }
};

class Solution
{
public:
    vector<int> subset;
    vector<vector<int>> s;
    int n;

    vector<vector<int>> subsets(vector<int>& nums)
    {
        n = nus.size();
        search(0);
        vector<vector<int>> ret;
        for (auto &ss : s)
        {
            vector<int> tmp;
            for (auto &i : ss)
            {
                tmp.push_back(num[i]);
            }
            ret.push_bakc(tmp);
        }
        return ret;
    }

    void search(int k)
    {
        if (k == n)
            s.push_back(subset);
        else
        {
            subset.push_back(k);
            search(k + 1);
            subset.pop_back();
            search(k + 1);
        }
    }

    vector<vector<int>> subsets2(vector<int> &nums)
    {
        vector<vector<int>> ret;
        vector<int> subset;
        backtrack(0, nums, subset, ret);
        return ret;
    }
    void backtrack(int k, vector<int> &nums, vector<int> &subset,
                   vector<vector<int>> &ret)
    {
        int n = nums.size();
        if (k == n)
        {
            ret.push_back(subset);
            return;
        }
        subset.push_back(nums[k]);
        backtrack(k + 1, nums, subset, ret);
        subset.pop_back();
        backtrack(k + 1, nums, subset, ret);
    }

    vector<vector<int>> subsets3(vector<int> &nums)
    {
        vector<vector<int>> ret{{}};
        for (auto &n : nums)
        {
            int sz = ret.size();
            for (int i = 0; i < sz; ++i)
            {
                ret.push_back(ret[i]);
                ret.back().push_back(n);
            }
        }
        return ret;
    }
};

class Solution
{
public:
    int dir[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    bool exist(vector<vector<char>> &board, string word)
    {
        if (word.empty())
            return true;
        if (board.empty() || board[0].empty())
            return false;
        int m = board.size(), n = board[0].size(), k = word.size();
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (backtrack(board, word, i, j, 0))
                    return true;
        return false;
    }
    {
        bool backtrack(vector<vector<char>> & board, string word, int i, int j, int k) int m = board.size(), n = board[0].size(), sz = word.size();
        if (board[i][j] != word[k])
            return false;
        if (k == sz - 1)
            return true;
        board[i][j] = '#';
        for (int d = 0; d < 4; ++d)
        {
            int ni = i + dir[d][0], nj = j + dir[d][1];
            if (ni < 0 || ni >= m || nj < 0 || nj >= n)
                continue;
            if (backtrack(board, word, ni, nj, k + 1))
                return true;
        }
        board[i][j] = word[k];
        return false;
    }
};

class Solution{
public:
    int removeDuplicate(vector<int> & nums){
        if(nums.size() <= 2){
            return nums.size();
        }
        int slow = 0, fast = 1, n = nums.size(), cnt = 1;
        for(; fast<n; ++fast){
            while(fast < n && nums[fast] == nums[fast -1]){
                if(cnt <= 1){
                    ++cnt;
                    nums[++slow] = nums[fast];
                }
                ++fast;
            }
            if(fast < n && nums[fast] != nums[slow]){
                cnt = 1;
                nums[++slow] = nums[fast];
            }
        }
        return slow + 1;
    }
};

class Solution
{
public:
    ListNode *deleteDuplicate(ListNode *head)
    {
        if (head == nullptr || head->next == nullptr)
            return head;

        ListNode *slow = head, *fast = head->next;
        while (fast->next)
        {
            while (fast && fast->val == slow->val && fast->next)
            {
                fast = fast->next;
            }
            if (fast && fast->val != slow->val)
            {
                slow->next = fast;
                slow = slow->next;
            }
        }
        if (slow->val == fast->val)
        {
            slow->next = fast->next;
        }
        return head;
    }
};

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

class Solution
{
public:
    void mergeSortedArray(vector<int> &nums1, int m, vector<int> &nums2, int n)
    {
        copy(nums2.begin(), nums2.end(), nums1.begin() + m);
        inplace_merge(nums1.begin(), nums1.begin() + m, nums1.end());
    }
};

class Solution
{
public:
    vector<int> inorderTraversal(TreeNode *root)
    {
        if (!root)
            return {};
        vector<int> ret = inorderTraversal(root->left);
        ret.push_back(root->val);
        vector<int> right = inorderTraversal(root->right);
        ret.insert(ret.end(), right.begin(), right.end());
        return ret;
    }

    vector<int> inorderTraversal(TreeNode *root)
    {
        if (!root)
            return {};
        vector<int> ret;
        TreeNode *cur = root;
        stack<TreeNode *> st;
        while (cur || !st.empty())
        {
            while (cur)
            {
                st.push(cur);
                cur = cur->left;
            }
            cur = st.top();
            st.pop();
            ret.push_back(cur->val);
            cur = cur->right;
        }
        return ret;
    }
};

class Solution
{
public:
    // Unique Binary Search Tree
    int numTrees(int n)
    {
        long C = 1;
        for (int i = 0; i < n; ++i)
            C = C * 2 * (2 * i + 1) / (i + 2);
        return C;
    }
};

class Solution
{
public:
    bool isSymmetric(TreeNode *root)
    {
        stack<pair<TreeNode *, TreeNode *>> st;
        st.emplace(root, root);
        while (!st.empty())
        {
            pair<TreeNode *, TreeNode *> cur = st.top();
            st.pop();
            if (cur.first == nullptr && cur.second == nullptr)
                continue;
            if (cur.first != nullptr && cur.second == nullptr)
                return false;
            if (cur.first == nullptr && cur.second != nullptr)
                return false;
            if (cur.first->val != cur.second->val)
                return false;
            st.emplace(cur.first->left, cur.second->right);
            st.emplace(cur.first->right, cur.second->left);
        }
        return true;
    }

    bool isSymmetric(TreeNode *root)
    {
        if (!root)
            return true;
        return isSubtreeSymmetric(root->left, root->right);
    }
    bool isSubtreeSymmetric(TreeNode *left, TreeNode *right)
    {
        if (!left && !right)
            return true;
        if ((!left && right) || (left && !right))
            return false;
        return left->val == right->val && isSubtreeSymmetric(left->left, right - > right) && isSubtreeSymmetric(left->right, right->left);
    }
};

class Solution{
public:
    vector<vector<int>> levelOrder(TreeNode* root){
        if(root == nullptr){
            return {};
        }
        vector<vector<int>> ret;
        queue<TreeNode*> q;
        q.push(root);
        while(!q.empty()){
            int n = q.size();
            vector<int> level;
            for(int i=0; i<n; ++i){
                TreeNode* cur = q.front();
                q.pop();
                level.push_back(curr->val);
                if(cur->left){
                    q.push(cur->left);
                }if(cur->right){
                    q.push(cur->right);
                }
            }
            ret.push_back(level);
        }
        return ret;
    }
};

class Solution
{
public:
    vector<vector<int>> zigzagLevelOrder(TreeNode *root)
    {
        vector<vector<int>> ret;
        if (root == nullptr)
            return ret;
        queue<pair<TreeNode *, int>> q;
        q.emplace(root, 0);
        while (!q.empty())
        {
            int n = q.size();
            vector<int> level;
            int depth = q.front().second;
            for (int i = 0; i < n; ++i)
            {
                pair<TreeNode *, int> cur = q.front();
                q.pop();
                level.push_back(cur.first->val);
                if (cur.first->left)
                    q.emplace(cur.first->left, cur.second + 1);
                if (cur.first->right)
                    q.emplace(cur.first->right, cur.second + 1);
            }
            if (depth % 2 != 0)
                reverse(level.begin(), level.end());
            ret.push_back(level);
        }
        return ret;
    }
};

class Solution{
public:
    // construct binary tree from preorder and Inorder
    TreeNode *buildTree(vector<int> &preorder, vector<int> &inorder, int p_start = 0, int p_stop = INT_MAX, int i_start = 0, int i_stop = INT_MAX)
    {
        if (p_stop == INT_MAX)
            p_stop = i_stop = inorder.size() - 1;
        if (i_start > i_stop)
            nullptr;
        int i = i_start;
        for (; i <= i_stop; ++i)
            if (inorder[i] == preorder[p_start])
                break;

        TreeNode *root = new TreeNode(preorder[p - start]);
        root->left = buildTree(preorder, inorder, p_start + 1, p_start + 1 + (i - 1) - i_start, i_start, i - 1);
        root->right = buildTree(preorder, inorder, p_start + 1 + (i - 1) - i_start + 1, p_stop, i + 1, i_stop);
        return root;
    }
};

class solution
{
public:
    TreeNode *sortedListToBST(ListNode *head)
    {
        if (head == nullptr)
            return nullptr;
        if (head->next == nullptr)
            return new TreeNode(head->val);
        ListNode *slow = head, *fast = head, *preSlow = head;
        while (fast && fast->next)
        {
            preSlow = slow;
            slow = slow->next;
            fast = fast->next->next;
        }
        preSlow->next = nullptr;
        TreeNode *root = new TreeNode(slow->val);
        root->left = sortedListToBST(head);
        root->right = sortedListToBST(slow->next);
        return root;
    }
};

class Solution{
public:
    bool isBalanced(TreeNode* root){
        if(root == nullptr)
            return true;
        return abs(depth(root->left) - depth(root->right)) <= 1 && isBalanced(root->left) && isBalanced(root->right);
    }

    int depth(TreeNode* root){
        if(root == nullptr)
            return 0;
        return 1 + max(depth(root->left), depth(root->right));
    }
}

class Solution
{
public:
    void flatten(TreeNode *node)
    {
        if (root == nullptr)
            return;
        flatten(root->left);
        flatten(root->right);
        if (root->left)
        {
            TreeNode *cur = root->left;
            while (cur->right)
                cur = cur->right;

            cur->right = root->right;
            root->right = root->left;
            root->left = nullptr;
        }
    }

    void flatten2(TreeNode *root)
    {
        if (root == nullptr)
            return;

        TreeNode *list = new TreeNode(0), *l = list;
        stack<TreeNode *> st;
        st.push(root);
        while (!st.empty())
        {
            TreeNode *cur = st.top();
            st.pop();
            if (cur->right)
                st.push(cur->right);
            if (cur->left)
                st.push(cur->left);
            l->right = cur;
            l->left = nullptr;
            l = l->right;
        }
        root = list->right;
    }
};

class Solution
{
public:
    vector<vector<int>> generatePascalTriangle2(int numRows)
    {
        if (numRows < 0)
            return {};
        vector<vector<int>> ret(numRows);
        for (int i = 0; i < numRows; ++i)
        {
            ret[i].resize(i + 1, 1);
            for (int j = 1; j < i; ++j)
                ret[i][j] = ret[i - 1][j] + ret[i - 1][j - 1];
        }
        return ret;
    }
};

class Solution
{
public:
    int maxProfit(vector<int> &prices)
    {
        int n = prices.size();
        if (n <= 1)
            return 0;
        vector<int> diff(n - 1, 0);
        for (int i = 0; i < n - 1; ++i)
        {
            diff[i] = prices[i + 1] - prices[i];
        }
        int max_sum = max(0, diff[0]);
        for (int i = 1; i < n - 1; ++i)
        {
            if (diff[i - 1] > 0)
                diff[i] += diff[i - 1];
            max_sum = max(diff[i], max_sum);
        }
        return max_sum;
    }
};

class solution
{
public:
    int sumNumber(TreeNode *root)
    {
        long long ret = 0, num = 0;
        backtrack(num, ret, root);
        return ret;
    }

    void backtrack(long long &num, long long &ret, TreeNode *root)
    {
        if (!root)
            return;
        if (!root->left && !root->right)
        {
            ret += num * 10 + root->val;
            return;
        }
        num = num * 10 + root->val;
        backtrack(num, ret, root->left);
        backtrack(num, ret, root->right);
        num /= 10;
    }
};

class Solution
{
public:
    int singleNumber(vector<int> &nums)
    {
        int ret = 0;
        for (auto &i : nums)
            ret ^= i;
        return ret;
    }
};

class Solution
{
public:
    int singleNumberII(vector<int> &nums)
    {
        unordered_map<int, int> cnt;
        for (auto &i : nums)
            ++cnt[i];
        for (auto &[k, v] : cnt)
            if (v == 1)
                return k;
        return 0;
    }
};

class Solution
{
public:
    vector<int> preorderTraversal(TreeNode *root)
    {
        if (root == nullptr)
            return {};
        vector<int> ret;
        ret.push_back(root->val);
        vector<int> l = preorderTraversal(root->left);
        vector<int> r = preorderTraversal(root->right);
        ret.insert(ret.end(), l.begin(), l.end());
        ret.insert(ret.end(), r.begin(), r.end());
        return ret;
    }

    vector<int> preorderTravesal(TreeNode* root){
        if(root == nullptr)
            return {};
        vector<int> ret;
        stack<TreeNode*> st;
        st.push(root);
        while(!st.empty()){
            TreeNode* cur = st.top();
            st.pop();
            ret.push_back(cur->val);
            if(cur->right){
                st.push(cur->right);
            }
            if(cur->left){
                st.push(cur->left);
            }
        }
        return ret;
    }
};

class Solution
{
public:
    vector<int> postorderTraversal(TreeNode *root)
    {
        vector<int> ret;
        if (root == nullptr)
            return ret;
        stack<TreeNode *> st;
        st.push(root);
        while (!st.empty())
        {
            TreeNode *cur = st.top();
            st.pop();
            ret.push_back(cur->val);
            if (cur->left != nullptr)
                st.push(cur->left);
            if (cur->right != nullptr)
                st.push(cur->right);
        }
        reverse(ret.begin(), ret.end());
        return ret;
    }

    vector<int> postorderTraversal(TreeNode *root)
    {
        if (!root)
            return {};
        vector<int> ret = postorderTraversal(root->left);
        vector<int> right = postorderTraversal(root->right);
        ret.insert(ret.end(), right.begin(), right.end());
        ret.push_back(root->val);
        return ret;
    }
};

class Solution
{
public:
    // minium in rotated sorted Array
    int findMin(vector<int> &nums)
    {
        if (nums.empty())
            return -1;
        int n = nums.size();
        int lo = 0, hi = n - 1;
        while (lo < hi)
        {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > nums[hi])
            {
                lo = mid + 1;
            }
            else
            {
                hi = mid;
            }
        }
        return nums[hi];
    }
};

class Solution
{
public:
    int findPeakElement(vector<int> &nums)
    {
        if (nums.empty())
            return -1;
        int lo = 0, hi = nums.size() - 1;
        while (lo < hi)
        {
            int mid = lo + (hi - lo) / 2;
            if (mid - 1 >= lo && nums[mid] < nums[mid - 1])
            {
                hi = mid - 1;
            }
            else if (mid + 1 <= hi && nums[mid] < nums[mid + 1])
            {
                lo = mid + 1;
            }
            else
            {
                return mid;
            }
        }
        return lo;
    }
};

class Solution{
public:
    int majorityElement(vector<int>& nums){
        int n = nums.size();
        if(n == 0)
            return -1;
        unordered_map<int, int> s;
        int major = nums[0], cnt = 1;
        for(auto& i : nums){
            if(++s[i] > cnt){
                cnt = s[i];
                major = i;
            }
        }
        return cnt > n/2 ? major : -1;
    }
};

class Solution
{
public:
    int houseRob(vector<int> &nums)
    {
        if (nums.size() >= 2)
            nums[1] = max(nums[0], nums[1]);
        for (int i = 2; i < nums.size(); ++i)
            nums[i] = max(nums[i - 1], nums[i - 2] + nums[i]);
        return nums.size() > 0 ? nums.back() : 0;
    }
};

class Solution
{
public:
    const int inf = numeric_limits<int>::max();
    vector<vector<int>> dp;
    int rows, cols;
    int getMinHealth(int curCell, int nextRow, int nextCol)
    {
        if (nextRow >= rows || nextCol >= cols)
            return inf;
        int nextCell = dp[nextRow][nextCol];
        return max(1, nextCell - curCell);
    }
    int calculateMinimumHP(vector<vector<int>> &dungeon)
    {
        rows = dungeon.size();
        cols = dungeon[0].size();
        dp.assign(rows, vector<int>(cols, inf));
        int curCell, rightHealth, downHealth, nextHealth, minHealth;
        for (int row = rows - 1; row >= 0; --row)
        {
            for (int col = cols - 1; col >= 0; --col)
            {
                curCell = dungeon[row][col];
                rightHealth = getMinHealth(curCell, row, col + 1);
                downHealth = getMinHealth(curCell, row + 1, col);
                nextHealth = min(rightHealth, downHealth);
                if(nextHealth != inf){
                    minHealth = nextHealth;
                } else {
                    minHealth = curCell >= 0 ? 1 : 1 - curCell;
                }
                dp[row][col] = minHealth;
            }
        }
        return dp[0][0];
    }
};

class Solution
{
public:
    int numIslands(vector<vector<char>> &grid)
    {
        int m = grid.size();
        if (m == 0)
            return 0;
        int n = grid[0].size(), ret = 0;
        stack<pair<int, int>> st;
        int d[4][2] = {{0,
                        -1},
                       {0, 1},
                       {-1, 0},
                       {1, 0}};
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if (grid[i][j] == '1')
                {
                    ++ret;
                    st.emplace(i, j);
                    while (!st.empty())
                    {
                        pair<int, int> cur = st.top();
                        st.pop();
                        grid[cur.first][cur.second] = '0';
                        for (int k = 0; k < 4; ++k)
                        {
                            int ni = cur.first + d[k][0], nj = cur.second + d[k][1];
                            if (ni > -1 && ni < m && nj > -1 && nj < n && grid[ni][nj] == '1')
                                st.emplace(ni, nj);
                        }
                    }
                }
            }
        }
        return ret;
    }
};

class UnionFind
{
private:
    vector<int> id;
    vector<int> sz;

public:
    UnionFind(int n)
    {
        id.resize(n);
        iota(id.begin(), id.end(), 0);
        sz.resize(n, 1);
    }
    int find(int x)
    {
        if (x == id[x])
            return x;
        return id[x] = find(id[x]);
    }
    bool merge(int x, int y)
    {
        int i = find(x), j = find(y);
        if (i == j)
            return false;
        if (sz[i] > sz[j])
        {
            id[j] = i;
            sz[i] += sz[j];
        }
        else
        {
            id[i] = j;
            sz[j] += sz[i];
        }
        return true;
    }
};

class solution{
public:
    bool isIsomorphic(string s, string t){
        unordered_map<char, unordered_set<char>> mpST, mpTS;
        for(int i=0; i<(int)s.size(); ++i){
            mpST[s[i]].insert(t[i]);
            mpTS[t[i]].insert(s[i]);
        }
        for(auto& m : mpST)
            if(m.second.size() > 1)
                return false;

        for(auto& m : mpTS)
            if(m.second.size() > 1)
                return false;
        
        return true;
    }
};

class Solution
{
public:
    bool containsNearbyDuplicate(vector<int> &nums, int k)
    {
        unordered_map<int, int> mp;
        int n = nums.size();
        for (int i = 0; i < n; ++i)
        {
            if (mp.count(nums[i]) > 0)
            {
                if (i - mp[nums[i]] <= k)
                {
                    return true;
                }
            }
            mp[nums[i]] = i;
        }
        return false;
    }

    bool containsNearbyDuplicate(vector<int> &nums, int k)
    {
        unordered_set<int> visited;
        for (int i = 0; i < (int)nums.size(); ++i)
        {
            if (visited.count(nums[i]) > 0)
                return true;
            visited.insert(nums[i]);
            if (i - k >= 0)
                visited.erase(nums[i - k]);
        }
        return false;
    }
};

class Solution
{
public:
    int maximalSquare(vector<vector<char>> &matrix)
    {
        int m = matrix.size();
        if (m == 0)
            return 0;
        int n = matrix[0].size();
        int ret = 0;
        vector<vector<int>> dp(m + 1, vector<int>(n + 1, 0));
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                dp[i + 1][j + 1] = matrix[i][j] - '0';
                if (dp[i + 1][j + 1] > 0)
                {
                    dp[i + 1][j + 1] = min(dp[i][j], min(dp[i][j + 1], dp[i + 1][j])) + 1;
                    ret = max(ret, dp[i + 1][j + 1]);
                }
            }
        }
        return ret * ret;
    }
};


class Solution{
public:
    int countNode(TreeNode* root){
        if(!root){
            return 0;
        }
        queue<TreeNode*> q;
        q.push(root);
        int ret = 0;
        while(!q.empty()){
            int n = q.size();
            ret += n;
            for(int i=0; i<n; ++i){
                TreeNode* cur = q.front();
                q.pop();
                if(cur->left){
                    q.push(cur->left);
                }if(cur->right){
                    q.push(cur->right);
                }
            }
        }
        return ret;
    }
};

class Solution {
public:
    TreeNode* invertTree(TreeNode* root){
        if(root == nullptr)
            return nullptr;
        swap(root->left, root->right);
        invertTree(root->left);
        invertTree(root->right);
        return root;
    }
};

class Solution
{
public:
    int kthSmallest(TreeNode *root, int k)
    {
        stack<TreeNode *> st;
        st.push(root);
        unordered_set<TreeNode *> visited;
        while (!st.empty())
        {
            TreeNode *cur = st.top();
            st.pop();
            if (cur->left && visited.count(cur->left) == 0)
            {
                if (cur->right)
                    st.push(cur->right);
                st.push(cur);
                st.push(cur->left);
            }
            else
            {
                if (!cur->left && cur->right)
                    st.push(cur->right);
                visited.insert(cur);
                if (--k == 0)
                    return cur->val;
            }
        }
        return 0;
    }

    class Solution
    {
    public:
        int kthSmallest(TreeNode *root, int k)
        {
            vector<int> list = BST2vector(root);
            return list[k - 1];
        }
        vector<int> BST2vector(TreeNode *root)
        {
            if (!root)
                return {};
            vector<int> ret = BST2vector(root->left);
            ret.push_back(root->val);
            vector<int> right = BST2vector(root->right);
            ret.insert(ret.end(), right.begin(), right.end());
            return ret;
        }
    };
};

class Solution
{
public:
    bool isPowerOfTwo(int n)
    {
        return n > 0 && builtin_popcount(n) == 1;
    }
};

class Solution
{
public:
    void deleteNode(ListNode *node)
    {
        if (node == nullptr)
            return;
        if (node->next == nullptr)
        {
            node = node->next;
        }
        else
        {
            node->val = node->next->val;
            node->next = node->next->next;
            node = node->next;
        }
    }

    void deleteNode(ListNode *node)
    {
        *node = *(node->next);
    }

    void deleteNode(ListNode *node)
    {
        node->val = node->next->val;
        node->next = node->next->next;
    }
};

class Solution
{
public:
    bool isAnagram(string s, string t)
    {
        vector<int> cntS(26, 0), cntT(26, 0);
        for (int i = 0; i < (int)s.size(); ++i)
            ++cntS[s[i] - 'a'];
        for (int i = 0; i < (int)t.size(); ++i)
            ++cntT[t[i] - 'a'];
        for (int i = 0; i < 26; ++i)
        {
            if (cntS[i] != cntT[i])
            {
                return false;
            }
        }
        return true;
    }

    bool isAnagram(string s, string t)
    {
        sort(s.begin(), s.end());
        sort(t.begin(), t.end());
        return s == t;
    }
};

class Solution
{
public:
    bool isStrobogrammatic(string num)
    {
        unordered_map<char, char> mp{{'0' '8'}, {'9', '6'}};
        string stro =
            "";
        for (auto &n : num)
        {
            if (mp.count(n) == 0)
                return false;
            stro = mp[n] + stro;
            , '0'
        }
        , {'1'} return stro == num;
    }
};
