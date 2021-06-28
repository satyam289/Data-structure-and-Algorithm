package graph;

import java.util.LinkedList;
import java.util.Queue;

//https://www.geeksforgeeks.org/path-rectangle-containing-circles/
//https://www.interviewbit.com/problems/valid-path/
public class RectanglePathCircle {

    class Coordinate {
        int x;
        int y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int[][] moves = { { 1, -1 }, { 1, 1 }, { 1, 0 }, { -1, -1 }, { -1, 1 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    // BFS
    // Time Complexity : M * N * NoCir + M*N =~ 0(N^3)
    // Space Complexity : (M + 1)*(N + 1) + (M*N) =~ 0(N^2)
    public String solve(int M, int N, int NoCir, int Radius, int[] xCordinate, int[] yCordinate) {
        int[][] board = new int[M + 1][N + 1];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                for (int k = 0; k < NoCir; k++) {
                    if (Math.sqrt(Math.pow(xCordinate[k] - i, 2) + Math.pow(yCordinate[k] - j, 2)) <= Radius) {
                        board[i][j] = -1;
                    }
                }
            }
        }
        if (board[0][0] == -1 || board[M][N] == -1) {
            return "NO";
        }
        Queue<Coordinate> q = new LinkedList<>();
        q.add(new Coordinate(0, 0));
        board[0][0] = 1;
        while (!q.isEmpty()) {
            Coordinate point = q.poll();
            for (int i = 0; i < moves.length; i++) {
                int x = point.x + moves[i][0];
                int y = point.y + moves[i][1];
                if (x >= 0 && y >= 0 && x <= M && y <= N && board[x][y] == 0) {
                    if (x == M && y == N) {
                        return "YES";
                    }
                    board[x][y] = 1;
                    q.add(new Coordinate(x, y));
                }
            }
        }
        return "NO";
    }
}
