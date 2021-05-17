package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://leetcode.com/problems/binary-tree-right-side-view/
public class LeftRightView {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    public List<Integer> rightSideView1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        rightSideViewRec(root, 1, result);
        return result;
    }

    private void rightSideViewRec(TreeNode root, int level, List<Integer> result) {
        if (root == null)
            return;

        if (level > max_level) {
            result.add(root.val);
            max_level = level;
        }
        rightSideViewRec(root.right, level + 1, result);
        rightSideViewRec(root.left, level + 1, result);
    }

    public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {

            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (i == size - 1) {
                    result.add(node.val);
                }
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
        }
        return result;
    }

    // https://www.geeksforgeeks.org/print-left-view-binary-tree/

    static int max_level = 0;

    public List<Integer> leftSideView1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        leftSideViewRec(root, 1, result);
        return result;
    }

    private void leftSideViewRec(TreeNode root, int level, List<Integer> result) {
        if (root == null)
            return;

        if (level > max_level) {
            result.add(root.val);
            max_level = level;
        }
        leftSideViewRec(root.left, level + 1, result);
        leftSideViewRec(root.right, level + 1, result);
    }

    public static void leftSideView2(TreeNode root) {
        if (root == null)
            return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == 0) {
                    System.out.print(node.val + " ");
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
    }
}
