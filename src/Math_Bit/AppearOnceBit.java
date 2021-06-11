package Math_Bit;

//https://www.interviewbit.com/problems/single-number-ii/
//https://www.geeksforgeeks.org/find-the-element-that-appears-once/
public class AppearOnceBit {

    /*
     * Apporach 1 : 0(n^2) check each number over given array Apporach 2 : 0(nlogn)
     * Sort and then chek the number appear only one time Apporach 3 : 0(n) Using
     * HashMap with Space Complexity 0(N)
     */
    // Takes Every Ith bit by Mod 3 (as appear trice)
    // Time Cmplexity : 0(n) and Space Complexity : 0(32) ~ 0(1)
    public static int findOnce1(int[] arr) {
        int num = 0;
        int count = 0;
        for (int i = 0; i < 32; ++i) { // int range
            count = 0; // count the bit
            for (int a : arr) { // all ements for ith index
                if ((a & 1 << i) != 0) { // check if ith bit is 1, then inrement the count
                    count++;
                }
            }
            if (count % 3 != 0) { // if total ith count not mulpiple of 3
                num += 1 << i; // add bit at i th index
            }
        }
        return num;
    }

    // Best (More Space Optimised) Space: 0(1)
    public static int findOnce2(int[] arr) {
        int three_0 = Integer.MAX_VALUE, three_1 = 0, three_2 = 0; // represent (3^n, 3^n+1, 3^n+2)

        for (int i = 0; i < arr.length; i++) {
            int c_three_0 = arr[i] & three_0;
            int c_thee_1 = arr[i] & three_1;
            int c_three_2 = arr[i] & three_2;

            three_0 &= (~c_three_0);
            three_1 |= c_three_0;

            three_1 &= (~c_thee_1);
            three_2 |= c_thee_1;

            three_2 &= (~c_three_2);
            three_0 |= c_three_2;
        }
        return three_1;
    }

    public static void main(String[] args) {
        int[] input = { 3, 3, 3, 7 };
        System.out.println(findOnce1(input));
        System.out.println(findOnce2(input));
    }
}
