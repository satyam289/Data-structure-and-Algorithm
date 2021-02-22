package Miscellaneous;

import java.util.Stack;

public class BaseUrlExaction {

    public static String simplifyPath(String a) {

        if (a.isEmpty() || a.equals("/"))
            return "/";

        String[] segments = a.split("/");

        Stack<String> nameStack = new Stack<>();

        for (int i = 0; i < segments.length; i++) {
            String curr = segments[i];
            if (curr.isEmpty() || curr.equals(".")) {
                continue;
            }
            if (curr.equals("..")) {
                if (!nameStack.isEmpty()) {
                    nameStack.pop();
                }
                continue;
            }
            nameStack.push(curr);
        }
        StringBuilder path = new StringBuilder();
        for (String name : nameStack) {
            path.append("/");
            path.append(name);
        }
        if (path.length() == 0)
            path.append("/");

        return path.toString();
    }

    public static void main(String[] args) {
        System.out.println(simplifyPath("www/some/./../satyam/kumar"));
    }

}