package graph;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/pacific-atlantic-water-flow/
//https://www.interviewbit.com/problems/water-flow/
public class PacificAltanticWaterFlow {

    // can be solves both dfs and bfs
    public static List<List<Integer>> waterFlow(int[][] heights) {
        int rowLen = heights.length;
        int colLen = heights[0].length;

        boolean[][] pacific = new boolean[rowLen][colLen];
        boolean[][] atlantic = new boolean[rowLen][colLen];

        for (int col = 0; col < colLen; col++) {
            dfs(heights, 0, col, Integer.MIN_VALUE, pacific);
            dfs(heights, rowLen - 1, col, Integer.MIN_VALUE, atlantic);
        }
        for (int row = 0; row < rowLen; row++) {
            dfs(heights, row, 0, Integer.MIN_VALUE, pacific);
            dfs(heights, row, colLen - 1, Integer.MIN_VALUE, atlantic);
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {

                if (pacific[i][j] && atlantic[i][j]) {
                    ArrayList<Integer> postion = new ArrayList<>();
                    postion.add(i);
                    postion.add(j);
                    result.add(postion);
                }
            }
        }
        return result;
    }

    private static void dfs(int[][] heights, int row, int col, int preHeight, boolean[][] ocean) {
        if (row < 0 || row >= heights.length || col < 0 || col >= heights[0].length) {
            return;
        }
        if (heights[row][col] < preHeight) {
            return;
        }
        if (ocean[row][col]) {
            return;
        }
        ocean[row][col] = true;
        int currHeight = heights[row][col];
        dfs(heights, row + 1, col, currHeight, ocean);
        dfs(heights, row - 1, col, currHeight, ocean);
        dfs(heights, row, col + 1, currHeight, ocean);
        dfs(heights, row, col - 1, currHeight, ocean);
    }

    public static void main(String[] args) {
        int[][] mat = { { 1, 2, 2, 3, 5 }, { 3, 2, 3, 4, 4 }, { 2, 4, 5, 3, 1 }, { 6, 7, 1, 4, 5 }, { 5, 1, 1, 2, 4 } };
        List<List<Integer>> result = waterFlow(mat);
        for (List<Integer> list : result) {
            System.out.print(list.toString() + " ");
        }
    }
}
