package Linked;

import java.util.PriorityQueue;

class MergeKSortedList {

    private static class ListNode {
        int data;
        ListNode next;
        ListNode(int data) {
            this.data = data;
            next = null;
        }
    }
    // https://www.geeksforgeeks.org/merge-two-sorted-linked-lists/
    public static ListNode MergeTwo(ListNode headA, ListNode headB) {
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        while (true) {
            if (headA == null) {
                head.next = headB;
                break;
            }
            if (headB == null) {
                head.next = headA;
                break;
            }
            if (headA.data < headB.data) {
                head.next = headA;
                headA = headA.next;
            } else {
                head.next = headB;
                headB = headB.next;
            }
            head = head.next;
        }
        return dummy.next;
    }
     
    // Time Complexity : 0(m+n)
    public static ListNode mergeTwoRec(ListNode headA, ListNode headB) {
        if (headA == null) {
            return headB;
        }
        if (headB == null) {
            return headA;
        }
        if (headA.data < headB.data) {
            headA.next = mergeTwoRec(headA.next, headB);
            return headA;
        } else {
            headB.next = mergeTwoRec(headA, headB.next);
            return headB;
        }
    }
    
    //https://www.geeksforgeeks.org/merge-k-sorted-linked-lists/
    /*
    Approach1 : a)Taking two list at the time and merge them, pick one by one and merge with resultant
                b)Maintain K array pointer of head node, find min of them and move ahead ptr of min node  
                Time Complexity: 0(N2)
    Approach2 : combine all list in one list, then finallt sort them using merge
                Time Complexity: 0(Nklogk)
    Approach3 : Divide And Conquer: Time Complexity : 0(NlogK)
    Approach4 : Using MinHeap: Time Complexity : 0(NklogK)
    */
    public static ListNode mergeKList(ListNode[] headArr, int k) {
        int start = 0;
        int end = k;
        while (end != 0) {
            start = 0;
            while (start < end) { // end approaching toward start , finally to 0 index
                headArr[start] = MergeTwo(headArr[start], headArr[end]);
                start++;
                end--;
            } 
        }
        return headArr[0];
    }
    
    //https://www.geeksforgeeks.org/merge-k-sorted-linked-lists-set-2-using-min-heap/
    public static ListNode mergeKList2(ListNode[] headArr, int k) {
        ListNode head = null;
        ListNode last = null;

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.data - b.data);
        for (int i = 0; i < k; i++) {
            if (headArr[i] != null) {
                minHeap.add(headArr[i]);
            }
        }
        while (!minHeap.isEmpty()) {
            ListNode minimum = minHeap.poll();
            if (minimum.next != null) {
                minHeap.add(minimum.next);
            }
            if (head == null) {
                head = minimum;
                last = minimum;
            } else {
                last.next = minimum;
                last = minimum;
            }
        }
        return head;
    }

    public static void main(String[] args){
        int k = 3;
        ListNode arr[] = new ListNode[k];
 
        arr[0] = new ListNode(1);
        arr[0].next = new ListNode(3);
        arr[0].next.next = new ListNode(5);
        arr[0].next.next.next = new ListNode(7);
 
        arr[1] = new ListNode(2);
        arr[1].next = new ListNode(4);
        arr[1].next.next = new ListNode(6);
        arr[1].next.next.next = new ListNode(8);
 
        arr[2] = new ListNode(0);
        arr[2].next = new ListNode(9);
        arr[2].next.next = new ListNode(10);
        arr[2].next.next.next = new ListNode(11);
 
        // Merge all lists
        ListNode head = mergeKList(arr, k - 1);
        printList(head);
    }

    private static void printList(ListNode node)
    {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }
}