package Array;

import java.util.HashMap;

public class RomanToInteger {
    
    public static int romanToInt(String str) {
        if (str.length() == 0)
            return 0;

        int n = str.length() - 2;
        int sum = getValFromRoman(str.charAt(n + 1));
        while (n >= 0) {
            if (getValFromRoman(str.charAt(n)) < getValFromRoman(str.charAt(n + 1))) {
                sum -= getValFromRoman(str.charAt(n));
            } else {
                sum += getValFromRoman(str.charAt(n));
            }
            n--;
        }
        return sum;
    }

    private static int getValFromRoman(char a) {
        if (a == 'I')
            return 1;
        else if (a == 'V')
            return 5;
        else if (a == 'X')
            return 10;
        else if (a == 'L')
            return 50;
        else if (a == 'C')
            return 100;
        else if (a == 'D')
            return 500;
        else if (a == 'M')
            return 1000;
        return 0;
    }

    public static int romanToInt2(String str) {
        if (str == null | str == "") {
            return 0;
        }
        HashMap<String, Integer> store = new HashMap<>(13);
        store.put("I", 1);
        store.put("IV", 4);
        store.put("V", 5);
        store.put("IX", 9);
        store.put("X", 10);
        store.put("XL", 40);
        store.put("L", 50);
        store.put("XC", 90);
        store.put("C", 100);
        store.put("CD", 400);
        store.put("D", 500);
        store.put("CM", 900);
        store.put("M", 1000);
        int n = str.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (i <= n - 2) {
                String sub = str.substring(i, i + 2);
                if (store.containsKey(sub)) {
                    ans += store.get(sub);
                    i++;
                } else {
                    ans += store.get(str.substring(i, i + 1));
                }
            }
        }
        return ans;
    }

    public static void main(String args[]){
        System.out.println(romanToInt("XIV"));
        System.out.println(romanToInt2("XIV"));
    }
}
