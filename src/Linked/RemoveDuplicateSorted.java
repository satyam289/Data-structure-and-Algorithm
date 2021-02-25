package Linked;

public class RemoveDuplicateSorted {

    private static class ListNode {
        int data;
        ListNode next;
    }

    public static ListNode removeDuplicate(ListNode head) {
        ListNode curr = head;
        while (curr.next != null) {
            if (curr.next.data == curr.data) {
                curr.next = curr.next.next; // skip the equal value
            } else {
                curr = curr.next;
            }
        }
        return head;
    }
}