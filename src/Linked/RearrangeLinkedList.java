package Linked;

// https://www.geeksforgeeks.org/rearrange-a-given-linked-list-in-place/

/**
 * Given a singly linked list L0 -> L1 -> … -> Ln-1 -> Ln. 
 * Rearrange the list : L0 -> Ln -> L1 -> Ln-1 -> L2 -> Ln-2 …
 */
public class RearrangeLinkedList {
 
    static Node head; // head of the list

    /* Node Class */
    private static class Node {
        int data;
        Node next;
        Node(int d) {
            data = d;
            next = null;
        }
    }

    private  static void printlist(Node node) {
        if (node == null) {
            return;
        }
        while (node != null) {
            System.out.print(node.data + " -> ");
            node = node.next;
        }
        System.out.println("");
    }

    private static Node reverselist(Node node) {
        Node prev = null, curr = node, next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        node = prev;
        return node;
    }

    public static void rearrange(Node node) {

        Node slow = node, fast = slow.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        //Split the linked list in two halves
        Node node1 = node;
        Node node2 = slow.next;
        slow.next = null;
        // Reverse the second half
        node2 = reverselist(node2);
        node = new Node(0);  // dummy Node

        // Merge alternate nodes
        Node curr = node;
        while (node1 != null || node2 != null) {

            if (node1 != null) {
                curr.next = node1;
                curr = curr.next;
                node1 = node1.next;
            }

            if (node2 != null) {
                curr.next = node2;
                curr = curr.next;
                node2 = node2.next;
            }
        }
        // Assign the head of the new list to head pointer
        node = node.next;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        printlist(head); 
        rearrange(head);
        printlist(head);
    }
}
