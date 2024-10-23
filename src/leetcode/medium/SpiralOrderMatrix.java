package leetcode.medium;

import java.util.*;

public class SpiralOrderMatrix {

    public static void main(String[] args) {
        int[][] input = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 } };
        System.out.println(Arrays.toString(spiralOrder(input).toArray()));
    }

    public static ArrayList<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> result = new ArrayList<>();

        if (matrix == null || matrix.length == 0)
            return result;

        int m = matrix.length;
        int n = matrix[0].length;
        int x = 0;
        int y = 0;

        while (m > 0 && n > 0) {
            if (m == 1) {
                for (int i = 0; i < n; i++) {
                    result.add(matrix[x][y++]);
                }
                break;
            } else if (n == 1) {
                for (int i = 0; i < m; i++) {
                    result.add(matrix[x++][y]);
                }
                break;
            }
            // process a circle
            // top - move right
            for (int i = 0; i < n - 1; i++) {
                result.add(matrix[x][y++]);
            }
            // right - move down
            for (int i = 0; i < m - 1; i++) {
                result.add(matrix[x++][y]);
            }
            // bottom - move left
            for (int i = 0; i < n - 1; i++) {
                result.add(matrix[x][y--]);
            }
            // left - move up
            for (int i = 0; i < m - 1; i++) {
                result.add(matrix[x--][y]);
            }

            x++;
            y++;
            m = m - 2;
            n = n - 2;
        }
        return result;
    }
}
