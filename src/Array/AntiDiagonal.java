package Array;

import java.util.ArrayList;

/*
       (0, 0)
    (0, 1), (1, 0)
 (0,2), (1, 1), (2, 0)
    (1, 2), (2, 1)
       (2, 2)
*/
// https://www.geeksforgeeks.org/zigzag-or-diagonal-traversal-of-matrix/
class AntiDiagonal {

   public static void diagonal(int[][] matrix, int row, int column) {

      for (int line = 1; line <= (row + column - 1); line++) {
         int start_col = Math.max(0, line - row);

         int count = Math.min(line, column - start_col);

         for (int k = 0; k < count; k++) {
            int start_row = Math.min(line, row);
            System.out.print(matrix[start_row - k - 1][start_col + k] + " ");
         }
         System.out.println("");
      }
   }

   private static void diagonalOrder2(int[][] arr, int R, int C) {
      for (int k = 0; k < R; k++) {
         System.out.print(arr[k][0] + " "); // first element in diagonal already printed
         // set row index for next point in diagonal
         int i = k - 1;
         // set column index for next point in diagonal
         int j = 1;
         /* Print Diagonally upward */
         while (isValid(i, j, R, C)) {
            System.out.print(arr[i][j] + " ");
            i--;
            j++;
         }
         System.out.println("");
      }

      for (int k = 1; k < C; k++) {
         System.out.print(arr[R - 1][k] + " ");
         // set row index for next point in diagonal
         int i = R - 2;
         // set column index for next point in diagonal
         int j = k + 1;
         /* Print Diagonally upward */
         while (isValid(i, j, R, C)) {
            System.out.print(arr[i][j] + " ");
            i--;
            j++;
         }
         System.out.println("");
      }
   }

   static boolean isValid(int i, int j, int R, int C) {
      if (i < 0 || i >= R || j >= C || j < 0)
         return false;
      return true;
   }

   public static void diagonalOrder3(int[][] arr, int n, int m) {

      // the 2D vector will have (n+m-1) rows that is equal to the number of diagnols
      ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>(n + m - 1);
      for (int i = 0; i < n + m - 1; i++) {
         ans.add(new ArrayList<Integer>());
      }

      for (int i = 0; i < n; i++) {
         for (int j = 0; j < m; j++) {
            (ans.get(i + j)).add(arr[i][j]);
         }
      }

      for (int i = 0; i < ans.size(); i++) {
         for (int j = ans.get(i).size() - 1; j >= 0; j--) { // reading from opposite
            System.out.print(ans.get(i).get(j) + " ");
         }
         System.out.println();
      }
   }

   public static void main(String[] args) {
      int M[][] = { { 1, 2, 3, 4 }, 
                    { 5, 6, 7, 8 }, 
                    { 9, 10, 11, 12 }, 
                    { 13, 14, 15, 16 }, 
                    { 17, 18, 19, 20 }, };
      diagonal(M, 5, 4);
      System.out.println(" ---2nd Apporach--- ");
      diagonalOrder2(M, 5, 4);
      System.out.println(" ---3rd Apporach--- ");
      diagonalOrder3(M, 5, 4);
   }
}
