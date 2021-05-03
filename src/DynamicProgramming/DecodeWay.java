package DynamicProgramming;

/**
 * A message containing letters from A-Z is being encoded to numbers using the
 * following mapping: 'A' -> 1 'B' -> 2 ... 'Z' -> 26 Given an encoded message
 * containing digits, determine the total number of ways to decode it. For
 * example, Given encoded message "12", it could be decoded as "AB" (1 2) or "L"
 * (12). The number of ways decoding "12" is 2.
 * It may be assumed that the input contains valid digits from 0 to 9 and 
 * there are no leading 0’s, no extra trailing 0’s, and no two or more consecutive 0’s
 *
 */

// https://www.interviewbit.com/problems/ways-to-decode/
// https://www.geeksforgeeks.org/count-possible-decodings-given-digit-sequence/
public class DecodeWay {

    public static void main(String[] args) {
        String input = "131";
        System.out.println(getNoofWays(input));
        System.out.println(countDecodingDp(input.toCharArray(), input.length()));
    }

    public static int getNoofWays(String str) {
        int n = str.length();
        if (n == 0 || (n == 1 && str.charAt(0) == '0')) { 
            return 0;
        }
        return countDecoding(str.toCharArray(), n);
    }

    private static int countDecoding(char[] digits, int n) {

        if (n == 0 || n == 1) // base (both case 0-9 , 10-26)
            return 1;

        if (digits[0] == '0') {  // leading zero 
            return 0;
        }
        int count = 0;
        if (digits[n - 1] > '0') // only contribute 1-9 number only
            count = countDecoding(digits, n - 1);

        if (digits[n - 1] <= '6' && (digits[n - 2] == '1' || digits[n - 2] == '2')) {  // only contribute 10-26 number only
            count += countDecoding(digits, n - 2);
        }
        return count;
    }

    public static int countDecodingDp(char[] digits, int n) {
        int count[] = new int[n + 1];
        count[0] = 1; //no character (base case)
        count[1] = 1; //one character
        if (digits[0] == '0')
            return 0;

        for (int i = 2; i <= n; i++) {
            count[i] = 0;

            if (digits[i - 1] > '0') {
                count[i] = count[i - 1];
            }

            if (digits[i - 2] == '1' || (digits[i - 2] == '2' && digits[i - 1] <= '6'))
                count[i] += count[i - 2];
        }
        return count[n];
    }
}
