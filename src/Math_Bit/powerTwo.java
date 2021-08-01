package Math_Bit;

import java.math.BigInteger;

/*
https://www.interviewbit.com/problems/power-of-2/

Find if Given number is power of 2 or not. More specifically, find if given number can be expressed as 2^k where k >= 1.
Input:
number length can be more than 64, which mean number can be greater than 2 ^ 64 (out of long long range)
Output:
return 1 if the number is a power of 2 else return 0
Input : 128
Output : 1
*/
public class powerTwo {

    public int power1(String A) {
        if (A.equals("1")) {
            return 0;
        }
        BigInteger b_current = new BigInteger(A);
        BigInteger b2 = new BigInteger("2");
        BigInteger b1 = new BigInteger("1");
        while (!b_current.equals(b1)) {
            if ((b_current.remainder(b2)).intValue() == 0) {
                b_current = b_current.divide(b2);
                continue;
            } else {
                return 0;
            }
        }
        return 1;
    }

    public int power2(String A) {
        if (A.equals("1"))
            return 0;
        BigInteger N = new BigInteger(A);
        if (N.bitCount() == 1)
            return 1;
        else
            return 0;
    }

    public int power3(String a) {
        if (a == null)
            return 0;
        char arr[] = a.toCharArray();
        int arrStart = 0;
        int arrEnd = arr.length - 1;

        while (arrStart < arrEnd) {

            if (((int) arr[arrEnd] - 48) % 2 != 0)
                return 0;
            for (int i = arrStart, carryOver = 0; i <= arrEnd; i++) {
                int currElement = (int) arr[i] - 48;
                currElement = 10 * carryOver + currElement;

                if (currElement < 2) {
                    arr[i] = 48;
                    carryOver = currElement;
                } else {
                    arr[i] = (char) (48 + currElement / 2);
                    carryOver = currElement % 2;
                }
            }
            if (arr[arrStart] == 48)
                arrStart++;
        }
        if (((int) arr[arrEnd] - 48) % 2 == 0)
            return 1;
        return 0;
    }

    // https://www.interviewbit.com/old/problems/power-of-two-integers/
    /*
     * Given a positive integer which fits in a 32 bit signed integer, find if it
     * can be expressed as A^P where P > 1 and A > 0. A and P both should be
     * integers.
     * 
     * Input : 4 Output : True as 2^2 = 4.
     */
    public boolean isPower(int a) {
        if (a == 1)
            return true;
        for (int i = 2; i * i <= a; i++) {
            int p = a;
            while (p % i == 0) {
                p /= i;
            }
            if (p == 1)
                return true;
        }
        return false;
    }

    public int isPower2(int A) {
        if (A == 1)
            return 1;
        double logA = Math.log(A);
        double size = Math.sqrt(A);
        for (int a = 1; a <= size; a++) {
            for (int p = 2; p <= size; p++) {
                double power = Math.pow(a, p);
                if (power == A) {
                    return 1;
                }

                else if (power > A)
                    break;
            }
        }

        return 0;
    }

    public boolean isPower3(int a) {
        if (a == 1)
            return true;

        for (int i = 2; i * i <= a; i++) {
            int x = a;

            while (x % i == 0) {
                x /= i;
            }
            if (x == 1) {
                return true;
            }
        }
        return false;
    }
}
