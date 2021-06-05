package StackQueue;

import java.util.Stack;

//https://leetcode.com/problems/longest-valid-parentheses/
//https://www.interviewbit.com/problems/longest-valid-parentheses/
public class LongestValidParenthesis {

    // Space Complexity: 0(N)
    public static int longestValidParentheses(String input) {
        int max = 0;
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '(') {
                st.push(i);
            } else {
                st.pop();
                if (st.isEmpty()) {
                    st.push(i);
                } else {
                    max = Math.max(max, i - st.peek());
                }
            }
        }
        return max;
    }

    // Space Complexity : 0(1)
    public static int longestValidParentheses2(String s) {
        int open = 0;
        int close = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                open++;
            } else {
                close++;
            }
            if (open == close) {
                max = Math.max(max, open + close);
            }
            if (close > open) {
                close = open = 0;
            }
        }
        open = close = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (ch == '(') {
                open++;
            } else {
                close++;
            }
            if (open == close) {
                max = Math.max(max, open + close);
            }
            if (open > close) {
                close = open = 0;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        String input = ")()())";
        System.out.println(longestValidParentheses(input));
        System.out.println(longestValidParentheses2(input));
    }

    // https://leetcode.com/problems/longest-valid-parentheses/solution/
    public int longestValidParenthesesDP(String s) {
        int maxans = 0;
        int dp[] = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') { // "....()"
                    dp[i] = 2 + (i >= 2 ? dp[i - 2] : 0);

                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') { // "...))"
                    int LenBeforeLastTwoValid = 0; // __((....))
                    if ((i - dp[i - 1]) >= 2) {
                        LenBeforeLastTwoValid = dp[i - dp[i - 1] - 2];
                    }
                    dp[i] = 2 + dp[i - 1] + LenBeforeLastTwoValid;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }
}
