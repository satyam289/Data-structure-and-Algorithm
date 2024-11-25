package leetcode.hard;

import java.util.*;

public class KClosestBinarySearch {

    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
    }

    public List<Integer> closestKValue(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<>();
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        inorder(root, target, false, s1); // predecessors
        inorder(root, target, true, s2); // successors

        while (k-- > 0) {
            if (s1.empty()) {
                res.add(s2.pop());
            } else if (s2.isEmpty()) {
                res.add(s1.pop());
            } else if (Math.abs(s1.peek() - target) < Math.abs(s2.peek() - target)) {
                res.add(s1.pop());
            } else {
                res.add(s2.pop());
            }
        }
        return res;
    }

    private void inorder(TreeNode root, double target, boolean reverse, Stack<Integer> stack) {
        if (root == null) {
            return;
        }
        inorder(reverse ? root.right : root.left, target, reverse, stack);
        if ((reverse && root.val <= target) || (!reverse && root.val > target)) {
            return;
        }
        stack.push(root.val);
        inorder(reverse ? root.left : root.right, target, reverse, stack);
    }
}
