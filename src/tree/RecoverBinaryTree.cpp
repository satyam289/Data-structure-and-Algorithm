#include <vector>

using namespace std;

class RecoverBinaryTree
{
    struct TreeNode
    {
        int val;
        TreeNode *left;
        TreeNode *right;
        TreeNode(int x) : val(x), left(NULL), right(NULL) {}
    };

    void inorder(TreeNode *root, int &first, int &last, int &prev)
    {
        if (root == NULL)
            return;
        inorder(root->left, first, last, prev);
        if (prev != -1 && prev > root->val)
        {
            if (first == -1)
            {
                first = prev;
            }
            last = root->val;
        }
        prev = root->val;
        inorder(root->right, first, last, prev);
    }
    vector<int> recoverTree(TreeNode *A)
    {
        if (A == NULL)
            return {};
        int firstVal = -1;
        int lastVal = -1;
        int prev = -1;
        inorder(A, firstVal, lastVal, prev);
        return {lastVal, firstVal};
    }
};

/* Java Impl
// O(n) space solution
public class Solution {
    public void inOrder(TreeNode root, ArrayList<Integer> list){
        if(root == null){
            return;
        }
        inOrder(root.left, list);
        list.add(root.val);
        inOrder(root.right, list);
    }
    public ArrayList<Integer> recoverTree(TreeNode A) {
        ArrayList<Integer> list = new ArrayList<>();
        inOrder(A, list);
        int first = 0;
        int second = 0;
        int index = 0;
        for(int i=1; i<list.size(); i++){
            if(list.get(i) < list.get(i-1)){
                first = list.get(i-1);
                second = list.get(i);
                index = i;
                break;
            }
        }
        for(int i=index+1; i<list.size(); i++){
            if(list.get(i) < list.get(i-1)){
                second = list.get(i);
                break;
            }
        }
        if(first > second){
            int temp = second;
            second = first;
            first = temp;
        }
        return new ArrayList<>(Arrays.asList(first, second));
    }
}
*/