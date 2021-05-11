package tree;

import java.util.Deque;
import java.util.LinkedList;

//https://www.geeksforgeeks.org/level-order-traversal-in-spiral-form/
public class SpiralTree {

    private static class Node {
        int val;
        Node left;
        Node right;
        Node(int val) {
            this.val = val;
            left = right = null;
        }
    }
    
    public static void spiralWithOneDeque(Node node) {
        if (node == null) {
            return;
        }
        Deque<Node> q = new LinkedList<>();
        q.add(node);
        int count = 1;
        boolean flip = true;

        while (!q.isEmpty()) {
            count = q.size();
            while (count-- > 0) {
                if (flip) {
                    node = q.pollFirst();
                    System.out.print(node.val + "->");
                    if (node.left != null) {
                        q.addLast(node.left);
                    }
                    if (node.right != null) {
                        q.addLast(node.right);
                    }
                } else {
                    node = q.pollLast();
                    System.out.print(node.val +"->");
                    if (node.right != null) {
                        q.addFirst(node.right);
                    }
                    if (node.left != null) {
                        q.addFirst(node.left);
                    }
                }
            }
            flip = !flip;
            System.out.println("");
        }
    }

    public static void spiralWithDequeAndDelimiter(Node node) {
        if (node == null)
            return;

        Deque<Node> q = new LinkedList<>();
        q.add(node);
        q.add(null);

        while (q.size() > 1) {
            node = q.peekFirst();
            while (node != null) {
                node = q.pollFirst();
                System.out.print(node.val + "->");
                if (node.left != null) {
                    q.addLast(node.left);
                }
                if (node.right != null) {
                    q.addLast(node.right);
                }
                node = q.peekFirst();
            }
            System.out.println();

            node = q.getLast();
            while (node != null) {
                node = q.pollLast();
                System.out.print(node.val + "->");
                if (node.right != null) {
                    q.addFirst(node.right);
                }
                if (node.left != null) {
                    q.addFirst(node.left);
                }
                node = q.peekLast();
            }
            System.out.println();
        }
    }


   /*
            1
        2      3
      7  6    5  4

   */
    public static void main(String[] args)
    {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(7);
        root.left.right = new Node(6);
        root.right.left = new Node(5);
        root.right.right = new Node(4);
        System.out.println("Spiral order traversal of Binary Tree is ");
        spiralWithOneDeque(root);
        //spiralWithDequeAndDelimiter(root);
    }
}
