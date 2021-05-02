package StackQueue;

import java.util.Stack;

public class ArithemeticExpression {

    public static void main(String[] args) {
        System.out.println(solve("3*(4+5)-6/(1+2)"));
    }

    public static int solve(String str) {

        String postStr = infixToPostfix(str);
        Stack<Integer> resultSt = new Stack<>();

        for (int i = 0; i < postStr.length(); i++) {
            char ch = postStr.charAt(i);

            if (ch > '0' && ch < '9') { // or Character.isDigit(ch)
                resultSt.push(ch - '0');
            } else {

                int result = 0;
                int num1 = resultSt.pop();
                int num2 = resultSt.pop();
                // System.out.println("num1: " + num1 + " num2: " + num2);
                switch (ch) {
                case '+':
                    result = num1 + num2;
                    resultSt.push(result);
                    break;
                case '-':
                    result = num2 - num1;
                    resultSt.push(result);
                    break;
                case '*':
                    result = num1 * num2;
                    resultSt.push(result);
                    break;
                case '/':
                    result = num1 % num2;
                    resultSt.push(result);
                    break;
                default:
                    resultSt.push(0);
                    break;
                }
            }
        }
        return resultSt.pop();
    }

    private static String infixToPostfix(String str) {
        String result = new String();
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                result += ch;
            } else if (ch == '(') {
                st.push(ch);
            } else if (ch == ')') {
                while (!st.isEmpty() && st.peek() != '(') {
                    result += st.pop();
                }
                st.pop();
            }else{
                while (!st.isEmpty() && getPrefer(st.peek()) > getPrefer(ch)) {
                    result += st.pop();
                }
                st.push(ch);
            }
        }

        while (!st.isEmpty()) {
            if (st.peek() == '(') {
                return "Invalid Expression";
            }
            result += st.pop();
        }
        System.out.println(result);
        return result;
    }

    private static int getPrefer(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }
}
