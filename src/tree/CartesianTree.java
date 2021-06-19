package tree;

import java.util.ArrayList;

//https://www.geeksforgeeks.org/cartesian-tree-from-inorder-traversal-segment-tree/
//https://www.interviewbit.com/problems/inorder-traversal-of-cartesian-tree/
//https://www.geeksforgeeks.org/cartesian-tree/
public class CartesianTree {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
            left = null;
            right = null;
        }
    }

    public TreeNode buildTree(ArrayList<Integer> A) {
        return buildTreeRec(A, 0, A.size() - 1);
    }

    public TreeNode buildTreeRec(ArrayList<Integer> A, int low, int high) {
        if (low > high) {
            return null;
        }
        int max = Integer.MIN_VALUE;
        int index = -1;
        for (int i = low; i <= high; i++) {
            if (A.get(i) > max) {
                max = A.get(i);
                index = i;
            }
        }
        TreeNode root = new TreeNode(max);
        root.left = buildTreeRec(A, low, index - 1);
        root.right = buildTreeRec(A, index + 1, high);
        return root;
    }
}
