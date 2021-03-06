package DynamicProgramming;

import java.math.*;

class BattleCompute { // goldman sac interview using

    public static void main(String[] argss) {
        BigInteger b1 = BigInteger.ONE;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int noofparticipant = 3;
        int[] participantLuckyNumber = { 46, 46, 61 };
        int i;
        for (i = 0; i < noofparticipant; i++) {

            int luckynumber = participantLuckyNumber[i];

            for (int j = 2; j <= luckynumber; j++) {

                b1 = b1.multiply(new BigInteger(j + ""));
                // System.out.println(new BigInteger(j+""));
            }
            int x = 0, y = 0;
            String s1 = b1.toString();

            for (int k = 0; k < s1.length(); k++) {
                int digits = Integer.parseInt(s1.charAt(k) + "");
                if (digits == 0)
                    y++;
                x += digits;
            }

            int result = x - y;
            if (result > max) {
                max = result;
            }
            if (result < min) {
                min = result;
            }

        }
        // System.out.println(maxnumber+" "+max);
        // System.out.println(minnumber+" "+min);
        int[] arr = { 1, 3, 10, 2, 3, 5, 100, 20, 5, 50 };
        for (int i1 = 0; i1 < arr.length; i1 += 2) {
            System.out.println(arr[i1] + arr[i1 + 1]);
        }

    }
}