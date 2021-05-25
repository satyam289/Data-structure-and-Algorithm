package StackQueue;

import java.util.Deque;
import java.util.LinkedList;

/*
https://www.geeksforgeeks.org/snake-ladder-problem-2/
Given a snake and ladder board, find the minimum number of dice throws 
required to reach the destination or last cell from source or 1st cell. 
*/
public class SnakeLadder {

    static int TotalCell = 30; // total cell
    static int MaxDiceFace = 6;

    private static class Cell {
        int vertex; // vertex number
        int distance; // distance from vertex

        Cell(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        Cell() {
        }
    }

    // TimeComplexity : 0(N)
    public static int solveSnake(int[] input) {

        Deque<Cell> q = new LinkedList<>();
        int[] visited = new int[TotalCell];
        q.add(new Cell(0, 0));
        visited[0] = 1;

        while (!q.isEmpty()) {

            Cell currCell = q.poll();
            if (currCell.vertex == TotalCell - 1) {
                return currCell.distance;
            }
            for (int i = currCell.vertex + 1; i <= currCell.vertex + MaxDiceFace && i < TotalCell; i++) {
                if (visited[i] == 0) {
                    Cell newCell = new Cell();
                    visited[i] = 1;
                    if (input[i] != -1) { // Ladder or Snake cell value
                        newCell.vertex = input[i];
                    } else {
                        newCell.vertex = i;
                    }
                    newCell.distance = currCell.distance + 1;
                    q.add(newCell);
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int moves[] = new int[TotalCell];
        for (int i = 0; i < TotalCell; i++) {
            moves[i] = -1;
        }
        // Ladders
        moves[2] = 21;
        moves[4] = 7;
        moves[10] = 25;
        moves[19] = 28;
        // Snakes
        moves[26] = 0;
        moves[20] = 8;
        moves[16] = 3;
        moves[18] = 6;
        System.out.println("Min Dice throws required is " + solveSnake(moves));
    }
}
