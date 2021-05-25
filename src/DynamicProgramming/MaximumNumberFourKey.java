package DynamicProgramming;

/*
https://www.geeksforgeeks.org/how-to-print-maximum-number-of-a-using-given-four-keys/
Key 1 : 'A'
Key 2 : Ctrl + A
Key 3 : Ctrl + C
Key 4 : Ctrl + V

N = 7  
output: 9 (A,A,A, key1, key2, key3, key3) = AAAAAAAAA

Explaination:
   from start from 4, max all recursion (@here key3 mean pasing same function)
   F(4) + key1+key2+F(4) =  2 * F(4) = 8
   F(3) + key1+key2+F(3)+F(3) = 3 * F(3) = 9 (MAX)
   F(2) + key1+key2+F(2)+F(2)+F(2) = 4 * F(2) = 8
   F(1) + key1+key2+F(1)+F(1)+F(1)+F(1) = 5 * F(1) = 5
*/
public class MaximumNumberFourKey {

    public static int getMaxOccurance(int N) {
        if (N <= 0)
            return 0;
        if (N <= 6)
            return N;

        int max = Integer.MIN_VALUE;

        for (int i = N - 3; i >= 1; i--) {
            max = Math.max(max, (N - 1 - i) * getMaxOccurance(i));
        }
        return max;
    }

    public static int getMaxOccuranceDP(int N) {
        if (N <= 6) {
            return N;
        }
        int[] table = new int[N + 1];
        for (int i = 0; i <= 6; i++) {
            table[i] = i;
        }
        for (int i = 7; i <= N; i++) {
            for (int j = i - 3; j >= 0; j--) {
                table[i] = Math.max(table[i], (i - 1 - j) * table[j]);
            }
        }
        return table[N];
    }

    public static void main(String[] args) {
        System.out.println(getMaxOccuranceDP(7));
    }
}
