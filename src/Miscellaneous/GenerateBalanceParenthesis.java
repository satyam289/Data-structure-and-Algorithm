package Miscellaneous;

import java.util.Arrays;

public class GenerateBalanceParenthesis {
    static int count = 0;

    public static void main(String[] args) {
        System.out.println("Generated Balanced {} parenthesis combination : ");
        printParenthesis(3);
        System.out.println("Total Count is : " + count);
    }

    public static void printParenthesis(char[] str, int pos, int n, int openCount, int closeCount) {
        if (pos == 2 * n) {
            count++;
            System.out.println(Arrays.toString(str));
        }
        if (openCount < n) {
            str[pos] = '{';
            printParenthesis(str, pos + 1, n, openCount + 1, closeCount);
        }
        if (openCount > closeCount) {
            str[pos] = '}';
            printParenthesis(str, pos + 1, n, openCount, closeCount + 1);
        }

    }

    public static void printParenthesis(int n) {
        if (n > 0) {
            char[] str = new char[2 * n];
            printParenthesis(str, 0, n, 0, 0);
        }
    }

}
