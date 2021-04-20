package graph;

public class MinimunSpanningWeightedGraph { // non direction weight grap (every node is visited with minimum value)

    Graph graph;

    MinimunSpanningWeightedGraph() {
        graph = new Graph(10);
    }


    private class Graph {
        private int[][] adjmax;
        private vertex[] vertexArray;
        private PriorityQueue q;
        private int nver;
        private int currentvertex;

        Graph(int maxsize) {
            adjmax = new int[maxsize][maxsize];
            q = new PriorityQueue(maxsize);
            nver = 0;
            vertexArray = new vertex[maxsize];
            for (int i = 0; i < maxsize; i++) {
                for (int j = 0; j < maxsize; j++) {
                    adjmax[i][j] = Integer.MAX_VALUE; // filling infinity
                }
            }
        }

        public void addvertex(char ch) {
            vertexArray[nver] = new vertex(ch);
            nver++;
        }

        public void addEdge(int start, int end, int weight) {
            adjmax[start][end] = weight;
            adjmax[end][start] = weight;
        }

        public void mstw() {

            currentvertex = 0;
            int startingvertex = 0;
            while (startingvertex < nver - 1) { // visit till all vertex

                vertexArray[currentvertex].wasvisited = true; // mark that is visit
                startingvertex++;

                for (int j = 0; j < nver; j++) { // for each column in that one row in table (adjmax)
                    if (j == currentvertex) // skip if it same vertex
                        continue;
                    if (vertexArray[j].wasvisited) // skip it was visited
                        continue;
                    int distance = adjmax[currentvertex][j];
                    if (distance == Integer.MAX_VALUE) // if distance is infinity , skip
                        continue;
                    putinPQ(j, distance); // finally put in queue if its distance is lesser of same desination
                }
                // q.display();
                if (q.isEmpty()) {
                    System.out.println("graph is not connected");
                    return;
                }

                Edge e = q.removeMin(); // minimum value in queue
                currentvertex = e.des; // setting destination of minimum value of queue as next vertex
                System.out.print(vertexArray[e.src].ch);
                System.out.print(vertexArray[currentvertex].ch);
                System.out.print(e.distance);
                System.out.print(" ");

            }
            for (int j = 0; j < nver; j++)
                vertexArray[j].wasvisited = false;

        }

        public void putinPQ(int vertex, int newdistance) {
            int queuedestinationindex = q.find(vertex); // find, if any exist with same destination
            if (queuedestinationindex != -1) { // if yes

                Edge tempedge = q.peekN(queuedestinationindex); // check that edge's distance
                int olddistance = tempedge.distance;

                if (olddistance > newdistance) { // if edge's distance is greater , replace it with new else skip no
                                                 // need of insertion
                    q.removeN(queuedestinationindex);
                    Edge newEdge = new Edge(currentvertex, vertex, newdistance);
                    q.insert(newEdge);
                }
            } else {
                Edge newEdge = new Edge(currentvertex, vertex, newdistance); // if there is no same destination
                q.insert(newEdge);
            }

        }

        private class vertex {
            char ch;
            boolean wasvisited;

            vertex(char ch) {
                this.ch = ch;
                wasvisited = false;
            }
        }

        private class Edge {
            int src;
            int des;
            int distance;

            Edge(int src, int des, int distance) {
                this.src = src;
                this.des = des;
                this.distance = distance;
            }
        }

        private class PriorityQueue { // min at end(decreasing order)
            private Edge[] a;
            int size;
            int maxsize;

            PriorityQueue(int maxsize) {
                this.maxsize = maxsize;
                size = 0;
                a = new Edge[maxsize];
            }

            public void insert(Edge e) {
                if (size == maxsize - 1)
                    System.out.println("queue is full");
                else {
                    int i;
                    for (i = 0; i < size; i++) {
                        if (a[i].distance <= e.distance)
                            break;
                    }
                    for (int j = size - 1; j >= i; j--)
                        a[j + 1] = a[j];
                    // System.out.println(e.distance+" "+e.src+" "+e.des);
                    a[i] = e;
                    size++;
                }

            }

            public boolean isEmpty() {
                return size == 0;
            }

            public Edge peek() {
                return a[size - 1];
            }

            public Edge peekN(int n) {
                return a[n];
            }

            public Edge removeMin() {
                /*
                 * Edge temp= a[0]; for(int i=1;i<size;i++){ a[i-1]=a[i]; } size--; return temp;
                 */
                return a[--size];

            }

            public void removeN(int n) {
                for (int j = n; j < size - 1; j++)
                    a[j] = a[j + 1];
                size--;
            }

            public int find(int finddes) {
                for (int i = 0; i < size; i++)
                    if (a[i].des == finddes)
                        return i;

                return -1;
            }

            public void display() {
                for (int i = 0; i < size; i++) {
                    Edge e = a[i];
                    System.out.print(vertexArray[e.src].ch + "" + vertexArray[e.des].ch + "" + e.distance + "  ");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        MinimunSpanningWeightedGraph minSpanObj = new MinimunSpanningWeightedGraph();
        minSpanObj.graph.addvertex('a');
        minSpanObj.graph.addvertex('b');
        minSpanObj.graph.addvertex('c');
        minSpanObj.graph.addvertex('d');
        minSpanObj.graph.addvertex('e');
        minSpanObj.graph.addvertex('f');
        minSpanObj.graph.addEdge(0, 1, 6); // ab6
        minSpanObj.graph.addEdge(0, 3, 4); // ad4
        minSpanObj.graph.addEdge(1, 2, 10); // bc10
        minSpanObj.graph.addEdge(1, 3, 7); // bd7
        minSpanObj.graph.addEdge(1, 4, 7); // be7
        minSpanObj.graph.addEdge(2, 3, 8); // cd8
        minSpanObj.graph.addEdge(2, 4, 5); // ce5
        minSpanObj.graph.addEdge(2, 5, 6); // cf6
        minSpanObj.graph.addEdge(3, 4, 12); // de12
        minSpanObj.graph.addEdge(4, 5, 7); // ef7
        System.out.print("Minimum Spanning Tree:  ");
        minSpanObj.graph.mstw(); // AD AB BE EC CF
    }
}
