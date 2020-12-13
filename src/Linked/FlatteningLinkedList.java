package Linked;

/**
 * 5 -> 10 -> 19 -> 28
 * |    |     |     |
 * V    V     V     V
 * 7    20    22    35
 * |          |     |
 * V          V     V
 * 8          50    40
 * |                |
 * V                V
 * 30               45
 *
 * 5->7->8->10->19->20->22->28->30->35->40->45->50
 */
public class FlatteningLinkedList {

    private static class Node {
        int data;
        Node next;
        Node down;

        Node(int data) {
            this.data = data;
        }
    }

    private static Node Flatten(Node head) {
        if (head == null || head.next == null)
            return head;

        Node rightNode = Flatten(head.next);
        head = mergeSorted(head, rightNode);
        return head;
    }


    private static Node mergeSorted(Node node1, Node node2) {
        if (node1 == null)
            return node2;
        if (node2 == null)
            return node1;

        Node temp;
        if (node1.data < node2.data) {
            temp = node1;
            temp.down = mergeSorted(node1.down, node2);
        } else {
            temp = node2;
            temp.down = mergeSorted(node1, node2.down);
        }
        temp.next = null;
        return temp;
    }

    private static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.down;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node root = new Node(5);
        root.down = new Node(7);
        root.down.down = new Node(8);
        root.down.down.down = new Node(30);

        root.next = new Node(10);
        root.next.down = new Node(20);

        root.next.next = new Node(19);
        root.next.next.down = new Node(22);
        root.next.next.down.down = new Node(50);

        root.next.next.next = new Node(28);
        root.next.next.next.down = new Node(35);
        root.next.next.next.down.down = new Node(40);
        root.next.next.next.down.down.down = new Node(45);

        Node head = Flatten(root);
        printList(head);
    }
}

