package leetcode.medium;

import java.util.*;

public class RestoreIPAddress {

    public static void main(String[] args) {
        String input = "25525511135";
        List<String> res = restoreIPAddress(input);
        System.out.println(Arrays.toString(res.toArray()));
    }

    private static List<String> restoreIPAddress(String input) {
        List<String> res = new ArrayList<>();
        if (input.length() > 12) {
            return res;
        }
        System.out.println("hh");
        backtrack(input, 3, res, "");
        return res;
    }

    private static void backtrack(String input, int dot, List<String> res, String ip) {
        if (dot == 0) {
            if (isValid(input)) {
                ip += input;
                System.out.println(ip);
                res.add(ip);
            }
            return;
        }
        for (int i = 1; i < 4 && i < input.length(); i++) {
            String pre = input.substring(0, i);
            if (!isValid(pre)) {
                continue;
            }
            backtrack(input.substring(i), dot - 1, res, ip + pre + ".");
        }
    }

    private static boolean isValid(String s) {
        if (s.startsWith("0") && s.length() > 1 || Integer.valueOf(s) > 255)
            return false;
        return true;
    }
}
