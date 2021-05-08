package tree;

//https://www.geeksforgeeks.org/sorted-linked-list-to-balanced-bst/
public class SortedLinkedListBst {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
            left = right = null;
        }
    }

    private class ListNode {
        public int val;
        public ListNode next;
    }

    ListNode listnode = null;

    /*
    Apporach1: Find Mid element make root, recursively construct left subtree and right subtree.
               Since Finding mid Node is costly operation in linked list
    Apporach2: Build left subtree first, then root  and finally right by linear fashion below mention           
    */
    public TreeNode sortedListToBST(ListNode input) {
        if( input == null){
            return null;
        }
        int n = getLen(input);
        listnode = input;
        return sortedListToBSTRec(n);
    }
    
    private TreeNode sortedListToBSTRec(int n) {
        if (n <= 0) {
            return null;
        }
        TreeNode lNode = sortedListToBSTRec(n / 2);
        TreeNode root = new TreeNode(listnode.val);
        root.left = lNode;

        listnode = listnode.next;

        root.right = sortedListToBSTRec(n - n / 2 - 1);
        return root;
    }
    
    private int getLen(ListNode node) {
        int count = 0;
        ListNode curr = node;
        while (curr != null) {
            curr = curr.next;
            count++;
        }
        return count;
    }
}   
