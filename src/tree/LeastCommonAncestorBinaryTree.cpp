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

    int checkAndFindlca(TreeNode *root, int val1, int val2)
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

private:

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
};

/**
 * Java Solution for one pass
 public class Solution {
    
    //Time Complexity : 0(n)
    Node findLCA(Node node, int n1, int n2)
    {
        if (node == null)
            return null;

        if (node.data == n1 || node.data == n2)
            return node;
 
        Node left_lca = findLCA(node.left, n1, n2);
        Node right_lca = findLCA(node.right, n1, n2);
 
        if (left_lca!=null && right_lca!=null)
            return node;

        return (left_lca != null) ? left_lca : right_lca;
    }


    //Time Complexity  : 0(n)  Visited Twice 

    private List<Integer> path1 = new ArrayList<>();
    private List<Integer> path2 = new ArrayList<>();

    private int findLCA(Node root, int n1, int n2) {

        if (!findPath(root, n1, path1) || !findPath(root, n2, path2)) {
            return -1;
        }
        int i;
        for (i = 0; i < path1.size() && i < path2.size(); i++) {
            if (!path1.get(i).equals(path2.get(i)))
                break;
        }
        return path1.get(i-1);
    }

    private boolean findPath(Node root, int n, List<Integer> path)
    {
        if (root == null) {
            return false;
        }
        path.add(root.data);
        if (root.data == n) {
            return true;
        }
 
        if (root.left != null && findPath(root.left, n, path)) {
            return true;
        }
 
        if (root.right != null && findPath(root.right, n, path)) {
            return true;
        }
        path.remove(path.size()-1); //not present in subtree, hence remove
        return false;
    }
}
*/