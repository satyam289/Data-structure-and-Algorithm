package Miscellaneous;

public class NoOfIslandMatrix {

    public static void main(String[] args) {
        int M[][] = new int[][]{
                {1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {1, 0, 0, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 0, 1, 0, 1}
        };
        System.out.println("No of Island : " + countIsland(M));
    }

    private static int countIsland(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int count = 0;
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 1 && (!visited[i][j])) {
                    dfs(matrix, visited, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private static void dfs(int[][] matrix, boolean[][] visited, int i, int j) {

        int rowMoves[] = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
        int colMoves[] = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};
        visited[i][j] = true;
        for (int k = 0; k < 8; k++) {
            if (isSafe(i + rowMoves[k], j + colMoves[k], matrix, visited))
                dfs(matrix, visited, i + rowMoves[k], j + colMoves[k]);
        }
    }

    private static boolean isSafe(int row, int col, int[][] matrix, boolean[][] visited) {
        return (row >= 0) && (row < matrix.length) &&
                (col >= 0) && (col < matrix[0].length) &&
                (matrix[row][col] == 1 && !visited[row][col]);
    }
 
    //https://leetcode.com/problems/max-area-of-island/
    public int maxAreaOfIsland(int [][] grid){
        int max = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    max = Math.max(max, dfs(grid, i, j));
                }
            }
        }
        return max;
    }

    public int dfs(int[][] grid, int i, int j){
        if(i < 0 || i >= grid.length || j < 0  || j >= grid[i].length || grid[i][j] == 0){
            return 0;
        }
        grid[i][j] = 0;
        int count = 1;
        count += dfs(grid, i+1, j);
        count += dfs(grid, i-1, j);
        count += dfs(grid, i, j+1);
        count += dfs(grid, i+1, j-1);
        return count;
    }
}
