package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//https://www.geeksforgeeks.org/print-cousins-of-a-given-node-in-binary-tree/
//https://www.geeksforgeeks.org/check-two-nodes-cousins-binary-tree/
public class PrintCousinTree {

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

    // Optimised
    public ArrayList<Integer> solve(TreeNode A, int B) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(A);
        int size = 0;
        boolean isFound = false;
        while (!q.isEmpty() && !isFound) {
            size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if ((node.left != null && node.left.val == B) || (node.right != null && node.right.val == B)) {
                    isFound = true;
                    continue;
                }
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
        }
        if (isFound) {
            while (!q.isEmpty()) {
                TreeNode sameLvl = q.poll();
                result.add(sameLvl.val);
            }
        }
        return result;
    }

    // Brute Force
    public void printCousin(TreeNode root, TreeNode node) {
        int level = getLevel(root, node, 1);
        printLevel(root, node, level);
    }

    private void printLevel(TreeNode root, TreeNode node, int level) {
        if (root == null || level < 2) // Cant possible to have cousin for root node and level below
            return;

        if (level == 2) { // check and stop 2 level above from found node
            if (root.left == node || root.right == node) { // ignore sibling
                return;
            }
            if (root.left != null) {
                System.out.println(root.left.val);
            }
            if (root.right != null) {
                System.out.println(root.right.val);
            }
        } else {
            printLevel(root.left, node, level - 1);
            printLevel(root.right, node, level - 1);
        }
    }

    private int getLevel(TreeNode root, TreeNode node, int level) {
        if (root == null)
            return 0;
        if (root == node)
            return level;

        int leftLevel = getLevel(root.left, node, level + 1);
        if (leftLevel != 0) {
            return leftLevel;
        }
        return getLevel(root.right, node, level + 1);
    }
}
