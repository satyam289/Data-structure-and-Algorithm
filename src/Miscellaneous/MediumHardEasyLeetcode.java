package Miscellaneous;

import java.util.*;

public class MediumHardEasyLeetcode {
    
    /*
     * LeetCode : Longest Palindromic Substring
Question Given an unsorted array of integers, find the length of longest increasing subsequence.
Example:
Input: [10,9,2,5,3,7,101,18]
Output: 4 
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4
     */
    public int lengthOfLIS(int [] nums) {
        if(nums.length <=0 ){
            return nums.length;
        }
        int [] dp = new int[nums.length];
        int max = -1;
        Arrays.fill(dp, 1);
        for(int i=1; i< nums.length; i++) {
            for(int j=0; j<i; j++) {
                if(nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    private class Node {
        int val;
        Node left;
        Node right;
        Node next;
        Node(int val){
            this.val= val;
        }
    }
    
    /*
     * LeetCode : Populating Next Right Pointers in Each Node 
Question : You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:

Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
Initially, all next pointers are set to NULL.

Input: root = [1,2,3,4,5,6,7]
Output: [1,#,2,3,#,4,5,6,7,#]
     */
    public Node connect (Node root){
        if(root == null){
            return null;
        }
        Queue<Node> q = new LinkedList();
        q.add(root);
        q.add(null);
        while(!q.isEmpty()){
            Node curr = q.poll();
            if(curr == null && q.isEmpty()){
                return root;
            }else if(curr == null){
                q.add(null);
                continue;
            } else {
                curr.next = q.peek();
                if(curr.left != null){
                    q.add(curr.left);
                }
                if(curr.right != null){
                    q.add(curr.right);
                }
            }
        }
        return root;
    }
    
    /**
     * LeetCode : Product of Array Except Self
 
Question : Given an array nums of n integers where n greater than 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Input:  [1,2,3,4]
Output: [24,12,8,6]

Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.
     */
    public int [] productExceptSelf(int [] nums){
        int [] prod = new int[nums.length];

        prod[0] = 1;
        for(int i = 1; i< nums.length; i++){
            prod[i] = prod[i-1] * nums[i-1];
        }

        int temp = 1;
        for(int i = nums.length-2 ; i>=0; i--){
            temp *= nums[i+1];
            prod[i] *= temp;
        }
        return prod;
    }
   
    /**
     * LeetCode : Generate Parentheses
 
Question : Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:
[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
     */
    public List<String> generateParenthesis(int n){
        List<String> result2 = new ArrayList<>();
        helperGP(result2, "", 0, 0, n);
        return result2;
    }


    private void helperGP(List<String> result, String s, int open, int close, int n) {
        if(s.length() == n*2){
            result.add(s);
            return;
        }
        if(open < n){
            helperGP(result, s+"(", open+1, close, n);
        }
        if(open > close){
            helperGP(result, s+")", open, close+1, n);
        }
    }

    public boolean isValidBST(Node root){
        return helperBT(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean helperBT(Node root, int minValue, int maxValue) {
        if(root == null)
            return true;
        if(root.val <= minValue || root.val >= maxValue){
            return false;
        }
        return helperBT(root.left, minValue, root.val) && helperBT(root.right, root.val, maxValue);
    }
    
    /**
     * LeetCode : Group Anagrams
Question : Given an array of strings, group anagrams together.
Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
     */
    public List<List<String>> groupAnagrams(String [] strs){
        List<List<String>> res = new ArrayList<>();
        if(strs.length == 0){
            return res;
        }

        HashMap<String, ArrayList<String>> hm = new HashMap<>();
        for(String str: strs){
            int [] count = new int[26];
            for(int i=0; i<str.length(); i++) {
                count[str.charAt(i)-'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<count.length; i++) {
                sb.append(count[i]).append("#");
            }
            if(hm.containsKey(sb.toString())) {
                hm.get(sb.toString()).add(str);
            } else {
                ArrayList<String> temp = new ArrayList<String>();
                temp.add(str);
                hm.put(sb.toString(), temp);
            }
        }
        for(List<String> list : hm.values()){
            res.add(list);
        }
        return res;
    }
    
    class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    } 

    /*
     * LeetCode : Odd Even Linked List
Question : Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.
You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

Example 1:
Input: 1-2-3-4-5-NULL
Output: 1-3-5-2-4-NULL
     */
    public ListNode oddEvenList(ListNode  head){
        if(head == null || head.next == null)
            return head;
        
        ListNode odd = head; // 1
        ListNode evenHead = head.next; //2
        ListNode even = evenHead;
        while(even != null && even.next != null){
            odd.next = even.next; // 1->3 // 1->3->5
            odd = odd.next; // 3  // 5
            even.next = odd.next; //2->4 // 2->4->null
            even = even.next;// 4 // null           
        }
        odd.next = evenHead;
        return head;
    }

    class Obj {
        String word;
        int len;
        Obj(String word, int len) {
            this.word = word;
            this.len = len;
        }
    }

    /**
     * LeetCode : Word Ladder
 
Question : Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.

Input:
beginWord = "hit",
endWord = "cog",

wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
     */
    public int LadderLength(String beginWord, String endWord, List<String> wordList){
        if(beginWord.length() == 0 || endWord.length() ==0 || wordList.size() ==0){
            return 0;
        }
        Queue<Obj> q = new LinkedList<>();
        q.add(new Obj(beginWord, 1));
        
        while(!q.isEmpty()){
            Obj curr = q.poll();
            ListIterator<String> itr = wordList.listIterator();
            while(itr.hasNext()){
                String str = itr.next();
                itr.remove();
                if(isAdjacent(str, curr.word)){
                    q.add(new Obj(str, curr.len+1));
                }
                if(str.equals(endWord)){
                    return curr.len+1;
                }
            }
        }
        return 0;
    }

    public boolean isAdjacent(String s1 , String s2){
        int count =0;
        for(int i=0; i< s1.length(); i++){
            if(s1.charAt(i) != s2.charAt(i)){
                count++;
            }
            if(count >1) {
                return false;
            }
        }
        return count == 1;
    }

    /**
     * LeetCode 75. Sort Colors
Question : Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
Note: You are not suppose to use the library's sort function for this problem.

Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]

A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
     */
    public void sortColors(int [] nums){
        int i = 0; // start meant for 0
        int j = nums.length-1; // mid meant for 1
        int k = j; // end meant for 2
        while( i <= j ){
            int curr = nums[j]; // 0 // 2
            if(curr == 0){
                swap(nums, i++, j);
            } else if(curr == 2){
                swap(nums, j--, k--);
            } else {
                j--;
            }
        }
    }

    public void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
    
    /*
     * LeetCode 394. Decode String
 
Question : Given an encoded string, return its decoded string.
The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
     */
    public String decodeString(String s){
        Stack<Integer> is = new Stack<>();
        Stack<String> ss = new Stack<>();

        int ptr = 0;
        String res = "";
        while(ptr < s.length()) {
            char curr = s.charAt(ptr);
            if(Character.isDigit(curr)) {
                int num = 0;
                while(Character.isDigit(s.charAt(ptr))) {
                    num = num * 10 + (s.charAt(ptr) - '0');
                    ptr++;
                }
                is.push(num);
            } else if(curr == '[') {
                ss.push(res);
                res = "";
                ptr++;
            } else if(curr == ']') {
                StringBuilder sb = new StringBuilder(ss.pop());
                int repeat = is.pop();
                for(int i=1; i<= repeat; i++){
                    sb.append(res);
                }
                res = sb.toString();
                ptr++;
            } else {
                res += curr;
                ptr++;
            }
        }
        return res;
    }
    
    /**
preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7
     */
    HashMap<Integer, Integer> hm= new HashMap<>();
    int preIndex = 0;

    public Node buildTree(int [] preOrder, int[] inOrder){
        for(int i=0; i<inOrder.length; i++){
            hm.put(inOrder[i], i);
        }
        return buildTree(preOrder, inOrder, 0, inOrder.length-1);
    }

    public Node buildTree(int[] preOrder, int[] inOrder, int start, int end) {
        if(start > end)
            return null;
        
        Node root = new Node(preOrder[preIndex++]);
        if(start == end){
            return root;
        }
        int index = hm.get(root.val);
        root.left = buildTree(preOrder, inOrder, start, index-1);
        root.right = buildTree(preOrder, inOrder, index+1, end);
        return root;
    }
    
    /*
     * LeetCode : Unique Paths
 
Question : A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
How many possible unique paths are there?

Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right - Right - Down
2. Right - Down - Right
3. Down - Right - Right
     */
    public int uniquePath(int m , int n){
        int [][] dp = new int[m][n];
        for(int i=0; i<m; i++){
            dp[i][0] = 1;
        }
        for(int i=0; i<n; i++){
            dp[0][i] = 1;
        }
        for(int i=1; i<m; i++){
            for(int j=1; j<n;j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

 /*
 LeetCode :  Minimum Path Sum
 
Question : Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
Note: You can only move either down or right at any point in time.

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 */   
    public int minPathSum(int [][] grid){
        int rows = grid.length;
        int cols = grid[0].length;
         
        int [][] dp = new int[rows][cols];

        for(int i=rows-1; i>=0; i--) {
            for(int j=cols-1; j>=0; j--) {

                if(i == rows-1 && j != cols-1){
                    dp[i][j] = grid[i][j] + grid[i][j+1];
                } else if(j == cols-1 && i != rows-1){
                    dp[i][j] = grid[i][j] + grid[i+1][j];
                } else if(i != rows-1 && j != rows-1){
                    dp[i][j] = grid[i][j] + Math.min(dp[i+1][j], dp[i][j+1]);
                } else {
                    dp[i][j] = grid[i][j];
                }
            }
        }
        return dp[0][0];
    }

    /*
     * Question : Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) - 8
sumRegion(1, 1, 2, 2) - 11
sumRegion(1, 2, 2, 4) - 12
Note:
You may assume that the matrix does not change.
There are many calls to sumRegion function.
You may assume that row1 ≤ row2 and col1 ≤ col2.
     */

    int [][] dp;

    public void numMatrix(int [][] matrix){
        if(matrix.length == 0|| matrix[0].length == 0){
            return;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;

        dp = new int[rows+1][cols +1];
        for(int i=0; i< rows; i++){
            for(int j=0; j<cols; j++){
                dp[i+1][j+1] = matrix[i][j] + dp[i][j+1] + dp[i+1][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2){
        return dp[row2+1][col2+1] - dp[row1+1][col1+1];
    }
    
    /*
     * Question : You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:

Input: coins = [1, 2, 5], amount = 11
Output: 3 
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
Note:
You may assume that you have an infinite number of each kind of coin.
     */
    public int coinChange(int[] coins, int amount){
        if(amount == 0 || coins.length == 0)
            return 0;
        
        int [] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;

        for(int i=0; i<coins.length; i++) {
            for(int j=coins[i]; j<=amount; j++) {
                dp[j] = Math.min(dp[j], dp[j-coins[i]]+1);
            }
        }
        return dp[amount] == amount+1 ? -1 : dp[amount];
    }
    
    /*
     * LeetCode : Largest Number
 
Question : Given a list of non negative integers, arrange them such that they form the largest number.
Input: [10,2]
Output: "210"
Example 2:

Input: [3,30,34,5,9]
Output: "9534330"
Note: The result may be very large, so you need to return a string instead of an integer.
     */
    public String largestNumber(int [] nums){
        if(nums.length == 0)
            return "";
        
        String [] sa = new String[nums.length];
        for(int i=0; i<nums.length; i++){
            sa[i] = Integer.toString(nums[i]);
        }
        Arrays.sort(sa, new Comparator<String>() {
            @Override
            public int compare(String a, String b){
                String o1 = a + b;
                String o2 = b + a;
                return o2.compareTo(o1);
            }
        });
        if(sa[0].equals("0")){
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for(String s: sa){
            sb.append(s);
        }
        return sb.toString();
    }
    
    /**
     * LeetCode : Merge Intervals
 
Question : Given a collection of intervals, merge all overlapping intervals.

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]

Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Input:
11110
11010
11000
00000

Output: 1
     */
    class Interval{
        int start;
        int end;
        Interval(int start, int end){
            this.start = start;
            this.end = end;
        }
    }

    public int [][] merge(int [][] intevals){
        if(intevals.length <= 1){
            return intevals;
        }
        LinkedList<Interval> l1 = new LinkedList<>();
        for(int [] temp : intevals){
            l1.add(new Interval(temp[0], temp[1]));
        }
        Collections.sort(l1, new Comparator<Interval>(){
            @Override
            public int compare(Interval a, Interval b){
                return a.start - b.start;
            }
        });
        LinkedList<Interval> result = new LinkedList<>();
        for(Interval curr: l1){
            if(result.isEmpty() || result.getLast().end < curr.start){
                result.add(curr);
            } else {
                result.getLast().end = Math.max(curr.end, result.getLast().end);
            }
        }
        int [][] res = new int[result.size()][2];
        int count = 0;
        for(Interval temp : result){
            res[count][0] = temp.start;
            res[count][0] = temp.end;
            count++;
        }
        return res;
    }

    /*
     * LeetCode : Longest Palindromic Substring
 
Question  Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
Example:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
     */

    public String longestPalindrome(String s){
        if(s != null && s.length() <= 1)
            return s;
        
        int len = 0, start = 0, end = 0, len1 = 0, len2 = 0;
        for(int i=0; i<s.length(); i++) {
            len1 = expandAroundCenter(s, i, i);
            len2 = expandAroundCenter(s, i, i+1);
            len = Math.max(len1, len2);
            if(len > end - start) {
                start = i - (len-1)/2;
                end = i + (len/2);
            }
        }
        return s.substring(start, end+1);
    }

    public int expandAroundCenter(String s, int i, int j){
        while(i>= 0 && j < s.length() && s.charAt(i) == s.charAt(j)){
            i--;
            j++;
        }
        return j-1-i;
    }

    /*
     * LeetCode : Number of Islands
 
Question  Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Input:
11110
11010
11000
00000

Output: 1

     */
    public int numIsland(char [][] grid){
        if(grid == null || grid.length == 0)
            return 0;
        
            boolean [][] visited = new boolean[grid.length][grid[0].length];
            int count = 0;
            for(int i=0; i< grid.length; i++){
                for(int j=0; j<grid[0].length; j++){
                    if(grid[i][j] == '1' && visited[i][j] == false){
                        dfs(grid, visited, i, j);
                        count++;
                    }
                }
            }
            return count;
       }
       
       public void  dfs(char [][] grid, boolean [][] visited, int i, int j){
        if(i<0 || i>= grid.length || j<0 || j>=grid[0].length){
            return;
        }
        visited[i][j] = true;
        dfs(grid, visited, i, j+1);
        dfs(grid, visited, i+1, j);
        dfs(grid, visited, i, j-1);
        dfs(grid, visited, i-1, j);
    }
    
    /**
     * LeetCode : K Closest Points To Origin
 
Question - We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane is the Euclidean distance.)

You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)

Example 1:

Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation: 
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) lt sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
     */
    public int[][] kCloset(int[][] points, int k){
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int [] a, int [] b){
                return b[0]*b[0] + b[1]*b[1] - (a[0]*a[0] + a[1]*a[1]);
            }
        });

        for(int [] p : points){
            pq.add(p);
            if(pq.size() > k){
                pq.poll();
            }
        }
        int [][] result = new int[k][2];
        int i=0;
        while(!pq.isEmpty()){
            result[i] = pq.poll();
            i++;
        }
        return result;
    }
    
    /*
     * LeetCode : Kth Largest Element in an Array
 
Question - Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4

Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.
     */
    public int findKthLargest(int [] nums, int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int ele: nums){
            pq.add(ele);
            if(pq.size() > k){
                pq.poll();
            }
        }
        return pq.poll();
    }

    /*
     * LeetCode : Add Two Numbers 
 
Question - You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Input: (2 - 4 - 3) + (5 - 6 - 4)
Output: 7 - 0 - 8
Explanation: 342 + 465 = 807
     */
    public ListNode add(ListNode l1, ListNode l2){
        int v1 = 0, v2 =0, sum = 0, carry =0;
        ListNode sumHead = new ListNode(-1);
        ListNode ptr = sumHead;

        while(l1!= null || l2 != null){
            if (l1 != null){
                v1 = l1.val;
                l1 = l1.next;
            } else {
                v1 = 0;
            }
            if(l2 != null){
                v2 = l2.val;
                l2 = l2.next;
            } else {
                v2 = 0;
            }
            sum = v1 + v2 + carry;
            carry = sum / 10;
            sum = sum % 10;

            ListNode temp = new ListNode(sum);
            ptr.next = temp;;
            ptr = ptr.next;
        }
        if(carry != 0) {
            ListNode temp = new ListNode(carry);
            ptr.next = temp;
        }
        return sumHead.next;
    }

/**
 * LeetCode : Longest Substring Without Repeating Characters 
 
Question - Given a string, find the length of the longest substring without repeating characters.

Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3.

 */
    public int lengthOfLongestSubString(String s){
        int st=0, en=0, cl=0, ml = -1;
        HashSet<Character> hs = new HashSet<>();
        while(en < s.length()){
            char curr = s.charAt(en);
            if(!hs.contains(curr)){
                hs.add(curr);
                en++;
                cl = en - st;
            } else {
                if(ml < cl){
                    ml = cl;
                }
                while(st <= en && en < s.length()){
                    if(s.charAt(st) != s.charAt(en)){ //move the start until, we found end character same , shrink the window
                        hs.remove(s.charAt(st));
                        st++;
                    } else { // repeating character case in substring, same the window but move forward
                        st++;
                        en++;
                        break;
                    }
                }
            }
        }
        if(ml < cl) {
            ml = cl;
        }
        if(ml == -1) {
            ml = s.length();
        }
        return ml;
    }
     
    //Container With Most Water
    public int maxArea(int [] height){
        int max = 0;
        int i=0, j= height.length-1;
        while(i<j){
            int area = (j-i)* Math.min(height[i], height[j]);
            if(max < area)
                max = area;
            
            if(height[i] < height[j]){
                i++;
            } else{
                j--;
            }
        }
        return max;
    }
    
    /*
     * LeetCode :   Maximal Square

Problem URL - https://leetcode.com/problems/maximal...
 
Question :Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

Input: 

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4
     */
    public int maximalSquare(char [][] matrix){
        if(matrix.length == 0) 
            return 0;
        
        int max = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;

        int [][] db = new int[rows][cols];
        for(int i=0; i<rows; i++) {
            db[i][0] = matrix[i][0] == '0' ? 0 : 1;
            max = Math.max(max, dp[i][0]);
        }
        for(int i=0; i< cols; i++){
            db[0][i] = matrix[0][i] == '0' ? 0 : 1;
            max = Math.max(max, dp[0][i]);
        }

        for(int i=1; i<rows; i++) {
            for(int j=1; j<cols; j++) {
                if(matrix[i][j] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i-1][j-1]), dp[i][j-1]) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max*max;
    }
    
    /**
     * LeetCode :   Best Time to Buy and Sell Stock with Cooldown 

Problem URL - https://leetcode.com/problems/best-ti...
 
Question :Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)

Example:

Input: [1,2,3,0,2]
Output: 3 
Explanation: transactions = [buy, sell, cooldown, buy, sell]
     */
    public int maxProfit(int [] prices){
        int len = prices.length;
        if(len <= 1)
            return 0;
        
        if(len == 2 && (prices[1] > prices[0])){
            return prices[1] - prices[0];
        } else if( len ==  2 && prices[0] > prices[1]){
            return 0;
        }
        int [][] dp = new int[len][2];

        dp[0][0] = 0; // no stock, no purchase on day 0
        dp[0][1] = -prices[0]; // we need to purchase as we have stock
        dp[1][0] = Math.max(dp[0][0], dp[0][1] + prices[1]); // either no purchase as same as yesterday or sold it today
        dp[1][1] = Math.max(dp[0][1], dp[0][0] - prices[1]); // // either no purchase as same as yesterday or purchase it today
        
        for(int i=2; i<len; i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-2][0] - prices[i]);
        }
        return dp[len-1][0];
    }

    /*
     * Question :Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i; and a non-negative integer fee representing a transaction fee.

You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction. You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)

Return the maximum profit you can make.

Example 
:
Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
Output: 8
Explanation: The maximum profit can be achieved by:
Buying at prices[0] = 1
Selling at prices[3] = 8
Buying at prices[4] = 4
Selling at prices[5] = 9
The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
     */

    public int maxProfit(int [] prices, int fee){
        int len = prices.length;
        if(len <= 1)
            return 0;
        
        int [][] dp = new int[len][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0]-fee;
        for(int i=1; i<len; i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][0] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i] - fee);
        }
        return dp[len-1][0];
    }
    
    /*
     * Time complexity - O(N * 2^N) 
Details - O(2^N) -  To generate all subsets 
    O(N) - For copying them into output list.

Space Complexity - O(N * 2^N) to keep all the subsets of length N, since each of N elements could be present or absent.
where N is length of given input array.
 
Question :Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
     */
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subset(int [] nums){
        if(nums.length == 0)
            return res;
        bt(0, new ArrayList<>(), nums);
        return res;
    }
    
    private void bt(int start, ArrayList<Integer> curr, int[] nums){
        res.add(new ArrayList<>(curr));
        for(int i=start; i<nums.length; i++){
            curr.add(nums[i]);
            bt(i+1, curr, nums);
            curr.remove(curr.size()-1);
        }
    }
    
    /**
     * Question :Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
     */

     /*
      * Question :Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
Note: The solution set must not contain duplicate subsets.

Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
      */
    List<List<String>> result = new ArrayList<>();

    public List<List<String>> subsetWithDup(int [] nums){
        if(nums.length == 0)
            return result;
        
        Arrays.sort(nums);
        bt2(0, new ArrayList<>(), nums);
        return result;
    }

    private void bt2(int start, ArrayList<Integer> curr, int [] nums){
        res.add(new ArrayList<>(curr));
        for(int i= start; i<nums.length; i++){
            if(i> start && nums[i-1] == nums[i]){
                continue;
            }
            curr.add(nums[i]);
            bt2(i+1, curr, nums);
            curr.remove(curr.size() -1);
        }
    }

    /*
     * Question : Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] , find the minimum number of conference rooms required.

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:

Input: [[7,10],[2,4]]
Output: 1
     */
    public int minMeetingRooms(int [][] intervals){
        if(intervals == null || intervals.length == 0)
            return 0;
        
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int [] b){
                return a[0] - b[0];
            }
        });
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int [] b){
                return a[1] - b[1];
            }
        });
        pq.add(intervals[0]);
        for(int i=1; i<intervals.length; i++){
            int[] curr = intervals[i];
            int[] pre = pq.poll();
            if(curr[0] < pre[1]){
                pq.add(pre);
                pq.add(curr);
            } else {
                pre[1] = curr[1];
                pq.add(pre);
            }
        }
        return pq.size();
    }
    
    /*
     * Question : Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
Input: [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
Example 2:

Input: [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
Example 3:

Input: [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
     */

    public int  eraseOverlapInterval(int [][] intervals){
        if(intervals ==  null || intervals.length == 0)
            return 0;
        
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int [] b){
                return a[0] - b[0];
            }
        });
        LinkedList<int []> res = new LinkedList<>();
        res.add(intervals[0]);

        for(int i=1; i<intervals.length; i++){
            int [] curr = intervals[i];
            int [] last = res.getLast();
            if(last[1] > curr[0]){
                last[1] = Math.min(last[i], curr[i]);
                res.removeLast();
                res.addLast(last);
            } else {
                res.addLast(curr);
            }
        }
        return intervals.length - res.size();
    }

   
    /**
     * Question : Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
     */
    public int [][] intervalIntersection(int[][] A, int [][]B){
        if(A== null || A.length == 0 && B== null || B.length == 0)
            return new int[0][0];
        
        int i=0, j=0;
        ArrayList<int[]> res = new ArrayList<>();
        while(i<A.length && j<B.length){
            int max = Math.max(A[i][0], B[j][0]);
            int min = Math.min(A[i][1], B[j][1]);
            if(max < min){
                res.add(new int [] {max, min});
            }
            if(A[i][1] < B[i][1]){
                i++;
            } else{
                j++;
            }
        }
        int [][] result2 = new int[res.size()][2];
        for(i=0; i<res.size(); i++){
            result2[i] = res.get(i);
        }
        return result2;
    }

    /*
     * Question : Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
Input: [2,1,5,6,2,3]
Output: 10
     */
    public int largestRectangleArea(int [] heights){
        if(heights.length == 0)
            return 0;
        
        if(heights.length ==1)
            return heights[0];
        
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        stack.add(0);
        
        for(int i=1; i<heights.length; i++){
            int curr = heights[i];
            if(curr >= heights[stack.peek()]){
                stack.add(i);
            } else {
                while(!stack.isEmpty() && curr < heights[stack.peek()]){
                    int temp = heights[stack.pop()];
                    if(stack.isEmpty()){
                        max = Math.max(max, temp*i);
                    } else {
                        max = Math.max(max, temp*(i-stack.peek()-1));
                    }
                }
                stack.add(i);
            }
        }
         int i = heights.length;
         while(!stack.isEmpty()){
            int temp = heights[stack.pop()];
            if(stack.isEmpty()){
                max = Math.max(max, temp*i);
            } else {
                max = Math.max(max, temp*(i-stack.peek()-1));
            }
        }
        return max;
    }

     /*
      * LeetCode : Maximal Rectangle
Problem URL - https://leetcode.com/problems/maximal...
Question :Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
Input:
[
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]
Output: 6
      */
    public int findtheArea(int [] h){
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        stack.add(0);
        
        for(int i=1; i<h.length; i++){
            int curr = h[i];
            if(stack.isEmpty() || curr >= h[stack.peek()]){
                stack.add(i);
            } else {
                while(!stack.isEmpty() && curr < h[stack.peek()]){
                    int temp = h[stack.pop()];
                    if(stack.isEmpty()){
                        max = Math.max(max, temp*i);
                    } else {
                        max = Math.max(max, temp*(i-stack.peek()-1));
                    }
                }
                stack.add(i);
            }
        }
         int i = h.length;
         while(!stack.isEmpty()){
            int temp = h[stack.pop()];
            if(stack.isEmpty()){
                max = Math.max(max, temp*i);
            } else {
                max = Math.max(max, temp*(i-stack.peek()-1));
            }
        }
        return max;
    }
    
    /*
     * Question :Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
Find the maximum coins you can collect by bursting the balloons wisely.
You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
0 gt n lt 500, 
0 gt  nums[i] lt 100

Input: [3,1,5,8]
Output: 167 
Explanation: nums = [3,1,5,8] -- [3,5,8] --   [3,8]   --  [8]  -- []
             coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
     */
    public int maxCoin(int [] nums){
        int len = nums.length;
        if(len == 0)
            return 0;
        
        int [][] dp = new int[len][len];
        for(int n=0; n<len; n++){
            for(int i=0; i+n<len ; i++){
                int j = i+n;
                for(int k=i; k<=j; k++){
                    int leftNum = i == 0 ? 1: nums[i-1];
                    int rightNum = j == len-1 ? 1: nums[j+1];
                    int left = k == i ? 0: dp[i][k-1];
                    int right = k == j ? 0: dp[k+1][j];
                    dp[i][j] = Math.max(dp[i][j], leftNum*nums[k]*rightNum + left + right);
                }
            }
        }
        return dp[0][len-1];
    }
    
    /*
     * Question :Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
Input:
[
  1-4-5,
  1-3-4,
  2-6
]
Output: 1-1-2-3-4-4-5-6
     */
    public ListNode mergeKList(ListNode [] list){
        if(list.length == 0)
            return null;
        
        int interval = 1;
        int len = list.length;
        while(interval < len){
            for(int i=0; i< len-interval; i+=(interval*2)){
                merge(list, i, i+interval);
            }
            interval *= 2;
        }
        return list[0];
    }

    private void merge(ListNode[] lists, int index1, int index2){
        ListNode ans = new ListNode(-1);
        ListNode ptr = ans;
        ListNode l1 = lists[index1];
        ListNode l2 = lists[index2];
        while(l1 !=null || l2!= null){
            if(l1 == null){
                ptr.next = l2;
                break;
            }
            if(l2 == null){
                ptr.next = l1;
                break;
            }
            if(l1.val < l2.val){
                ptr.next = l1;
                l1 = l1.next;
            } else {
                ptr.next = l2;
                l2 = l2.next;
            }
            ptr = ptr.next;
        }
        lists[index1] = ans.next;
    }

    /*
    Question :Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
Input:
[
  1-4-5,
  1-3-4,
  2-6
]
Output: 1-1-2-3-4-4-5-6 */  
    public ListNode mergeKList2(ListNode[] lists){
        if(lists.length == 0){
            return null;
        }
        int interval = 1;
        int len = lists.length;
        while(interval < len){
            for(int i=0; i<len - interval; i += (interval*2)){
                merge2(lists, i, i+ interval);
            }
            interval *= 2;
        }
        return lists[0];
    }

    public void merge2(ListNode[] lists, int index1, int index2){
        ListNode ans = new ListNode(-1);
        ListNode ptr = ans;
        ListNode l1 = lists[index1];
        ListNode l2 = lists[index2];

        while(l1 != null || l2 != null){
            if(l1 == null){
                ptr.next = l2;
                break;
            } if(l2 == null){
                ptr.next = l1;
                break;
            } if(l1.val < l2.val){
                ptr.next = l1;
                l1 = l1.next;
            } else {
                ptr.next = l2;
                l2 = l2.next;
            }
            ptr = ptr.next;
        }
        lists[index1] = ans.next;
    }
     
    /*
     * Question :Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.
Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

Input: [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
             Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
     */
    public int maxProfit2(int [] prices){
        if(prices.length == 0)
            return 0;
        int fb = Integer.MIN_VALUE, sb = Integer.MIN_VALUE;
        int fs = 0, ss =0;
        for(int i=0; i<prices.length; i++){
            fb = Math.max(fb, -prices[i]);
            fs = Math.max(fs, fb + prices[i]);
            sb = Math.max(sb, fs - prices[i]);
            ss = Math.max(ss, sb + prices[i]);
        }
        return ss;
    }
    
    /*
     * Question :Say you have an array for which the i-th element is the price of a given stock on day i.
Design an algorithm to find the maximum profit. You may complete at most k transactions.
Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
Input: [2,4,1], k = 2
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
     */
    public int maxProfit(int k, int [] prices){
        int len = prices.length;
        if(len <= 1 || k <= 0){
            return 0;
        }
        int profit = 0;
        if(k >= len/2){
            for(int i=0; i<len-1; i++){
                if(prices[i] < prices[i+1]){
                    profit += (prices[i+1] - prices[i]);
                }              
            }
            return profit;
        }
        int [] buy = new int[k];
        Arrays.fill(buy, Integer.MIN_VALUE);
        int [] sell = new int[k];
        for(int i=0; i<prices.length; i++){
            for(int j=0; j<k; j++){
                buy[j] = Math.max(buy[j], j==0 ? 0 - prices[i] : sell[i-1] - prices[i]);
                sell[j] = Math.max(sell[j], buy[j] + prices[i]);
            }
        }
        return sell[k-1];
    }

    // Easy 

    /*
     LeetCode : Climbing Stairs
 
Question : Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
Find all the elements of [1, n] inclusive that do not appear in this array.

Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

Input:
[4,3,2,7,8,2,3,1]

Output:
[5,6]
     */
    public List<Integer> findDisappearedNumbers(int [] nums){
        for(int i=0; i< nums.length; i++){
            int curr = Math.abs(nums[i]);
            nums[curr-1] = -(Math.abs(nums[curr-1]));
        }
        List<Integer> res = new ArrayList<>();
        for(int i=0; i<nums.length; i++){
            if(nums[i] > 0){
                res.add(i+1);
            }
        }
        return res;
    }
    
    /*
     * LeetCode : Climbing Stairs
 
Question : Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.
You need to find the shortest such subarray and output its length.


Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5

Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
     */
    public int findUnsortedSubArray(int [] nums){
        int min = Integer.MIN_VALUE, max = Integer.MIN_VALUE;
        for(int i=0; i<nums.length-1; i++){
            if(nums[i] > nums[i+1]){
                min = Math.min(min, nums[i+1]);
            }
        }

        for(int i=nums.length-1; i>=0; i--){
            if(nums[i] > nums[i-1]) {
                max = Math.min(max, nums[i-1]);
            }
        }
        
        int l=0, r=0;
        for(int i=0; i<nums.length; i++){
            if(nums[i] > min){
                l = i;
                break;
            }
        }

        for(int i = nums.length -1; i>=0; i--){
            if(nums[i] < max){
                r = i;
                break;
            }
        }
        return r-l >=0 ? r-l+1 : 0;
    }

/*
 * LeetCode : Move Zeroes
 
Question : Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.
 */
    public void moveZeros(int [] nums){
        int i=0, j=0;
        while(j < nums.length){
            while (i < nums.length && nums[i] != 0) {
                i++;
            }
            if(i == nums.length)
                break;
            
                j = i+1;
                while(j<nums.length && nums[j] == 0){
                    j++;
                }
                if(j == nums.length)
                    break;
                
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
        }
    }
    
    /*
     * LeetCode : Invert Binary Tree
 
Question : Invert a binary tree.


Input:

     4
   /   \
  2     7
 / \   / \
1   3 6   9

Output:

     4
   /   \
  7     2
 / \   / \
9   6 3   1
     */
    public Node invert(Node root){
        if(root == null){
            return null;
        }
        Node invertRight = invert(root.right);
        Node invertLeft = invert(root.left);
        root.right = invertLeft;
        root.left = invertRight;
        return root;
    }
    
    /*
     * LeetCode 437. Path Sum III

Question : You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 - 3
2.  5 - 2 - 1
3. -3 - 11

     */
    int count = 0;
    ArrayList<Integer> list = new ArrayList<>();

    public int pathSum(Node root, int sum){
        getSum(root, sum);
        return count;
    }

    public void getSum(Node root, int sum){
        if(root == null){
            return;
        }
        list.add(root.val);
        getSum(root.left, sum);
        getSum(root.right, sum);

        int temp = 0;
        for(int i = list.size()-1; i>=0; i--){
            temp += list.get(i);
            if(temp == sum)
                count++;
        }
        list.remove(list.size()-1);
    }

    /*
     * Question : You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Note: Given n will be a positive integer.
 

Example 1:

Input: 2
Output: 2

Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps

Example 2:

Input: 3
Output: 3

Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
     */

    public int climbStairs(int n){
        int [] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i=2; i<=n ; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    /*
     * LeetCode 543. Diameter of Binary Tree

Question : Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

Given a binary tree
          1
         / \
        2   3
       / \     
      4   5    
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
     */

    int ans = 0;
    public int getDiameter(Node node){
        if(node == null){
            return 0;
        }
        int l = getDiameter(node.left);
        int r = getDiameter(node.right);
        ans = Math.max(ans, (l+r+1));
        return Math.max(l, r)+1;
    }
    
    /*
     * LeetCode : Majority Element 
 
Question - Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:
Input: [3,2,3]
Output: 3

Example 2:
Input: [2,2,1,1,1,2,2]
Output: 2
     */
    public int majorityElement(int [] nums){
        Integer cand = null;
        int count =0;
        for(int num : nums){
            if(count == 0){
                cand = num;
            }
            count += cand == num ? 1 : -1;
        }
        return cand;
    }

    /*
     * LeetCode 101. Symmetric Tree
 
Question : Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
 

But the following [1,2,2,null,3,null,3] is not:

    1
   / \
  2   2
   \   \
   3    3
     */
    public boolean isSymmetric(Node root){
        return isMirror(root, root);
    }

   private boolean isMirror(Node root1, Node root2){
        if(root1== null && root2== null)
            return true;
        if(root1!= null && root2!= null)
            return true;
        if(root1!= null || root2!= null)
            return false;
        
        return root1.val == root2.val &&  isMirror(root1.left, root2.right) &&  isMirror(root1.right, root2.left);
    }
    
    /*
     * LeetCode 234. Palindrome Linked List
 
Question : Given a singly linked list, determine if it is a palindrome.
Input: 1-2
Output: false
Example 2:

Input: 1-2-2-1
Output: true
     */
    public boolean isPalindrome(ListNode head){
        ListNode sp = head, fp = head, mid = null;
        while(fp != null && fp.next != null){
            sp = sp.next;
            fp = fp.next.next;
        }
        if(fp != null){
            mid = sp.next;
        } else {
            mid = sp;
        }
        ListNode prev  = null, next = null;
        while(mid != null){
            next = mid.next;
            mid.next = prev;
            prev = mid;
            mid = next;
        }
        while(prev != null){
            if(prev.val != head.val){
                return false;
            }
            prev = prev.next;
            head = head.next;
        }
        return true;
    }
    
    /*
     * LeetCode : Range Sum Query - Immutable
 
Question : Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -   1
sumRange(2, 5) -   -1
sumRange(0, 5) -   -3
Note:
You may assume that the array does not change.
There are many calls to sumRange function.
     */
    int [] dp2;
    public void numArray(int [] nums){
        int n = nums.length;
        dp2 = new int[n+1];
        dp2[0] = 0;
        for(int i=1; i<=n; i++){
            dp2[i] = dp2[i-1] + nums[i-1];
        }
    }

    public int sumRange(int i, int j){
        return dp2[j+1] - dp2[i];
    }

   
    /*
     * LeetCode : House Robber
 
Question : You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
             Total amount you can rob = 2 + 9 + 1 = 12.
     */
    public int rob(int[] nums){
        int n = nums.length;
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return nums[0];
        }
        int [] dp3 = new int[n];
        dp3[0] = nums[0];
        dp3[1] = Math.max(dp3[0], dp3[1]);
        for(int i=2; i<n; i++){
            dp3[i] = Math.max(dp3[i-1], nums[i]+dp3[i-2]);
        }
        return dp3[n-1];
    }

   /*
    * LeetCode : Two Sum II - Input array is sorted 
 
Question - Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.

Note : Your returned answers (both index1 and index2) are not zero-based.

Example:
Input: numbers = [2,7,11,15], target = 9
Output: [1,2]
Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
    */
    public int [] twoSum(int []  numbers, int target){
        int [] result = new int[2];
        int left = 0, right = numbers.length;

        while(left < right ){
            int l = numbers[left];
            int r = numbers[right];

            if( l + r  == target){
                result[0] = left + 1;
                result[1] = right + 1;
                break;
            } else if(l+r < target){
                left++;
            } else{
                right--;
            }
        }
        return result;
    }
    
    //LeetCode Two sum unsorted array 
    public int [] twoSum2(int []  nums, int target){
        int [] result = new int[2];
        HashMap<Integer, Integer> hm = new HashMap<>();

        for(int i=0; i < nums.length; i++){
            int comp = target - nums[i];
            if(hm.containsKey(comp)){
                result[0] = hm.get(comp);
                result[1] = i;
            } else {
                hm.put(nums[i], i);
            }
        }
        return result;
    }
    
    /*
     * LeetCode : Meeting Rooms

Problem URL - https://leetcode.com/problems/meeting...

Time complexity - O(N * log N) 
Space Complexity - O(1)

where N is length of given input array.

Question :Given an array of meeting time intervals where intervals[i] = [starti, endi], determine if a person could attend all meetings.

Example 1:

Input: intervals = [[0,30],[5,10],[15,20]]
Output: false
Example 2:

Input: intervals = [[7,10],[2,4]]
Output: true
     */
    public boolean canAttenMetting(int [][] intevals){
        if(intevals == null || intevals.length == 0){
            return true;
        }
        Arrays.sort(intevals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int [] b){
                return a[0] - b[0];
            }
        });
        int [] prev = intevals[0];
        for(int i=1; i<intevals.length; i++){
            int [] curr = intevals[i];
            if(prev[i] > curr[0]){
                return false;
            }
            prev = curr;
        }
        return true;
    }
}