package Array;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
https://www.hackerearth.com/practice/algorithms/dynamic-programming/introduction-to-dynamic-programming-1/practice-problems/algorithm/odd-even-subarrays-72ad69db/
 */

public class EvenOddSubArrayNumber {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] arr = new int[n];
        Map<BigInteger, BigInteger> m = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int input = s.nextInt();
            if (input % 2 != 0) {
                arr[i] = 1;
            }
        }
        m.put(BigInteger.ZERO, BigInteger.ONE);
        BigInteger even = BigInteger.ZERO;
        BigInteger odd = BigInteger.ZERO;
        BigInteger count = BigInteger.ZERO;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 1)
                odd = odd.add(BigInteger.ONE);
            else
                even = even.add(BigInteger.ONE);
            BigInteger diff = odd.subtract(even);
            if (m.get(diff) != null) {
                BigInteger value = m.get(diff);
                count = count.add(value);
                m.put(diff, value.add(BigInteger.ONE));
            } else {
                m.put(diff, BigInteger.ONE);
            }
        }
        s.close();
        System.out.println("" + count);
    }
}

