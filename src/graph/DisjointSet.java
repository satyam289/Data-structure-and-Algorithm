package graph;

import java.util.HashMap;

import java.util.Map;

public class DisjointSet {

    private class Node {
        long data;
        int rank;
        Node parent;
    }

    private Map<Long, Node> map = new HashMap<>();

    public void makeSet(long data) {
        Node node = new Node();
        node.data = data;
        node.parent = node;
        node.rank = 0;
        map.put(data, node);
    }

    public boolean union(long data1, long data2) {
        Node node1 = map.get(data1);
        Node node2 = map.get(data2);

        Node parent1 = findSet(node1);
        Node parent2 = findSet(node2);
        if (parent1.data == parent2.data) {
            return false;
        }
        // whoever's rank is higher becomes parent of other
        if (parent1.rank >= parent2.data) {
            if (parent1.rank == parent2.rank) {
                parent1.rank = parent1.rank + 1;
            }
            parent2.parent = parent1;
        } else {
            parent1.parent = parent2;
        }
        return true;
    }

    public long findSet(long data) {
        return findSet(map.get(data)).data;
    }

    private Node findSet(Node node) {
        Node parent = node.parent;
        if (parent == node) {
            return parent;
        }
        node.parent = findSet(node.parent); // path compression
        return node.parent;
    }
}

/*
/**
 * A disjoint set data structure (also called union find or merge find set)
 * is a data structure that tracks a set of elements partitioned into a number of disjoint (non-overlapping) subsets.
 * Some situations where disjoint sets can be used are- to find connected components of a graph, kruskal's algorithm for finding Minimum Spanning Tree etc.
 *

#include <iostream>
#include <vector>

using std::cout;
using std::endl;
using std::vector;

vector<int> root, rank;

/**
 *
 * Function to create a set
 * @param n number of element
 *
 *
void CreateSet(int n) {
    root = vector<int>(n + 1);
    rank = vector<int>(n + 1, 1);
    for (int i = 1; i <= n; ++i) {
        root[i] = i;
    }
}

/**
 *
 * Find operation takes a number x and returns the set to which this number
 * belongs to.
 * @param x element of some set
 * @return set to which x belongs to
 *
 *
int Find(int x) {
    if (root[x] == x) {
        return x;
    }
    return root[x] = Find(root[x]);
}

/**
 *
 * A utility function to check if x and y are from same set or not
 * @param x element of some set
 * @param y element of some set
 *
 *
bool InSameUnion(int x, int y) { return Find(x) == Find(y); }

/**
 *
 * Union operation combines two disjoint sets to make a single set
 * in this union function we pass two elements and check if they are
 * from different sets then combine those sets
 * @param x element of some set
 * @param y element of some set
 *
 *
void Union(int x, int y) {
    int a = Find(x), b = Find(y);
    if (a != b) {
        if (rank[a] < rank[b]) {
            root[a] = b;
        } else if (rank[a] > rank[b]) {
            root[b] = a;
        } else {
            root[a] = b;
            ++rank[b];
        }
    }
}

int main() {
    int n = 100;
    CreateSet(n);
    for (int i = 1; i <= 100; ++i) {
        if (root[i] != i) {
            cout << "Fail" << endl;
            break;
        }
    }
    if (InSameUnion(1, 2)) {
        cout << "Fail" << endl;
    }
    Union(1, 2);
    if (!InSameUnion(1, 2)) {
        cout << "Fail" << endl;
    }
    return 0;
} 
*/