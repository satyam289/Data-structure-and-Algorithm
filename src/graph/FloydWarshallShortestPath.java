package graph;

import java.util.Deque;
import java.util.LinkedList;

class FloydWarshallShortestPath {

    private static final int INF = 1000000;

    public int[][] allPairShortestPath(int[][] distanceMatrix) {

        int distance[][] = new int[distanceMatrix.length][distanceMatrix.length];
        int path[][] = new int[distanceMatrix.length][distanceMatrix.length];

        for (int i = 0; i < distanceMatrix.length; i++) {
            for (int j = 0; j < distanceMatrix[i].length; j++) {
                distance[i][j] = distanceMatrix[i][j];
                if (distanceMatrix[i][j] != INF && i != j) {
                    path[i][j] = i;
                } else {
                    path[i][j] = -1;
                }
            }
        }

        for (int k = 0; k < distanceMatrix.length; k++) {
            for (int i = 0; i < distanceMatrix.length; i++) {
                for (int j = 0; j < distanceMatrix.length; j++) {
                    if (distance[i][k] == INF || distance[k][j] == INF) {
                        continue;
                    }
                    if (distance[i][j] > distance[i][k] + distance[k][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                        path[i][j] = path[k][j];
                    }
                }
            }
        }
        // if values on diagonal of distance matrix is negative
        // then there is negative weight cycle in the graph.
        for (int i = 0; i < distance.length; i++) {
            if (distance[i][i] < 0) {
                throw new RuntimeException("Negative Weight Cycle");
            }
        }

        printPath(path, 3, 2);
        return distance;
    }

    public void printPath(int[][] path, int start, int end) {
        if (start < 0 || end < 0 || start >= path.length || end >= path.length) {
            throw new IllegalArgumentException();
        }

        System.out.println("Actual path - between " + start + " " + end);
        Deque<Integer> stack = new LinkedList<>();
        stack.addFirst(end);
        while (true) {
            end = path[start][end];
            if (end == -1) {
                return;
            }
            stack.addFirst(end);
            if (end == start) {
                break;
            }
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pollFirst() + " ");
        }
        System.out.println();
    }
}