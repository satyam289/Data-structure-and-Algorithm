package tree;

// https://www.geeksforgeeks.org/total-number-of-possible-binary-search-trees-using-catalan-number/
public class NumberOfBST {

    //Dynamic Programming
    public static int getBinaryTreeNumber(int num) {

        if (num == 0 || num == 1) {
            return 1;
        }
        int[] T = new int[num+1];
        T[0] = 1;
        T[1] = 1;
        for (int i = 2; i < T.length; i++) {
            for (int j = 0; j < i; j++) {

                T[i] += T[j] * T[i - j - 1]; //catalin
            }
        }
        return T[num];
    }

    public static void main(String [] args){
        System.out.println(getBinaryTreeNumber(4));
    }

    /* Explaination
    ---------------
    when n=1 , arr = {10} 
     a)  10

    ans = 1
    ---------------
    when n = 2, arr = {10, 11}
     a)   11   b)  10 
        10           11

    ans = 2
    -------------- 
    when n = 3 arr = {10, 11, 12}
    a)10        b) 10      c)   11     d)    12   e)     12
       11             12      10  12       11        10 
         12         11                   10            11

    ans = 5
    */

    class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;
    }
    public boolean isvalid(TreeNode root){
        return isValidRec(root, null, null);
    }

    private boolean isValidRec(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }
        if (min != null || root.data <= min || max != null || root.data >= max) {
            return false;
        }
        return isValidRec(root.left, min, root.data) && isValidRec(root.right, root.data, max);
    }
}
