#include <iostream>

using namespace std;

struct ListNode { 
	int data; 
	struct ListNode* next; 
};

ListNode *removeNthFromEnd(ListNode *head, int b)
{
    if (head == NULL || b == 0)
    {
        return head;
    }
    ListNode *fast = head;
    ListNode *slow = head;
    int count = 1;
    while (fast != NULL && count != b)
    {
        fast = fast->next;
        count++;
    }
    if (fast == NULL)
    {
        head = head->next;
        return head;
    }
    ListNode *prev = NULL;
    while (fast->next != NULL)
    {
        prev = slow;
        slow = slow->next;
        fast = fast->next;
    }
    if (prev != NULL)
    {
        prev->next = slow->next;
    }
    else
    {
        head = slow->next;
    }
    return head;
}

/* package Linked;

public class RemoveNthNode {
    
    private static class ListNode{
        ListNode next;
        int data;
        public ListNode(int data){
            this.data = data;
            next = null;
        }
    }
    //https://www.interviewbit.com/problems/remove-nth-node-from-list-end/
    public ListNode removeNthFromEnd(ListNode A, int N) {

        if (A == null || N == 0) {
            return A;
        }

        ListNode fast = A;
        for (int i = 0; i < N; i++) {
            if (fast.next == null) {
                return A.next;
            } else {
                fast = fast.next;
            }
        }

        ListNode slow = A;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        ListNode temp = slow.next;
        slow.next = slow.next.next;
        temp.next = null;
        return A;
    }
}
 */