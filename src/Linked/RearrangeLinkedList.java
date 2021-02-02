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

    void printlist(Node node) {
        if (node == null) {
            return;
        }
        while (node != null) {
            System.out.print(node.data + " -> ");
            node = node.next;
        }
    }

    Node reverselist(Node node) {
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

    void rearrange(Node node) {

        // 1) Find the middle point using tortoise and hare method
        Node slow = node, fast = slow.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2) Split the linked list in two halves
        // node1, head of first half 1 -> 2 -> 3
        // node2, head of second half 4 -> 5
        Node node1 = node;
        Node node2 = slow.next;
        slow.next = null;

        // 3) Reverse the second half, i.e., 5 -> 4
        node2 = reverselist(node2);

        // 4) Merge alternate nodes
        node = new Node(0); // Assign dummy Node

        // curr is the pointer to this dummy Node, which will
        // be used to form the new list
        Node curr = node;
        while (node1 != null || node2 != null) {

            // First add the element from first list
            if (node1 != null) {
                curr.next = node1;
                curr = curr.next;
                node1 = node1.next;
            }

            // Then add the element from second list
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

        RearrangeLinkedList list = new RearrangeLinkedList();
        RearrangeLinkedList.head = new Node(1);
        RearrangeLinkedList.head.next = new Node(2);
        RearrangeLinkedList.head.next.next = new Node(3);
        RearrangeLinkedList.head.next.next.next = new Node(4);
        RearrangeLinkedList.head.next.next.next.next = new Node(5);

        list.printlist(head); // print original list
        list.rearrange(head); // rearrange list as per ques
        System.out.println("");
        list.printlist(head); // print modified list
    }
}
