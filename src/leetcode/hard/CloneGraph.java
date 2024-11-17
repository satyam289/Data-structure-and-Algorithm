package leetcode.hard;

import java.util.*;

public class CloneGraph {

    class UndirectedGraphNode {
        int label;
        ArrayList<UndirectedGraphNode> neighbours;

        UndirectedGraphNode(int x) {
            label = x;
            neighbours = new ArrayList<>();
        }
    }

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null)
            return null;

        LinkedList<UndirectedGraphNode> queue = new LinkedList<>();
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        UndirectedGraphNode newHead = new UndirectedGraphNode(node.label);
        queue.add(node);
        map.put(node, newHead);

        while (!queue.isEmpty()) {
            UndirectedGraphNode curr = queue.pop();
            ArrayList<UndirectedGraphNode> currNeighbours = curr.neighbours;

            for (UndirectedGraphNode aNeighbour : currNeighbours) {
                if (!map.containsKey(aNeighbour)) {
                    UndirectedGraphNode copy = new UndirectedGraphNode(aNeighbour.label);
                    map.put(aNeighbour, copy);
                    map.get(curr).neighbours.add(copy);
                    queue.add(aNeighbour);
                } else {
                    map.get(curr).neighbours.add(map.get(aNeighbour));
                }
            }
        }
        return newHead;
    }
}
