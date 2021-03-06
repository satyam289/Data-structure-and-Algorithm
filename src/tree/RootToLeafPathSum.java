package tree;

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
}
