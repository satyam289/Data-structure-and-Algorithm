package Linked;

public class KReverseList {

    private static class ListNode {
        int data;
        ListNode next;
    }

    public ListNode reverseList(ListNode A, int B) {

        ListNode head = null;
        ListNode begin = head;
        ListNode old = null;

        while (begin != null) {

            ListNode pre = null;
            ListNode curr = begin;
            int counter = 0;
            while ((curr != null) && (counter < B)) {
                ListNode temp = curr.next;
                curr.next = pre;
                pre = curr;
                curr = temp;
                counter++;
            }

            if (old != null) {
                old.next = pre; // (begining connection)connect old k splited node to reversed node
            } else {
                head = pre; // begining of linked list
            }
            begin.next = curr; // (end connection)connection start and end of reseverd linked list
            old = begin; // update old just before begin node
            begin = curr; // next cycle begin
        }
        return head;
    }
}
