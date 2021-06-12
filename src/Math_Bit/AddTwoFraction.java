package Math_Bit;

class AddTwoFraction {

    // Function to add two fractions
    public static void addFraction(int num1, int den1, int num2, int den2) {
        int gcd = getGcd(den1, den2);
        // finding LCM of den1 and den2 (LCM * GCD = a * b)
        int newDen = (den1 * den2) / gcd;
        int newNum = (num1) * (newDen / den1) + (num2) * (newDen / den2);
        simply(newDen, newNum);
    }

    // Greatest Common Factor
    private static int getGcd(int a, int b) {
        if (a == 0)
            return b;
        return getGcd(b % a, a);
    }

    // https://www.interviewbit.com/problems/greatest-common-divisor/
    private static int gcd2(int A, int B) {
        if (B == 0) { // base case
            return A;
        }
        if (A == 0) { // base case
            return B;
        }
        if (B > A) {
            return gcd2(B, A);
        } else {
            return gcd2(A % B, B);
        }
    }

    private static void simply(int den3, int num3) {
        int common_factor = getGcd(num3, den3);
        den3 = den3 / common_factor;
        num3 = num3 / common_factor;
        System.out.println(num3 + "/" + den3);
    }

    public static void main(String[] args) {
        int num1 = 1, den1 = 500, num2 = 2, den2 = 1500;
        System.out.print(num1 + "/" + den1 + " + " + num2 + "/" + den2 + " is equal to ");
        addFraction(num1, den1, num2, den2);
    }
}
