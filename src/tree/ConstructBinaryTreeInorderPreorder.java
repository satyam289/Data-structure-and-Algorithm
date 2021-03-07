package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

// https://www.geeksforgeeks.org/construct-tree-from-given-inorder-and-preorder-traversal/
public class ConstructBinaryTreeInorderPreorder {
    
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
            left = null;
            right = null;
        }
    }

    HashMap<Integer, Integer> map = new HashMap<>();
    int index;

    
    public TreeNode buildTree(ArrayList<Integer> A, ArrayList<Integer> B) {
        int n = A.size();
        index = 0;
        for(int i=0; i<n; i++) {
            map.put(B.get(i), i);
        }
        return buildTreeRec(A, 0, n -1);
    }
    
     public TreeNode buildTreeRec(ArrayList<Integer> A, int start, int end){
        
        if(start > end || index >= A.size()){
            return null;
        }
        
        int data = A.get(index);
        index++;
        TreeNode node = new TreeNode(data);
        
        if(start == end){
            return node;
        }
        int inOrderIndex = map.get(data);
        node.left = buildTreeRec(A, start, inOrderIndex-1);
        node.right = buildTreeRec(A, inOrderIndex+1, end);
    
        return node;
    }

    public TreeNode buildTreeOptimised(ArrayList<Integer> preorder, ArrayList<Integer> inorder) {
        HashSet<TreeNode> lookup = new HashSet<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = null;
        int preIndex = 0;
        int n = preorder.size();

        for (int inIndex = 0; inIndex < n;) {
            do {
                TreeNode tmp = new TreeNode(preorder.get(preIndex));
                if (root == null) {
                    root = tmp;
                }
                if (!stack.isEmpty()) {
                    if (lookup.contains(stack.peek())) {
                        lookup.remove(stack.peek());
                        stack.peek().right = tmp;
                        stack.pop();
                    } else {
                        stack.peek().left = tmp;
                    }
                }
                stack.push(tmp);
            } while (preorder.get(preIndex++) < inorder.get(inIndex) && preIndex < n);

            TreeNode node = null;
            while (!stack.isEmpty() && inIndex < n && stack.peek().val == inorder.get(inIndex)) {
                node = stack.pop();
                inIndex++;
            }

            if (node != null) {
                lookup.add(node);
                stack.push(node);
            }
        }
        return root;
    }
}
