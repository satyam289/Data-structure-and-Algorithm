#include <iostream>

using namespace std;

struct TreeNode
{
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

class BSTIterator
{
private:
    stack<TreeNode *> st;
    TreeNode *curr;

public:
    BSTIterator(TreeNode *root)
    {
        curr = root;
    }

    /**
     * @return the next smallest number
     */
    int next()
    {
        while (curr)
        {
            st.push(curr);
            curr = curr->left;
        }
        curr = st.top();
        st.pop();
        int ret = curr->val;
        curr = curr->right;
        return ret;
    }

    /**
     * @return whether we have a next smallest number
     */
    bool hasNext()
    {
        return curr || !st.empty();
    }
};

class BSTIterator2
{
private:
    vector<TreeNode *> inorder;

    vector<TreeNode *> inorderTraversal(TreeNode *root)
    {
        if (root == nullptr)
            return {};

        vector<TreeNode *> leftTraversal = inorderTraversal(root->left);
        vector<TreeNode *> rightTraversal = inorderTraversal(root->right);

        leftTraversal.push_back(root);
        leftTraversal.insert(leftTraversal.end(), rightTraversal.begin(), rightTraversal.end());
        return leftTraversal;
    }

public:
    BSTIterator2(TreeNode *root)
    {
        inorder = this->inorderTraversal(root);
        reverse(inorder.begin(), inorder.end());
    }

    int next()
    {
        TreeNode *ret = inorder.back();
        inorder.pop_back();
        return ret->val;
    }

    bool hasNext()
    {
        return !inorder.empty();
    }
};