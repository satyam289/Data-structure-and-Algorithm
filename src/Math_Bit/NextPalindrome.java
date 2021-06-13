package Math_Bit;

import java.math.BigInteger;

//https://www.geeksforgeeks.org/given-a-number-find-next-smallest-palindrome-larger-than-this-number/
//https://www.interviewbit.com/problems/next-smallest-palindrome/
public class NextPalindrome {

    public String solve(String A) {
        int n = A.length();
        if (n == 1) { // Single Digits Case
            int ch = Integer.parseInt(A.charAt(0) + "");
            if (ch < 9) {
                return (ch + 1) + "";
            }
            return "11";
        }

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = A.charAt(i) - '0'; // converting to int array
        }
        int mid = n / 2;
        int start = mid - 1;
        int end = (n % 2 == 0) ? mid : mid + 1; // Even length is mid, odd case is mid +1

        while (start >= 0 && arr[start] == arr[end]) { // Find first different Digit between left and right
            start--;
            end++;
        }
        boolean isLeftSmaller = false;
        if (start < 0 || arr[start] < arr[end]) {
            isLeftSmaller = true;
        }
        while (start >= 0) {
            arr[end++] = arr[start--]; // copy all the left digits to right
        }
        int carry = 0;
        if (isLeftSmaller) {
            carry = 1;
            if (n % 2 == 1) { // odd case, update mid element only
                arr[mid] += 1;
                carry = arr[mid] / 10;
                arr[mid] %= 10;
            }
            // reset to start and end ptr
            start = mid - 1;
            end = (n % 2 == 0) ? mid : mid + 1;
            // propagate carry to both end till carry > 0
            while (start >= 0 && carry > 0) {
                arr[start] = arr[start] + 1;
                carry = arr[start] / 10;
                arr[start] %= 10;
                arr[end] = arr[start]; // copy the same to right
                start--;
                end++;
            }
        }
        String res = "";
        for (int i = 0; i < n; i++) {
            res += (arr[i] + "");

        }
        if (carry > 0) { // Case when all 9, appending 1 either end. For Ex: 999 -> 1001
            res = "1" + res.substring(0, n - 1) + "1";
        }
        return res;
    }

    public String solve2(String num) {
        int len = num.length();
        String left = num.substring(0, len / 2);
        String middle = num.substring(len / 2, len - len / 2); // empty in case of even or one character in case odd
                                                               // length
        String right = num.substring(len - len / 2);

        if (right.compareTo(reverse(left)) < 0) {
            return left + middle + reverse(left);
        }

        String next = new BigInteger(left + middle).add(BigInteger.ONE).toString();
        return next.substring(0, left.length() + middle.length()) + reverse(next).substring(middle.length());
    }

    private static String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }
}
