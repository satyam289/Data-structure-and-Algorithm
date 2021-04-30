package StackQueue;

import java.util.Stack;

public class ValidParenthesis {
    
    public static boolean isValid(char[] arr) {

        Stack<Character> st = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            char ch = arr[i];
            if (ch == '(' || ch == '{' || ch == '[') {
                st.push(ch);
            } else {
                if (st.isEmpty()) {
                    return false;
                }
                char top = st.peek();
                if (top == '(' && ch == ')' || top == '{' && ch == '}' || top == '[' && ch == ']') {
                    st.pop();
                } else {
                    return false;
                }
            }
        }
        return st.isEmpty();
    }

    public static void main(String [] args){
        System.out.println(isValid("({[]})".toCharArray()));
        System.out.println(isValid("({[}})".toCharArray()));
    }
}
