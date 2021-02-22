package Linked;

public class ReverseLinkedListBetween {
    
    private static class ListNode{
        ListNode next;
        int data;
        public ListNode(int data){
            this.data = data;
            next = null;
        }
    }

    public static ListNode reverseBetween(ListNode A, int B, int C) {
        ListNode head = A;
        ListNode curr = A;
        int i = 0;
        ListNode previousNode = null;
        while (curr != null && i < B) { // going till point B
            previousNode = curr;
            curr = curr.next;
            i++;
        }

        ListNode startptrReverse = curr;
        ListNode pre = null;
        for (int j = B; (j <= C) && (curr != null); j++) { // going to B to C (reversing)
            ListNode tmp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = tmp;
        }

        if (previousNode != null) {
            previousNode.next = pre; // connecting A end to start of reverse linked list
        } else {
            head = pre; // In case reverse start from 1, head should point new address
        }
        startptrReverse.next = curr; // going C to end
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        print(head);
        head = reverseBetween(head, 0, 3);
        print(head);
    }

    public static void print(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.data + "--->");
            current = current.next;
        }
        System.out.println("");
    }
}
