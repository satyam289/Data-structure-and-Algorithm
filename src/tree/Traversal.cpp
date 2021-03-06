#include <stack>
#include <algorithm>
#include <vector>

using namespace std;

class Traversal
{
    struct TreeNode
    {
        int val;
        TreeNode *left;
        TreeNode *right;
        TreeNode(int x) : val(x), left(NULL), right(NULL) {}
    };

public:
    vector<int> postOrderTraversal(TreeNode *root)
    {
        stack<TreeNode *> *st;
        vector<int> result;

        if (root == NULL)
        {
            return result;
        }

        st->push(root);
        while (!st->empty())
        {
            TreeNode *curr = st->top();
            result.push_back(curr->val);
            st->pop();
            if (curr->left)
            {
                st->push(curr->left);
            }
            if (curr->right)
            {
                st->push(curr->right);
            }
        }
        // node-right-left  -> left-right-node
        reverse(result.begin(), result.end());
        return result;
    }
};

/***Java Impl pre-post-inorder traversal without using recursion**

ArrayList<Integer> preorderTraversal(TreeNode A){

    ArrayList<Integer> arr = new ArrayList<Integer>();
    Stack<TreeNode> st = new Stack<>();
    st.push(A);
    while (!st.isEmpty())
    {
        TreeNode node = st.pop();
        arr.add(node.val);
        if (node.right != null)
        {
            st.push(node.right);
        }
        if (node.left != null)
        {
            st.push(node.left);
        }
    }
    return arr;
}

void postOrder (TreeNode root)
{
    Stack s1 = new Stack();
    Stack s2 = new Stack();
 
    s1.push (root);
    while (s1.empty() == false)
    {
        TreeNode node = s1.pop();
        s2.push (node);
 
        if (node.left)
            s1.push (node.left); // pre-order pushes right first
        if (node.right)
            s1.push (node.right); //post-order pushes left in the last
    }
 
    while (s2.empty() == false) // reverse to get the post-order
        print (s2.pop());
}

void inorder(TreeNode node){

    Stack<TreeNode> st  = new Stack<>();
    TreeNode curr = node;
    while (true) {
        while(curr!=null) {  
            st.push(curr);  // first push current element (either left or right)
            curr = curr.left;  // keep pushing leftmost element in the stack
        }

        if(st.isEmpty()){
            break;
        }
        TreeNode node = st.pop(); 
        print(node.data);   
        curr = node.right;   // Till stack only contain left subtree from root, next time it should navigate right sub tree before visiting parent
    }
}

*/

