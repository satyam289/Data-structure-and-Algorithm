package Array;

import java.util.ArrayList;

//https://www.interviewbit.com/problems/set-matrix-zeros/
public class SetMatrixZero {

    // optimal
    public void setZeroes(ArrayList<ArrayList<Integer>> matrix) {
        boolean firstRow = false;
        boolean firstCol = false;

        for (int i = 0; i < matrix.size(); i++) {
            if (matrix.get(i).get(0) == 0) {
                firstCol = true;
                break;
            }
        }

        for (int i = 0; i < matrix.get(0).size(); i++) {
            if (matrix.get(0).get(i) == 0) {
                firstRow = true;
                break;
            }
        }

        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(0).size(); j++) {
                if (matrix.get(i).get(j) == 0) {
                    matrix.get(i).set(0, 0);
                    matrix.get(0).set(j, 0);
                }
            }
        }

        for (int i = 1; i < matrix.size(); i++) {
            for (int j = 1; j < matrix.get(i).size(); j++) {
                if (matrix.get(i).get(0) == 0 || matrix.get(0).get(j) == 0) {
                    matrix.get(i).set(j, 0);
                }
            }
        }

        if (firstCol) {
            for (int i = 0; i < matrix.size(); i++)
                matrix.get(i).set(0, 0);
        }

        if (firstRow) {
            for (int i = 0; i < matrix.get(0).size(); i++)
                matrix.get(0).set(i, 0);
        }
    }

    // Better
    public void setZeroes2(ArrayList<ArrayList<Integer>> a) {
        int m = a.size();
        int n = a.get(0).size();
        boolean row[] = new boolean[a.size()];
        boolean col[] = new boolean[a.get(0).size()];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a.get(i).get(j) == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j])
                    a.get(i).set(j, 0);
            }
        }
    }

    // Brute Force
    public void setZeroes3(ArrayList<ArrayList<Integer>> a) {

        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < a.get(0).size(); j++) {
                if (a.get(i).get(j) == 0) {
                    updateAllRowColNegative(a, i, j);
                }
            }
        }
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < a.get(0).size(); j++) {
                if (a.get(i).get(j) == -1) {
                    a.get(i).set(j, 0);
                }
            }
        }
    }

    public void updateAllRowColNegative(ArrayList<ArrayList<Integer>> a, int ith, int jth) {
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).get(jth) == 0) {
                continue;
            }
            a.get(i).set(jth, -1);
        }
        for (int j = 0; j < a.get(0).size(); j++) {
            if (a.get(ith).get(j) == 0) {
                continue;
            }
            a.get(ith).set(j, -1);
        }
    }
}
