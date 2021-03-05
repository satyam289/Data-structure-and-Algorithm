package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.Queue;

//https://www.interviewbit.com/problems/vertical-order-traversal-of-binary-tree/

public class PrintVerticalTree {

    private class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
    }
    private class NodeInfo{
        int hd;
        TreeNode node;
        NodeInfo(TreeNode node, int hd){
            this.hd = hd;
            this.node = node;
        }
    }
    
    public ArrayList<ArrayList<Integer>> verticalOrderTraversal(TreeNode A) {
        
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(A == null){
            return result;
        }
        TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();
        verticalOrderTraversalRec(A, map);
        
        for(int key : map.keySet()){
            ArrayList<Integer> list = map.get(key);
            result.add(list);
        }
        return result;
    }
    
    void verticalOrderTraversalRec(TreeNode A, TreeMap<Integer, ArrayList<Integer>> map){
        Queue<NodeInfo> q = new LinkedList<>();
        NodeInfo rootInfo = new NodeInfo(A, 0);
        q.add(rootInfo);
        
        while(!q.isEmpty()){
            
            NodeInfo nodeinfo = q.poll();
            int currHd = nodeinfo.hd;
            TreeNode currNode = nodeinfo.node;
            
            if(!map.containsKey(currHd)){  
               ArrayList<Integer> arr = new ArrayList<>();
               arr.add(currNode.val);
               map.put(currHd, arr);
            }else{
               map.get(currHd).add(currNode.val);
            }
            
           if(currNode.left != null){
              q.add(new NodeInfo(currNode.left, currHd - 1));
           }
           if(currNode.right != null){
              q.add(new NodeInfo(currNode.right, currHd + 1));
           }
        }
    } 
    
    // This will not maintain of same vertical line while pritnting list
   /*  public ArrayList<ArrayList<Integer>> verticalOrderTraversal(TreeNode A) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(A == null){
            return result;
        }
        TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();
        verticalOrderTraversalRec(A, 0, map);
        for(int key : map.keySet()){
            ArrayList<Integer> list = map.get(key);
            result.add(list);
        }
        return result;
    }
    
    public void verticalOrderTraversalRec(TreeNode A, int hd, TreeMap<Integer, ArrayList<Integer>> map){
        if(A == null){
            return;
        }
        if(map.containsKey(hd)){
            map.get(hd).add(A.val);
        }else{
            ArrayList<Integer> arr = new ArrayList<>();
            arr.add(A.val);
            map.put(hd, arr);
        }
        verticalOrderTraversalRec(A.left, hd-1, map);
        verticalOrderTraversalRec(A.right, hd+1, map);
    } */
}
