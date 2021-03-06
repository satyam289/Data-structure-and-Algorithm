package tree;

import java.util.ArrayList;
import java.util.HashMap;

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
}
