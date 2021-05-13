package Linked;

public class RemoveDuplicateSorted {

    private static class ListNode {
        int data;
        ListNode next;
        ListNode(int val){
            this.data = val;
            next = null;
        }
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
    
    // https://www.interviewbit.com/problems/remove-duplicates-from-sorted-list-ii/
    // Remove all element from list that appear twice, consider only distict node.
    public ListNode deleteDuplicates(ListNode head){
        if(head == null )
            return head;
            
        ListNode d = new ListNode(0);
        d.next = head;
        ListNode prev = d;
        ListNode curr = head;
        boolean dup = false;
        while(curr.next != null){
            if(curr.data == curr.next.data){
                curr.next = curr.next.next; // chopping duplicate node by reconnecting
                dup = true;
            }else{
                if(dup){
                    prev.next = curr.next; // skipping current node which is present in duplicate, connecting next node of current
                }else{
                    prev = curr; // Moving previous pointer with non repeating current node
                }
                curr = curr.next; 
                dup = false;
            }
        }
        if(dup){  // if duplicate, connnect previous node to null ( current.next == null)
            prev.next = curr.next;
        }
        return d.next;
    }
}