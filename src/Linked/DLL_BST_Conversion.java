package Linked;

public class DLL_BST_Conversion {

    private static LinkedListNode head;
    private static TreeNode root;

    public LinkedListNode convertDLLToBST() {
        int n = getLength();
        return convertDLLToBST(n);
    }

    /**
     * Time Complexity : 0(n)
     **/
    public LinkedListNode convertDLLToBST(int n) {

        if (n <= 0) {
            return null;
        }
        /* Recursively construct the left subtree */
        LinkedListNode left = convertDLLToBST(n / 2);
        /* head_ref now refers to middle node, make middle node as root of BST*/
        LinkedListNode root = head;
        // Set pointer to left subtree
        root.pre = left;
        /* Change head pointer of Linked List for parent recursive calls */
        head = head.next;
        /* Recursively construct the right subtree and link it with root. */
        root.next = convertDLLToBST(n - n / 2 - 1);
        return root;
    }

    public TreeNode convertBSTToDLL(TreeNode root) {
        if (root == null)
            return null;

        TreeNode left = convertBSTToDLL(root.left);
        if (left != null) {
            /* connect the rightmost node of left subtree to root */
            for (; left.right != null; left = left.right) ;
            left.right = root;
            root.left = left;
        }

        TreeNode right = convertBSTToDLL(root.right);
        if (right != null) {
            /* connect the leftmost node of right subtree to root */
            for (; right.left != null; right = right.left) ;
            right.left = root;
            root.right = right;
        }
        return root;
    }

    public int getLength() {
        LinkedListNode curr = head;
        int count = 0;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        return count;
    }

    public static void printLinkedList(LinkedListNode linkedListNode) {
        while (linkedListNode != null) {
            System.out.print(linkedListNode.data + "->");
            linkedListNode = linkedListNode.next;
        }
        System.out.println("");
    }

    public static void printPreOrderBST(LinkedListNode linkedListNode) {
        if (linkedListNode == null) {
            return;
        }
        System.out.print(linkedListNode.data + "->");
        printPreOrderBST(linkedListNode.pre);
        printPreOrderBST(linkedListNode.next);
    }

    private static void printDLL(TreeNode treeNode) {
        // Setting mid (root of tree) to start treeNode of linkedlistList
        while (treeNode.left != null) {
            treeNode = treeNode.left;
        }
        while (treeNode != null) {
            System.out.print(treeNode.data + "->");
            treeNode = treeNode.right;
        }
    }


    public static void main(String[] args) {
        DLL_BST_Conversion dllbst = new DLL_BST_Conversion();

        // LinkedList Construction
        head = new LinkedListNode(1);
        head.next = new LinkedListNode(2);
        head.next.next = new LinkedListNode(3);
        head.next.next.next = new LinkedListNode(4);
        head.next.next.next.next = new LinkedListNode(5);
        head.next.next.next.next.next = new LinkedListNode(6);

        printLinkedList(head);
        System.out.println("After converting DLL to BST ::");
        printPreOrderBST(dllbst.convertDLLToBST());
        System.out.println("");

        //bst Construction
        root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);

        System.out.println("After converting BST to DLL ::");
        printDLL(dllbst.convertBSTToDLL(root));
    }

    private static class LinkedListNode {
        int data;
        LinkedListNode pre;
        LinkedListNode next;

        LinkedListNode(int data) {
            this.data = data;
        }
    }

    private static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data) {
            this.data = data;
        }
    }
}
