package Math_Bit;

//https://www.interviewbit.com/problems/count-total-set-bits/
public class CountSetBit {
    //Count set bit of number
    // Time Complexity : 0(logn) , Space Complexity: 0(1)
    public int countSetBitNum1(int n) {
        int count = 0;
        while (n < 0) {
            count += (n & 1);
            n >>= 1;
        }
        return count;
    }

    // Brain Kernighan Algorithm
    public int countSetBitNum2(int n) {
        int count = 0;
        while (n > 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }
   

    //Count all set bit from 1 to N
    public int countSetBitNth(int n) {
        if (n == 0) {
            return 0;
        }
        int x = largeestPowerOf2(n);
        int count_N_minus_one = x * (1 << (x - 1));
        int mostSignificantBitCount = n - (1 << x) + 1;
        int remaining = n - (1 << x);
        return count_N_minus_one + mostSignificantBitCount + countSetBitNth(remaining);
    }

    private int largeestPowerOf2(int n) {
        int x = 0;
        while ((1 << x) <= n) {
            x++;
        }
        return x - 1;
    }

}
