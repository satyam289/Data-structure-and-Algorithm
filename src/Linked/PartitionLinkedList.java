package Linked;

//https://www.interviewbit.com/problems/partition-list/
public class PartitionLinkedList {
    
    private static class ListNode {
        int val;
        ListNode next;
    }

    public ListNode partition(ListNode A, int B) {

        ListNode curr = A;
        ListNode lessStartptr = null; // less partition
        ListNode lessEndPtr = null; 
        ListNode moreStartptr = null; // greater partition
        ListNode moreEndPtr = null;

        while (curr != null) {
            if (curr.val < B) {
                if (lessEndPtr == null) {
                    lessEndPtr = curr;
                    lessStartptr = curr;
                } else {
                    lessEndPtr.next = curr;
                    lessEndPtr = curr;
                }
            } else {
                if (moreEndPtr == null) {
                    moreEndPtr = curr;
                    moreStartptr = moreEndPtr;
                } else {
                    moreEndPtr.next = curr;
                    moreEndPtr = curr;
                }
            }
            curr = curr.next;
        }
        if (lessEndPtr == null) {
            return moreStartptr;
        }
        if (moreEndPtr == null) {
            return lessStartptr;
        }
        moreEndPtr.next = null;  // setting last node to null
        lessEndPtr.next = moreStartptr; // connection less partition to start of greater partition

        return lessStartptr;
    }
}