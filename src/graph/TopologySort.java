package graph;

import java.util.*;
import graph.Graph2.Vertex2;

/*
  For directed Acyclic Graph
 */
public class TopologySort<T> {

    public void doTopologySort(Graph2<T> graph) {
        Stack<Vertex2<T>> stack = new Stack<>(); // Deque<Vertex> deque = new ArrayDeque<>();
        Set<Vertex2<T>> visited = new HashSet<>();
        for (Vertex2<T> v : graph.getAllVertex()) { // covering all the vertex
            topologicalSortUtil(v, stack, visited);
        }
        while (!stack.empty()) { // printing
            System.out.println(stack.pop().data);
        }
    }

    public void topologicalSortUtil(Vertex2<T> vertex, Stack<Vertex2<T>> stack, Set<Vertex2<T>> visited) {
        visited.add(vertex);
        List<Graph2.Vertex2<T>> list = vertex.getAllAdajecent();
        for (Vertex2<T> adj : list) {
            if (visited.contains(adj)) { // skip if vertex is already visited
                continue;
            }
            topologicalSortUtil(adj, stack, visited);
        }
        stack.add(vertex); // add only when all its adjacent vertex is visited
    }
}
