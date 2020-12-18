package graph;

import java.util.HashMap;
import java.util.Map;

public class DijkstraShortestPath {

    public <T> Map<Graph2.Vertex2<T>, Integer> shortestPath(Graph2<T> graph, Graph2.Vertex2<T> src) {

        BinaryMinHeap<Graph2.Vertex2<T>> minHeap = new BinaryMinHeap<>();
        Map<Graph2.Vertex2<T>, Integer> distance = new HashMap<>();
        Map<Graph2.Vertex2<T>, Graph2.Vertex2<T>> parent = new HashMap<>();

        for (Graph2.Vertex2<T> vertex : graph.getAllVertex()) {
            minHeap.add(Integer.MAX_VALUE, vertex);
        }

        minHeap.decrease(src, 0);
        distance.put(src, 0);
        parent.put(src, null);

        while (!minHeap.isEmpty()) {
            BinaryMinHeap<Graph2.Vertex2<T>>.Node minheapNode = minHeap.extractMinNode();
            Graph2.Vertex2<T> current = minheapNode.key;
            distance.put(current, minheapNode.weight);

            for (Graph2.Edge<T> edge : current.edges) {
                Graph2.Vertex2<T> adjacent = getVertexForEdge(current, edge);
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

    private <T> Graph2.Vertex2<T> getVertexForEdge(Graph2.Vertex2<T> v, Graph2.Edge<T> e) {
        return e.vertex1.id == (v.id) ? e.vertex2 : e.vertex1;
    }
}
