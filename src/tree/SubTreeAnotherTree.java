package tree;

import java.util.ArrayList;
import java.util.List;

//https://www.geeksforgeeks.org/check-if-a-binary-tree-is-subtree-of-another-binary-tree/
//https://www.geeksforgeeks.org/check-binary-tree-subtree-another-binary-tree-set-2/

public class SubTreeAnotherTree {

    private static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    public static boolean isSubTree(Node node1, Node node2) {
        if (node1 == null) {
            return true;
        }
        if (node2 == null) {
            return false;
        }
        if (isIdentical(node1, node2)) {
            return true;
        }
        return isSubTree(node1.left, node2) || isSubTree(node1.right, node2);
    }

    private static boolean isIdentical(Node root1, Node root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        return root1.data == root1.data && isIdentical(root1.left, root2.left) && isIdentical(root1.right, root2.right);
    }

    public static void main(String[] args) {

        Node root1 = new Node(26);
        root1.right = new Node(3);
        root1.right.right = new Node(3);
        root1.left = new Node(10);
        root1.left.left = new Node(4);
        root1.left.left.right = new Node(30);
        root1.left.right = new Node(6);

        Node root2 = new Node(10);
        root2.right = new Node(6);
        root2.left = new Node(4);
        root2.left.right = new Node(30);

        if (isSubTree(root1, root2)) {
            System.out.println("Tree 2 is subtree of Tree 1 ");
        } else {
            System.out.println("Tree 2 is not a subtree of Tree 1");
        }
        System.out.println(isSubTree2(root1, root2));
        System.out.println(isSubTree3(root1, root2));
    }

    // Time Complexity: 0(n2)
    private static boolean isSubTree2(Node tree, Node subtree) {
        if (tree == subtree) {
            return false;
        }
        List<Integer> first = new ArrayList<>();
        inorder(tree, first);
        List<Integer> second = new ArrayList<>();
        inorder(subtree, second);
        if (!convertToStr(first).contains(convertToStr(second))) {
            return false;
        }
        first.clear();
        second.clear();
        postorder(tree, first);
        postorder(subtree, second);
        if (!convertToStr(first).contains(convertToStr(second))) {
            return false;
        }
        return true;
    }

    private static void inorder(Node node, List<Integer> arr) {
        if (node == null) {
            return;
        }
        inorder(node.left, arr);
        arr.add(node.data);
        inorder(node.right, arr);
    }

    private static void postorder(Node node, List<Integer> arr) {
        if (node == null) {
            return;
        }
        postorder(node.left, arr);
        postorder(node.right, arr);
        arr.add(node.data);
    }

    // Time Complexity: 0(N)
    public static boolean isSubTree3(Node tree, Node subtree) {
        if (tree == null)
            return true;
        if (subtree == null)
            return false;

        List<String> first = new ArrayList<>();
        preorder2(tree, first);
        List<String> second = new ArrayList<>();
        preorder2(subtree, second);
        if (strstr(convertToStr(first), convertToStr(second)) != null) { // we can call contains also for easy impl
            return false;
        }

        first.clear();
        second.clear();
        inorder2(tree, first);
        inorder2(subtree, second);
        if (strstr(convertToStr(first), convertToStr(second)) != null) {
            return false;
        }
        return true;
    }

    // Similar String::contains Implementation ** KMP string matching ***
    private static String strstr(String hayString, String needle) {
        if (hayString == null || needle == null) {
            return null;
        }
        int hlen = hayString.length();
        int nlen = needle.length();
        if (hlen < nlen) {
            return null;
        }
        for (int i = 0; i <= hlen - nlen; i++) {
            if (hayString.charAt(i) == needle.charAt(0)) {
                int j = 0;
                for (; j < nlen; j++) {
                    if (hayString.charAt(i + j) != needle.charAt(i)) {
                        break;
                    }
                }
                if (j == nlen) {
                    return hayString.substring(i);
                }
            }
        }
        return null;
    }

    private static void inorder2(Node node, List<String> arr) {
        if (node == null) {
            arr.add("$");
            return;
        }
        inorder2(node.left, arr);
        arr.add(node.data + "");
        inorder2(node.right, arr);
    }

    private static void preorder2(Node node, List<String> arr) {
        if (node == null) {
            arr.add("$");
            return;
        }
        arr.add(node.data + "");
        preorder2(node.left, arr);
        preorder2(node.right, arr);
    }

    private static <T> String convertToStr(List<T> arr) {
        String str = "";
        for (int i = 0; i < arr.size(); i++) {
            str += String.valueOf(arr.get(i));
        }
        return str;
    }
}
