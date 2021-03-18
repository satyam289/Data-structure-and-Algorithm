package Array;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/spiral-matrix/

public class SipralMatrix {

    public List<Integer> sipralOrder(int[][] matrix) {

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
}
