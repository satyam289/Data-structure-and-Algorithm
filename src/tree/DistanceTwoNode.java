package tree;

//https://www.geeksforgeeks.org/find-distance-between-two-nodes-of-a-binary-tree/
public class DistanceTwoNode {
    
    class Node {
        int data;
        Node left;
        Node right;
        Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    public static int findDistance(Node root, int a, int b) {
        Node lca = LCA(root, a, b);
        int a_distance = findLevel(lca, a, 0);
        int b_distance = findLevel(lca, b, 0);
        return a_distance + b_distance;
    }

    private static int findLevel(Node root, int targetNodeVal, int level) {
        if (root == null) {
            return -1;
        }
        if (root.data == targetNodeVal) {
            return level;
        }
        int left = findLevel(root.left, targetNodeVal, level + 1);
        if (left == -1) {
            return findLevel(root.right, targetNodeVal, level + 1);
        }
        return left;
    }

    private static Node LCA(Node root, int a, int b) {

        if (root == null) {
            return null;
        }
        if (root.data == a || root.data == b) {
            return root;
        }
        Node left = LCA(root.left, a, b);
        Node right = LCA(root.right, a, b);
        if (left != null && right != null) {
            return root;
        }
        if (left != null) {
            return left;
        } else {
            return right;
        }
    }
}
