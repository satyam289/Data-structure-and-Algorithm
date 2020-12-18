package graph;

import StackQueue.Queue;
import StackQueue.Stack;

public class Graph {

    class Vertex {

        char ch;
        boolean wasvisited;

        Vertex(char ch) {
            this.ch = ch;
            wasvisited = false;
        }
    }

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


    public void mst() {    //minimum spanning tree same as dfs
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

    public static void main(String[] args) {

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
    }
}


