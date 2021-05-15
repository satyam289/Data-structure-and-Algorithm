package graph;

import java.util.LinkedList;
import java.util.Queue;

/*
https://leetcode.com/accounts/login/?next=/problems/walls-and-gates/
You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. Fill each empty room with the distance to its nearest gate.If it is impossible to reach a gate, it should be filled with INF.

For example:

INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF

After running your function, the 2D grid should be:
  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
*/
public class WallAndGate {
    
    //DFS Solution
    public void wallsAndGates(int[][] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    wallAndGatesRec(rooms, 0, i, j);
                }
            }
        }
    }

    private void wallAndGatesRec(int[][] rooms, int distance, int row, int col) {
        if (row < 0 || row >= rooms.length || col < 0 || col >= rooms[0].length || rooms[row][col] == -1) {
            return;
        }
        if (rooms[row][col] != -1 || rooms[row][col] < distance) { // wall or visited or distance is already less
            return;
        }
        if (distance < rooms[row][col]) {
            rooms[row][col] = distance;
        }
        wallAndGatesRec(rooms, distance + 1, row - 1, col);
        wallAndGatesRec(rooms, distance + 1, row + 1, col);
        wallAndGatesRec(rooms, distance + 1, row, col - 1);
        wallAndGatesRec(rooms, distance + 1, row, col + 1);
    }

    //BFS
    public void wallsAndGates2(int[][] rooms) {
        if (rooms == null || rooms.length == 0) {
            return;
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    wallsAndGateUtil(rooms, q, 0, i, j);
                }
            }
        }
    }

    private void wallsAndGateUtil(int[][] rooms, Queue<Integer> q, int row, int col, int distance) {
        checkUpdateAdd(row, col, distance, rooms, q);
        int collength = rooms[0].length;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int val = q.poll();
                int x = val / collength;
                int y = val % collength;
                checkUpdateAdd(x - 1, y, distance + 1, rooms, q);
                checkUpdateAdd(x + 1, y, distance + 1, rooms, q);
                checkUpdateAdd(x, y - 1, distance + 1, rooms, q);
                checkUpdateAdd(x, y + 1, distance + 1, rooms, q);
            }
            distance++;
        }
    }

    private void checkUpdateAdd(int row, int col, int distance, int[][] rooms, Queue<Integer> q) {
        if (row < 0 || row >= rooms.length || col < 0 || col >= rooms[0].length) {
            return;
        }
        if (rooms[row][col] == -1) { // wall
            return;
        }
        if (distance > rooms[row][col]) { // return there is no point going further, if cell is already less value(might be visited one)
            return;
        }
        if (distance < rooms[row][col]) {
            rooms[row][col] = distance;
        }
        int encodeInfo = row * rooms[0].length + col;
        q.add(encodeInfo);
    }
}
