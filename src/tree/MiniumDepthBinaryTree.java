package tree;

public class MiniumDepthBinaryTree {
    static class Node {
        int data;
        Node left, right;

        public Node(int item) {
            data = item;
            left = right = null;
        }
    }

    public static void main(String[] args) {
        Node tree = new Node(1);
        tree.left = new Node(2);
        tree.right = new Node(3);
        tree.left.left = new Node(4);
        tree.left.right = new Node(5);
        System.out.println(miniumdepth(tree));
        System.out.println(minimumdepthOptimized(tree, 0));
    }

    public static int miniumdepth(Node root) {

        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 1;
        if (root.left == null)
            return miniumdepth(root.right) + 1;
        if (root.right == null)
            return miniumdepth(root.left) + 1;
        return Math.min(miniumdepth(root.left), miniumdepth(root.right)) + 1;

    }

    public static int minimumdepthOptimized(Node root, int level) {
        if (root == null)
            return level;
        return Math.min(minimumdepthOptimized(root.left, level + 1), minimumdepthOptimized(root.right, level + 1));
    }

}
