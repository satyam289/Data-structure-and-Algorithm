package Array;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/spiral-matrix/

public class SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }
        int m = matrix.length - 1;
        int n = matrix[0].length - 1;
        int left = 0;
        int right = m;
        int top = 0;
        int bottom = n;

        while (result.size() <= m * n) {

            for (int j = left; j <= right && result.size() < m * n; j++) {
                result.add(matrix[top][j]);
            }
            top++;
            for (int i = top; i <= bottom && result.size() < m * n; i++) {
                result.add(matrix[i][right]);
            }
            right--;
            for (int j = right; j >= left && result.size() < m * n; j--) {
                result.add(matrix[bottom][j]);
            }
            bottom--;
            for (int i = bottom; i >= top && result.size() < m * n; i--) {
                result.add(matrix[i][left]);
            }
            left++;
        }
        return result;
    }

    public ArrayList<Integer> spiralOrderOptimised(final List<ArrayList<Integer>> A) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        int m, n;
        m = A.size();
        n = A.get(0).size();
        if (m == 0)
            return result;

        int dir = 0; // right
        int row, col, layer;
        row = col = layer = 0;
        result.add(A.get(0).get(0));

        for (int step = 1; step < m * n; step++) {
            switch (dir) {
            // Go right
            case 0:
                if (col == n - layer - 1) {
                    dir = 1;
                    row++;
                } else {
                    col++;
                }
                break;

            // Go down
            case 1:
                if (row == m - layer - 1) {
                    dir = 2;
                    col--;
                } else {
                    row++;
                }
                break;

            // Go left
            case 2:
                if (col == layer) {
                    dir = 3;
                    row--;
                } else {
                    col--;
                }
                break;

            // Go up
            case 3:
                if (row == layer + 1) {
                    dir = 0;
                    col++;
                    layer++;
                } else {
                    row--;
                }
                break;
            }
            result.add(A.get(row).get(col));
        }
        return result;
    }
}