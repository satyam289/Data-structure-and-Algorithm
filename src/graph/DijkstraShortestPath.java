package graph;

import java.util.HashMap;
import java.util.Map;

public class DijkstraShortestPath {

   public <T> Map<Vertex2, Integer>  shortestPath(Graph2<T> graph, Vertex2<T> src){

       BinaryMinHeap<Vertex2> minHeap = new BinaryMinHeap<>();

       Map<Vertex2, Integer> distance = new HashMap<>();
       Map<Vertex2, Vertex2> parent = new HashMap<>();

       for(Vertex2<T> vertex: graph.getAllVertex()){
          minHeap.add(Integer.MAX_VALUE, vertex);
       }

       minHeap.decrease(src, 0);
       distance.put(src,0);
       parent.put(src, null);

       while(!minHeap.isEmpty()) {
           BinaryMinHeap<Vertex2>.Node minheapNode = minHeap.extractMinNode();
           Vertex2<T> current = minheapNode.key;
           distance.put(current, minheapNode.weight);

           for (Edge<T> edge : current.edges) {
               Vertex2 adjacent = getVertexForEdge(current, edge);
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

    private <T> Vertex2<T> getVertexForEdge(Vertex2<T> v, Edge<T> e){
        return e.vertex1.id == (v.id) ? e.vertex2 : e.vertex1;
    }

}
