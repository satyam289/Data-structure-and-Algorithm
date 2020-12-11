package Linked;

public class CloneLinkedListRandomPtr {

    private static class Node{
        int data;
        Node next;
        Node random;

        public Node(int data) {
            this.data = data;
        }
    }

    // 0(1) space In-place
    private static Node cloneLinkedList(Node head){

        Node curr = head, temp = null;
        // insert addition Node after every node
        while(curr != null){
            temp = curr.next;
            curr.next = new Node(curr.data);
            curr.next.next = temp;
            curr = temp;
        }

        curr = head;

        while (curr != null) {
            if (curr.next != null) {
                curr.next.random = curr.random != null ? curr.random.next : null;
            }
            curr = curr.next != null ? curr.next.next : null;
        }

        curr = head;
        Node result = head.next;
        Node copy = head.next;
        while (curr != null && copy != null) {
            curr.next = curr.next != null ? curr.next.next : null;
            copy.next = curr.next != null ? curr.next.next : null; //copy.next.next
            curr = curr.next;
            copy = copy.next;
        }

        return result;
    }

    static void print(Node start)
    {
        Node ptr = start;
        while (ptr != null)
        {
            System.out.println("Data = " + ptr.data + ", Random = "+ptr.random.data);
            ptr = ptr.next;
        }
    }

    public static void main(String[] args) {
        Node start = new Node(1);
        start.next = new Node(2);
        start.next.next = new Node(3);
        start.next.next.next = new Node(4);
        start.next.next.next.next = new Node(5);

        // 1's random points to 3
        start.random = start.next.next;

        // 2's random points to 1
        start.next.random = start;

        // 3's and 4's random points to 5
        start.next.next.random = start.next.next.next.next;
        start.next.next.next.random = start.next.next.next.next;

        // 5's random points to 2
        start.next.next.next.next.random = start.next;

        System.out.println("Original list : ");
        print(start);

        System.out.println("Cloned list : ");
        Node cloned_list = cloneLinkedList(start);
        print(cloned_list);
    }
}
