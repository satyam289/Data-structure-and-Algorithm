package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinaryMinHeap<T> {

    private List<Node> allNode = new ArrayList<>();
    private Map<T,Integer> nodePosition = new HashMap<>();

    public static void main(String[] args) {
        BinaryMinHeap<String> heap = new BinaryMinHeap<>();
        heap.add(3, "Satyam");
        heap.add(4, "ROCKY");
        heap.add(8, "Kumar");
        heap.add(10, "Steve");
        heap.add(5, "Charlie");
        heap.add(6, "NTF");
        heap.add(2,"AFR");
        heap.decrease("Satyam", 1);
        heap.printHeap();
    }

    public boolean containsData(T key){
        return  nodePosition.containsKey(key);
    }

    public void add(int weight, T key){   //adding at bottom
        Node node = new Node(key,weight);
        allNode.add(node);
        int current = allNode.size() -1;
        nodePosition.put(node.key, current);
        int parentIndex = (current -1)/2;

        while(parentIndex >= 0){                     // trickle up
            Node parentNode = allNode.get(parentIndex);
            Node currentNode = allNode.get(current);

            if(parentNode.weight > currentNode.weight){
                swap(parentNode, currentNode);
                updatePositionMap(parentNode.key, currentNode.key, parentIndex, current );
                current = parentIndex;
                parentIndex = (parentIndex -1)/2;
            }else{
                break;
            }
        }
    }

    private void updatePositionMap(T data1, T data2, int pos1, int pos2) {
        nodePosition.remove(data1);
        nodePosition.remove(data2);
        nodePosition.put(data1, pos1);
        nodePosition.put(data2, pos2);
    }

    private void swap(Node node1, Node node2) {

        int weight = node1.weight;
        T data = node1.key;
        node1.key = node2.key;
        node1.weight = node2.weight;

        node2.key = data;
        node2.weight = weight;
    }

    public void decrease(T data, int newWeight){    // decreasing the weight value
        Integer position = nodePosition.get(data);
        allNode.get(position).weight = newWeight;
        int parent = (position -1)/2;
        while(parent >= 0){                       //trickle down
            if(allNode.get(parent).weight > allNode.get(position).weight){
                swap(allNode.get(parent), allNode.get(position));
                updatePositionMap(allNode.get(parent).key, allNode.get(position).key, parent, position);
                position = parent;
                parent = (parent -1)/2;
            }else{
                break;
            }
        }
    }

    public void printHeap(){
        for(Node n : allNode){
            System.out.println(n.weight + " " + n.key);
        }
    }

    public Node extractMinNode() {
        int size = allNode.size() - 1;
        Node minNode = new Node(allNode.get(0).key, allNode.get(0).weight);
        allNode.get(0).weight = allNode.get(size).weight;     //putting the last on top
        allNode.get(0).key = allNode.get(size).key;
        nodePosition.remove(minNode.key);   //removing the node position of first(min)
        nodePosition.remove(allNode.get(0)); //removing the node position of last
        nodePosition.put(allNode.get(0).key, 0);  // updating the node position
        allNode.remove(size);
        int currentIndex = 0;
        size--;
        while (true) {                             // trickle down
            int left = 2 * currentIndex + 1;
            int right = 2 * currentIndex + 2;
            if (left > size)
                break;
            if (right > size) {
                right = left;
            }
            int smallerIndex = allNode.get(left).weight < allNode.get(right).weight ? left : right;
            if (allNode.get(currentIndex).weight > allNode.get(smallerIndex).weight) {
                swap(allNode.get(currentIndex), allNode.get(smallerIndex));
                updatePositionMap(allNode.get(currentIndex).key, allNode.get(smallerIndex).key, currentIndex, smallerIndex);
                currentIndex = smallerIndex;
            }
            break;
        }
        return minNode;
    }

    public class Node{
        int weight;
        T key;
        Node(T key ,int weight){
            this.weight = weight;
            this.key = key;
        }
    }

}
