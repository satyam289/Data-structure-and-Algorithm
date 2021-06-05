package StackQueue;

import java.util.Stack;

public class ValidParenthesis {

    // Time complexity: 0(n) , Space Complexity: 0(n)
    public static boolean isValid(char[] arr) {

        Stack<Character> st = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            char ch = arr[i];
            if (ch == '(' || ch == '{' || ch == '[') {
                st.push(ch);
            } else {
                if (st.isEmpty() || !isSame(st.peek(), ch)) {
                    return false;
                } else {
                    st.pop();
                }
            }
        }
        return st.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValid2("({[]})".toCharArray()));
        System.out.println(isValid2("({[}})".toCharArray()));
    }

    // Time Complexity: 0(n^2), Space Complexity: 0(1)
    public static boolean isValid2(char[] arr) {
        int top = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(' || arr[i] == '{' || arr[i] == '[') {
                top = i;
            } else {
                if (top == -1 || !isSame(arr[top], arr[i])) {
                    return false;
                } else {
                    top = getNextTop(arr, top - 1);
                }
            }
        }
        return top == -1;
    }

    /*
     * Similar to stack Impl : Next top lies in range of [0, top-1] If we see top
     * pointing to closing braces, move start point forward, otherwise backward we
     * reach start at -1, it mean we have more opening than closing brace, top
     * pointer give next Index
     */
    private static int getNextTop(char[] arr, int top) {
        int start = 0;
        while (top >= 0) {
            if (arr[top] == ')' || arr[top] == '}' || arr[top] == ']') {
                start++;
            } else {
                start--;
            }
            if (start == -1) {
                return top;
            }
            top--;
        }
        return -1;
    }

    private static boolean isSame(char ch1, char ch2) {
        return ch1 == '(' && ch2 == ')' || ch1 == '{' && ch2 == '}' || ch1 == '[' && ch2 == ']';
    }
}
