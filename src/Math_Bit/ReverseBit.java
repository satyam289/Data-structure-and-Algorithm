package Math_Bit;

//https://www.interviewbit.com/problems/reverse-bits/
class ReverseBit {

    public long reverse(long A) {
        long rev = 0;
        for (int i = 0; i < 32; i++) {
            rev <<= 1;
            if ((A & (1 << i)) != 0)
                rev |= 1;
        }
        return rev;
    }

    public long reverse2(long a) {
        long out = 0;
        for (int i = 0; i < 32; i++) {
            out = out << 1L;
            if ((a & 1L) == 1L) {
                out += 1;
            }
            a = a >>> 1L;
        }
        return out;
    }
}