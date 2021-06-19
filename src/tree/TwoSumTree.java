package tree;

import java.util.HashSet;
import java.util.Stack;

//https://www.interviewbit.com/old/problems/2sum-binary-tree/
public class TwoSumTree {

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

    int a = 0;

    // Brute Force
    public int t2Sum(TreeNode A, int B) {
        HashSet<Integer> set = new HashSet<>();
        t2SumRec(A, B, set);
        return a;
    }

    private void t2SumRec(TreeNode A, int B, HashSet<Integer> set) {
        if (A == null) {
            return;
        }
        if (set.contains(B - A.val)) {
            a = 1;
            return;
        } else {
            set.add(A.val);
        }
        t2SumRec(A.left, B, set);
        t2SumRec(A.right, B, set);
    }

    // optimised
    public int t2Sum2(TreeNode A, int B) {

        if (A == null || (A.left == null && A.right == null)) {
            return 0;
        }
        Stack<TreeNode> minStack = new Stack<>();
        Stack<TreeNode> maxStack = new Stack<>();
        TreeNode temp1 = A;
        while (temp1 != null) {
            minStack.push(temp1);
            temp1 = temp1.left;
        }
        TreeNode temp2 = A;
        while (temp2 != null) {
            maxStack.push(temp2);
            temp2 = temp2.right;
        }
        int minNext = 0, maxNext = 0;
        if (!minStack.isEmpty()) {
            minNext = getMinNext(minStack);
        }
        if (!maxStack.isEmpty()) {
            maxNext = getMaxNext(maxStack);
        }
        while (!minStack.isEmpty() && !maxStack.isEmpty() && minNext < maxNext) {
            if (minNext != maxNext && minNext + maxNext == B) {
                return 1;
            }
            if (minNext + maxNext < B && minStack.size() > 0) {
                minNext = getMinNext(minStack);
            } else if (minNext + maxNext > B && maxStack.size() > 0) {
                maxNext = getMaxNext(maxStack);
            }
        }
        return 0;
    }

    private int getMaxNext(Stack<TreeNode> maxStack) {
        TreeNode currNode = maxStack.pop();
        int value = currNode.val;
        currNode = currNode.left;
        while (currNode != null) {
            maxStack.push(currNode);
            currNode = currNode.right;
        }
        return value;
    }

    private int getMinNext(Stack<TreeNode> minStack) {
        TreeNode currNode = minStack.pop();
        int value = currNode.val;
        currNode = currNode.right;
        while (currNode != null) {
            minStack.push(currNode);
            currNode = currNode.left;
        }
        return value;
    }
}
