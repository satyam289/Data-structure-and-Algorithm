package tree;

import java.util.ArrayList;

// https://www.geeksforgeeks.org/diameter-of-a-binary-tree/
public class Diameter {

    /*
     * The diameter of a tree (or width) is the number of nodes on
     * the longest path between two end nodes
     */
    
    private static class Node {
        int data;
        Node left, right;
        public Node(int data) {
            this.data = data;
            left = right = null;
        }
    }
    
    // Time Complexity: 0(n2)
    public int diameterRec(Node root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        int leftDiameter = diameterRec(root.left);
        int rightDiameter = diameterRec(root.right);

        return Math.max(Math.max(leftDiameter, rightDiameter), 1 + leftHeight + rightHeight);
    }

    private int getHeight(Node root) {
        if (root == null) {
            return 0;
        }
        return (1 + Math.max(getHeight(root.left), getHeight(root.right)));
    }
   
    int max = Integer.MIN_VALUE;

    // Time Complexity: 0(n)
    public int diameter2(Node root) {
        calculateHeightAndDiameter(root);
        return max;
    }

    private int calculateHeightAndDiameter(Node root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = calculateHeightAndDiameter(root.left);
        int rightHeight = calculateHeightAndDiameter(root.right);
        max = Math.max(max, leftHeight + rightHeight + 1);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public static void main(String [] argrs) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        Diameter obj = new Diameter();
        System.out.println("Diameter is " + obj.diameterRec(root));
        System.out.println("Optimal Diameter is " + obj.diameter2(root));
    }
   
// https://www.interviewbit.com/problems/largest-distance-between-nodes-of-a-tree/
    /* Check above mutiple solution using dfs/bfs 
    input = [-1, 0, 0, 0, 3]
    output : 3

    node 0 is the root and the whole tree looks like this: 
          0
       /  |  \
      1   2   3
               \
                4
 One of the longest path is 1 -> 0 -> 3 -> 4 and its length is 3, thus the answer is 3.
    */
    int ans = 0;

    public int solve(ArrayList<Integer> A) {
        if (A.size() == 1) {
            return 0;
        }
        int startpt = 0;
        ArrayList<ArrayList<Integer>> levelwise = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < A.size(); i++) {
            levelwise.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < A.size(); i++) {
            if (A.get(i) == -1) {
                startpt = i;
            } else {
                levelwise.get(A.get(i)).add(i);
            }
        }
        dfs(levelwise, startpt); // or backward iterate from n-1 to 0 using for loop
        return ans;
    }

    public int dfs(ArrayList<ArrayList<Integer>> levelwise, int startpt) {
        int firstMax = 0;
        int secondMax = 0;
        for (int i = 0; i < levelwise.get(startpt).size(); i++) {
            int nextNode = levelwise.get(startpt).get(i);

            int val = dfs(levelwise, nextNode); // recursion

            if (val >= firstMax) {
                secondMax = firstMax;
                firstMax = val;
            } else if (val >= secondMax) {
                secondMax = val;
            }
        }
        ans = Math.max(ans, firstMax + secondMax);
        return firstMax + 1;
    }

    // Time Complexity: 0(n)
    public int solveBest(ArrayList<Integer> A) {
        int[] depthCount = new int[A.size()];
        int ans = 0;
        for (int i = A.size() - 1; i > 0; i--) {

            int parentdepthCount = depthCount[A.get(i)];
            ans = Math.max(ans, parentdepthCount + depthCount[i] + 1); //  @ here depthCount[i] mean current depth count
            depthCount[A.get(i)] = Math.max(depthCount[i] + 1, parentdepthCount); // updating parent depth count
        }
        return ans;
    }
}
