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

/*     private static ListNode domergeSort2(ListNode link) {
        if (link == null || link.next == null) {
            return link;
        }
        ListNode middle = partition(link);
        ListNode secondHalf = middle.next;
        middle.next = null;
        return merge2(domergeSort(link), domergeSort(secondHalf));

    }

    private static ListNode partition(ListNode link) {
        if (link == null)
            return null;

        ListNode slowptr = link;
        ListNode fastptr = link.next;
        while (fastptr != null && fastptr.next != null) {
            slowptr = slowptr.next;
            fastptr = fastptr.next.next;
        }
        return slowptr;
    }

    private static ListNode merge2(ListNode m1, ListNode m2) {
        ListNode dummy = new ListNode(-1);                 
        ListNode mergehead = dummy;
        while (m1 != null && m2 != null) {

            if (m1.val < m2.val) {
                dummy.next = m1;
                m1 = m1.next;
            } else {
                dummy.next = m2;
                m2 = m2.next;
            }
            dummy = dummy.next;
        }
        dummy.next = (m1 == null) ? m2 : m1;
        return mergehead.next;
    } */

// https://www.interviewbit.com/old/problems/sort-binary-linked-list/
public ListNode solve(ListNode A) {
    int c = 0;
    ListNode i = A;
    while (i != null) {
        if (i.val == 0)
            c++;
        i = i.next;
    }
    i = A;
    while (i != null && c > 0) {
        i.val = 0;
        i = i.next;
        c--;
    }
    while (i != null) {
        i.val = 1;
        i = i.next;
    }
    return A;
}

public ListNode solve2(ListNode head) {
    // Make two unique heads zeroHead and oneHead
    ListNode zeroHead = new ListNode(-1);
    ListNode zero = zeroHead;
    ListNode oneHead = new ListNode(-1);
    ListNode one = oneHead;
    ListNode temp = head;

    while (temp != null) {
        if (temp.val == 0) { // if zero value, then move the zero pointer
            zero.next = temp;
            zero = zero.next;
        } else {
            one.next = temp; // if one value, then move the one pointer
            one = one.next;
        }
        temp = temp.next; // finally update the temp
    }
    // To remove any 'next' connections from the end of list (else TLE!!!)
    one.next = null;
    // Point the next of zeroHead to oneHead
    zero.next = oneHead.next;
    return zeroHead.next;
}
}
