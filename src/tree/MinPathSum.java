package tree;

//https://www.interviewbit.com/problems/max-sum-path-in-binary-tree/
public class MinPathSum {
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

    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode A) {
        maxPathSumRec(A);
        return max;
    }

    private int maxPathSumRec(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftSum = maxPathSumRec(root.left);
        int rightSum = maxPathSumRec(root.right);

        int pathSum = root.val + Math.max(rightSum, leftSum);
        max = Math.max(max, Math.max(pathSum, Math.max(root.val + leftSum + rightSum, root.val)));
        return pathSum;
    }
}
