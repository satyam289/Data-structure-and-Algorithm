package graph;

import StackQueue.Queue;
import StackQueue.Stack;
import Linked.LinkedList;

class Vertex {

    char ch;
    boolean wasvisited;

    Vertex(char ch) {
        this.ch = ch;
        wasvisited = false;
    }
}

public class Graph {
    private Vertex[] vertexarr;
    private int[][] adjmax;
    private Stack thestack;
    private int nver;
    private Queue q;

    Graph(int size) {
        vertexarr = new Vertex[size];
        adjmax = new int[size][size];
        q = new Queue(size);
        thestack = new Stack(size);
        nver = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                adjmax[i][j] = 0;
            }
        }
    }

    public void addVertex(char ch) {
        vertexarr[nver++] = new Vertex(ch);
    }

    public void addedge(int start, int end) {
        adjmax[start][end] = 1;
        adjmax[end][start] = 1;
    }

    public void dfs() {
        vertexarr[0].wasvisited = true;
        System.out.print(vertexarr[0].ch + "  ");
        thestack.push(0);
        while (!thestack.isEmpty()) {

            int vertex = thestack.peek();
            int nextvertex = getadjUnvistedVertex(vertex);
            // System.out.println(vertexarr[vertex].ch);
            if (nextvertex == -1) {
                thestack.pop();
            } else {
                System.out.print(vertexarr[nextvertex].ch + "  ");
                vertexarr[nextvertex].wasvisited = true;
                thestack.push(nextvertex);
            }
        }
        for (int i = 0; i < nver; i++) {
            vertexarr[i].wasvisited = false;
        }

    }

    public void bfs() {
        vertexarr[0].wasvisited = true;
        System.out.print(vertexarr[0].ch + "  ");
        q.add(0);
        while (!q.isEmpty()) {
            int data = q.remove();
            int i;
            while ((i = getadjUnvistedVertex(data)) != -1) {

                vertexarr[i].wasvisited = true;
                System.out.print(vertexarr[i].ch + "  ");
                q.add(i);

            }
            // System.out.println("queuue array is " +q);
        }
        for (int i = 0; i < nver; i++) {
            vertexarr[i].wasvisited = false;
        }
    }

    public void mst() { // minimum spanning tree same as dfs
        vertexarr[0].wasvisited = true;
        thestack.push(0);
        while (!thestack.isEmpty()) {
            int vertex = thestack.peek();
            int nextvertex = getadjUnvistedVertex(vertex);
            if (nextvertex == -1)
                thestack.pop();
            else {
                vertexarr[nextvertex].wasvisited = true;
                System.out.print(vertexarr[vertex].ch + " ");
                System.out.print(vertexarr[nextvertex].ch + "      ");
                thestack.push(nextvertex);
            }
        }
    }

    public int getadjUnvistedVertex(int vertex) {
        for (int i = 0; i < nver; i++) {
            if (!vertexarr[i].wasvisited && adjmax[vertex][i] == 1)
                return i;
        }
        return -1;
    }

    class GraphImpLinkedAdjList {

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

        public void main(String[] args) {

            Graph g = new Graph(10);
            g.addVertex('a');
            g.addVertex('b');
            g.addVertex('c');
            g.addVertex('e');
            g.addVertex('d');
            g.addedge(0, 1);
            g.addedge(1, 2);
            g.addedge(0, 3);
            g.addedge(3, 4);
            g.dfs();
            System.out.println(" ");
            g.bfs();
            System.out.println(" ");
            g.mst();

            GraphImpLinkedAdjList grapLink = new GraphImpLinkedAdjList(5);
            grapLink.addVertex('a');
            grapLink.addVertex('b');
            grapLink.addVertex('c');
            grapLink.addVertex('e');
            grapLink.addVertex('d');
            grapLink.addEdge(0, 1);
            grapLink.addEdge(1, 2);
            grapLink.addEdge(0, 3);
            grapLink.addEdge(3, 4);
            grapLink.dfs();
        }
    }
}
