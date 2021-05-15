package Array;

import java.util.HashSet;

/*
https://leetcode.com/problems/happy-number/
A happy number is a number Starting with any positive integer, replace the number by the sum of the squares of its digits follow up.
or it loops endlessly in a cycle are not happy.

Input: n = 19
Output: true

Explanation:
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1

*/
public class HappyNumber {

    public static boolean isHappy(int n) {

        HashSet<Integer> seen = new HashSet<>();
        while (n != 1) {
            int sum = 0;
            int current = n;

            while (current != 0) {
                int lastDig = current % 10;
                sum += (lastDig * lastDig);
                current = current / 10;
            }

            if (seen.contains(sum)) { // found cycle
                return false;
            }
            seen.add(sum);
            n = sum;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.print(isHappy(19));
    }
}
