package Linked;

public class Palindrome {
    private static class ListNode {
        int val;
        ListNode next;
    }

    // Space Complexity: 0(1) Reverse second half of linked list and then compare by
    // one by one
    public int lPalin(ListNode A) {

        ListNode slow = A;
        ListNode fast = A;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //reached half list
        if (fast != null) {
            slow = slow.next; // Odd number, skip the middle node
        }
        //reverse the second half
        ListNode pre = null;
        ListNode curr = slow;
        while (curr != null) {
            ListNode tmp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = tmp;
        }
        //comparing reverse and head node of list
        while (pre != null) {
            if (A.val != pre.val) { // Not Equal
                return 0; // Not Palindrome
            }
            A = A.next;
            pre = pre.next;
        }
        return 1; // Palindrome
    }
}
