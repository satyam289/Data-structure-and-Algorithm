package tree;

import java.util.LinkedList;
import java.util.Queue;

//https://www.geeksforgeeks.org/connect-nodes-at-same-level-with-o1-extra-space/

/**
 * Output Tree
       A--->NULL
      / \
     B-->C-->NULL
    / \   \
   D-->E-->F-->NULL
*/

public class ConnectLevelwiseNode {

    private static class Node {
        int data;
        Node left;
        Node right;
        Node nextRight;

       public Node(int data) {
            this.data = data;
            left = null;
            right = null;
            nextRight = null;
        }
    }
   
    //In place
    public static void connectSameLevel(Node root) {

        if (root == null)
            return;

        Node node = root;

        while (node != null) {

            Node SameLevelNode = node;

            while (SameLevelNode != null) {

                Node leftNode = SameLevelNode.left;
                Node rightNode = SameLevelNode.right;

                if (leftNode != null) {
                    if (rightNode != null) {
                        leftNode.nextRight = rightNode;
                    } else {
                        leftNode.nextRight = getRightNeighbour(SameLevelNode);
                    }
                }

                if (rightNode != null) {
                    rightNode.nextRight = getRightNeighbour(SameLevelNode);
                }
                SameLevelNode = SameLevelNode.nextRight;
            }

            if (node.left != null)
                node = node.left;
            else if (node.right != null)
                node = node.right;
            else
                node = getRightNeighbour(node);
        }
    }

    public static Node getRightNeighbour(Node node) {
        Node rightChild = node.nextRight;
        while (rightChild != null) {
            if (rightChild.left != null) {
                return rightChild.left;
            }
            if (rightChild.right != null) {
                return rightChild.right;
            }
            rightChild = rightChild.nextRight;
        }
        return rightChild;
    }

    public static void main(String args[]) {
        /*
         * Constructed binary tree is 10 
         *                            / \ 
         *                           8   2 
         *                          /     \ 
         *                         3       90
         */

        System.out.println("Following are populated nextRight pointers in " + " the tree(-1 is printed if there is no nextRight)"); 
        Node tree = new Node(10);
        tree.left = new Node(8);
        tree.right = new Node(2);
        tree.left.left = new Node(3);
        tree.right.right = new Node(90);

        // Populates nextRight pointer in all nodes
        connectSameLevel(tree);
           
        // Let us check the values of nextRight pointers 
        int a = tree.nextRight != null ?  tree.nextRight.data : -1; 
        int b = tree.left.nextRight != null ?  tree.left.nextRight.data : -1; 
        int c = tree.right.nextRight != null ? tree.right.nextRight.data : -1; 
        int d = tree.left.left.nextRight != null ? tree.left.left.nextRight.data : -1; 
        int e = tree.right.right.nextRight != null ? tree.right.right.nextRight.data : -1; 

        // Now lets print the values 
        
        System.out.println("nextRight of " + tree.data + " is " + a); 
        System.out.println("nextRight of " + tree.left.data  + " is " + b); 
        System.out.println("nextRight of " + tree.right.data + " is " + c); 
        System.out.println("nextRight of " + tree.left.left.data + " is " + d); 
        System.out.println("nextRight of " + tree.right.right.data + " is " + e); 
    }
    
    void connect(Node root) {
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);

        Node pre = null;

        while (q.size() > 1) {
            Node node = q.poll();
            if (pre != null) {
                pre.nextRight = node;
            }
            if (node == null) {
                q.add(null);
                pre = null;
            } else {
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
            pre = node;
        }
    }

    // This approach works only for Complete Binary Trees.
    void connectRecursively(Node node) {
        if (node == null)
            return;

        if (node.left != null) { // assume Complete tree (right child is not null)
            node.left.nextRight = node.right;
        }

        if (node.right != null) {
            node.right.nextRight = (node.nextRight != null) ? node.nextRight.left : null;
        }
        connectRecursively(node.left);
        connectRecursively(node.right);
    }

}