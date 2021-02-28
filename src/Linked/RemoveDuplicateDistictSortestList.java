package Linked;

public class RemoveDuplicateSortestList {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
            next = null;
        }
    }
    public ListNode deleteDuplicates(ListNode head){
        if(head == null )
            return head;
            
        ListNode d = new ListNode(0);
        d.next = head;
        ListNode prev = d;
        ListNode curr = head;
        boolean dup = false;
        while(curr.next != null){
            if(curr.val == curr.next.val){
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
