package Linked;

public class InsertionSortLinkedList {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            next = null;
        }
    }

    public static ListNode insertionSortList(ListNode A) {

        if (A == null || A.next == null) {
            return A;
        }

        ListNode result = new ListNode(0);
        result.next = A;
        ListNode start = A.next;
        ListNode preOuter = A;

        while (start != null) {

            ListNode temp = result.next;  // update start ptr
            ListNode pre = null;
            while (temp.val < start.val && temp != start) {
                pre = temp;
                temp = temp.next;
            }
            //when temp == start, there is no change (order is fine), change required only when temp != start
            if(temp != start){  
                 // removing Node (**first operation***)
                 preOuter.next = start.next; 
                 // adding Node
                 if (pre == null) {   //begining of node
                     start.next = temp;
                     result.next = start;  //setting begging head
                } else {
                     pre.next = start;    //Setting Intermediate node
                    start.next = temp;
                } 
           }
            preOuter = start;  
            start = start.next;
        }
        return result.next;
    }

    public ListNode insertionSortList2(ListNode A) {
        if(A==null||A.next==null) return A;
         ListNode insert,current=A.next;
         while(current!=null){
             insert=A;
             while(insert!=current){
                 if(insert.val<current.val) insert=insert.next;
                 else{
                     int temp=current.val;
                     current.val=insert.val;
                     insert.val=temp;
                 }
             }
             current=current.next;
         }
         return A;
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
