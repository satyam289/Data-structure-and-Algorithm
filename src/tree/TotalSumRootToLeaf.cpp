#include <iostream>
#include <vector>

using namespace std;

class Solution
{
    struct TreeNode
    {
        int val;
        TreeNode *left;
        TreeNode *right;
        TreeNode(int x) : val(x), left(NULL), right(NULL) {}
    };

public:

//https://www.interviewbit.com/problems/sum-root-to-leaf-numbers/

/*
    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Return the sum = (12 + 13) % 1003 = 25 % 1003 = 25
 */
    int sumNumbers(TreeNode *root, int currSum)
    {
        if (!root)
        {
            return 0;
        }
        currSum = (currSum * 10 + root->val) % 1003;
        if (!root->left && !root->right) // consider only node has no children
        {
            return currSum;
        }
        return (sumNumbers(root->left, currSum) % 1003) + (sumNumbers(root->right, currSum) % 1003);
    }

    int sumNumbers(TreeNode *root)
    {
        return sumNumbers(root, 0);
    }

// https://www.interviewbit.com/problems/root-to-leaf-paths-with-sum/
/*
        5
       / \
      4   8
     /   / \
    11  13  4
   /  \    / \
  7    2  5   1
 Given sum = 22,       
[
   [5,4,11,2],
   [5,8,4,5]
]
*/
void pathSumHelper(TreeNode *root, int remainingSum, vector<int> current, vector<vector<int>> ret){
    if(root == NULL)
      return;
    remainingSum  -= root->val;
    current.push_back(root->val);

    if(root->left == NULL && root->right == NULL){
        if(remainingSum == 0){
            ret.push_back(current);
        }
        current.pop_back();
        return;
    }
    pathSumHelper(root->left, remainingSum, current, ret);
    pathSumHelper(root->right, remainingSum, current, ret);
    current.pop_back();
}

vector<vector<int>> pathSum(TreeNode * root, int sum){
    vector<vector<int> > ans;
    vector<int> current;
    pathSumHelper(root, sum, current, ans);
    return ans;
}

/*** java Impl
 
  ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
    
    public ArrayList<ArrayList<Integer>> pathSum(TreeNode A, int B) {
        pathSumRec(A, B, new ArrayList<Integer>());
        return result;
    }
    
    public void pathSumRec(TreeNode A, int sum, ArrayList<Integer> path){
        if(A == null) {
            return;
        }
        path.add(A.val); 
        sum = sum - A.val;
        
        if(A.left == null && A.right == null && (sum == 0)){
            result.add(new ArrayList<Integer>(path));
            //path.remove(path.size()-1);   // uncomment this
            //return;                       // uncomment this
        }
        pathSumRec(A.left, sum, path);
        pathSumRec(A.right, sum, path);
        
        path.remove(path.size()-1);
    }
****/
};