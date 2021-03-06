#include <iostream>

using namespace std;

//https://www.interviewbit.com/problems/least-common-ancestor/
class LeastCommonAncestorBinaryTree
{
    struct TreeNode
    {
        int val;
        TreeNode *left;
        TreeNode *right;
        TreeNode(int x) : val(x), left(NULL), right(NULL) {}
    };

public:
    TreeNode *LCA(TreeNode *root, int val1, int val2)
    {
        if (!root)
            return NULL;
        if (root->val == val1 || root->val == val2)
            return root;
        TreeNode *left = LCA(root->left, val1, val2);
        TreeNode *right = LCA(root->right, val1, val2);
        if (left && right)
        {
            return root;
        }
        return left ? left : right;
    }

    bool find(TreeNode *root, int val1)
    {
        if (!root)
        {
            return false;
        }
        if (root->val == val1)
            return true;
        return find(root->left, val1) || find(root->right, val1);
    }

    int lca(TreeNode *root, int val1, int val2)
    {
        if (!find(root, val1) || !find(root, val2))
        {
            return -1;
        }
        TreeNode *ans = LCA(root, val1, val2);
        if (!ans)
        {
            return -1;
        }
        return ans->val;
    }
};

/**
 * Java Solution for one pass
 public class Solution {
    
    static int n11,n22;
	
    static TreeNode fun(TreeNode root,int n1,int n2){ //bottom up
	    if(root==null)
	        return null;
	    TreeNode left=fun(root.left,n1,n2);
	    TreeNode right=fun(root.right,n1,n2);
	    if(n1==root.val)
	        n11=1;
	    if(n2==root.val)
	       n22=1;
	    if(n1==root.val || n2==root.val)  // always in recursrion stack, for case both nodes lies in same branch
	        return root;
	    if(left!=null && right!=null)
	        return root;
	    if(left==null)
	        return right;
	    else 
	        return left;
}
	public int lca(TreeNode root, int n1, int n2) {
	        n11=n22=0;
	      TreeNode re=fun(root,n1,n2);
	      int a;   
	      if(n11==0||n22==0)  // flag of both node, if only one is present,  it should return -1
	        return -1;
	      if(re==null)  //
	         a=-1;
	      else
	         a=re.val;
	      return a;      
	}
}
*/