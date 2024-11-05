#include <unordered_map>
#include <iostream>

using namespace std;

class LRUCache
{
private:
    unordered_map<int, int> cache;
    unordered_map<int, int> priority;
    // max-heap
    priority_queue<pair<int, int>, vector<pair<int, int>>, less<pair<int, int>>> pq;
    int capacity, rank;

public:
    LRUCache(int capacity)
    {
        this->capacity = capacity;
        rank = INT_MAX;
        cache.clear();
        priority.clear();
    }

    int get(int key)
    {
        if (cache.count(key) == 0)
        {
            return -1;
        }
        else
        {
            priority[key] = rank--;
            priority.emplace(key, priority[key]);
            pq.emplace(priority[key], key);
            return cache[key];
        }
    }

    void put(int key, int value)
    {
        cache[key] = value;
        priority[key] = rank--;
        pq.emplace(priority[key], key);
        while (cache.size() > capacity)
        {
            pair<int, int> top = pq.top();
            while (!pq.empty() && priority[top.second] != top.first)
            {
                pq.pop();
                top = pq.top();
            }
            pq.pop();
            cache.erase(top.second);
            priority.erase(top.second);
        }
    }
};