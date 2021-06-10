package Math_Bit;

// https://www.geeksforgeeks.org/find-square-root-number-upto-given-precision-using-binary-search/
public class SquareRoot {

    public static float sqrt(int number, int precision) {

        int start = 0, end = number;
        int mid;
        double result = 0.0;

        while (start <= end) {
            mid = (start + end) / 2;

            if (mid * mid == number) {
                result = mid;
                break;
            }
            if (mid * mid < number) {
                start = mid + 1;
                result = mid; // floor of the square root
            } else {
                end = mid - 1;
            }
        }

        double increment = 0.1;
        for (int i = 0; i < precision; i++) {
            while (result * result <= number) {
                result += increment;
            }
            result = result - increment;
            increment = increment / 10;
        }
        return (float) result;
    }

    public static void main(String[] args) {
        System.out.println(sqrt(50, 3));
        System.out.println(sqrt(10, 4));
    }
}
