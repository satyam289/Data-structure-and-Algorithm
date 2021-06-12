package Math_Bit;

// Hamming Distance Problem
//https://www.interviewbit.com/problems/different-bits-sum-pairwise/
class DiffBitSumPair {

    // Time Complexity : 0(n2)
    public int cntBits1(int[] A) {
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                count += getOneCount(A[i], A[j]);
            }
        }
        return count;
    }

    private int getOneCount(int a, int b) {
        int val = a ^ b;
        int count = 0;
        while (val > 0) {
            if (val % 2 != 0) {
                count++;
            }
            val /= 2;
        }
        return count;
    }

    // https://www.interviewbit.com/problems/sum-of-pairwise-hamming-distance/
    // Time Complexity : 0(n)
    public int cntBits2(int[] A) {
        long ans = 0;
        long count0 = 0;
        long count1 = 0;
        for (int i = 0; i <= 31; i++) // 32nd bit (int range)
        {
            count0 = 0;
            count1 = 0;
            for (int j = 0; j < A.length; j++) {
                if ((A[j] & 1 << i) == 0) // comparing ith bit position with 1
                    count0++;
            }
            count0 %= 1000000007;
            count1 = A.length - count0;
            ans += 2 * count0 * count1;
            ans %= 1000000007;
        }
        return (int) ans % 1000000007;
    }
}