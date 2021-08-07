package Array;

import java.util.ArrayList;
import java.util.Collections;

//https://www.interviewbit.com/problems/valid-ip-addresses/
public class ValidIpAddress {
    Boolean checkIfValid(String A) {
        String[] s = A.split("\\.");

        if (A.charAt(0) == '.' || A.charAt(A.length() - 1) == '.')
            return false;

        if (s.length != 4)
            return false;

        for (int i = 0; i < 4; i++) {
            if (s[i].charAt(0) == '0' && s[i].length() != 1)
                return false;
            if (s[i].length() == 0)
                return false;
            if (!(Integer.parseInt(s[i]) >= 0 && Integer.parseInt(s[i]) <= 255))
                return false;
        }
        return true;

    }

    public ArrayList<String> restoreIpAddresses(String A) {
        ArrayList<String> k = new ArrayList<String>();
        if (A.length() > 12)
            return k;
        for (int i = 0; i < A.length(); i++) {
            for (int j = i + 1; j < A.length(); j++) {
                for (int z = j + 1; z < A.length(); z++) {

                    StringBuilder s = new StringBuilder();
                    for (int m = 0; m < A.length(); m++) {
                        if (m == i || m == j || m == z) {
                            s.append(".");
                        }
                        s.append(A.charAt(m));
                    }
                    if (checkIfValid(s.toString())) {
                        k.add(s.toString());
                    }
                }
            }
        }
        Collections.sort(k);
        return k;
    }

    public ArrayList<String> restoreIpAddresses2(String A) {

        ArrayList<String> ans = new ArrayList<String>();
        int n = A.length();
        if (n <= 3) {
            return ans;
        }
        for (int i = 1; i <= 3; i++) {
            for (int j = i + 1; j <= Math.min(n, i + 3); j++) {
                for (int k = j + 1; k <= Math.min(n, j + 3); k++) {

                    String one = A.substring(0, i);
                    String two = A.substring(i, j);
                    String three = A.substring(j, k);
                    String Four = A.substring(k);

                    if (isv(one) && isv(two) && isv(three) && isv(Four)) {
                        ans.add(one + "." + two + "." + three + "." + Four);
                    }

                }
            }
        }
        return ans;
    }

    public boolean isv(String s) {
        if (s.length() == 0) {
            return false;
        }
        int number = Integer.parseInt(s);
        if (number > 0 && number < 256 && s.charAt(0) != '0') {
            return true;
        }
        if (number == 0 && s.length() == 1) {
            return true;
        }
        return false;
    }
}
