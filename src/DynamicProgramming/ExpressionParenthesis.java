package DynamicProgramming;

import java.util.HashSet;
import java.util.Set;

public class ExpressionParenthesis {

    public static class Holder {
        private Set<Integer> valueHolder = new HashSet<>();

        Set<Integer> value() {
            return valueHolder;
        }
    }

    public static boolean evalute(char[] expression, int result) {
        int n = expression.length;
        int[] operand = new int[n / 2 + 1];
        char[] operator = new char[n / 2];

        int index1 = 0, index2 = 0;
        operand[index2++] = expression[0] - '0';
        for (int i = 1; i < expression.length; i += 2) {
            operator[index1++] = expression[i];
            operand[index2++] = expression[i + 1] - '0';
        }
        Holder T[][] = new Holder[operand.length][operand.length];
        for (int i = 0; i < T.length; i++) {
            for (int j = 0; j < T[0].length; j++) {
                T[i][j] = new Holder();
            }
        }
        for (int i = 0; i < T.length; i++) {
            T[i][i].value().add(operand[i]);
        }

        for (int len = 2; len <= T.length; len++) {
            for (int i = 0; i < T.length - len + 1; i++) {
                int j = i + len - 1;
                for (int k = i; k < j; k++) {

                    for (int x : T[i][k].value()) {
                        for (int y : T[k + 1][j].value()) {
                            if (operator[k] == '/' && y == 0) {
                                continue;
                            }
                            T[i][j].value().add(calculate(x, y, operator[k]));
                        }
                    }
                }
            }
        }
        T[0][T.length - 1].value().forEach(i -> {
            System.out.println(i + " ");
        });
        for (int i : T[0][T.length - 1].value()) {
            if (i == result)
                return true;
        }
        return false;
    }

    private static int calculate(int x, int y, char sign) {
        if (sign == '+') {
            return x + y;
        }
        if (sign == '-') {
            return x - y;
        }
        if (sign == '*') {
            return x * y;
        }
        if (sign == '/') {
            return x / y;
        } else {
            throw new IllegalArgumentException("Not supported operator");
        }
    }

    public static void main(String[] args) {
        String s = "2*3-1";
        System.out.println(evalute(s.toCharArray(), 5));
    }
}
