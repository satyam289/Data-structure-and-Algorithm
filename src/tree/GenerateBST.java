package tree;

import java.util.ArrayList;

//https://www.interviewbit.com/problems/unique-binary-search-trees/
// https://www.geeksforgeeks.org/construct-all-possible-bsts-for-keys-1-to-n/
public class GenerateBST {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class Solution {
        public ArrayList<TreeNode> generateTrees(int a) {
            return generateTrees(1, a);
        }

        public ArrayList<TreeNode> generateTrees(int start, int end) {
            ArrayList<TreeNode> list = new ArrayList<>();
            if (start > end) {
                list.add(null);
                return list;
            }
            for (int i = start; i <= end; i++) {

                ArrayList<TreeNode> leftsubTree = generateTrees(start, i - 1);
                ArrayList<TreeNode> rightsubTree = generateTrees(i + 1, end);
                for (int l = 0; l < leftsubTree.size(); l++) {
                    for (int r = 0; r < rightsubTree.size(); r++) {
                        TreeNode root = new TreeNode(i);
                        root.left = leftsubTree.get(l);
                        root.right = rightsubTree.get(r);
                        list.add(root);
                    }
                }
            }
            return list;
        }
    }

    // https://www.interviewbit.com/problems/unique-binary-search-trees-ii/
    // https://www.geeksforgeeks.org/program-nth-catalan-number/
    public int numTrees1(int num) {
        if (num <= 1) {
            return 1;
        }
        int res = 0;
        for (int i = 0; i < num; i++) {
            res += numTrees1(i) * numTrees1(num - i - 1);
        }
        return res;
    }

    // catalane DP Apporach
    public int numTrees2(int num) {
        int[] dp = new int[num + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= num; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[num];
    }
}
