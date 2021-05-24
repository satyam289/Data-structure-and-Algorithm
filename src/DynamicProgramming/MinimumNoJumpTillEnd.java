package DynamicProgramming;

//https://www.geeksforgeeks.org/minimum-number-of-jumps-to-reach-end-of-a-given-array/
//https://www.geeksforgeeks.org/minimum-number-jumps-reach-endset-2on-solution/
public class MinimumNoJumpTillEnd {

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

    public static void main(String[] args) {
        int[] arr = { 2, 3, 1, 1, 2, 4, 2, 0, 1, 1 };
        System.out.println(getMinJump(arr));
    }
}
