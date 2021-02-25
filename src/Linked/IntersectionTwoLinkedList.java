package Linked;
// https://www.geeksforgeeks.org/write-a-function-to-get-the-intersection-point-of-two-linked-lists/

public class IntersectionTwoLinkedList {

    private static class ListNode {
        int data;
        ListNode next;
    }

    /** 
     *  1) If we two pointer apporach: 0(m*n)
     *  2) hash map of visited node : Time Complexity: 0(m+n) , space complexity 0(m)
     *  3) Below apporach:  0(m+n), space complexity 0(1) 
     */
    public static ListNode findIntersection(ListNode A, ListNode B) {

        if (A == null || B == null) {
            return null;
        }
        ListNode first = A;
        ListNode second = B;
        int l1 = getLength(A);
        int l2 = getLength(B);
        int diff = l1 - l2;
        if (diff > 0) {
            while (first != null && (diff > 0)) {
                first = first.next;
                diff--;
            }
        } else {
            while (second != null && (diff < 0)) {
                second = second.next;
                diff++;
            }
        }
        while (first != null) {
            if (first == second) { //always compare the address, not the value
                return first;
            }
            first = first.next;
            second = second.next;
        }
        return null;
    }

    public static int getLength(ListNode list) {
        ListNode curr = list;
        int count = 1;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        return count;
    }
}
