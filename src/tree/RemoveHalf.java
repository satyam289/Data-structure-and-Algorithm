package tree;

//https://www.interviewbit.com/problems/remove-half-nodes/
class RemoveHalf {

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

    // Bottom Up
    public TreeNode removeHalf(TreeNode A) {
        if (A == null) {
            return null;
        }
        A.left = removeHalf(A.left);
        A.right = removeHalf(A.right);

        if ((A.left != null && A.right != null) || (A.left == null && A.right == null)) {
            return A;
        }
        if (A.left == null) {
            return A.right;
        }
        return A.left;
    }

    // Top Down
    public TreeNode removeHalf2(TreeNode A) {
        if (A.left == null && A.right == null)
            return A;

        if (A.left == null)
            return removeHalf2(A.right);
        else if (A.right == null)
            return removeHalf2(A.left);

        A.left = removeHalf2(A.left);
        A.right = removeHalf2(A.right);
        return A;
    }

    // https://www.interviewbit.com/old/problems/merge-two-binary-tree/
    public TreeNode mergeTwo(TreeNode A, TreeNode B) {
        if (A == null && B == null) {
            return null;
        }
        if (A == null) {
            return B;
        }
        if (B == null) {
            return A;
        }
        A.val += B.val;
        A.left = mergeTwo(A.left, B.left);
        A.right = mergeTwo(A.right, B.right);
        return A;
    }
}
