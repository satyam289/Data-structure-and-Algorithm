package StackQueue;

import java.util.Stack;

public class ArithemeticExpression {

    public static void main(String[] args) {
        System.out.println(solve("3*(4+5)-6/(1+2)"));
    }

    public static int solve(String str) {

        String postStr = convertToPostfix(str);
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
                    // System.out.println("addition " + result);
                    resultSt.push(result);
                    break;
                case '-':
                    result = num2 - num1;
                    // System.out.println("subtraction" + result);
                    resultSt.push(result);
                    break;
                case '*':
                    result = num1 * num2;
                    // System.out.println("multiplication" + result);
                    resultSt.push(result);
                    break;
                case '/':
                    result = num1 % num2;
                    // System.out.println("division" + result);
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

    private static String convertToPostfix(String str) {

        StringBuffer convertPostfixStr = new StringBuffer();
        Stack<Character> st = new Stack<>();

        char[] charr = str.toCharArray();
        for (char ch : charr) {
            switch (ch) {
            case '+':
            case '-':
                setPriority(ch, st, convertPostfixStr, 1);
                break;

            case '*':
            case '/':
                setPriority(ch, st, convertPostfixStr, 2);
                break;

            case '(':
                st.push(ch);
                break;

            case ')':
                addTillOpeningBracket(st, convertPostfixStr);
                break;
            default:
                convertPostfixStr.append(ch);
                break;
            }
        }

        while (!st.isEmpty()) { 
            convertPostfixStr.append(st.pop()); // converting stack data into string
        }
        return convertPostfixStr.toString();
    }

    public static void setPriority(char ch, Stack<Character> st, StringBuffer convertPostfixStr, int CurrPriority) {

        if (st.isEmpty()) {
            st.push(ch);
            return;
        }
        char top = (char) st.peek();
        if (top == '(') {
            st.push(ch);
        } else {
            int topPriority = (top == '+' || top == '-') ? 1 : (top == '*' || top == '%') ? 2 : 0;

            if (CurrPriority > topPriority) {
                st.push(ch);
            } else {
                convertPostfixStr.append(top);
                st.pop();
                st.push(ch);
            }
        }
    }

    public static void addTillOpeningBracket(Stack<Character> st, StringBuffer convertPostfixStr) {
        char ch = st.pop();
        while (!st.isEmpty() && ch != '(') {
            convertPostfixStr.append(ch);
            ch = st.pop();
        }
    }
}
