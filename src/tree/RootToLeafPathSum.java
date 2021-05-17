package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://www.geeksforgeeks.org/convert-a-given-tree-to-sum-tree/

/*
                  10
               /      \
             -2        6
           /   \      /  \
         8     -4    7    5
  
              20(4-2+12+6)
               /      \
         4(8-4)      12(7+5)
           /   \      /  \
         0      0    0    0
 */

public class RootToLeafPathSum {

    private static class Tree {
        int data;
        Tree left;
        Tree right;

        Tree(int data) {
            this.data = data;
        }
    }

    public static int printRootToLeafpath(Tree node) {
        if (node == null) {
            return 0;
        }
        int pre = node.data;
        int leftdata = printRootToLeafpath(node.left);
        int rightdata = printRootToLeafpath(node.right);
        node.data = leftdata + rightdata;
        return node.data + pre;
    }

    public static void print(Tree tree) {
        if (tree == null) {
            return;
        }
        System.out.print(tree.data + " ");
        print(tree.left);
        print(tree.right);
    }

    public static void main(String[] args) {
        Tree root = new Tree(10);
        root.left = new Tree(-2);
        root.right = new Tree(6);
        root.left.left = new Tree(8);
        root.left.right = new Tree(-4);
        root.right.left = new Tree(7);
        root.right.right = new Tree(5);
        print(root);
        printRootToLeafpath(root);
        System.out.println("");
        print(root);
    }

    //https://leetcode.com/problems/binary-tree-paths/
    public List<String> binaryTreePaths(Tree root) {
        List<String> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        binaryTreePathsRec(root, "", result);
        return result;
    }

    private void binaryTreePathsRec(Tree root, String path, List<String> result) {
        path += "-> " + root.data;
        if (root.left != null && root.right != null) {
            result.add(path);
            return;
        }
        if (root.left != null) {
            binaryTreePathsRec(root.left, path, result);
        }
        if (root.right != null) {
            binaryTreePathsRec(root.right, path, result);
        }
    }

    //https://leetcode.com/problems/range-sum-of-bst/
    /**
     Given the root node of a binary search tree and two integers low and high,
     return the sum of values of all nodes with a value in the inclusive range [low, high].
     */
    public int rangeSumBST(Tree root, int low, int high) {
        int sum = 0;
        if (root == null) {
            return sum;
        }
        Queue<Tree> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Tree node = q.poll();
            if (node.data >= low && node.data <= high) {
                sum += node.data;
            }
            if (node.left != null && node.data > low) {
                q.add(node.left);
            }
            if (node.right != null && node.data < high) {
                q.add(node.right);
            }
        }
        return sum;
    }

    //https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/
    //https://www.geeksforgeeks.org/longest-consecutive-sequence-binary-tree/
    public int longestConsecutiveSumPath(Tree root) {
        int[] max = new int[1];
        longestConsecutiveSumPathRec(root, max, 0, 0);
        return max[0];
    }

    private void longestConsecutiveSumPathRec(Tree root, int[] max, int currMax, int target) {
        if (root == null) {
            return;
        }
        if (root.data == target) {
            currMax++;
        } else {
            currMax = 1;
        }
        max[0] = Math.max(max[0], currMax);
        longestConsecutiveSumPathRec(root.left, max, currMax, root.data + 1);
        longestConsecutiveSumPathRec(root.right, max, currMax, root.data + 1);
    }
}
