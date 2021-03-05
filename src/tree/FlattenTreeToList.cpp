#include <iostream>

class FlattenTree{
    struct TreeNode
    {
        int val;
        TreeNode  *left;
        TreeNode  *right;
        TreeNode(int x) : val(x), left(NULL), right(NULL) {}
    };
   public:
        void flatten(TreeNode *root){
            if(!root)
               return;

            TreeNode *node = root;
            while(node){

                if(node->left){
                    TreeNode *rightMost = node->left;
                    while(rightMost->right){
                        rightMost = rightMost->right;
                    }
                    rightMost->right = node->right;

                    node->right = node->left;
                    node->left = NULL;
                }
                
                node = node->right;
            }     
        }
    
}

/*
Using space 0(N)
public TreeNode flatten(TreeNode a)
{
    Stack<TreeNode> st = new Stack<>();
    st.push(a);
    TreeNode head = st.peek();

    while (!st.isEmpty())
    {
        TreeNode curr = st.pop();
        if (curr.right != null)
        {
            st.push(curr.right);
        }
        if (curr.left != null)
        {
            st.push(curr.left);
        }
        if (st.size() > 0)
        {
            curr.right = st.peek();
        }
        curr.left = null;
    }
    return head;
}

Without using space (In-place)

public TreeNode flatten(TreeNode a) {
        flattenNodeRec(a);
        return a;
    }
    
    public void flattenNodeRec(TreeNode a){
        if(a == null){
            return;
        }
        if(a.left == null && a.right == null){
            return;
        }
        if(a.left != null){
            flattenNodeRec(a.left);
            TreeNode tmp = a.right;
            a.right = a.left;
            a.left = null;
            
            TreeNode curr = a.right;
            while(curr.right != null){
                curr = curr.right;
            }
            curr.right = tmp;
        }
        flattenNodeRec(a.right);
    }
*/