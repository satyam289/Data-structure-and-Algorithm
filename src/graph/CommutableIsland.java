package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//https://www.interviewbit.com/problems/commutable-islands/
class CommutableIsland {

    private int find(int u, int[] parent) {
        if (u != parent[u]) {
            parent[u] = find(parent[u], parent);
        }
        return parent[u];
    }

    private boolean union(int u, int v, int[] parent, int[] rank) {
        int rootU = find(u, parent);
        int rootV = find(v, parent);
        if (rootU == rootV)
            return false;
        if (rank[rootU] > rank[rootV]) {
            parent[rootV] = rootU;
        } else {
            parent[rootU] = rootV;
            if (rank[rootU] == rank[rootV])
                rank[rootV] += 1;
        }
        return true;
    }

    public int solve(int A, ArrayList<ArrayList<Integer>> B) {
        int totalCost = 0;
        Collections.sort(B, (a, b) -> a.get(2) - b.get(2));
        int[] parent = new int[A + 1];
        int[] rank = new int[A + 1];
        for (int i = 1; i <= A; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
        for (List<Integer> li : B) {
            int u = li.get(0);
            int v = li.get(1);
            int cost = li.get(2);
            if (this.union(u, v, parent, rank)) {
                totalCost += cost;
            }
        }
        return totalCost;
    }
}