package Miscellaneous;

import java.util.*;

public class MediumHardEasyLeetcode {

    public int lengthOfLIS(int [] nums) {
        if(nums.length <=0 ){
            return nums.length;
        }
        int [] dp = new int[nums.length];
        int max = -1;
        Arrays.fill(dp, 1);
        for(int i=1; i< nums.length; i++) {
            for(int j=0; j<i; j++) {
                if(nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    private class Node {
        int val;
        Node left;
        Node right;
        Node next;
        Node(int val){
            this.val= val;
        }
    }

    public Node connect (Node root){
        if(root == null){
            return null;
        }
        Queue<Node> q = new LinkedList();
        q.add(root);
        q.add(null);
        while(!q.isEmpty()){
            Node curr = q.poll();
            if(curr == null && q.isEmpty()){
                return root;
            }else if(curr == null){
                q.add(null);
                continue;
            } else {
                curr.next = q.peek();
                if(curr.left != null){
                    q.add(curr.left);
                }
                if(curr.right != null){
                    q.add(curr.right);
                }
            }
        }
        return root;
    }

    public int [] productExceptSelf(int [] nums){
        int [] prod = new int[nums.length];

        prod[0] = 1;
        for(int i = 1; i< nums.length; i++){
            prod[i] *= nums[i];
        }

        int temp = 1;
        for(int i = nums.length -2 ; i>=0; i--){
            prod[i] *= (nums[i+1]*temp);
            temp *= nums[i+1];
        }
        return prod;
    }

    public List<String> generateParenthesis(int n){
        ArrayList<String> result = new ArrayList<>();
        helperGP(result, "", 0, 0, n);
    }


    private void helperGP(ArrayList<String> result, String s, int open, int close, int n) {
        if(s.length() == n*2){
            result.add(s);
            return;
        }
        if(open < n){
            helperGP(result, s+"(", open+1, close, n);
        }
        if(open > close){
            helperGP(result, s+")", open, close+1, n);
        }
    }

    public boolean isValidBST(Node root){
        return helperBT(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean helperBT(Node root, int minValue, int maxValue) {
        if(root == null)
            return true;
        if(root.val <= minValue || root.val >= maxValue){
            return false;
        }
        return helperBT(root.left, minValue, root.val) && helperBT(root.right, root.val, maxValue);
    }

    public List<List<String>> groupAnagrams(String [] strs){
        List<List<String>> res = new ArrayList<>();
        if(strs.length == 0){
            return res;
        }

        HashMap<String, ArrayList<String>> hm = new HashMap<>();
        for(String str: strs){
            int [] count = new int[26];
            for(int i=0; i<str.length(); i++) {
                count[str.charAt(i)-'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<count.length; i++) {
                sb.append(count[i]).append("#");
            }
            if(hm.containsKey(sb.toString())) {
                hm.get(sb.toString()).add(str);
            } else {
                hm.put(sb.toString(), new ArrayList<>().add(str));
            }
        }
        for(List<String> list : hm.values()){
            res.add(list);
        }
        return res;
    }
}
