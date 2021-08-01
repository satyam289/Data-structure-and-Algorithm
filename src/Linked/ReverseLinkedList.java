package Linked;

import java.util.Stack;

public class ReverseLinkedList {

    private static class ListNode {
        ListNode next;
        int data;

        public ListNode(int data) {
            this.data = data;
            next = null;
        }
    }

    // Reverse linked list from itermediate position from B to C (In-place)
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
        for (int j = B; (j <= C) && (curr != null); j++) { // going to B to C
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
        head.next.next = new ListNode(4);
        head.next.next.next = new ListNode(5);
        head.next.next.next.next = new ListNode(6);
        print(head);
        head = reverseIntermediate(head, 0, 3);
        print(head);
    }

    private static void print(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.data + "--->");
            current = current.next;
        }
        System.out.println("");
    }

    // https://www.geeksforgeeks.org/reverse-a-linked-list/
    public static ListNode reverseList(ListNode head) {
        Stack<ListNode> st = new Stack<>();
        while (head != null) {
            st.push(head);
            head = head.next;
        }

        ListNode dummy = new ListNode(-1);
        head = dummy;
        while (!st.isEmpty()) {
            ListNode current = st.pop();
            head.next = new ListNode(current.data);
            head = head.next;
        }
        return dummy.next;
    }

    // Optimised : In place Reverse Linked List
    public ListNode reverseList2(ListNode node) {
        ListNode pre = null;
        ListNode current = node;
        while (current != null) {
            ListNode temp = current.next;
            current.next = pre;
            pre = current;
            current = temp;
        }
        return pre;
    }

    /*
     * https://www.geeksforgeeks.org/reverse-a-list-in-groups-of-given-size/ Input:
     * 1->2->3->4->5->6->7->8->NULL, K = 3 Output: 3->2->1->6->5->4->8->7->NULL
     */
    public ListNode Kreverse(ListNode head, int k) {
        if (head == null)
            return null;

        ListNode current = head;
        ListNode prev = null;
        ListNode temp = null;
        int count = 0;

        /* Reverse first k nodes of linked list */
        while (count < k && current != null) {
            temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
            count++;
        }
        if (current != null)
            head.next = Kreverse(current, k);

        // prev is now head of input list
        return prev;
    }

    /*
     * You are given a singly-linked list that contains N integers. A subpart of the
     * list is a contiguous set of even elements, bordered either by either end of
     * the list or an odd element. For example, if the list is [1, 2, 8, 9, 12, 16],
     * the subparts of the list are [2, 8] and [12, 16]. Then, for each subpart, the
     * order of the elements is reversed. In the example, this would result in the
     * new list, [1, 8, 2, 9, 16, 12].
     */
    public ListNode reverseContinousEven(ListNode head) {
        ListNode curr = head;
        ListNode pre = null;
        while (curr != null) {

            if (curr.data % 2 == 0) {
                ListNode revpre = null;
                ListNode start = curr;
                while (curr != null && curr.data % 2 == 0) {
                    ListNode temp = curr.next;
                    curr.next = revpre;
                    revpre = curr;
                    curr = temp;
                }
                if (pre != null) {
                    pre.next = revpre;
                } else {
                    head = revpre;
                }
                start.next = curr;
            }
            pre = curr;
            if (curr != null) {
                curr = curr.next;
            }
        }
        return head;
    }
   
    //https://www.interviewbit.com/problems/even-reverse/
    public ListNode solve(ListNode A) {
        ListNode temp = A;
        Stack<Integer> s = new Stack<Integer>();
        while (temp != null) {
            s.push(temp.data);
            temp = temp.next;
        }
        temp = A;
        int size = s.size();
        if (size % 2 == 0) {
            while (temp != null && s.size() > 0) {
                int value = s.pop();
                temp = temp.next;
                temp.data = value;
                temp = temp.next;
                s.pop();
            }
        } else {
            s.pop();
            while (temp != null && s.size() > 0) {
                int value = s.pop();
                temp = temp.next;
                temp.data = value;
                temp = temp.next;
                s.pop();
            }
        }
        return A;
    }
}
