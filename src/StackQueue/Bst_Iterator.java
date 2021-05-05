package StackQueue;

import java.util.Stack;

// https://www.interviewbit.com/problems/bst-iterator/
// next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
public class Bst_Iterator {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    Stack<TreeNode> st = null;

    Bst_Iterator(TreeNode root) {
        st = new Stack<>();
        TreeNode curr = root;
        while (curr != null) {
            st.push(curr);
            curr = curr.left; // pushing only left node
        }
    }

    /** @return check next smallest number */
    public boolean hasNext() {
        return !st.empty();
    }

    /** @return the next smallest number */
    public int next() {
        if (st.empty()) {
            return -1;
        }
        TreeNode curr = st.pop();
        int val = curr.val;

        curr = curr.right; // cheking right node
        while (curr != null) {
            st.push(curr);
            curr = curr.left; // pusing all left node of right node
        }
        return val;
    }
}