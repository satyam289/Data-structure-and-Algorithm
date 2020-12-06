package Linked;

public class DoublyLinkedListToBST {

    private static Node head;

    public Node convertDLLToBST() {
        int n = getLength();
        return convertDLLToBST(n);
    }

    public int getLength() {
        Node curr = head;
        int count = 0;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        return count;
    }

    public void printLinkedList(Node node) {
        while (node != null) {
            System.out.print(node.data + "->");
            node = node.next;
        }
        System.out.println("");
    }

    public void printPreOrderBST(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data + "->");
        printPreOrderBST(node.pre);
        printPreOrderBST(node.next);
    }

    /**
     * Time Complexity : 0(n)
     **/
    public Node convertDLLToBST(int n) {

        if (n <= 0) {
            return null;
        }
        /* Recursively construct the left subtree */
        Node left = convertDLLToBST(n / 2);
        /* head_ref now refers to middle node, make middle node as root of BST*/
        Node root = head;
        // Set pointer to left subtree
        root.pre = left;
        /* Change head pointer of Linked List for parent recursive calls */
        head = head.next;
        /* Recursively construct the right subtree and link it with root. */
        Node right = convertDLLToBST(n - n / 2 - 1);
        root.next = right;
        return root;
    }

    public static void main(String[] args) {
        DoublyLinkedListToBST dllbst = new DoublyLinkedListToBST();
        dllbst.head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        dllbst.printLinkedList(head);
        System.out.println("After converting DLL to BST ::");
        dllbst.printPreOrderBST(dllbst.convertDLLToBST());
    }

    private static class Node {
        int data;
        Node pre;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }
}
