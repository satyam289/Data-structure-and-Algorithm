package Linked;

public class SwapAlternateNode {

    private static class ListNode {
         public int val;
         public ListNode next;
         ListNode(int x) { val = x; next = null; }
    }

    public static ListNode swapAlternateNode(ListNode A) {
        if(A == null || A.next == null)
           return A;

        ListNode curr = A;
        ListNode pre = null;
        ListNode head = A.next;
        while(curr != null && curr.next != null) { 
            ListNode nextNode = curr.next; 
            ListNode nextToNextNode = curr.next.next; 
            nextNode.next = curr;   // reverse the connection
            if(pre != null) {
                pre.next = nextNode;  //now nextNode become first node
             }
            pre = curr;              //now current become second node
            curr = nextToNextNode;   
        }
        pre.next = curr;
        return head;
    }

    public static ListNode swapAlternateNodeRecursive(ListNode A) {
          if(A == null || A.next == null)
             return A;

          ListNode NextNode = A.next;
          ListNode NextToNextNode = A.next.next;
          NextNode.next = A;
          A.next = swapAlternateNodeRecursive(NextToNextNode);
          return NextNode;
    }


    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);

        printlist(head); // print original list
        //head = swapAlternateNode(head); // rearrange list as per ques
        head = swapAlternateNodeRecursive(head);
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
