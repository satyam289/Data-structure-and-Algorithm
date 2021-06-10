package Math_Bit;

public class DivideIntegerBit {

    public static int divideBitOperation(int A, int B) {
        int sign = ((A < 0) ^ (B < 0)) ? -1 : 1;
        long m = Math.abs((long) A);
        long n = Math.abs((long) B);
        long t = 0;
        long q = 0;
        for (long i = 31; i >= 0; i--) {
            if ((t + (n << i)) <= m) {
                t += (n << i);
                q = q | (1L << i);
            }
        }
        if (sign < 0)
            q = -q;
        if (q >= Integer.MAX_VALUE || q < Integer.MIN_VALUE)
            return Integer.MAX_VALUE;
        return (int) q;
    }

    public static void main(String[] args) {
        System.out.println(divideBitOperation(10, 5));
    }
}