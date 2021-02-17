package Linked;

public class SwapFirstandLastNode {

    private static class ListNode {
         public int val;
         public ListNode next;
         ListNode(int x) { val = x; next = null; }
    }

    public static ListNode reorderList(ListNode A) {
        ListNode slow = A;
        ListNode fast = A;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode second = slow.next;
        slow.next = null;
        second = reverseList(second);
        ListNode head = new ListNode(0);
        ListNode result = head;
        while (A != null && second != null) {
            result.next = A;
            A = A.next;
            
            result.next.next = second;
            second = second.next;
            result = result.next.next;
        }
        result.next = A != null ? A : second;
        return head.next;
    }

    public static ListNode reverseList(ListNode A) {

        if (A == null) {
            return null;
        }

        ListNode pre = null;
        ListNode curr = A;
        while (curr != null) {
            ListNode tmp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = tmp;
        }
        return pre;
    }

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);

        printlist(head); // print original list
        reorderList(head); // rearrange list as per ques
        System.out.println("");
        printlist(head); // print modified list
    }

    private static void printlist(ListNode node) {
        if (node == null) {
            return;
        }
        while (node != null) {
            System.out.print(node.val + " -> ");
            node = node.next;
        }
    }
}
