package Array;

import java.util.HashMap;
import java.util.Map;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.
For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
The number of ways decoding "12" is 2.
 *
 */

public class Decode {

	public static void main(String[] args) {
		String str = "1212";
		System.out.println("Number of ways to decode : " + getNoofWays(str));
		decodeMessageUtil(str, 0, 0, 0, getEncodedMap(), new String[str.length()]);
	}

	public static int getNoofWays(String str) {
		Map<Integer, Integer> memorization = new HashMap<>();
		return getNoofWaysUtil(str, 0, memorization);
	}

	private static void decodeMessageUtil(String arr, int previousDigit, int counter, int start,
			Map<Integer, String> map, String[] result) {
		if (start >= arr.length()) {
			printResult(counter, result);
			return;
		}
		int digit = Integer.parseInt(arr.charAt(start) + "");
		if (digit == 0) {
			result[counter] = "0";
			return;
		}
		int twodigit = previousDigit * 10 + digit;
		result[counter] = map.get(digit);
		decodeMessageUtil(arr, digit, counter + 1, start + 1, map, result);
		if (twodigit > 9 && twodigit <= 26) {
			result[counter - 1] = map.get(twodigit);
			decodeMessageUtil(arr, 0, counter, start + 1, map, result);
		}
	}

	private static int getNoofWaysUtil(String input, int start, Map<Integer, Integer> memorization) {
		if (input.length() == start)
			return 1;
		if (memorization.containsKey(start))
			return memorization.get(start);

		String s1 = input.substring(start, start + 1);
		if (s1.equals("0")) {
			memorization.put(start, 0);
			return 0;
		}
		int c1 = getNoofWaysUtil(input, start + 1, memorization);
		int c2 = 0;
		if ((start + 2) <= input.length()) {
			s1 = input.substring(start, start + 2);
			if (Integer.valueOf(s1) <= 26) {
				c2 = getNoofWaysUtil(input, start + 2, memorization);
			}
		}
		memorization.put(start, c1 + c2);
		return c1 + c2;
	}

	private static void printResult(int counter, String[] result) {
		for (int i = 0; i < counter; i++)
			System.out.print(result[i]);
		System.out.println();
	}

	private static Map<Integer, String> getEncodedMap() {
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "a");
		map.put(2, "b");
		map.put(3, "c");
		map.put(4, "d");
		map.put(5, "e");
		map.put(6, "f");
		map.put(7, "g");
		map.put(8, "h");
		map.put(9, "i");
		map.put(10, "j");
		map.put(11, "k");
		map.put(12, "l");
		map.put(13, "m");
		map.put(14, "n");
		map.put(15, "o");
		map.put(16, "p");
		map.put(17, "q");
		map.put(18, "r");
		map.put(19, "s");
		map.put(20, "t");
		map.put(21, "u");
		map.put(22, "v");
		map.put(23, "w");
		map.put(24, "x");
		map.put(25, "y");
		map.put(26, "z");
		return map;
	}

}
