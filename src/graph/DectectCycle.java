package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import graph.Graph2.Edge;
import graph.Graph2.Vertex2;

//https://www.geeksforgeeks.org/detect-cycle-in-a-graph/
//https://www.geeksforgeeks.org/detect-cycle-undirected-graph/
public class DectectCycle {

    class DirectedGraph {

        public <T> boolean hasCycle(Graph2<Integer> graph) {

            Set<Vertex2<Integer>> whiteSet = new HashSet<>();
            Set<Vertex2<Integer>> graySet = new HashSet<>();
            Set<Vertex2<Integer>> balckSet = new HashSet<>();

            for (Vertex2<Integer> ver : graph.getAllVertex()) {
                whiteSet.add(ver);
            }

            while (whiteSet.size() > 0) {
                Vertex2<Integer> startvertex = whiteSet.iterator().next();
                if (dfs(startvertex, whiteSet, graySet, balckSet)) {
                    return true;
                }
            }
            return false;
        }

        private boolean dfs(Vertex2<Integer> currvertex, Set<Vertex2<Integer>> whiteSet, Set<Vertex2<Integer>> graySet,
                Set<Vertex2<Integer>> blackSet) {

            moveVertex(currvertex, whiteSet, graySet);

            for (Vertex2<Integer> neighbour : currvertex.getAllAdajecent()) {
                if (blackSet.contains(neighbour)) {
                    continue;
                }
                if (graySet.contains(neighbour)) {
                    return true;
                }
                if (dfs(neighbour, whiteSet, graySet, blackSet)) {
                    return true;
                }
            }
            moveVertex(currvertex, graySet, blackSet);
            return false;
        }

        private void moveVertex(Vertex2<Integer> vertex, Set<Vertex2<Integer>> sourceSet,
                Set<Vertex2<Integer>> destinationSet) {
            sourceSet.remove(vertex);
            destinationSet.add(vertex);
        }

        // https://www.interviewbit.com/old/problems/cycle-in-directed-graph/
        public int solve(int n, int[][] B) {
            HashMap<Integer, ArrayList<Integer>> adj = new HashMap<>();
            for (int[] edge : B) {
                if (adj.containsKey(edge[0])) {
                    adj.get(edge[0]).add(edge[1]);
                } else {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(edge[1]);
                    adj.put(edge[0], list);
                }
            }
            boolean[] visited = new boolean[n + 1];
            for (int i = 1; i <= n; i++) {
                if (visited[i]) {
                    continue;
                }
                if (dfsCycle(i, adj, visited)) {
                    return 1;
                }
            }
            return 0;
        }

        private boolean dfsCycle(int currVer, HashMap<Integer, ArrayList<Integer>> adj, boolean[] visited) {
            if (adj.get(currVer) == null) {
                return false;
            }
            visited[currVer] = true;

            for (int neigh : adj.get(currVer)) {
                if (visited[neigh] || dfsCycle(neigh, adj, visited)) {
                    return true;
                }
            }
            visited[currVer] = false;
            return false;
        }
    }

    class UndirecredGraph {

        public <T> boolean hasCycle(Graph2<T> graph) {

            DisjointSet ds = new DisjointSet();
            for (Vertex2<T> vertex : graph.getAllVertex()) {
                ds.makeSet(vertex.id);
            }
            for (Edge<T> edge : graph.getAllEdges()) {
                if (!ds.union(edge.vertex1.id, edge.vertex2.id)) {
                    return true;
                }
            }
            return false;
        }

        public <T> boolean hasCycleDFS(Graph2<T> graph) {
            Set<Vertex2<T>> visited = new HashSet<>();
            for (Vertex2<T> vertex : graph.getAllVertex()) {
                if (visited.contains(vertex)) {
                    continue;
                }
                if (hasDFSUtil(vertex, visited, null)) { // root node has null parent
                    return true;
                }
            }
            return false;
        }

        private <T> boolean hasDFSUtil(Vertex2<T> vertex, Set<Vertex2<T>> visited, Vertex2<T> parentVer) {
            visited.add(vertex);
            for (Vertex2<T> adjVer : vertex.getAllAdajecent()) {
                if (adjVer.equals(parentVer)) {
                    continue;
                }
                if (visited.contains(adjVer)) {
                    return true;
                }
                if (hasDFSUtil(adjVer, visited, vertex)) {
                    return true;
                }
            }
            return false;
        }

        // https://www.interviewbit.com/old/problems/cycle-in-undirected-graph/
        public int solve(int N, int[][] B) {
            if (N == 2) {
                return 0;
            }
            int[] parent = new int[N + 1];
            int[] rank = new int[N + 1];
            Arrays.fill(rank, 1);
            for (int i = 0; i <= N; i++) {
                parent[i] = i;
            }
            for (int[] edge : B) {
                if (isUnion(edge[0], edge[1], parent, rank)) {
                    return 1;
                }
            }
            return 0;
        }

        private boolean isUnion(int u, int v, int[] parent, int[] rank) {
            int pu = find(u, parent);
            int pv = find(v, parent);
            if (pu == pv) {
                return true;
            }
            if (rank[pu] > rank[pv]) {
                parent[pv] = pu;
            } else {
                parent[pu] = pv;
                if (rank[pu] == rank[pv]) {
                    rank[pv] = rank[pv] + 1;
                }
            }
            return false;
        }

        private int find(int u, int[] parent) {
            if (u != parent[u]) {
                parent[u] = find(parent[u], parent);
            }
            return parent[u];
        }
    }
}
