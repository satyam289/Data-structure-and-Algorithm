package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graph.Graph2.Edge;
import graph.Graph2.Vertex2;

/*  https://www.youtube.com/watch?v=oP2-8ysT3QQ
A spanning tree is subgraph of graph such that all n vertices are connted to each other
and there total n-1 edges in subgraph, so basically no cycle in subgraph
A minimum spanning tree of weighted undirected graph such that sum of the weight of edges is minimum
*/
public class PrimMST {

    // Greedy Alogrithm
    public List<Edge<Integer>> minimumSpanningTree(Graph2<Integer> graph) {

        BinaryMinHeap<Vertex2<Integer>> minHeap = new BinaryMinHeap<>();
        Map<Vertex2<Integer>, Edge<Integer>> vertexToEdge = new HashMap<>();

        List<Edge<Integer>> result = new ArrayList<>();

        for (Vertex2<Integer> ver : graph.getAllVertex()) {
            minHeap.add(Integer.MAX_VALUE, ver);
        }
        Vertex2<Integer> startVer = graph.getAllVertex().iterator().next();
        minHeap.decrease(startVer, 0);

        while (!minHeap.isEmpty()) {
            Vertex2<Integer> currVex = minHeap.extractMin();

            Edge<Integer> spaningTreeEdge = vertexToEdge.get(currVex);
            if (spaningTreeEdge != null) {
                result.add(spaningTreeEdge);
            }

            for (Edge<Integer> edge : currVex.edges) {
                Vertex2<Integer> adjacent = getVertexForEdge(currVex, edge);
                if (minHeap.containsData(adjacent) && minHeap.getWeight(adjacent) > edge.weight) {
                    minHeap.decrease(adjacent, edge.weight);
                    vertexToEdge.put(adjacent, edge);
                }
            }
        }
        return result;
    }

    private Vertex2<Integer> getVertexForEdge(Vertex2<Integer> ver, Edge<Integer> edge) {
        return edge.vertex1.equals(ver) ? edge.vertex2 : edge.vertex1;
    }
}
