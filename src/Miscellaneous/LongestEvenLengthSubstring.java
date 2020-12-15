package Miscellaneous;

public class LongestEvenLengthSubstring {

    // Time: 0(n3)
    public static int findLength(String str)
    {
        int len = str.length();
        int maxlen = 0;
        for (int i = 0; i < len; i++)
        {
            for (int j = i + 1; j < len; j += 2)
            {
                int lengthofSubString = j - i + 1;
                int leftsum = 0, rightsum = 0;
                for (int k = 0; k < lengthofSubString/2; k++)
                {
                    leftsum += (str.charAt(i + k) - '0');
                    rightsum += (str.charAt(i + k + lengthofSubString/2) - '0');
                }
                if (leftsum == rightsum && maxlen < lengthofSubString)
                    maxlen = lengthofSubString;
            }
        }
        return maxlen;
    }

    //Time : 0(n2) space : 0(n2)
    public static int findLengthDynamic(String str)
    {
        int n = str.length();
        int maxlen = 0;

        int sum[][] = new int[n][n];

        // Fill the diagonal values for
        // substrings of length 1
        for (int i = 0; i < n; i++)
            sum[i][i] = str.charAt(i) - '0';

        // Fill entries for substrings of length 2 to n
        for (int len = 2; len <= n; len++)
        {
            for (int i = 0; i < n - len + 1; i++)
            {
                int j = i + len - 1;
                int k = len/2;

                sum[i][j] = sum[i][j-k] + sum[j-k+1][j];

                // Update result if 'len' is even, left and right sums are same and len is more than maxlen
                if (len % 2 == 0 && sum[i][j-k] == sum[(j-k+1)][j] && len > maxlen)
                    maxlen = len;
            }
        }
        return maxlen;
    }

    //Time : 0(n2) space : 0(1)
    public static int findLengthBest(String str, int n) {
        int ans = 0;

        // Consider all possible midpoints one by one
        for (int i = 0; i <= n - 2; i++) {
            /* For current midpoint 'i', keep expanding substring on
           both sides, if sum of both sides becomes equal update ans */
            int l = i, r = i + 1;
            int lsum = 0, rsum = 0;

            /* move on both sides till indexes go out of bounds */
            while (r < n && l >= 0) {
                lsum += str.charAt(l) - '0';
                rsum += str.charAt(r) - '0';
                if (lsum == rsum) {
                    ans = Math.max(ans, r - l + 1);
                }
                l--;
                r++;
            }
        }
        return ans;
    }



    public static void main(String[] args)
    {
        String str = "1538023";
        System.out.println("Length of the substring by using brute force is  " + findLength(str));
        System.out.println("Length of the substring by using dynamic programming is " + findLengthDynamic(str));
        System.out.println("Length of the substring by using Best is " + findLengthBest(str, str.length()));
    }

}
