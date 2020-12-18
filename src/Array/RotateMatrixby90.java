package Array;

public class RotateMatrixby90 {

    public static void main(String[] args) {
        int[][] a = {{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        dorotateby90(a);
        dorotateby90SecondApporach(a);
    }

    private static void dorotateby90SecondApporach(int[][] a) {  // best one   always N X N matrix

        int N = a.length;

        if (a.length != a[0].length)
            System.err.println("Matrix is not proper rows and column number");

        else {

            for (int i = 0; i < (N / 2); i++) {

                for (int j = i; j < N - 1 - i; j++) {             // starts at i and end at length-i

                    int temp = a[i][j];
                    a[i][j] = a[j][N - 1 - i];
                    a[j][N - 1 - i] = a[N - 1 - i][N - 1 - j];
                    a[N - 1 - i][N - 1 - j] = a[N - 1 - j][i];
                    a[N - 1 - j][i] = temp;
                }
            }

            for (int[] ints : a) {
                for (int j = 0; j < N; j++) {
                    System.out.print(ints[j] + "   ");
                }
                System.out.println();
            }
		}
    }


    private static void dorotateby90(int[][] a) {

        int rows = a.length;
        int columns = a[0].length;
        int[][] transpose = new int[columns][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(a[i][j] + "   ");
                transpose[j][i] = a[i][j];
            }
            System.out.println();
        }
        System.out.println("----------------------");

        for (int i = 0; i < columns / 2; i++) {                             // reversing the rowise upto half rows
            // since transpose matrix become column X row
            for (int j = 0; j < rows; j++) {

                int temp = transpose[i][j];
                transpose[i][j] = transpose[columns - 1 - i][j];
                transpose[columns - 1 - j][j] = temp;
            }
        }

        for (int i = 0; i < columns; i++) {                              // since transpose matrix become column X row
            for (int j = 0; j < rows; j++) {
                System.out.print(transpose[i][j] + "   ");
            }
            System.out.println();
        }
    }
}
