package graph;

import java.util.LinkedList;
import java.util.Queue;

//https://www.interviewbit.com/problems/knight-on-chess-board/
class KnightChessBoard {

    class Coordinate {
        int x;
        int y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // BFS
    public int knight(int N, int M, int x1, int y1, int x2, int y2) {
        int[][] moves = { { -1, -2 }, { -2, -1 }, { -1, 2 }, { -2, 1 }, { 1, -2 }, { 2, -1 }, { 1, 2 }, { 2, 1 } };
        boolean[][] isVisited = new boolean[N + 1][M + 1];
        Queue<Coordinate> q = new LinkedList<>();
        q.add(new Coordinate(x1, y1));
        isVisited[x1][y1] = true;
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Coordinate coordinate = q.poll();
                if (coordinate.x == x2 && coordinate.y == y2) {
                    return step;
                }
                for (int j = 0; j < moves.length; j++) {
                    int new_x = coordinate.x + moves[j][0];
                    int new_y = coordinate.y + moves[j][1];
                    if (isValid(new_x, new_y, N, M) && !isVisited[new_x][new_y]) {
                        q.add(new Coordinate(new_x, new_y));
                        isVisited[new_x][new_y] = true;
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private boolean isValid(int new_x, int new_y, int N, int M) {
        if (new_x <= 0 || new_y <= 0 || new_x > N || new_y > M) {
            return false;
        }
        return true;
    }
}