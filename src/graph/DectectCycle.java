package graph;

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
    
        private boolean dfs(Vertex2<Integer> currvertex, Set<Vertex2<Integer>> whiteSet, Set<Vertex2<Integer>> graySet, Set<Vertex2<Integer>> blackSet) {
            
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
    
        private void moveVertex(Vertex2<Integer> vertex, Set<Vertex2<Integer>> sourceSet, Set<Vertex2<Integer>> destinationSet) {
            sourceSet.remove(vertex);
            destinationSet.add(vertex);
        }
    }

    class UndirecredGraph {

        public <T> boolean hasCycle(Graph2<T> graph) {

            DisjointSet ds = new DisjointSet();
            for (Vertex2<T> vertex : graph.getAllVertex()) {
                ds.makeSet(vertex.id);
            }
            for (Edge<T> edge : graph.getAllEdges()) {
                
                long parent1 = ds.findSet(edge.vertex1.id);
                long parent2 = ds.findSet(edge.vertex2.id);
                
                if (parent1 == parent2) {
                    return true;
                }
                ds.union(edge.vertex1.id, edge.vertex2.id);
            }
            return false;
        }

        public <T> boolean hasCycleDFS(Graph2<T> graph){
            Set<Vertex2<T>> visited = new HashSet<>();
            
            for(Vertex2<T> vertex: graph.getAllVertex()){
                if(visited.contains(vertex)){
                    continue;
                }
                if(hasDFSUtil(vertex, visited, null)){ // root node has null parent
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
    }
}



