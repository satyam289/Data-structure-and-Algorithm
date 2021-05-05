package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://www.interviewbit.com/problems/clone-graph/
public class GraphClone {

    private class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>();
        }
    };

    // dfs apporach
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        HashMap<UndirectedGraphNode, UndirectedGraphNode> hm = new HashMap<>();
        return cloneGraphRec(node, hm);
    }

    public UndirectedGraphNode cloneGraphRec(UndirectedGraphNode node,
            HashMap<UndirectedGraphNode, UndirectedGraphNode> hm) {
        if (hm.containsKey(node)) {
            return hm.get(node);
        }
        UndirectedGraphNode copyNode = new UndirectedGraphNode(node.label);
        hm.put(node, copyNode);

        for (UndirectedGraphNode child : node.neighbors) {
            copyNode.neighbors.add(cloneGraphRec(child, hm));
        }
        return copyNode;
    }

    // bfs approach
    public UndirectedGraphNode cloneGraph2(UndirectedGraphNode node) {
        HashMap<UndirectedGraphNode, UndirectedGraphNode> hm = new HashMap<>();
        Queue<UndirectedGraphNode> q = new LinkedList<>();
        q.add(node);
        UndirectedGraphNode copyNode = new UndirectedGraphNode(node.label);
        hm.put(node, copyNode);

        while (!q.isEmpty()) {
            UndirectedGraphNode polledNode = q.poll();
            UndirectedGraphNode copyPolledNode = hm.get(polledNode);

            for (UndirectedGraphNode child : polledNode.neighbors) {

                UndirectedGraphNode copyChildNode = null;
                if (!hm.containsKey(child)) {
                    copyChildNode = new UndirectedGraphNode(child.label);
                    hm.put(child, copyChildNode);
                    q.add(child);
                } else {
                    copyChildNode = hm.get(child);
                }

                copyPolledNode.neighbors.add(copyChildNode);
            }
        }
        return copyNode;
    }
}
