package DynamicProgramming;

//https://www.interviewbit.com/problems/min-jumps-array/
//https://www.geeksforgeeks.org/minimum-number-of-jumps-to-reach-end-of-a-given-array/
//https://www.geeksforgeeks.org/minimum-number-jumps-reach-endset-2on-solution/
public class MinimumNoJumpTillEnd {

    // Time Complexity : 0(n^2)
    public static int getMinJump(int[] arr) {

        int n = arr.length;
        int[] noOfJump = new int[n];
        int[] actualJump = new int[n];
        for (int i = 0; i < n; i++) {
            noOfJump[i] = Integer.MAX_VALUE;
        }
        noOfJump[0] = 0;
        actualJump[0] = -1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] + j >= i) {
                    if (noOfJump[j] + 1 < noOfJump[i]) {
                        noOfJump[i] = noOfJump[j] + 1;
                        actualJump[i] = j;
                    }
                }
            }
        }
        System.out.println("The jumps are : ");
        int pos = actualJump[n - 1];
        while (pos != -1) {
            System.out.print(arr[pos] + "-");
            pos = actualJump[pos];
        }
        return noOfJump[n - 1];
    }

    // Time Complexity : 0(n)
    public int jump(int[] A) {
        if (A.length == 1 && A[0] == 0) {
            return 0;
        }
        int jumps = 1;
        int maxReach = A[0];
        int reachTill = A[0];

        for (int i = 1; i < A.length; i++) {
            if (maxReach < i) {
                return -1;
            }
            if (reachTill < i) {
                jumps++;
                reachTill = maxReach;
            }
            maxReach = Math.max(maxReach, i + A[i]);
        }
        return jumps;
    }

    public static void main(String[] args) {
        int[] arr = { 2, 3, 1, 1, 2, 4, 2, 0, 1, 1 };
        System.out.println(getMinJump(arr));
    }
}
