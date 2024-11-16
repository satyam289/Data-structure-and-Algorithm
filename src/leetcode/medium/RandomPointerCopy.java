package leetcode.medium;

import java.util.*;

public class RandomPointerCopy {
    class RandomNode {
        int label;
        RandomNode next, random;

        RandomNode(int x) {
            this.label = x;
        }
    }

    public RandomNode copyRandomList(RandomNode head) {
        if (head == null)
            return null;

        RandomNode p = head;
        // copy every node and insert to list
        while (p != null) {
            RandomNode copy = new RandomNode(p.label);
            copy.next = p.next;
            p.next = copy;
            p = copy.next;
        }
        // copy random pointer for each new node
        p = head;
        while (p != null) {
            if (p.random != null) {
                p.next.random = p.random.next;
            }
            p = p.next.next;
        }
        // break list to two
        p = head;
        RandomNode newHead = head.next;
        while (p != null) {
            RandomNode tmp = p.next;
            p.next = tmp.next;
            if (tmp.next != null) {
                tmp.next = tmp.next.next;
            }
            p = p.next;
        }
        return newHead;
    }

    public RandomNode copRandomNode2(RandomNode head) {
        if (head == null)
            return null;

        HashMap<RandomNode, RandomNode> map = new HashMap<>();
        RandomNode newHead = new RandomNode(head.label);

        RandomNode p = head;
        RandomNode q = newHead;
        map.put(head, newHead);

        p = p.next;
        while (p != null) {
            RandomNode temp = new RandomNode(p.label);
            map.put(p, temp);
            q.next = temp;
            q = temp;
            p = p.next;
        }

        p = head;
        q = newHead;
        while (p != null) {
            if (p.random != null)
                q.random = map.get(p.random);
            else
                q.random = null;

            p = p.next;
            q = q.next;
        }
        return newHead;
    }
}
