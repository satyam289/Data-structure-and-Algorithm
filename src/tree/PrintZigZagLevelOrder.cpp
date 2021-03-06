#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

struct TreeNode
{
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

vector<vector<int>> zigzagLevelOrder(TreeNode *A)
{
    vector<vector<int>> ans;
    queue<TreeNode *> q;
    q.push(A);
    bool flag = true;
    while (!q.empty())
    {

        int size = q.size();
        vector<int> temp;
        while (size--)
        {
            TreeNode *tp = q.front();
            q.pop();
            temp.push_back(tp->val);
            if (tp->left)
            {
                q.push(tp->left);
            }
            if (tp->right)
            {
                q.push(tp->right);
            }
        }
        if (flag)
        {
            ans.push_back(temp);
            flag = false;
        }
        else
        {
            flag = true;
            reverse(temp.begin(), temp.end());
            ans.push_back(temp);
        }
    }
    return ans;
}

/*** Java Implementaion ***
public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode A)
{
    ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
    if (A == null)
    {
        return result;
    }
    Queue<TreeNode> q = new LinkedList<>();
    q.add(A);
    q.add(null);
    boolean isReverse = false;
    while (q.size() > 1)
    {
        ArrayList<Integer> arr = new ArrayList<>();
        while (q.peek() != null) // ensure same level Node
        {
            TreeNode node = q.poll();
            if (isReverse)
            {
                arr.add(0, node.val);  // insert at 0 index(reverse order).
            }
            else
            {
                arr.add(node.val);
            }
            if (node.left != null)
            {
                q.add(node.left);
            }
            if (node.right != null)
            {
                q.add(node.right);
            }
        }
        result.add(arr);  // Either we reverse the list by above mechanism or Collections.reverse(arr) at alternate interval
        q.poll();
        q.add(null);
        isReverse = !isReverse;
    }
    return result;
}
*/