package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

// https://www.geeksforgeeks.org/construct-a-binary-tree-from-postorder-and-inorder/

public class ConstructBinaryFromInorderPostOrder {

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
        index = n-1;
        for(int i=0; i<n; i++) {
            map.put(A.get(i), i);
        }
        return buildTreeRec(B, 0, n -1);
    }
    
    public TreeNode buildTreeRec(ArrayList<Integer> B, int start, int end){
        
        if(start > end){
            return null;
        }
        
        int data = B.get(index);
        index--;
        TreeNode node = new TreeNode(data);
        
        if(start == end){
            return node;
        }
        int inOrderIndex = map.get(data);
        node.right = buildTreeRec(B, inOrderIndex+1, end);
        node.left = buildTreeRec(B, start, inOrderIndex-1);
        
        return node;
    }

    

    public TreeNode buildTreeOptimised(ArrayList<Integer> inoder, ArrayList<Integer> postorder) {
        HashSet<TreeNode> lookup = new HashSet<>();
        Stack<TreeNode> stack = new Stack<>();

        int n = postorder.size();
        int postindex = n - 1;
        TreeNode root = null;

        for (int inIndex = n - 1; inIndex >= 0;) {
            do {
                TreeNode tmpNode = new TreeNode(postorder.get(postindex));
                if (root == null) {
                    root = tmpNode;
                }
                if (stack.size() > 0) {
                    if (lookup.contains(stack.peek())) {
                        lookup.remove(stack.peek());
                        stack.peek().left = tmpNode; 
                        stack.pop();
                    } else {
                        stack.peek().right = tmpNode;
                    }
                }
                stack.push(tmpNode);
            } while (postorder.get(postindex--) != inoder.get(inIndex) && postindex >=0);

            TreeNode lastnode = null;

            // clearing already visited node
            while (stack.size() > 0 && inIndex >= 0 && stack.peek().val == inoder.get(inIndex)) {
                lastnode = stack.pop();
                inIndex--;
            }

            if (lastnode != null) {
                stack.push(lastnode);
                lookup.add(lastnode);
            }
        }
        return root;
    }
}
