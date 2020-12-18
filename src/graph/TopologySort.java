package graph;


import java.util.*;

/*
  For directed Acyclic Graph
 */

public class TopologySort<T> {

    public void doTopologySort(Graph2<T> graph) {
        Stack<Vertex2<T>> stack = new Stack<>();         // Deque<Vertex> deque = new ArrayDeque<>();
        Set<Vertex2<T>> visited = new HashSet<>();
        for (Vertex2<T> v : graph.getAllVertex()) {      //covering all the vertex
            topologicalSortUtil(v, stack, visited);
        }
        while (!stack.empty()) {         // printing
            System.out.println(stack.pop().data);
        }
    }

    public void topologicalSortUtil(Vertex2<T> vertex, Stack<Vertex2<T>> stack, Set<Vertex2<T>> visited) {
        visited.add(vertex);
        for (Vertex2<T> adj : vertex.getAllAdajecent()) {
            if (visited.contains(adj)) {                     //skip if vertex is already visited
                continue;
            }
            topologicalSortUtil(adj, stack, visited);
        }
        stack.add(vertex);                             // add only when all its adjacent vertex is visited
    }


    public class Graph2<E> {

        //private List<Edge2<E>> allEdges;
        private Map<Long, Vertex2<E>> allvertex;
        boolean isDirected;

        Graph2(boolean isDirected) {
            allvertex = new HashMap<>();
            this.isDirected = isDirected;
        }

        public List<Vertex2<E>> getAllVertex() {
            return (List<Vertex2<E>>) allvertex.values();

        }
    }

    class Vertex2<Obj> {
        private T data;
        //private List<Edge2<T>> edges = new ArrayList<>();
        private List<Vertex2<Obj>> adjacentVertex = new ArrayList<>();

        public List<Vertex2<Obj>> getAllAdajecent() {
            return adjacentVertex;
        }
    }

    class Edge2<Obj> {
        //private boolean isDirected;
        //private Vertex2<T> vertex1;
        //private Vertex2<T> vertex2;
        //private int weight;
    }
}




