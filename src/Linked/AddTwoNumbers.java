package Linked;

public class AddTwoNumbers {
    
    private class ListNode {
        private ListNode next;
        int val;

        ListNode(int val) {
            this.val = val;
        }
    }
    
    public ListNode addTwoNumbers(ListNode A, ListNode B) {

        ListNode result = new ListNode(0);
        ListNode head = result;
        int carry = 0;
        while(A != null || B != null) {
            
            ListNode first = A;
            ListNode second = B;
            int sum = 0;
            
            if(first != null) {
                sum += first.val;
                A = A.next;
            }
            if(second != null) {
                sum += second.val;
                B = B.next;
            }
            if(carry > 0) {
                sum += carry;
            }
            
            carry = sum / 10;
            sum = sum % 10;
            result.next = new ListNode(sum);
            result = result.next;
        }

        if(carry > 0){
            result.next = new ListNode(carry);
        }
        return head.next;
    }
}
