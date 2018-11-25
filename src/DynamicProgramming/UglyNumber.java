package DynamicProgramming;

public class UglyNumber {

    public static void main(String[] args) {
        getNUglynumber(10);
        System.out.println(isUgly(13));
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
        if (number == 1) {
            return true;
        } else {
            return false;
        }

    }

}
