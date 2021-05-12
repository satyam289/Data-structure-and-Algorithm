package Linked;

import StackQueue.Stack;

public class ReverseLinkedList {
    
    private static class ListNode{
        ListNode next;
        int data;
        public ListNode(int data){
            this.data = data;
            next = null;
        }
    }
    
    // https://www.geeksforgeeks.org/reverse-a-linked-list/
    public ListNode  reverseList(Listnode head){
        Stack<ListNode> st= new Stack<>();
        while(head != null){
            st.push(head);
            head = head.next;
        }

        ListNode dummy = new ListNode(-1);
        head = dummy;
        while(!st.isEmpty()){
            ListNode current = st.pop();
            head.next = new LinkedList(current.val);
            head = head.next;
        }
        return dummy.next;
    }

    // In place
    public ListNode reverseOptimised(ListNode node){
        ListNode pre = null;
        ListNode current = node;
        while(current != null){
            ListNode temp = current.next;
            current.next = pre;
            pre = current;
            current = temp;
        }
        return pre;
    }

    //@here reverse linked list from B to C (In-place)
    public static ListNode reverseIntermediate(ListNode A, int B, int C) {
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
