package DynamicProgramming;

import java.util.Arrays;

/*
 input[] = {'T', 'T', 'F', 'T'}
 operator[] = {'|', '&', '^'}
 There are 4 ways
 ((T|T)&(F^T)), (T|(T&(F^T))), (((T|T)&F)^T) and (T|((T&F)^T))
*/

public class booleanParenthesis {

    public static void main(String[] args) {
        char input[] = {'T', 'T', 'F', 'T'};
        char operator[] = {'|', '&', '^'};
        System.out.println(noofways(input, operator, input.length));
    }

    private static int noofways(char[] input, char[] operator, int n) {
        int[][] T = new int[n][n];
        int[][] F = new int[n][n];

        for (int i = 0; i < n; i++) {
            T[i][i] = input[i] == 'T' ? 1 : 0;
            F[i][i] = input[i] == 'F' ? 1 : 0;
        }
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;
                for (int k = i; k < j; k++) {

                    int totalK1 = T[i][k] + F[i][k];
                    int totalK2 = T[k + 1][j] + F[k + 1][j];

                    if (operator[k] == '&') {
                        T[i][j] += T[i][k] * T[k + 1][j];
                        F[i][j] += (totalK1 * totalK2 - T[i][k] * T[k + 1][j]);
                    }
                    if (operator[k] == '|') {
                        F[i][j] += F[i][k] * F[k + 1][j];
                        T[i][j] += (totalK1 * totalK2 - F[i][k] * F[k + 1][j]);
                    }
                    if (operator[k] == '^') {
                        T[i][j] += (F[i][k] * T[k + 1][j] + T[i][k] * F[k + 1][j]);
                        F[i][j] += (T[i][k] * T[k + 1][j] + F[i][k] * F[k + 1][j]);
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(T[i]));
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(F[i]));
        }
        System.out.println();
        return T[0][n - 1];
    }

}
