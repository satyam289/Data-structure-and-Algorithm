package graph;


import Linked.LinkedList;
import StackQueue.Stack;

public class GraphImpLinkedAdjList {

    class Vertex {
        char ch;
        boolean wasvisited;

        Vertex(char ch) {
            this.ch = ch;
            wasvisited = false;
        }
    }

    Vertex[] vertexArray;
    LinkedList[] listArray;
    Stack thestack;
    int nver;

    GraphImpLinkedAdjList(int size) {
        vertexArray = new Vertex[size];
        listArray = new LinkedList[size];
        nver = 0;
        thestack = new Stack(size);
        for (int i = 0; i < size; i++)
            listArray[i] = new LinkedList();
    }

    public void addVertex(char ch) {
        vertexArray[nver] = new Vertex(ch);
        nver++;
    }

    public void addEdge(int start, int end) {
        LinkedList list = listArray[start];
        list.insert(end);
    }

    public void dfs() {
        System.out.print(vertexArray[0].ch);
        vertexArray[0].wasvisited = true;
        thestack.push(0);
        while (!thestack.isEmpty()) {
            int vertex = thestack.peek();
            LinkedList list2 = listArray[vertex];
            int Nextvertex = find(list2.head, vertexArray);
            if (Nextvertex == -1)
                thestack.pop();
            else {
                System.out.print(vertexArray[Nextvertex].ch);
                vertexArray[Nextvertex].wasvisited = true;
                thestack.push(Nextvertex);
            }
        }
    }

    public static void main(String[] args) {
        GraphImpLinkedAdjList g = new GraphImpLinkedAdjList(5);
        g.addVertex('a');
        g.addVertex('b');
        g.addVertex('c');
        g.addVertex('e');
        g.addVertex('d');
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(0, 3);
        g.addEdge(3, 4);
        g.dfs();
    }

    public int find(LinkedList.Link head, Vertex[] vertexArray) {
        LinkedList.Link current = head;
        for (int i = 0; i < nver; i++) {
            if (current == null)
                break;
            if (current.data == 1 && !vertexArray[i].wasvisited)
                return i;
            current = current.next;
        }
        return -1;
    }
}
