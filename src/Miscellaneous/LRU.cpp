#include <iostream>
#include <unordered_map>
#include <map>
using namespace std;

namespace MyQueue
{
    struct Node
    {
        int key;
        int val;
        struct Node *next;
        struct Node *pre;
        Node(int x, int y) : key(x), val(y), next(NULL), pre(NULL) {}
    } typedef Node;

    void add(int key, int val)
    {
        Node *newNode = new Node(key, val);
        newNode->next = head->next;
        newNode->pre = head;
        head->next = newNode;
        newNode->next->pre = newNode;
    }

    void remove(Node *A)
    {
        A->pre->next = A->next;
        A->next->pre = A->pre;
    }

    Node *head = NULL;
    Node *tail = NULL;
}

using namespace MyQueue;
class LRUCache
{

    unordered_map<int, Node *> mp;
    int capacity;

    LRUCache(int capacity)
    {
        this->capacity = capacity;
        mp.clear();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head->next = tail;
        tail->pre = head; //pointing to each other (head <--> tail)
    }

    int LRUCache::get(int key)
    {
        if (mp.find(key) != mp.end())
        {
            Node *node = mp[key];
            int x = node->val;
            mp.erase(key);
            remove(node);
            add(key, x);
            mp[key] = head->next;
            return x;
        }
        return -1;
    }

    void LRUCache::set(int key, int value)
    {
        if (mp.find(key) != mp.end())
        {
            Node *node = mp[key];
            remove(node);
            mp.erase(key);
            add(key, value);
            mp[key] = head->next;
        }
        else
        {
            if (mp.size() < capacity)
            {
                add(key, value);
                mp[key] = head->next;
            }
            else
            {
                mp.erase(tail->pre->key); // delete just before tail node
                remove(tail->pre);
                add(key, value);
                mp[key] = head->next;
            }
        }
    }
};

#include <list>

class LRU2
{
    map<int, int> mp2;
    int cap;
    list<int> lst;
    LRU2(int capacity)
    {
        mp2.clear();
        cap = capacity;
        lst.clear();
    }

    int LRU2::get(int key)
    {
        if (mp2.find(key) == mp2.end())
        {
            return -1;
        }
        lst.remove(key);
        lst.push_back(key);
        return mp2[key];
    }

    void LRU2::set(int key, int value)
    {
        if (cap == 0)
            return;

        if (mp2.find(key) != mp2.end())
        {
            lst.remove(key);
            lst.push_back(key);
            mp2[key] = value;
            return;
        }
        if (lst.size() < cap)
        {
            lst.push_back(key);
            mp2[key] = value;
            return;
        }
        map<int, int>::iterator it = mp2.find(lst.front());
        mp2.erase(it);
        lst.pop_front();
        lst.push_back(key);
        mp2[key] = value;
    }
};

class LRU3
{
    struct QueueNode
    {
        int key;
        int value;
        struct QueueNode *prev;
        struct QueueNode *next;
    };

    struct Queue
    {
        int capacity;
        int count;
        struct QueueNode *front;
        struct QueueNode *rear;
    };

    struct Hash
    {
        int size;
        struct QueueNode **array;
    };

    struct Queue *createQueue(int c)
    {
        struct Queue *q = (struct Queue *)malloc(sizeof(struct Queue));
        q->capacity = c;
        q->count = 0;
        q->front = q->rear = NULL;
        return q;
    };

    struct Hash *createHash(int s)
    {
        struct Hash *h = (struct Hash *)malloc(sizeof(struct Hash));
        h->size = s;
        h->array = (struct QueueNode **)malloc(s * sizeof(Hash*));
        int i;
        for (int i = 0; i < s; i++)
        {
            h->array[i] = NULL;
        }
        return h;
    }

    struct QueueNode *newNode(int key, int value)
    {
        struct QueueNode *node = (struct QueueNode *)malloc(sizeof(struct QueueNode));
        node->key = key;
        node->value = value;
        node->prev = NULL;
        node->next = NULL;
        return node;
    }

    int isEmpty(struct Queue *q)
    {
        return q->rear == NULL;
    }
    int isFull(struct Queue *q)
    {
        return q->count == q->capacity;
    }

    void deQueue(struct Queue *q)
    {
        if (isEmpty(q))
        {
            return;
        }
        struct QueueNode *temp = q->rear;
        if (q->front == q->rear)
        {
            q->front = NULL;
        }
        if (temp->prev)
        {
            temp->prev->next = NULL;
        }
        q->rear = q->rear->prev;

        q->count--;
        free(temp);
    }

    struct Queue* que;
    struct Hash* has;

    void enqueue(struct Queue *q, int key, int value)
    {
        if (isFull(q))
        {
            has->array[q->rear->key] = NULL;
            deQueue(q);
        }
        struct QueueNode *node = newNode(key, value);
        if (isEmpty(que))
        {
            q->front = q->rear = node;
        }
        else
        {
            node->next = q->front;
            q->front->prev = node;
            q->front = node;
        }
        has->array[key] = node;
        q->count++;
    }

    void moveNodeToFront(struct QueueNode *node, struct Queue *q)
    {
        node->prev->next = node->next;
        if (node->next)
        {
            node->next->prev = node->prev;
        }
        if (node == que->rear)
        {
            que->rear = node->prev;
        }
        node->next = que->front;
        que->front->prev = node;
        node->prev = NULL;
        que->front = node;
    }
    

    LRU3(int capacity)
    {
        if (que != NULL)
        {
            free(que);
        }
        if (has != NULL)
        {
            free(has);
        }
        que = createQueue(capacity);
        has = createHash(1000);
    }

    int LRU3::get(int key)
    {
        struct QueueNode *node = has->array[key];
        if (node != NULL)
        {
            int val = node->value;
            if (node != que->front)
            {
                moveNodeToFront(node, que);
            }
            return val;
        }
        else
        {
            return -1;
        }
    }

    void LRU3::set(int key, int value)
    {
        struct QueueNode *node = has->array[key];
        if (node == NULL)
        {
            enqueue(que, key, value);
        }
        else
        {
            node->value = value;
            if (node != que->front)
            {
                moveNodeToFront(node, que);
            }
        }
    }
};

/*
package Miscellaneous;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRU {

    int capacity;
    Map<Integer, Integer> map = new HashMap<>();
    LinkedList<Integer> list = new LinkedList<>();
    
    LRU(int capacity){
        this.capacity = capacity;
    }

    public int get(int key){
        if(map.containsKey(key)){
            list.remove(list.indexOf(key));
            list.addLast(key);
            return map.get(key);
        }
        return -1;
    }
    
    public void set(int key, int value) {
        if(map.containsKey(key)) {
            map.put(key, value);
            list.remove(list.indexOf(value));
            list.addLast(key);
        } else if(map.size() < capacity) {
            map.put(key, value);
            list.addLast(key);
        } else {
            int temp = list.removeFirst();
            map.remove(temp);

            map.put(key, value);
            list.addLast(key);
        }
    }   
}

class LRU2 {

    LinkedHashMap<Integer, Integer> cache;

    LRU2(int capacity) {
        cache = new LinkedHashMap<Integer, Integer>(capacity, 100, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        if (cache.containsKey(key))
            return cache.get(key);
        return -1;
    }

    public void set(int key, int value) {
        cache.put(key, value);
    }
}
*/