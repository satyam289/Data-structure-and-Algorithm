package graph;

public class Warshall_Algo {

    private static class Vertex {
        char ch;

        Vertex(char ch) {
            this.ch = ch;
        }
    }

    private Vertex[] vertexArray;
    private int[][] adjmax;
    private int nver;

    Warshall_Algo(int max) {
        vertexArray = new Vertex[max];
        adjmax = new int[max][max];
        nver = 0;
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                adjmax[i][j] = 0;
            }
        }
    }

    public void addVertex(char ch) {
        vertexArray[nver] = new Vertex(ch);
        nver++;
    }

    public void addEdge(int start, int end) {
        adjmax[start][end] = 1;
    }

    public void displayMaxtrix() {
        System.out.print("    ");
        for (int k = 0; k < nver; k++)
            System.out.print(vertexArray[k].ch + "   ");
        System.out.println("");
        System.out.println("  ___________________");
        for (int i = 0; i < nver; i++) {
            System.out.print(vertexArray[i].ch + " " + "|" + " ");
            for (int j = 0; j < nver; j++) {

                System.out.print(adjmax[i][j] + "   ");
            }
            System.out.println(" ");
        }
    }

    public void adjustedMartix() {
        for (int row = 0; row < nver; row++) {
            for (int col = 0; col < nver; col++) {

                if (adjmax[col][row] == 1) { // horizontally

                    for (int col2 = 0; col2 < nver; col2++) {
                        if (adjmax[row][col2] == 1) { // vertically of particular row (row)
                            adjmax[col][col2] = 1; // matrix both dynamic row & col
                        }
                    }
                }
            }
        }
        System.out.println(" ");
    }

    public static void main(String[] args) {
        Warshall_Algo d = new Warshall_Algo(10);
        d.addVertex('a');
        d.addVertex('b');
        d.addVertex('c');
        d.addVertex('d');
        d.addVertex('e');
        d.addEdge(0, 2);
        d.addEdge(1, 0);
        d.addEdge(1, 4);
        d.addEdge(4, 2);
        d.addEdge(3, 4);
        // d.addEdge(0, 1);
        // d.addEdge(1, 2);
        // d.addEdge(2, 0);
        d.adjustedMartix();
        d.displayMaxtrix();

        WeightedDirectedShortedPathGraph w = new WeightedDirectedShortedPathGraph(10);
        w.addVertex('a');
        w.addVertex('b');
        w.addVertex('c');
        w.addVertex('d');
        w.addVertex('e');
        w.addEdge(0, 1, 50);
        w.addEdge(0, 3, 80);
        w.addEdge(1, 2, 60);
        w.addEdge(1, 3, 90);
        w.addEdge(2, 4, 40);
        w.addEdge(3, 2, 20);
        w.addEdge(3, 4, 70);
        w.addEdge(4, 1, 50);
        System.out.println("**** Warshall shortest paths from B ***"); // zero index vertex
        for (int i = 0; i < WeightedDirectedShortedPathGraph.getNoVertex(); i++) {
            w.path(i);
            System.out.println();
        }
    }

    public class Floyd_Algo {
        private final int infinity = 100000;
        private int[][] adjmax;
        private int nver;

        Floyd_Algo(int max) {
            adjmax = new int[max][max];
            nver = 0;
            for (int i = 0; i < max; i++) {
                for (int j = 0; j < max; j++) {
                    adjmax[i][j] = infinity;
                }
            }
        }

        public void addEdge(int start, int end, int weight) {
            adjmax[start][end] = weight;
        }

        public void displayMaxtrix() {
            System.out.print("    ");
            for (int k = 0; k < nver; k++)
                System.out.println("");
            System.out.println("  ___________________");

            for (int i = 0; i < nver; i++) {
                for (int j = 0; j < nver; j++) {
                    System.out.print(adjmax[i][j] + "   ");
                }
                System.out.println(" ");
            }
        }

        public void adjustedMartix() {
            for (int row = 0; row < nver; row++) {
                for (int col = 0; col < nver; col++) {

                    if (adjmax[col][row] == 1) {
                        for (int row2 = 0; row2 < nver; row2++) {
                            if (adjmax[row][row2] == 1) {
                                adjmax[col][row2] = 1;
                            }
                        }
                    }
                }
            }
            System.out.println(" ");
        }
    }
}
