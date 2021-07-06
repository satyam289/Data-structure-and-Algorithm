#include <algorithm>
#include <array>
#include <iostream>
#include <vector>

const int mx = 1e6 + 5;
using ll = int64_t;

std::array<ll, mx> parent;
ll node, edge;
std::vector<std::pair<ll, std::pair<ll, ll>>> edges;

void initial()
{
    for (int i = 0; i < node + edge; ++i)
    {
        parent[i] = i;
    }
}

int root(int i)
{
    while (parent[i] != i)
    {
        parent[i] = parent[parent[i]];
        i = parent[i];
    }
    return i;
}

// Disjoint set union by rank
void join(int x, int y)
{
    int root_x = root(x);
    int root_y = root(y);
    parent[root_x] = root_y;
}

ll kruskal()
{
    ll mincost = 0;
    for (int i = 0; i < edge; ++i)
    {
        ll x = edges[i].second.first;
        ll y = edges[i].second.second;
        if (root(x) != root(y))
        {
            mincost += edges[i].first;
            join(x, y);
        }
    }
    return mincost;
}

int main()
{
    while (true)
    {
        int from = 0, to = 0, cost = 0, totalcost = 0;
        std::cin >> node >> edge; // input the nodes and edges
        if (node == 0 && edge == 0)
        {
            break;
        }
        initial();
        for (int i = 0; i < edge; ++i)
        {
            std::cin >> from >> to >> cost;
            edges.emplace_back(make_pair(cost, std::make_pair(from, to)));
            totalcost += cost;
        }
        sort(edges.begin(), edges.end());
        std::cout << kruskal() << std::endl;
        edges.clear();
    }
    return 0;
}

/*** Java Implementation
package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import graph.Graph2.Edge;
import graph.Graph2.Vertex2;

class Kruskal {
    public class EdgeComparator implements Comparator<Edge<Integer>> {
        @Override
        public int compare(Edge<Integer> edge1, Edge<Integer> edge2) {
            if (edge1.weight <= edge2.weight) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    public List<Edge<Integer>> getMST(Graph2<Integer> graph) {
        List<Edge<Integer>> allEdges = graph.getAllEdges();
        EdgeComparator edgeComparator = new EdgeComparator();

        // sort all edges in non decreasing order
        Collections.sort(allEdges, edgeComparator);
        DisjointSet disjointSet = new DisjointSet();

        for (Vertex2<Integer> vertex : graph.getAllVertex()) {
            disjointSet.makeSet(vertex.id);
        }
        List<Edge<Integer>> resultEdge = new ArrayList<Edge<Integer>>();
        
        for (Edge<Integer> edge : allEdges) {
            long root1 = disjointSet.findSet(edge.vertex1.id);
            long root2 = disjointSet.findSet(edge.vertex2.id);

            if (root1 == root2) {
                continue;
            } else {
                resultEdge.add(edge);
                disjointSet.union(edge.vertex1.id, edge.vertex2.id);
            }
        }
        return resultEdge;
    }
}
*/