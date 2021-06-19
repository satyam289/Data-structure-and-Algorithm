package tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

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
                    System.out.print(node.val + "->");
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

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(7);
        root.left.right = new Node(6);
        root.right.left = new Node(5);
        root.right.right = new Node(4);
        System.out.println("Spiral order traversal of Binary Tree is ");
        spiralWithOneDeque(root);
        // spiralWithDequeAndDelimiter(root);
    }

    // https://www.interviewbit.com/problems/diagonal-traversal/
    public ArrayList<Integer> solve(Node A) {
        Queue<Node> q = new LinkedList<>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (A == null) {
            return result;
        }
        q.add(A);
        while (!q.isEmpty()) {
            Node node = q.remove();

            while (node != null) {
                if (node.left != null) {
                    q.add(node.left);
                }
                result.add(node.val);
                node = node.right;
            }
        }
        return result;
    }

    // Similar as above
    public ArrayList<Integer> solve2(Node A) {
        if (A == null) {
            return new ArrayList<>(0);
        }
        Queue<Node> q = new LinkedList<>();
        Node root = A;
        while (root != null) {
            q.offer(root);
            root = root.right;
        }
        ArrayList<Integer> answer = new ArrayList<>();
        /*
         * For each de-queue operation check for the left node if not NULL then pick the
         * left node and add all the right children to the queue.
         */
        while (!q.isEmpty()) {
            root = q.poll();
            answer.add(root.val);
            root = root.left;
            while (root != null) {
                q.offer(root);
                root = root.right;
            }
        }
        return answer;
    }
}
