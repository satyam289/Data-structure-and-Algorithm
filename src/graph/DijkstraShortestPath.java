package graph;

import java.util.HashMap;
import java.util.Map;
import graph.Graph2.Edge;
import graph.Graph2.Vertex2;
import java.util.*;

public class DijkstraShortestPath {

    public <T> Map<Vertex2<T>, Integer> shortestPath(Graph2<T> graph, Vertex2<T> src) {

        BinaryMinHeap<Graph2.Vertex2<T>> minHeap = new BinaryMinHeap<>();
        Map<Vertex2<T>, Integer> distance = new HashMap<>();
        Map<Vertex2<T>, Vertex2<T>> parent = new HashMap<>();

        for (Vertex2<T> vertex : graph.getAllVertex()) {
            minHeap.add(Integer.MAX_VALUE, vertex);
        }

        minHeap.decrease(src, 0);
        distance.put(src, 0);
        parent.put(src, null);

        while (!minHeap.isEmpty()) {
            BinaryMinHeap<Vertex2<T>>.Node minheapNode = minHeap.extractMinNode();
            Graph2.Vertex2<T> current = minheapNode.key;
            distance.put(current, minheapNode.weight);

            for (Edge<T> edge : current.edges) {
                Vertex2<T> adjacent = getVertexForEdge(current, edge);
                if (!minHeap.containsData(adjacent)) {
                    continue;
                }
                int newDistance = distance.get(current) + edge.weight;
                if (minHeap.getWeight(adjacent) > newDistance) {
                    minHeap.decrease(adjacent, newDistance);
                    parent.put(adjacent, current);
                }
            }
        }
        return distance;
    }

    private <T> Vertex2<T> getVertexForEdge(Vertex2<T> v, Edge<T> e) {
        return e.vertex1.id == (v.id) ? e.vertex2 : e.vertex1;
    }
}

class Graph2<T> {

    static class Vertex2<T> {
        long id;
        public T data;
        public List<Edge<T>> edges = new ArrayList<>();
        public List<Vertex2<T>> adjacentVertex = new ArrayList<>();

        public Vertex2(long id) {
            this.id = id;
        }

        public void addAdjacentVertex(Edge<T> e, Vertex2<T> v) {
            edges.add(e);
            adjacentVertex.add(v);
        }

        public List<Vertex2<Obj>> getAllAdajecent() {
            return adjacentVertex;
        }
    }

    static class Edge<T> {
        public boolean isDirected = false;
        public Vertex2<T> vertex1;
        public Vertex2<T> vertex2;
        public int weight;

        Edge(Vertex2<T> vertex1, Vertex2<T> vertex2) { // for non weighted and undirected
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
        }

        Edge(Vertex2<T> vertex1, Vertex2<T> vertex2, boolean isDirected, int weight) { // weighted and directed
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.weight = weight;
            this.isDirected = isDirected;
        }

        Edge(Vertex2<T> vertex1, Vertex2<T> vertex2, boolean isDirected) { // directed
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.isDirected = isDirected;
        }
    }

    private List<Edge<T>> allEdges;
    private Map<Long, Vertex2<T>> allVertex;
    boolean isDirected; // directed and non-directed

    public Graph2(boolean isDirected) {
        allEdges = new ArrayList<>();
        allVertex = new HashMap<>();
        this.isDirected = isDirected;
    }

    public void addEdge(long id1, long id2) { // non weighted
        addEdge(id1, id2, 0);
    }

    public void addEdge(long id1, long id2, int weight) { // weighted
        Vertex2<T> vertex1;
        if (allVertex.containsKey(id1)) {
            vertex1 = allVertex.get(id1);
        } else {
            vertex1 = new Vertex2<>(id1);
            allVertex.put(id1, vertex1);
        }
        Vertex2<T> vertex2;
        if (allVertex.containsKey(id2)) {
            vertex2 = allVertex.get(id2);
        } else {
            vertex2 = new Vertex2<>(id2);
            allVertex.put(id2, vertex2);
        }
        Edge<T> edge = new Edge<>(vertex1, vertex2, isDirected, weight);
        allEdges.add(edge);
        vertex1.addAdjacentVertex(edge, vertex2); // adding adjacent vertex
        if (!isDirected) {
            vertex2.addAdjacentVertex(edge, vertex1);
        }
    }

    public void addVertex(Vertex2<T> vertex) {
        if (allVertex.containsKey(vertex.id)) {
            return;
        }
        allVertex.put(vertex.id, vertex);
        for (Edge<T> edge : vertex.edges) {
            allEdges.add(edge);
        }
    }

    public Vertex2<T> getVertex(long id) {
        return allVertex.get(id);
    }

    public List<Edge<T>> getAllEdges() {
        return Collections.unmodifiableList(allEdges);
    }

    public Collection<Vertex2<T>> getAllVertex() {
        return allVertex.values();
    }
}
