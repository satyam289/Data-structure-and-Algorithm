package Linked;

public class AddTwoList {
    
    private class ListNode {
        private ListNode next;
        int val;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode addList(ListNode A, ListNode B) {

        ListNode result = new ListNode(0);
        ListNode head = result;
        int carry = 0;
        int sum = 0;
        while (A != null || B != null) {
            sum = carry;
            if (A != null) {
                sum += A.val;
                A = A.next;
            }
            if (B != null) {
                sum += B.val;
                B = B.next;
            }
            result.next = new ListNode(sum % 10);
            result = result.next;
            carry = sum / 10;
        }
        if(carry > 0){
            result.next = new ListNode(carry);
        }
        return head.next;
    }

    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (i >= 0) {
                sum += num1.charAt(i) - '0';
            }
            if (j >= 0) {
                sum += num2.charAt(j) - '0';
            }
            carry = sum / 10;
            sb.append(sum % 10);
        }
        if (carry > 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
}
