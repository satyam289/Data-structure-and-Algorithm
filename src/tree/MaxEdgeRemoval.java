package tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//https://www.geeksforgeeks.org/maximum-edge-removal-tree-make-even-forest/
//https://www.interviewbit.com/problems/maximum-edge-removal/
public class MaxEdgeRemoval {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    int removeCount = 0;

    /*
     * Given an undirected tree which has even number of vertices, we need to remove
     * the maximum number of edges from this tree such that each connected component
     * of the resultant forest has an even number of vertices
     */
    public int solve(int A, ArrayList<ArrayList<Integer>> B) {
        ArrayList<ArrayList<Integer>> edge = new ArrayList<>();
        boolean[] visited = new boolean[A + 1];
        for (int i = 0; i < A + 1; i++) {
            edge.add(new ArrayList<>());
            visited[i] = false;
        }
        for (ArrayList<Integer> b : B) {
            edge.get(b.get(0)).add(b.get(1));
            edge.get(b.get(1)).add(b.get(0));
        }
        solveRec(1, edge, visited);
        return removeCount;
    }

    private int solveRec(int intialVexIdx, ArrayList<ArrayList<Integer>> edge, boolean[] visited) {
        int numberOddCount = 0;
        visited[intialVexIdx] = true;
        for (int i = 0; i < edge.get(intialVexIdx).size(); i++) {
            int finalVexIdx = edge.get(intialVexIdx).get(i);
            if (!visited[finalVexIdx]) {
                int count = solveRec(finalVexIdx, edge, visited);
                if (count % 2 == 0) {
                    removeCount++;
                } else {
                    numberOddCount += count;
                }
            }
        }
        return numberOddCount + 1;
    }

    // https://leetcode.com/problems/delete-nodes-and-return-forest/
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> result = new ArrayList<>();
        Set<Integer> toDeleteSet = new HashSet<>();
        for (int i : to_delete) {
            toDeleteSet.add(i);
        }
        delNodesRec(root, toDeleteSet, result);
        if (!toDeleteSet.contains(root.val)) {
            result.add(root);
        }
        return result;
    }

    private TreeNode delNodesRec(TreeNode root, Set<Integer> toDeleteSet, List<TreeNode> result) {
        if (root == null) {
            return null;
        }
        root.left = delNodesRec(root.left, toDeleteSet, result);
        root.right = delNodesRec(root.right, toDeleteSet, result);
        if (toDeleteSet.contains(root.val)) {
            if (root.left != null) {
                result.add(root.left);
            }
            if (root.right != null) {
                result.add(root.right);
            }
            return null;
        }
        return root;
    }
}
