package Math_Bit;

import java.util.HashMap;
import java.util.Map;

public class LeastNoPerfectSum {

    public static void main(String[] args) {
        Map<Integer, Integer> hm = new HashMap<>();
        System.out.println(getRecursively(31, hm));
    }

    public static int getRecursively(int num, Map<Integer, Integer> hm) {
        if (num <= 0)
            return 0;
        int allowed = (int) Math.floor(Math.sqrt(num));
        // System.out.println(allowed);
        int min = Integer.MAX_VALUE;
        if (hm.containsKey(num)) {
            return hm.get(num);
        }
        for (int i = 1; i <= allowed; i++) {
            int val = (int) Math.pow(i, 2);
            min = Math.min(min, 1 + getRecursively((num - val), hm));
        }
        hm.put(num, min);
        return min;
    }
}
