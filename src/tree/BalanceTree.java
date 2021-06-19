package tree;

//https://www.interviewbit.com/problems/balanced-binary-tree/
public class BalanceTree {

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

    // Time Complexity : 0(n2)
    public int isBalanced(TreeNode A) {
        return isBalancedRec(A) ? 1 : 0;
    }

    private boolean isBalancedRec(TreeNode A) {
        if (A == null) {
            return true;
        }
        int leftH = getHeight(A.left);
        int rightH = getHeight(A.right);
        return (Math.abs(leftH - rightH) <= 1) && isBalancedRec(A.left) && isBalancedRec(A.right);
    }

    private int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    class Height {
        int h;
    }

    // Time Complexity : 0(n)
    public int isBalanced2(TreeNode A) {
        return isBalancedRec(A, new Height()) ? 1 : 0;
    }

    private boolean isBalancedRec(TreeNode A, Height height) {
        if (A == null) {
            return true;
        }
        Height leftH = new Height();
        Height rightH = new Height();
        boolean isLeft = isBalancedRec(A.left, leftH);
        boolean isRight = isBalancedRec(A.right, rightH);

        height.h = Math.max(leftH.h, rightH.h) + 1;

        if (Math.abs(leftH.h - rightH.h) > 1) {
            return false;
        } else {
            return isLeft & isRight;
        }
    }
}
