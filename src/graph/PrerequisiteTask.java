package graph;

import java.util.ArrayList;

//https://www.geeksforgeeks.org/find-whether-it-is-possible-to-finish-all-tasks-or-not-from-given-dependencies/
//https://www.interviewbit.com/problems/possibility-of-finishing-all-courses-given-prerequisites/
public class PrerequisiteTask {

    // DFS
    public boolean canComplete1(int numTask, int[][] prerequisite) {
        ArrayList<ArrayList<Integer>> graph = make_graph(numTask, prerequisite);
        boolean[] path = new boolean[numTask];
        boolean[] visited = new boolean[numTask];
        for (int i = 0; i < numTask; i++) {
            if (!visited[i] && dfs_cycle(graph, i, path, visited)) {
                return false;
            }
        }
        return true;
    }

    private ArrayList<ArrayList<Integer>> make_graph(int numTask, int[][] prerequisite) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < numTask; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] pre : prerequisite) {
            graph.get(pre[1]).add(pre[0]);
        }
        return graph;
    }

    private boolean dfs_cycle(ArrayList<ArrayList<Integer>> graph, int node, boolean[] path, boolean[] visited) {
        if (visited[node]) {
            return true;
        }
        path[node] = true;
        visited[node] = true;
        for (int children : graph.get(node)) {
            if (path[children] || dfs_cycle(graph, children, path, visited)) {
                return true;
            }
        }
        path[node] = false;
        return false;
    }

    // BFS
    public boolean canComplete2(int numTask, int[][] prerequisite) {
        ArrayList<ArrayList<Integer>> graph = make_graph(numTask, prerequisite);
        int[] vertexCount = vertexfreq(graph);

        for (int i = 0; i < numTask; i++) {
            int j = 0;
            for (; j < numTask; j++) {
                if (vertexCount[i] == 0) {
                    break;
                }
            }
            if (j == numTask)
                return false;

            vertexCount[j] = -1;
            for (int child : graph.get(j)) {
                vertexCount[child]--;
            }
        }
        return true;
    }

    private int[] vertexfreq(ArrayList<ArrayList<Integer>> graph) {
        int[] vertexCount = new int[graph.size()];
        for (ArrayList<Integer> children : graph) {
            for (int child : children) {
                vertexCount[child]++;
            }
        }
        return vertexCount;
    }
}
