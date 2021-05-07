package tree;

//Find node with next greater value Node of the input node
// https://www.geeksforgeeks.org/inorder-successor-in-binary-search-tree
public class InorderSuccessor {

    /* Naive Apporach : Time Complexity : 0(N)
       Store the tree in sorted order using inorder traversal, return next element by finding the node in sorted list using binary search. 
    
      Better Apporach: 0(H), where h is height of Tree

      Case 1 : If right child is not null, leftmost of right child is successor
      Case 2 : Move up until we find node which is left of its parent
    
      In order to Move up, we need upward pointer in Node. (can be done, if we have parent pointer)
      Using same apporach, we start from root, Travel down the tree, if a node’s data is greater than root’s data then go right side, otherwise, go to left side.
    */
    
    private static class Node {
        int data;
        Node left;
        Node right;
    };

    public static Node nextGreaterValue(Node root, Node node) {
        Node curr = root;
        //Case 1
        if (curr.right != null) {

            curr = curr.right;
            while (curr.left != null) {
                curr = curr.left;
            }
            return curr;
        }
        //Case 2
        Node successor = null;

        while (curr != null) {
            if (curr.data > node.data) {
                successor = curr;
                curr = curr.left;
            } else if (curr.data < node.data) {
                curr = curr.right;
            } else {
                break;
            }
        }
        return successor;
    }
}
