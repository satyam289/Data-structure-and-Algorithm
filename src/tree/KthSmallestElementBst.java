package tree;

//https://www.interviewbit.com/problems/kth-smallest-element-in-tree/
//https://leetcode.com/problems/kth-smallest-element-in-a-bst/
public class KthSmallestElementBst {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    public int kthSmallest(TreeNode root, int k) {
        int[] result = new int[2];
        inorder(root, result, k);
        return result[1];
    }

    private void inorder(TreeNode root, int[] result, int k) {
        if (root == null) {
            return;
        }
        inorder(root.left, result, k);
        result[0]++;
        if (result[0] == k) {
            result[1] = root.val;
            return;
        }
        inorder(root.right, result, k);
    }
}
