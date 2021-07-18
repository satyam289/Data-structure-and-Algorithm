package Math_Bit;

//https://www.interviewbit.com/problems/palindromic-binary-representation/
public class PalindromeBinary {

    /* Will return the nth binary palindrome number */
    public int getNthPalindromeBinary(int n) {

        int count = 0;
        int len = 0;
        while (count < n) {
            len++;
            count += (1 << ((len - 1) / 2)); // add count of elements for that length: formula: 2^(len-1)/2 
        }
        count -= (1 << ((len - 1) / 2)); // jump previous group (end position)
        int offset = n - count - 1; 

        int ans = (1 << (len - 1));  

        ans |= (offset << (len / 2)); // Left Shift offset value by len/2

        int reverseAns = rev(ans >> (len / 2)); // shift right by len/2 then reverse 
        ans |= reverseAns;  // or operation between reversed and original 
        return ans;
    }

    private static int rev(int n) {
        int res = 0;
        while (n != 0) {
            int lb = (n & 1);
            res |= lb;
            res <<= 1;
            n >>= 1;
        }
        res >>= 1;
        return res;
    }
}