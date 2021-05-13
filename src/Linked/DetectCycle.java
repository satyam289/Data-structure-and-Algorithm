package Linked;

//https://www.geeksforgeeks.org/detect-and-remove-loop-in-a-linked-list/
public class DetectCycle {

    private static class ListNode {
        int data;
        ListNode next;
    }

    public ListNode detectandRemoveCycle(ListNode a) {

        ListNode slow = a;
        ListNode fast = a;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }

        if (fast == null || fast.next == null || fast != slow) {
            return null;
        }

        int count = 1;
        ListNode tmp = slow;
        while (slow.next != tmp) {
            slow = slow.next;
            count++;
        }

        ListNode first = a;
        ListNode second = a;
        for (int i = 0; i < count; i++) {
            second = second.next;
        }

        while (second != first) {
            first = first.next;
            second = second.next;
        }
        return second;
    }
}
