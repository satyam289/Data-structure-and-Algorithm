package StackQueue;

import java.util.Stack;

//https://www.interviewbit.com/problems/redundant-braces/
public class RedundantBraces {

    public int braces(String A) {
        char[] arr = A.toCharArray();
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(' || arr[i] == '+' || arr[i] == '*' || arr[i] == '-' || arr[i] == '/') {
                st.push(arr[i]);
            } else if (arr[i] == ')') {

                if (st.peek() == '(') {
                    return 1;
                }

                while (!st.isEmpty() && st.peek() != '(') {
                    st.pop();
                }
                if (st.peek() == '(') {
                    st.pop();
                }
            }
        }
        return 0;
    }
}
