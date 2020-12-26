package Miscellaneous;

import java.util.HashMap;
import java.util.Map;

//https://www.geeksforgeeks.org/find-recurring-sequence-fraction/

public class RecurringSequence {

    public static void main(String[] args) {
        System.out.println(convertFractionToDecimal(50, 22));
    }

    public static String convertFractionToDecimal(int numerator, int denominator) {

        String result = "";
        Map<Integer, Integer> map = new HashMap<>();

        int remainder = numerator % denominator;

        while (remainder != 0 && !map.containsKey(remainder)) {
            map.put(remainder, result.length());
            remainder = remainder * 10;
            result += String.valueOf( remainder / denominator );
            remainder = remainder % denominator;
        }
        if (remainder == 0) {
            return "No Recurrence";
        }
        return result.substring(map.get(remainder));
    }
}
