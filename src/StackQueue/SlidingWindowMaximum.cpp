#include <vector>
#include <deque>

using namespace std;

vector<int> slidingMaximum(vector<int> &A, int win)
{
    int n = A.size();
    vector<int> res;
    if (n < win)
    {
        return res;
    }
    res.resize(n - win + 1);
    deque<int> *q;

    for (int i = 0; i < win; i++)
    {
        while (!q->empty() && A[i] >= A[q->back()])
        {
            q->pop_back();
        }
        q->push_back(i);
    }
    res.push_back(q->front());

    for (int i = win; i < n; i++)
    {
        while (!q->empty() && q->front() <= i - win)
            q->pop_front();
        while (!q->empty() && A[i] >= A[q->back()])
            q->pop_back();
        q->push_back(i);
        res[i - win] = q->front();
    }
    return res;
}

/* package StackQueue;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

// https://www.interviewbit.com/problems/sliding-window-maximum/
public class SlidingWindowMaximum {
    
    public ArrayList<Integer> slidingMaximum(final int[] A, int win_size) {
        Deque<Integer> dq = new LinkedList<Integer>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        int i;
        
        for (i = 0; i < win_size; i++) {
            while (!dq.isEmpty() && A[i] >= A[dq.peekLast()]) {
                dq.removeLast();
            }
            dq.addLast(i);
        }
        result.add(A[dq.peek()]);

        if (win_size == A.length) {
            return result;
        }

        for (; i < A.length; i++) { 
            while (!dq.isEmpty() && dq.peek() <= i - win_size) {
                dq.removeFirst();
            }
            while (!dq.isEmpty() && A[dq.peekLast()] <= A[i]) {
                dq.removeLast();
            }
            dq.addLast(i);
            result.add(A[dq.peek()]);
        }
        return result;
    }

        public void printMaxAndMinKSubArray(final int[] A, int k) {
        Deque<Integer> q1 = new LinkedList<>();
        Deque<Integer> q2 = new LinkedList<>();

        for (int i = 0; i < k; i++) {
            while (!q1.isEmpty() || A[q1.peekLast()] < A[i]) {
                q1.removeLast();
            }
            while (!q2.isEmpty() || A[q2.peekLast()] > A[i]) {
                q2.removeLast();
            }
            q1.addLast(A[i]);
            q2.addLast(A[i]);
        }
        for (int i = k; i < A.length; i++) {

            while (!q1.isEmpty() || A[q1.peekFirst()] < i - k) {
                q1.removeFirst();
            }
            while (!q2.isEmpty() || A[q2.peekFirst()] > i - k) {
                q2.removeFirst();
            }
            while (!q1.isEmpty() || A[q1.peekLast()] < A[i]) {
                q1.removeLast();
            }
            while (!q2.isEmpty() || A[q2.peekLast()] > A[i]) {
                q2.removeLast();
            }
            q1.addLast(i);
            q2.addLast(i);
            System.out.println(q1.peekFirst());
            System.out.println(q2.peekFirst());
        }
    }
}
 */