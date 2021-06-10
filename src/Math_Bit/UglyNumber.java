package Math_Bit;

import java.util.Arrays;

public class UglyNumber {

    public static void main(String[] args) {
        getNUglynumber(10);
        System.out.println(isUgly(13));
        dynammicprogramming(10);
    }

    public static void getNUglynumber(int n) {
        int i = 1;
        int count = 0;
        while (count < n) {
            if (isUgly(i)) {
                System.out.println(i);
                count++;
            }
            i++;
        }
        System.out.println("The " + count + " ugly number is: " + (i - 1));
    }

    public static boolean isUgly(int number) {

        while (number % 2 == 0) {
            number = number / 2;
        }
        while (number % 3 == 0) {
            number = number / 3;
        }
        while (number % 5 == 0) {
            number = number / 5;
        }
        return number == 1;

    }

    public static void dynammicprogramming(int number) {
        int[] index = new int[] { 0, 0, 0 };
        int[] uglyarray = new int[number];
        int multiplier_two = 2;
        int multiplier_three = 3;
        int multiplier_five = 5;
        uglyarray[0] = 1;
        for (int i = 1; i < number; i++) {

            int min = Math.min(multiplier_two, Math.min(multiplier_three, multiplier_five));
            uglyarray[i] = min;
            if (min == multiplier_two) {
                index[0]++; // this is index is corresponds to current ith index (wrost case senario)
                multiplier_two = uglyarray[index[0]] * 2; // multiply with current filled array with 2

            }
            if (min == multiplier_three) {
                index[1]++;
                multiplier_three = uglyarray[index[1]] * 3;

            }
            if (min == multiplier_five) {
                index[2]++;
                multiplier_five = uglyarray[index[2]] * 5;
            }
        }
        System.out.println(Arrays.toString(uglyarray));
        System.out.println("the " + number + " ugly number are: " + uglyarray[number - 1]);
    }
}
