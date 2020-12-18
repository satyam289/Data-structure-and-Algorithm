package graph;

import java.util.*;

public class Graph2<T> {

    public static class Vertex2<T> {
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
    }

    public static class Edge<T> {
        public boolean isDirected = false;
        public Vertex2<T> vertex1;
        public Vertex2<T> vertex2;
        public int weight;

        Edge(Vertex2<T> vertex1, Vertex2<T> vertex2) {   // for non weighted and undirected
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
        }

        Edge(Vertex2<T> vertex1, Vertex2<T> vertex2, boolean isDirected, int weight) {  // weighted and directed
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.weight = weight;
            this.isDirected = isDirected;
        }

        Edge(Vertex2<T> vertex1, Vertex2<T> vertex2, boolean isDirected) {  // directed
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.isDirected = isDirected;
        }
    }

    private List<Edge<T>> allEdges;
    private Map<Long, Vertex2<T>> allVertex;
    boolean isDirected;               // directed and non-directed

    public Graph2(boolean isDirected) {
        allEdges = new ArrayList<>();
        allVertex = new HashMap<>();
        this.isDirected = isDirected;
    }

    public void addEdge(long id1, long id2) {      // non weighted
        addEdge(id1, id2, 0);
    }

    public void addEdge(long id1, long id2, int weight) {    // weighted
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
        vertex1.addAdjacentVertex(edge, vertex2);   // adding adjacent vertex
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
