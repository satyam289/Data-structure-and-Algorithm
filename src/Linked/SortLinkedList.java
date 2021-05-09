package Linked;

public class SortLinkedList {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            next = null;
        }
    }
    
    // https://leetcode.com/problems/insertion-sort-list/solution/
    // Time Complexity : 0(n2)
    public static ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode curr = head;

        while (curr != null) {

            ListNode prev = dummy;

            while (prev.next != null && prev.next.val < curr.val) {
                prev = prev.next;
            }

            ListNode temp = curr.next;
            // insert the current node to the new list
            curr.next = prev.next;
            prev.next = curr;
            // moving on to the next iteration
            curr = temp;
        }
        return dummy.next;
    }

    // swap its value instead of reconnecting link (Not practical use)
    public static ListNode insertionSortList2(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode pre, curr = head.next;
        while (curr != null) {
            pre = head; // from begining
            while (pre != curr) {
                if (pre.val < curr.val)
                    pre = pre.next;
                else { // swap its value
                    int temp = curr.val;
                    curr.val = pre.val;
                    pre.val = temp;
                }
            }
            curr = curr.next;
        }
        return head;
    }

    // Time Complexity : 0(nlogn)
    public ListNode mergeSortList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode temp = null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            temp = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        temp.next = null;

        ListNode left_side = mergeSortList(head);
        ListNode right_side = mergeSortList(slow);

        return merge(left_side, right_side);
    }

    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode sorted_node = new ListNode(0);
        ListNode curr = sorted_node;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                curr.next = head1;
                head1 = head1.next;
            } else {
                curr.next = head2;
                head2 = head2.next;
            }
            curr = curr.next;
        }
        if (head1 != null) {
            curr.next = head1;
        }
        if (head2 != null) {
            curr.next = head2;
        }
        return sorted_node.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(3);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(5);
        head.next.next.next.next = new ListNode(4);
        print(head);
        head = insertionSortList(head);
        print(head);
    }

    public static void print(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + "--->");
            current = current.next;
        }
        System.out.println("");
    }
}
