package Math_Bit;

//https://www.interviewbit.com/problems/implement-power-function/
public class PowerFnc {

    // Brute Force 0(N)
    public int pow(int x, int n, int d) {
        if (x == 0 || d == 1) {
            return 0;
        }
        if (x == 1 || n == 0) {
            return 1;
        }
        int sum = 1;
        for (int i = 0; i < n; i++) {
            sum = (sum * (x % d)) % d;
        }
        if (sum < 0) {
            return d + sum;
        }
        return sum;
    }

    // Optimised 0(Log N)
    public int pow2(int x, int n, int d) {
        if (x == 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        if (x < 0) {
            return pow2(x + d, n, d);
        }
        long temp = pow2(x, n / 2, d);
        long ans = ((temp % d) * (temp % d)) % d;
        if (n % 2 != 0) {
            ans = (ans * (x % d)) % d;
        }
        return (int) ans;
    }
}
